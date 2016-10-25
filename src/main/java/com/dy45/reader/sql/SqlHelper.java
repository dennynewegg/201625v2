package com.dy45.reader.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ExceptionUtil;
import com.dy45.reader.Util.HandlerUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.LogUtil;
import com.dy45.reader.Util.MathUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.OnFunListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2015/5/10.
 */
public class SqlHelper {

//    sqlite> CREATE TABLE testtable (first_col integer,second_col integer);
//    --创建最简单的索引，该索引基于某个表的一个字段。
//    sqlite> CREATE INDEX testtable_idx ON testtable(first_col);
//    --创建联合索引，该索引基于某个表的多个字段，同时可以指定每个字段的排序规则(升序/降序)。
//    sqlite> CREATE INDEX testtable_idx2 ON testtable(first_col ASC,second_col DESC);
//    --创建唯一性索引，该索引规则和数据表的唯一性约束的规则相同，即NULL和任何值都不同，包括NULL本身。
//    sqlite> CREATE UNIQUE INDEX testtable_idx3 ON testtable(second_col DESC);
//    sqlite> .indices testtable
//    testtable_idx
//            testtable_idx2
//    testtable_idx3

    static final Pattern sqlVariablePtn = Pattern.compile("@\\w+");

    static {
        dbFilePath = "Sqlite"+ File.separator+"svc.db3";
        String dbPath = FileUtil.getPath(SqlHelper.dbFilePath);
        File file = new File(dbPath);
        FileUtil.mkDirParent(file.getParentFile());
        dbFilePath = file.getPath();
        SQLiteDatabase database = getDatabase();
        database.close();

    }

    public static String dbFilePath ;


    public static void  executeSql(String sqlText) {
        executeSql(sqlText, aBoolean -> { });
    }

    public static void executeSql(final String sqlText,OnActionListener1<Boolean> listener){
        HandlerUtil.sync(new OnFunListener<Boolean>() {
            @Override
            public Boolean fun() {
                try {
                    SQLiteDatabase db = getDatabase();
                    db.execSQL(sqlText.toLowerCase());
                    db.close();
                    return new Boolean(true);
                } catch (Exception ex) {
                    LogUtil.trace(" " + sqlText);
                    ExceptionUtil.handle(ex);
                    return new Boolean(false);
                }
            }
        },listener);
    }

    public static  void executeSql(String sql,Object param){
        List<Object> list = new ArrayList<>(1);
        if(param!=null) {
            list.add(param);
        }
        executeSql(sql,list);
    }

    public static void  executeSql(String sql,List<?> list){
        String sqlText = buildSql(sql,list);
        executeSql(sqlText);
    }

    public static <T> T get(String sqlText, Class<T> clazz){
        List<T> results = getList(sqlText, clazz);
        if(results!=null
                && results.size()>0){
            return results.get(0);
        }
        return null;
    }

    private static <T> List<MethodInfo> getMethods(Cursor cursor, Class<T> clazz){
        List<MethodInfo> methodInfos = new ArrayList<>(20);
        int columnCount = cursor.getColumnCount();
        if(columnCount>0) {
            String[] columnNames = cursor.getColumnNames();
            List<String> columnNameList = new ArrayList<>();
            for(String name :columnNames){
                columnNameList.add(name);
            }
            methodInfos.addAll(getMethods(columnNameList,clazz,"set"));
            for(MethodInfo methodInfo:methodInfos){
                methodInfo.setColumnIndex(cursor.getColumnIndex(methodInfo.matchName));
            }
        }
        return methodInfos;
    }

    private static <T> List<MethodInfo> getMethods(List<String> methodNames
            ,Class<T> clazz
            ,String methodPrefix){
        boolean isGet = methodPrefix.equalsIgnoreCase("get");
        boolean isSet = methodPrefix.equalsIgnoreCase("set");

        List<MethodInfo> methodInfos = new ArrayList<>(10);
        Method[] methods = clazz.getMethods();
        for(String methodName:methodNames){
            String  matchName=methodPrefix+methodName.replace("@","");

            for(Method method:methods){
                if(matchName.compareToIgnoreCase(method.getName())==0) {
                    if(isSet){
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if(parameterTypes!=null
                           && parameterTypes.length==1){
                            Class<?> firstParamType = parameterTypes[0];
                            methodInfos.add(new MethodInfo(method,methodName,firstParamType,null));
                            break;
                        }
                    }
                    else if(isGet){
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if(parameterTypes ==null
                                || parameterTypes.length==0){
                            Class<?> returnType = method.getReturnType();
                            if(returnType.getName()!=void.class.getName()){
                                methodInfos.add(new MethodInfo(method,methodName,null,returnType));
                                break;
                            }
                        }
                    }
                    else {
                        methodInfos.add(new MethodInfo(method,methodName,null,null));
                    }
                }
            }
        }
        return methodInfos;
    }

    public static <T> List<T> getList(String sqlText, Class<T> clazz){
        SQLiteDatabase db = getDatabase();
        sqlText = sqlText.toLowerCase();

        Cursor cursor =  db.rawQuery(sqlText, null);
        List<T> results = new ArrayList<>(100);
        if(cursor!=null) {
            List<MethodInfo> methods = getMethods(cursor,clazz);
            if(methods!=null
                    && methods.size()>0) {
                while (cursor.moveToNext()) {
                    try{
                        T obj = clazz.newInstance();
                        for(MethodInfo method:methods){
                            method.setValue(obj,cursor);
                        }
                        results.add(obj);
                    }
                    catch (Exception ex){
                        LogUtil.trace(sqlText);
                    }
                }
            }
            cursor.close();
        }
        db.close();
        return results;
    }

    public static SQLiteDatabase getDatabase(){
        File file = new File(dbFilePath);
        return SQLiteDatabase.openOrCreateDatabase(file,null);
    }

    static class MethodInfo{
        public Method method;
        public int paramType;
        public int columnIndex;
        public String matchName;
        public int returnType;

        MethodInfo(Method method, String matchName,Class<?> paramType,Class<?> returnType) {
            this.method = method;
            this.matchName = matchName;
            if(paramType!=null) {
                this.paramType = getParamType(paramType);
            }
            if(returnType!=null){
                this.returnType = getParamType(returnType);
            }
        }

        public void setColumnIndex(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        private int getParamType(Class<?> paramType){
            int type = 0;
            if(paramType.getName() == int.class.getName()){
                type=1;
            }else if(paramType.getName()==double.class.getName()){
                type=2;
            }else if(paramType.getName()==Date.class.getName()){
                type=3;
            }
            return type;
        }


        public void setValue(Object obj,Cursor cursor){
            Object value = null;
            if(paramType == 1){
                value = cursor.getInt(columnIndex);
            }
            else if(paramType == 2){
                value = cursor.getDouble(columnIndex);
            }
            else{
                value = cursor.getString(columnIndex);
            }

            if(paramType == 3
               && value!=null){
                value = DateUtil.parseDate(value.toString(),"yyyy-MM-dd",DateUtil.MinDate);
            }

            try {
                method.invoke(obj, value);
            }
            catch(Exception ex)
            {

            }
        }

        public String getSqlParam(Object param) {
            Object obj = null;
            try{
                obj = method.invoke(param);
            }
            catch (Exception ex){

            }
            String sql = "";
            if(obj!=null){
                if(obj.getClass()==Date.class){
                    obj = DateUtil.toDayString((Date)obj);
                }else if(obj.getClass() == double.class){
                    obj = MathUtil.toString((double)obj);
                }

                sql = obj.toString().trim();

                if(returnType == 0
                        || returnType == 3){
                    sql = "'"+sql.replace("'","").replace("\"","")+"'";
                }
            }
            else {
                if(returnType==0
                   || returnType == 3){
                    sql="''";
                }
                else {
                    sql="null";
                }
            }
            return sql;
        }
    }

    private static String buildSql(String sqlText ,List<?> params){
        sqlText = sqlText.toLowerCase();
        if(params!=null
            && params.size()>0) {
            List<String> sqlVarList = getsqlVariables(sqlText);
            Object param = params.get(0);
            if (sqlVarList != null
                    && sqlVarList.size() > 0) {
                List<MethodInfo> methodInfos = getMethods(sqlVarList, param.getClass(), "get");
                if(methodInfos.size()>0) {
                    StringBuffer sqlBuffer = new StringBuffer(sqlText.length() * params.size() + 30);
                    for (Object obj : params) {
                        sqlBuffer.append(buildSql(sqlText, obj, methodInfos));
                    }
                    sqlText = sqlBuffer.toString();
                }
            }
        }
        return  sqlText;
    }

    private static String buildSql(String sqlText,Object param,List<MethodInfo> methodInfos){
        if(!sqlText.isEmpty()
            && methodInfos!=null)
        {
            for (MethodInfo methodInfo : methodInfos) {
                sqlText = sqlText.replace(methodInfo.matchName, methodInfo.getSqlParam(param));
            }
        }
        return sqlText;
    }

    private static List<String> getsqlVariables(String sqlText){
        Matcher matcher = sqlVariablePtn.matcher(sqlText);
        List<String> varList = new ArrayList<>();
        while (matcher.find()){
            String varSql = matcher.group(0);
            if(!varList.contains(varSql)) {
                varList.add(varSql);
            }
        }
        return varList;
    }
}

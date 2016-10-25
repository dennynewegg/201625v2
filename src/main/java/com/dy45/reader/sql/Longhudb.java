package com.dy45.reader.sql;

import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.LonghuDTO;
import com.dy45.reader.entity.LonghuSummaryDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 5/12/2015.
 */
public class Longhudb {
   static{
       createTable();
   }
    private static void createTable(){
        String sqlText = "create table IF NOT EXISTS longhu(rowid integer primary key autoincrement" +
                ",code char(8)" +
                ",name text" +
                ",date char(10)" +
                ",comName text" +
                ",buyamount DOUBLE" +
                ",sellamount DOUBLE" +
                ",avgcost double" +
                ");";
        SqlHelper.executeSql(sqlText);

        sqlText = "CREATE INDEX IF NOT EXISTS IX_longhu_Code ON longhu(code);";
        SqlHelper.executeSql(sqlText);

        sqlText = "CREATE INDEX IF NOT EXISTS IX_longhu_Code_date ON longhu(code asc,date asc);";
        SqlHelper.executeSql(sqlText);

        sqlText ="CREATE INDEX IF NOT EXISTS IX_longhu_date ON longhu(date asc);";
        SqlHelper.executeSql(sqlText);

    }

    public static void insertLonghuList(List<LonghuDTO> list){
        String sqlText = "insert into longhu(code,name,date,comname,buyamount,sellamount,avgcost)" +
                " values(@code,@name,@date,@comname,@buyamount,@sellamount,@avgcost);";
        SqlHelper.executeSql(sqlText,list);
    }

    public static Date getMaxLonghuDate(){
        String sqlText = "select max(date) as date\n" +
                "from longhu";
        List<LonghuDTO> list = SqlHelper.getList(sqlText,LonghuDTO.class);
        if(list!=null
            &&list.size()==1
            && list.get(0).getDate()!=null
            && list.get(0).getDate().compareTo(DateUtil.StartDate)>0)
        {
            return list.get(0).getDate();
        }
        return   DateUtil.addDay(new Date(), -50);
    }

    public static List<LonghuSummaryDTO> queryLonghuList(Date from,Date to){
        String sqlText ="SELECT  \n" +
                "    a.code \n" +
                "   ,MAX(a.name) AS name \n" +
                "   ,SUM(a.buyamount) AS buyamount \n" +
                "   ,SUM(a.sellamount) AS sellamount \n" +
                "   ,SUM(a.buyamount-a.sellamount) AS netamount \n" +
                "   ,SUM((a.buyamount-a.sellamount)/a.avgcost) AS netqty \n" +
                "   ,AVG(avgcost) AS avgcost \n" +
                "   ,COUNT(DISTINCT(DATE)) AS buyCount \n" +
                "FROM (\n" +
                "SELECT DISTINCT code,name,buyamount,sellamount,avgcost,date \n" +
                "from longhu AS a  \n" +
                "WHERE \n" +
                "    a.DATE>=@FromDate \n" +
                "    AND a.DATE<=@toDate ) as a\n" +
                "GROUP BY a.code";
        sqlText = sqlText.replace("@FromDate", DateUtil.toSqlString(from));
        sqlText = sqlText.replace("@toDate", DateUtil.toSqlString(to));
        List<LonghuSummaryDTO> list = SqlHelper.getList(sqlText,LonghuSummaryDTO.class);
        return filterdbData(list);
    }

    public static void deleteLonghu(){
        String sqlText = "\n" +
                "delete a\n" +
                "from longhu as a\n" +
                "inner join longhu as b \n" +
                "on a.code=b.code \n" +
                "and a.date=b.date \n" +
                "and a.buyamount = b.buyamount \n" +
                "and a.sellamount = b.sellamount \n" +
                "where a.rowid < b.rowid \n";
        SqlHelper.executeSql(sqlText);
    }


    public static List<LonghuDTO> getLonghuByDate(Date date){
        String sqlText = "select * from longhu\n" +
                "where date=@date;";
        sqlText = sqlText.replace("@date", DateUtil.toSqlString(date));
        List<LonghuDTO> list = SqlHelper.getList(sqlText,LonghuDTO.class);
        return filterdbData(list);
    }

    public static List<LonghuDTO> getLonghuListByCode(String code){
        String sqlText = "select * from longhu " +
                "where code='@code' " +
                "order by date desc " +
                "limit 100";
        sqlText = sqlText.replace("@code", StringUtil.getLongCode(code));
        List<LonghuDTO> list = SqlHelper.getList(sqlText,LonghuDTO.class);
        return filterdbData(list);
    }

    public static void deleteLongHuByID(int id){
        if(id>0) {
            String sqlText = "delete from longhu where rowid=@rowid;";
            sqlText = sqlText.replace("@rowid", String.valueOf(id));
            SqlHelper.executeSql(sqlText);
        }
    }


    private static <T extends LonghuDTO> List<T> filterdbData(List<T> list) {
        if(list!=null
            &&list.size()==1
            && list.get(0).getCode().isEmpty())
        {
            list.clear();
        }
        return  list;
    }
}

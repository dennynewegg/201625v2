package com.dy45.reader.sql;

import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.DayTradeDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2015/5/18.
 */
public class DayTradedb {

    static{
        createTable();
    }
    private static void createTable(){
        String sqlText = "create table if not exists daytrade(rowid INTEGER primary key autoincrement\n" +
                ",code char(8) not null\n" +
                ",name Text not null\n" +
                ",date char(10) not null\n" +
                ",open double not null\n" +
                ",high double not null\n" +
                ",low double not null\n" +
                ",close double not null\n" +
                ",rate double not null\n" +
                ",amount double not null\n" +
                ",volume double not null) ";
        SqlHelper.executeSql(sqlText);

        sqlText = "CREATE INDEX IF NOT EXISTS IX_daytrade_Code ON daytrade(code);";
        SqlHelper.executeSql(sqlText);

        sqlText = "CREATE INDEX IF NOT EXISTS IX_daytrade_Code_date ON daytrade(code asc,date asc);";
        SqlHelper.executeSql(sqlText);

        sqlText =" CREATE INDEX IF NOT EXISTS IX_daytrade_date ON daytrade(date asc);";
        SqlHelper.executeSql(sqlText);
    }

    public static DayTradeDTO getDayTrade(String code,Date date){
        String sqlText = "select * from daytrade " +
                "where code='@code' and date=@date";

        sqlText=sqlText.replace("@code",StringUtil.getLongCode(code));
        sqlText=sqlText.replace("@date", DateUtil.toSqlString(date));
        DayTradeDTO dayTradeDTO =SqlHelper.get(sqlText,DayTradeDTO.class);

        if(dayTradeDTO == null
            ||StringUtil.isEmpty(dayTradeDTO.getCode())){
            return null;
        }
        return dayTradeDTO;
    }

    public static void insertDayTrade(List<DayTradeDTO> list){
        deleteDayTrade(list.get(0).getDate());
        String sql = "insert into daytrade(code,name,date,open,high,low,close,rate,amount,volume)"
                +"values(@code,@name,@date,@open,@high,@low,@close,@rate,@amount,@volume);";
        SqlHelper.executeSql(sql,list);
    }

    public static void deleteDayTrade(Date date){
        String sql = "delete from daytrade where date >= "+DateUtil.toSqlString(date);
        SqlHelper.executeSql(sql);
    }

    public static List<DayTradeDTO> getStockCodeList(){
        String sql = "select distinct code,name from daytrade ;";
        return SqlHelper.getList(sql,DayTradeDTO.class);
    }

}

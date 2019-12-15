package com.bndrs.voice.common;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dcc
 */
public class DateUtil {

    /**
     * 获取当前时间
     * @return
     */
    public  static String getNowTime(){
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        return current;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    /**
     * 将日期类型对象转换成yyyy-MM-dd HH:mm:ss类型字符串 如果传入的日期为null,则返回空值
     *
     * @param date
     *            日期类型对象
     * @return 日期格式字符串 by Johnny.Chen
     */
    public static String formatTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formater.format(date);
    }

    /**
     * 两个日期差-分钟
     * @param dtBegin
     * @param dtEnd
     * @return
     */
    public static long getDateDiffMinutes(Date dtBegin,Date dtEnd){
        long diff = (dtEnd.getTime() - dtBegin.getTime())/1000;
        return diff/60;
    }
}

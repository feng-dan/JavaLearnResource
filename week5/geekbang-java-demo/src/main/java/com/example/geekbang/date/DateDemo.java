package com.example.geekbang.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间戳处理
 *
 * @author fengdan
 * @date 2021年05月25日 11:37
 */
public class DateDemo {
    public static void main(String[] args) throws Exception {
        time();
    }

    /*
    时间戳转换为时间格式
    select FROM_UNIXTIME(`f_created_at`,'%Y-%m-%d %H:%i:%S') as f_created_at from `t_address`;
    select FROM_UNIXTIME(`f_created_at`,'%Y-%m-%d') as time from `t_address`;
    select FROM_UNIXTIME(`f_created_at`,'%H:%i:%S') as f_created_at from `t_address`;
    时间格式转换为时间戳格式
    select UNIX_TIMESTAMP();
    * */
    public static void time() throws Exception {
        // TODO Auto-generated method stub
        //获得系统的时间，单位为毫秒,转换为妙
        long totalMilliSeconds = System.currentTimeMillis();

        DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);//格式化输出
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区 这句加上，很关键。
        dateFormatterChina.setTimeZone(timeZoneChina);//设置系统时区
        long totalSeconds = totalMilliSeconds / 1000;

        //求出现在的秒
        long currentSecond = totalSeconds % 60;

        //求出现在的分
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;

        //求出现在的小时
        long totalHour = totalMinutes / 60;
        long currentHour = totalHour % 24;

        //显示时间
        System.out.println("总毫秒为： " + totalMilliSeconds);
        System.out.println(currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");


        Date nowTime = new Date(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);

        System.out.println(retStrFormatNowDate);
    }

}

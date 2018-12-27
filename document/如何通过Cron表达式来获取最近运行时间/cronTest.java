package com.cloudwalk.ibis.schedule.monitor;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class cronTest {

    public static void main(String[] args) throws Exception {

        cronTest  cron= 	new cronTest();

//cron.getNextExecTime("0 0/1 * * * ? *", 3);

        System.out.println(cron.getNextExecTime("0 0 0 1 1,2 ?", 5)+"\n");
    }

    public static List<String> getNextExecTime(String cronExpression,Integer numTimes) {
        List<String> list = new ArrayList<String>();
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        try {
            cronTriggerImpl.setCronExpression(cronExpression);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        // 这个是重点，一行代码搞定
        List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, numTimes);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Date date : dates) {
            list.add(dateFormat.format(date));
        }
        return list;
    }

}
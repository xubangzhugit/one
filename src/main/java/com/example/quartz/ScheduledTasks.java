package com.example.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by tym on 2018/9/5 0005.
 */
@Component
//@EnableScheduling
public class ScheduledTasks {

    @Scheduled(fixedRateString = "3000", initialDelay = 1000)
    public void reportCurrentTime1() throws InterruptedException{
        System.out.println ("fixedRate: The time is now " + dateFormat ().format (new java.util.Date()));
        Thread.sleep(5000);
    }

    /*@Scheduled(fixedDelay = 1000 * 3, initialDelay = 5000)
    public void reportCurrentTime2() throws InterruptedException{
        System.out.println ("fixedDelay: The time is now " + dateFormat ().format (new java.util.Date()));
        Thread.sleep(2000);
    }*/

    //工作日每5秒执行一次
    /*@Scheduled(cron="0/5 34 15 10 10 MON-FRI")
    public void reportCurrentByCron(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date()));
    }*/

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
}

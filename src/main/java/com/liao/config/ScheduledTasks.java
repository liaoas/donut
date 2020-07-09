package com.liao.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务控制类
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 2000)
    public void tasksTime(){
        System.out.println("现在时间"+times.format(new Date()));
    }

}

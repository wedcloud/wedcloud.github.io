package com.wedcloud.springboot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author 许海斌
 * @create 2020/5/2 0002 14:54
 */
@Component
public class ScheduleController {
    @Scheduled(cron = "0 59 14 * * ?")
    public void say(){
    System.out.println("定时任务执行");
    }
}

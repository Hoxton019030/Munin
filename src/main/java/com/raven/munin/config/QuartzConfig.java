package com.raven.munin.config;


import com.raven.munin.quartz.job.OClockAlert;
import com.raven.munin.quartz.job.PrintNowTimeJob;
import com.raven.munin.quartz.trigger.CronTrigger;
import com.raven.munin.quartz.trigger.SimpleTrigger;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hoxton
 * @version 1.1.0
 */
@Configuration
public class QuartzConfig {

    @Autowired
    CronTrigger cronTrigger;

    @Autowired
    SimpleTrigger simpleTrigger;



    @Bean
    public void printHelloEverySecond() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(PrintNowTimeJob.class)
                .withIdentity("1","1")
                .storeDurably()
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail,simpleTrigger.triggeredEverySecond());
        scheduler.start();
    }

    @Bean
    public void OClockAlert() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(OClockAlert.class)
                .withIdentity("2","1")
                .storeDurably()
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail,cronTrigger.triggeredEveryOClock());
        scheduler.start();
    }

}

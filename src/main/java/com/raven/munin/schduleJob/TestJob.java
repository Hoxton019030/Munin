package com.raven.munin.schduleJob;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * @author Hoxton
 * @version 1.1.0
 */

@Slf4j
public class TestJob implements Job {

    int count = 0;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(LocalDateTime.now().toString());
    }
}

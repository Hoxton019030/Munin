package com.raven.munin.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Hoxton
 * @version 1.1.0
 */
public class OClockAlert implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("整點了，喘一下吧");
    }
}

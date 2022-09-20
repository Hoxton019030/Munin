package com.raven.munin.quartz.trigger;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Hoxton
 * @version 1.1.0
 */
@Component
public class SimpleTrigger {

    public Trigger triggeredEverySecond(){
        return TriggerBuilder.newTrigger()
                .withIdentity("1","1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(1))

                .build();
    }
}

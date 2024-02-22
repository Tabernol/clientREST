package com.example.clientrest.service;

import com.example.clientrest.entity.TimePeriod;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Service;


import java.text.ParseException;

@Service
public class CustomTriggerService {


    public String generateCronExpression(TimePeriod timePeriod) {
        // Logic to generate a cron expression based on timePeriod
        // Format: seconds minutes hours dayOfMonth month dayOfWeek year (optional)
        return String.format("0 %d %d ? * *", timePeriod.getTimeStart().getMinute(), timePeriod.getTimeStart().getHour());
    }

    public Trigger generateTrigger(TimePeriod timePeriod) {
        // Logic to generate a trigger based on timePeriod
        // Set the start time, end time, and frequency as needed
        // Use CronScheduleBuilder for more complex scheduling
        // Example: Run every 10 seconds within the specified time period
        return TriggerBuilder.newTrigger()
//                .startAt(DateBuilder.todayAt(timePeriod.getTimeStart().getHour(), timePeriod.getTimeStart().getMinute(), 0))
//                .endAt(DateBuilder.todayAt(timePeriod.getTimeFinish().getHour(), timePeriod.getTimeFinish().getMinute(), 0))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
    }

    public Trigger generateTrigger() {
        return TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
    }
}

package com.example.clientrest.service;

import com.example.clientrest.entity.TimePeriod;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
public class CustomTriggerService {

    public String generateCronExpression(TimePeriod timePeriod) {
        return String.format("0 %d %d ? * *", timePeriod.getTimeStart().getMinute(), timePeriod.getTimeStart().getHour());
    }

    public Trigger generateTrigger(TimePeriod timePeriod) {
        String cronExpression = generateCronExpression(timePeriod);

        return TriggerBuilder.newTrigger()
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }
}

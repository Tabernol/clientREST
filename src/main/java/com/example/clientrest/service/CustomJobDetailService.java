package com.example.clientrest.service;

import com.example.clientrest.entity.TimePeriod;
import com.example.clientrest.service.job.BroadcastJob;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.stereotype.Service;

@Service
public class CustomJobDetailService {
    public JobDetail generateJobDetail(TimePeriod timePeriod) {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("media", timePeriod.getMediaCollection());


        return JobBuilder.newJob().ofType(BroadcastJob.class)
                .storeDurably()
                .withIdentity("BroadCast_Job_Detail for timePeriod " + timePeriod.getId())
                .usingJobData(jobDataMap)
                .withDescription("BroadCast_Job_Detail for timePeriod " + timePeriod.getId())
                .build();
    }
}

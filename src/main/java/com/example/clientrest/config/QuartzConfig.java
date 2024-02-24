package com.example.clientrest.config;

import com.example.clientrest.service.job.BroadcastJob;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(BroadcastJob.class)
                .storeDurably()
                .withIdentity("BroadCast_Job_Detail")
                .withDescription("Invoke Sample Job service...")
                .build();
    }


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(jobFactory);
        return factoryBean;
    }
}

package com.example.clientrest.config;

import com.example.clientrest.service.BroadcastJob;
import com.example.clientrest.service.CustomTriggerService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("Broadcast_Qrtz_Trigger")
                .withDescription("Broadcast trigger")
                .usingJobData("currentIndex", 0)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobDetail job, Trigger trigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        return factoryBean;
    }


//    @Bean
//    public JobDetailFactoryBean jobDetailFactoryBean() {
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setJobClass(BroadcastJob.class);
//        factoryBean.setDescription("Broadcast JobDetail");
//        factoryBean.setDurability(true);
//        return factoryBean;
//    }
//
//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetail jobDetail) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetail);
//        factoryBean.setRepeatInterval(10000);  // Repeat every 10 seconds
//        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        return factoryBean;
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(JobDetail jobDetail, Trigger trigger) {
//        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//        factoryBean.setJobDetails(jobDetail);
//        factoryBean.setTriggers(trigger);
//        return factoryBean;
//    }
}

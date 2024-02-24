package com.example.clientrest.service.job;

import com.example.clientrest.application.BroadcastController;
import com.example.clientrest.service.job.BroadcastJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BroadcastFactory implements JobFactory {

    private final BroadcastController broadcastController;

    @Autowired
    public BroadcastFactory(BroadcastController broadcastController) {
        this.broadcastController = broadcastController;
    }

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();

        if (jobClass.isAssignableFrom(BroadcastJob.class)) {
            BroadcastJob broadcastJob = new BroadcastJob(broadcastController);
//            jobDetail.getJobDataMap().put
            // set data about content broadcasting
            log.info("Was created new Job " + broadcastJob);
            return broadcastJob;
        }

//        try {
//            // Dynamically create an instance of the job class and inject dependencies
//            Job job = jobClass.getDeclaredConstructor().newInstance();
//
//            if (job instanceof BroadcastJob) {
//                ((BroadcastJob) job).setBroadcastController(broadcastController);
//                // Set other necessary properties if needed
//                log.info("Created a new instance of BroadcastJob: " + job);
//                return job;
//            }
//
//            // Handle other job types if needed
//
//        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//            log.error("Error creating job instance: " + e.getMessage());
//            throw new SchedulerException("Error creating job instance", e);
//        }


        // Handle other job types if needed

        throw new IllegalArgumentException("Unsupported job class: " + jobClass.getName());
    }
}

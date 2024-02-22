package com.example.clientrest.controller;

import com.example.clientrest.entity.Schedule;
import com.example.clientrest.service.BroadcastAble;
import com.example.clientrest.service.BroadcastJob;
import com.example.clientrest.service.BroadcastService;
import com.example.clientrest.service.CustomTriggerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {
    private final Scheduler scheduler; // Injected by Spring in config
    private final JobDetail job; // Injected by Spring in config
    private final Trigger trigger; // Injected by Spring in config
    private final MediaDownloadservice mediaDownloadservice;
    private final BroadcastService broadcastService;
    private final BroadcastJob broadcastJob;

    private final CustomTriggerService customTriggerService;


    @PostMapping("/" + "${monitor.monitorUUIDUsername}") // add to URL unique identifier of each monitor
    public void obtainSchedule(@RequestBody Schedule schedule) throws JobExecutionException {
        log.info("schedule has received");
        log.info(schedule.toString());
        Set<String> uniqueFileName = schedule.getTimePeriodList()
                .stream()
                .map(timePeriod -> timePeriod.getMediaCollection())
                .flatMap(mediaCollections ->
                        mediaCollections.stream().map(mediaCollection ->
                                mediaCollection.getFileName())).collect(Collectors.toSet());

        log.info("Downloading all files...");
        uniqueFileName.forEach(fileName -> mediaDownloadservice.downloadMedia(fileName));
        log.info("Files have saved");

        List<String> pathByPeriod = broadcastService.getPathByPeriod(schedule.getTimePeriodList().get(0));


        // Set data for the job
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("pictureUrls", pathByPeriod);
        jobDataMap.put("currentIndex", 0);

        // Associate the data with the job when scheduling
        job.getJobDataMap().putAll(jobDataMap);

        try {
            log.info("path size" + pathByPeriod.size());
            scheduler.start();
            // Schedule the job with the trigger
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error("Error scheduling job: " + e.getMessage());
        }

//        broadcastAble.applyScheduleForMonitor(schedule);
//        log.info("Schedule was saved----------------------------");
    }
}

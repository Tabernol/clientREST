package com.example.clientrest.controller;

import com.example.clientrest.entity.Schedule;
import com.example.clientrest.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {

    @Autowired
    private Scheduler scheduler;
    private final MediaDownloadservice mediaDownloadservice;
    private final BroadcastService broadcastService;
    private final CustomTriggerService customTriggerService;
    private final CustomJobDetailService customJobDetailService;


    @PostMapping("/monitor/" + "${monitor.monitorUUIDUsername}") // add to URL unique identifier of each monitor
    public void obtainSchedule(@RequestBody Schedule schedule) throws SchedulerException {
        log.info("schedule has received");
        log.info(schedule.toString());
        mediaDownloadservice.downloadAllUniqueFiles(schedule);

//=============================================================================================
        // Unscheduling existing jobs and triggers
        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.anyJobGroup())) {
            scheduler.deleteJob(jobKey);
        }

        List<Trigger> triggers = schedule.getTimePeriodList().stream()
                .map(customTriggerService::generateTrigger)
                .toList();

        List<JobDetail> jobDetails = schedule.getTimePeriodList().stream()
                .map(customJobDetailService::generateJobDetail)
                .toList();

        for (int i = 0; i < triggers.size(); i++) {
            scheduler.scheduleJob(jobDetails.get(i), triggers.get(i)); //TODO change to another logic
        }

//=============================================================================================
    }
}

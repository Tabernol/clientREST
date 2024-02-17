package com.example.clientrest.controller;

import com.example.clientrest.entity.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {

    private final MediaDownloadservice mediaDownloadservice;


    @PostMapping("/obtain") // add to URL unique identifier of each monitor
    public void obtainSchedule(@RequestBody Schedule schedule) {
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

    }
}

package com.example.clientrest.service;

import com.example.clientrest.entity.MediaCollection;
import com.example.clientrest.entity.Schedule;
import com.example.clientrest.entity.TimePeriod;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BroadcastService implements BroadcastAble {

    private final String BASE_PATH = "src/main/resources/content/";

    public List<String> getPathByPeriod(TimePeriod timePeriod) {
        return timePeriod.getMediaCollection().stream()
                .map(mediaCollection -> BASE_PATH + mediaCollection.getFileName())
                .collect(Collectors.toList());
    }
}

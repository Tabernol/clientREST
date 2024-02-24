package com.example.clientrest.service;

import com.example.clientrest.entity.MediaCollection;
import com.example.clientrest.entity.Schedule;
import com.example.clientrest.entity.TimePeriod;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public interface BroadcastAble {

    public void startBroadcast();
//
//    void broadcastContent(List<MediaCollection> content);


//
//    void applyScheduleForMonitor(Schedule schedule);
//
//    void applyTimePeriodBroadcast(TimePeriod timePeriod);
//
//    default long calculateInitialDelay(LocalTime startTime) {
//        LocalTime now = LocalTime.now();
//        if (now.isAfter(startTime)) {
//            System.out.println("PLANING START TIME ON TOMORROW");
//            // If the start time has already passed today, calculate initial delay for the next day
//            startTime = startTime.plusHours(24);
//        }
//        return Duration.between(now, startTime).getSeconds();
//    }
//
//    default long calculatePeriod(LocalTime startTime, LocalTime endTime) {
//        // Calculate the period between broadcasts (in seconds)
//        return Duration.between(startTime, endTime).getSeconds();
//    }
}

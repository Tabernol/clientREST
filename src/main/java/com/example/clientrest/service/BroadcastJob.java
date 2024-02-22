package com.example.clientrest.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
public class BroadcastJob implements StatefulJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Start Job");

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        List<String> pictureUrls = (List<String>) dataMap.get("pictureUrls");
        int currentIndex = dataMap.getInt("currentIndex");

        log.info("Start Job" + " index " + currentIndex);

        if (pictureUrls != null && currentIndex < pictureUrls.size()) {
            String currentPicture = pictureUrls.get(currentIndex);
            log.info("Displaying picture: " + currentPicture);

            // Increment index for the next picture
            currentIndex++;
            log.info("Current index " + currentIndex);

            // Update the currentIndex in the JobDataMap for the next execution
            dataMap.put("currentIndex", currentIndex);
        } else {
            currentIndex = 0;

            // Update the currentIndex in the JobDataMap for the next execution
            dataMap.put("currentIndex", currentIndex);
        }
    }
}

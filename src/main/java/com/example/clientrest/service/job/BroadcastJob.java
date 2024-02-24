package com.example.clientrest.service.job;

import com.example.clientrest.application.BroadcastController;
import com.example.clientrest.entity.MediaCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BroadcastJob implements Job {
    private final BroadcastController broadcastController;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Start execute Job " + this.toString());
        log.info(jobExecutionContext.getJobDetail().getDescription());
        // Call a method on the controller to start the broadcast
        List<MediaCollection> media =
                (List<MediaCollection>) jobExecutionContext.getJobDetail().getJobDataMap().get("media");
        log.info("media " + media.toString());
        broadcastController.startBroadcast();
    }


//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info("Start Job");
//
//        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//
//        List<String> pictureUrls = (List<String>) dataMap.get("pictureUrls");
//        int currentIndex = dataMap.getInt("currentIndex");
//
//        log.info("Start Job" + " index " + currentIndex);
//
//        if (pictureUrls != null && currentIndex < pictureUrls.size()) {
//            String currentPicture = pictureUrls.get(currentIndex);
//            log.info("Displaying picture: " + currentPicture);
//
//            // Increment index for the next picture
//            currentIndex++;
//            log.info("Current index " + currentIndex);
//
//            // Update the currentIndex in the JobDataMap for the next execution
//            dataMap.put("currentIndex", currentIndex);
//        } else {
//            currentIndex = 0;
//
//            // Update the currentIndex in the JobDataMap for the next execution
//            dataMap.put("currentIndex", currentIndex);
//        }
//    }
}

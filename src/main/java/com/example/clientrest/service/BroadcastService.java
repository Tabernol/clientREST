package com.example.clientrest.service;

import com.example.clientrest.entity.TimePeriod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BroadcastService {

    private final String BASE_PATH = "src/main/resources/content/";

    public List<String> getPathByPeriod(TimePeriod timePeriod) {
        return timePeriod.getMediaCollection().stream()
                .map(mediaCollection -> BASE_PATH + mediaCollection.getFileName())
                .collect(Collectors.toList());
    }
}

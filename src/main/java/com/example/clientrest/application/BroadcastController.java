package com.example.clientrest.application;

import com.example.clientrest.service.BroadcastAble;
import com.example.clientrest.service.StopBroadcastAble;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BroadcastController implements BroadcastAble, StopBroadcastAble {
    @Override
    public void startBroadcast() {
        log.info("BroadcastController start broadcast");
    }

    @Override
    public void stopBroadcast() {
        log.info("BroadcastController stop broadcasting");
    }
}

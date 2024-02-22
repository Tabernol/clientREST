package com.example.clientrest.controller;

import com.example.clientrest.dto.MonitorRegistrationRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final RestTemplate restTemplate;
    @Value("${server.authenticationUrl}")
    private String authenticationUrl;
    @Value("${monitor.monitorUUIDUsername}")
    private String monitorUUIDUsername;
    @Value("${monitor.password}")
    private String password;

    @PostConstruct
    public void authenticateOnAppStart() {
        log.info(authenticationUrl);
        MonitorRegistrationRequest request = MonitorRegistrationRequest.builder()
                .monitorUUIDUsername(monitorUUIDUsername)
                .password(password)
                .ipAddress(getIpAddress())
                .build();

//        log.info(request.toString());
//        ResponseEntity<String> response =
//                restTemplate.postForEntity(authenticationUrl, request, String.class);
//
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            log.info("response from server " + response.getBody());
//            log.info("Authentication successful");
//        } else {
//            log.info("response from server " + response.getBody());
//            log.info("Authentication failed");
//        }
    }

    private String getIpAddress() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            return localhost.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Can't obtain IP address. ", e);
        }
    }
}

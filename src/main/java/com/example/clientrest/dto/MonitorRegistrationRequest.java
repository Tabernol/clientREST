package com.example.clientrest.dto;


import lombok.*;
import java.io.Serializable;


@Data
@Builder
@ToString
public class MonitorRegistrationRequest implements Serializable {
    private String monitorUUIDUsername;
    private String password;
    private String ipAddress;
}

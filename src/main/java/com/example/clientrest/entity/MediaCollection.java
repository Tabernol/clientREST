package com.example.clientrest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MediaCollection {
    private Long id;
    private Integer broadcast;
    private String fileName;

    @JsonCreator
    public MediaCollection(@JsonProperty("id") Long id,
                           @JsonProperty("broadcast") Integer broadcast,
                           @JsonProperty("fileName") String fileName) {
        this.id = id;
        this.broadcast = broadcast;
        this.fileName = fileName;
    }
}

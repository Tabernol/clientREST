package com.example.clientrest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@ToString
public class TimePeriod {
    private Long id;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeStart;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeFinish;
    private List<MediaCollection> mediaCollection;

    @JsonCreator
    public TimePeriod(@JsonProperty("id") Long id,
                      @JsonProperty("timeStart") LocalTime timeStart,
                      @JsonProperty("timeFinish") LocalTime timeFinish,
                      @JsonProperty("mediaCollection") List<MediaCollection> mediaCollection) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.mediaCollection = mediaCollection;

    }
}

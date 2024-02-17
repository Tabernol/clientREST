package com.example.clientrest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class Schedule {
    private Long id;
    private String scheduleName;
    private List<TimePeriod> timePeriodList;

    @JsonCreator
    public Schedule(@JsonProperty("id") Long id,
                    @JsonProperty("scheduleName") String scheduleName,
                    @JsonProperty("timePeriodList") List<TimePeriod> timePeriodList) {
        this.id = id;
        this.scheduleName = scheduleName;
        this.timePeriodList = timePeriodList;
    }
}

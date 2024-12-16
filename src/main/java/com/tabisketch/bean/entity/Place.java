package com.tabisketch.bean.entity;

import com.tabisketch.constant.Transportation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private int id;
    private int googlePlaceId;
    private int dayId;
    private int budget;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime desiredStartTime;
    private LocalTime desiredEndTime;
    private String toPolyLine;
    private Integer toTravelTime;
    private Transportation toTransportation;

    public static Place generate(
            final int googlePlaceId,
            final int dayId,
            final int budget,
            final LocalTime startTime,
            final LocalTime endTime,
            final LocalTime desiredStartTime,
            final LocalTime desiredEndTime,
            final String toPolyLine,
            final Integer toTravelTime,
            final Transportation toTransportation
    ) {
        return new Place(
                -1,
                googlePlaceId,
                dayId,
                budget,
                startTime,
                endTime,
                desiredStartTime,
                desiredEndTime,
                toPolyLine,
                toTravelTime,
                toTransportation
        );
    }
}

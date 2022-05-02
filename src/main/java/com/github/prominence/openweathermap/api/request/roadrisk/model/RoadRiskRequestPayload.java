package com.github.prominence.openweathermap.api.request.roadrisk.model;

import com.github.prominence.openweathermap.api.model.roadrisk.TrackPoint;

import java.util.List;

public class RoadRiskRequestPayload {
    private final List<TrackPoint> payload;

    public RoadRiskRequestPayload(List<TrackPoint> payload) {
        this.payload = payload;
    }

    public List<TrackPoint> getPayload() {
        return payload;
    }
}

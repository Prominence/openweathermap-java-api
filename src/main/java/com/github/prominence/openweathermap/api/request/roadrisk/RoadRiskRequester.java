package com.github.prominence.openweathermap.api.request.roadrisk;

import com.github.prominence.openweathermap.api.model.roadrisk.TrackPoint;
import com.github.prominence.openweathermap.api.request.RequestSettings;

import java.util.List;

public class RoadRiskRequester {
    private final RequestSettings requestSettings;

    public RoadRiskRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
        this.requestSettings.appendToURL("data/2.5/roadrisk");
    }

    public RoadRiskRequestCustomizer byTrackPoints(List<TrackPoint> trackPoints) {
        requestSettings.addToRequestBody("track", trackPoints);
        return new RoadRiskRequestCustomizer(requestSettings);
    }
}

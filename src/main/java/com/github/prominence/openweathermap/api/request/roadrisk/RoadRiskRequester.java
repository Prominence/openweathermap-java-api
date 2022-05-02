package com.github.prominence.openweathermap.api.request.roadrisk;

import com.github.prominence.openweathermap.api.model.roadrisk.TrackPoint;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.roadrisk.model.RoadRiskRequestPayload;
import com.github.prominence.openweathermap.api.serializer.RoadRiskRequestSerializer;

import java.util.List;

public class RoadRiskRequester {
    private final RequestSettings requestSettings;

    public RoadRiskRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
        this.requestSettings.appendToURL("data/2.5/roadrisk");
    }

    public RoadRiskRequestCustomizer byTrackPoints(List<TrackPoint> trackPoints) {
        requestSettings.setPayloadObject(new RoadRiskRequestPayload(trackPoints));
        requestSettings.setPayloadClass(RoadRiskRequestPayload.class);
        requestSettings.setPayloadSerializer(new RoadRiskRequestSerializer());
        return new RoadRiskRequestCustomizer(requestSettings);
    }
}

package com.github.prominence.openweathermap.api.request.roadrisk;

import com.github.prominence.openweathermap.api.request.RequestSettings;

public class RoadRiskRequestCustomizer {
    private final RequestSettings requestSettings;

    public RoadRiskRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public RoadRiskRequestTerminator retrieve() {
        return new RoadRiskRequestTerminator(requestSettings);
    }

    public RoadRiskAsyncRequestTerminator retrieveAsync() {
        return new RoadRiskAsyncRequestTerminator(requestSettings);
    }
}

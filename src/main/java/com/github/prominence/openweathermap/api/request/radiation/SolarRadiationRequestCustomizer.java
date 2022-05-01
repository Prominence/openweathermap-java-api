package com.github.prominence.openweathermap.api.request.radiation;

import com.github.prominence.openweathermap.api.request.RequestSettings;

public class SolarRadiationRequestCustomizer {
    private final RequestSettings requestSettings;

    public SolarRadiationRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public SolarRadiationRequestTerminator retrieve() {
        return new SolarRadiationRequestTerminator(requestSettings);
    }

    public SolarRadiationAsyncRequestTerminator retrieveAsync() {
        return new SolarRadiationAsyncRequestTerminator(requestSettings);
    }
}

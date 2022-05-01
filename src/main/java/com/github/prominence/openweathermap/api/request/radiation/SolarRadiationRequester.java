package com.github.prominence.openweathermap.api.request.radiation;

import com.github.prominence.openweathermap.api.request.RequestSettings;

public class SolarRadiationRequester {
    private final RequestSettings requestSettings;

    public SolarRadiationRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
        this.requestSettings.appendToURL("data/2.5/solar_radiation");
    }

    public CurrentSolarRadiationRequester current() {
        return new CurrentSolarRadiationRequester(requestSettings);
    }

    public ForecastSolarRadiationRequester forecast() {
        requestSettings.appendToURL("/forecast");
        return new ForecastSolarRadiationRequester(requestSettings);
    }

    public HistoricalSolarRadiationRequester historical() {
        requestSettings.appendToURL("/history");
        return new HistoricalSolarRadiationRequester(requestSettings);
    }
}

package com.github.prominence.openweathermap.api.request.radiation;

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.request.RequestSettings;

public class ForecastSolarRadiationRequester {
    private final RequestSettings requestSettings;

    public ForecastSolarRadiationRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public SolarRadiationRequestCustomizer byCoordinates(Coordinates coordinates) {
        requestSettings.putRequestParameter("lat", String.valueOf(coordinates.getLatitude()));
        requestSettings.putRequestParameter("lon", String.valueOf(coordinates.getLongitude()));
        return new SolarRadiationRequestCustomizer(requestSettings);
    }
}

package com.github.prominence.openweathermap.api.request.radiation;

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.request.RequestSettings;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class HistoricalSolarRadiationRequester {
    private final RequestSettings requestSettings;

    public HistoricalSolarRadiationRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public SolarRadiationRequestCustomizer byCoordinates(Coordinates coordinates, LocalDateTime startDate, LocalDateTime endDate) {
        requestSettings.putRequestParameter("lat", String.valueOf(coordinates.getLatitude()));
        requestSettings.putRequestParameter("lon", String.valueOf(coordinates.getLongitude()));
        requestSettings.putRequestParameter("start", String.valueOf(startDate.atZone(ZoneId.systemDefault()).toEpochSecond()));
        requestSettings.putRequestParameter("end", String.valueOf(endDate.atZone(ZoneId.systemDefault()).toEpochSecond()));
        return new SolarRadiationRequestCustomizer(requestSettings);
    }
}

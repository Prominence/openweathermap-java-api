package com.github.prominence.openweathermap.api.request.forecast.climatic;

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.request.RequestSettings;

public class ClimaticForecastRequester {
    private final RequestSettings requestSettings;

    public ClimaticForecastRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
        this.requestSettings.setSubdomain("pro");
        this.requestSettings.appendToURL("data/2.5/forecast/climate");
    }

    public ClimaticForecastRequestCustomizer byCoordinates(Coordinates coordinates) {
        requestSettings.putRequestParameter("lat", String.valueOf(coordinates.getLatitude()));
        requestSettings.putRequestParameter("lon", String.valueOf(coordinates.getLongitude()));
        return new ClimaticForecastRequestCustomizer(requestSettings);
    }
}

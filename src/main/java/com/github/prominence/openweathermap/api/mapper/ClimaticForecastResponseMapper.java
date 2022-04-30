package com.github.prominence.openweathermap.api.mapper;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.HumidityDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WeatherStateDeserializer;
import com.github.prominence.openweathermap.api.deserializer.forecast.climatic.*;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.forecast.climatic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClimaticForecastResponseMapper extends AbstractMapper {

    public ClimaticForecastResponseMapper(UnitSystem unitSystem) {
        objectMapper.setInjectableValues(new InjectableValues.Std().addValue("unitSystem", unitSystem != null ? unitSystem : UnitSystem.STANDARD));
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(WeatherState.class, new WeatherStateDeserializer());
        module.addDeserializer(Temperature.class, new ClimaticForecastTemperatureDeserializer());
        module.addDeserializer(AtmosphericPressure.class, new ClimaticForecastAtmosphericPressureDeserializer());
        module.addDeserializer(Humidity.class, new HumidityDeserializer());
        module.addDeserializer(Clouds.class, new ClimaticForecastCloudsDeserializer());
        module.addDeserializer(Rain.class, new ClimaticForecastRainDeserializer());
        module.addDeserializer(Snow.class, new ClimaticForecastSnowDeserializer());
        module.addDeserializer(Wind.class, new ClimaticForecastWindDeserializer());
        module.addDeserializer(Location.class, new ClimaticForecastLocationDeserializer());
        objectMapper.registerModule(module);
    }

    public Forecast mapToForecast(String json) {
        Forecast forecast;
        try {
            final JsonNode root = objectMapper.readTree(json);
            forecast = mapToForecast(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Forecast response", e);
        }

        return forecast;
    }

    private Forecast mapToForecast(JsonNode root) throws IOException {
        final Forecast forecast = new Forecast();
        forecast.setLocation(objectMapper.readValue(objectMapper.treeAsTokens(root.get("city")), Location.class));

        final List<WeatherForecast> forecasts = new ArrayList<>();

        final JsonNode forecastListNode = root.get("list");
        for (JsonNode forecastNode : forecastListNode) {
            forecasts.add(parseWeatherForecast(forecastNode));
        }

        forecast.setWeatherForecasts(forecasts);

        return forecast;
    }

    private WeatherForecast parseWeatherForecast(JsonNode rootNode) throws IOException {
        final WeatherForecast weatherForecast = new WeatherForecast();

        weatherForecast.setForecastTime(parseDateTime(rootNode.get("dt")));
        weatherForecast.setSunriseTime(parseDateTime(rootNode.get("sunrise")));
        weatherForecast.setSunsetTime(parseDateTime(rootNode.get("sunset")));

        weatherForecast.setWeatherStates(parseWeatherStates(rootNode.get("weather")));

        weatherForecast.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Temperature.class));
        weatherForecast.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), AtmosphericPressure.class));
        weatherForecast.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Humidity.class));
        weatherForecast.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Clouds.class));
        weatherForecast.setWind(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Wind.class));
        weatherForecast.setRain(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Rain.class));
        weatherForecast.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Snow.class));

        return weatherForecast;
    }
}

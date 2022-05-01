/*
 * Copyright (c) 2022 Alexey Zinchenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.prominence.openweathermap.api.mapper;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.AtmosphericPressureDeserializer;
import com.github.prominence.openweathermap.api.deserializer.TemperatureDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WeatherStateDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WindDeserializer;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.free.*;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Official API response documentation: <a href="https://openweathermap.org/forecast5#JSON">https://openweathermap.org/forecast5#JSON</a>.
 */
public class FiveDayThreeHourStepForecastResponseMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Instantiates a new forecast response mapper.
     *
     * @param unitSystem the unit system
     */
    public FiveDayThreeHourStepForecastResponseMapper(UnitSystem unitSystem) {
        objectMapper.setInjectableValues(new InjectableValues.Std().addValue("unitSystem", unitSystem != null ? unitSystem : UnitSystem.STANDARD));
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(WeatherState.class, new WeatherStateDeserializer());
        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        module.addDeserializer(AtmosphericPressure.class, new AtmosphericPressureDeserializer());
        module.addDeserializer(Wind.class, new WindDeserializer());
        objectMapper.registerModule(module);

    }

    /**
     * Maps forecast response into java object.
     *
     * @param json the json string
     * @return the forecast
     */
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
        forecast.setLocation(parseLocation(root.get("city")));

        final List<WeatherForecast> forecasts = new ArrayList<>(root.get("cnt").asInt());

        final JsonNode forecastListNode = root.get("list");
        for (JsonNode forecastNode : forecastListNode) {
            forecasts.add(parseWeatherForecast(forecastNode));
        }

        forecast.setWeatherForecasts(forecasts);

        return forecast;
    }

    private WeatherForecast parseWeatherForecast(JsonNode rootNode) throws IOException {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final JsonNode weatherArrayNode = rootNode.get("weather");
        if (weatherArrayNode != null) {
            final JsonNode weatherNode = weatherArrayNode.get(0);
            weatherForecast.setWeatherState(parseWeatherState(weatherNode));
        }

        final JsonNode mainNode = rootNode.get("main");
        weatherForecast.setTemperature(parseTemperature(mainNode));
        weatherForecast.setAtmosphericPressure(parsePressure(mainNode));
        weatherForecast.setHumidity(parseHumidity(mainNode));
        weatherForecast.setClouds(parseClouds(rootNode));
        weatherForecast.setWind(parseWind(rootNode));
        weatherForecast.setRain(parseRain(rootNode));
        weatherForecast.setSnow(parseSnow(rootNode));

        final JsonNode sysNode = rootNode.get("sys");
        if (sysNode != null) {
            weatherForecast.setDayTime("d".equals(sysNode.get("pod").asText()) ? DayTime.DAY : DayTime.NIGHT);
        }

        weatherForecast.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(rootNode.get("dt").asLong()), TimeZone.getDefault().toZoneId()));
        weatherForecast.setForecastTimeISO(rootNode.get("dt_txt").asText());

        return weatherForecast;
    }

    private WeatherState parseWeatherState(JsonNode weatherNode) throws IOException {
        if (weatherNode == null) {
            return null;
        }
        return objectMapper.readValue(objectMapper.treeAsTokens(weatherNode), WeatherState.class);
    }

    private Temperature parseTemperature(JsonNode rootNode) throws IOException {
        return objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Temperature.class);
    }

    private AtmosphericPressure parsePressure(JsonNode rootNode) throws IOException {
        return objectMapper.readValue(objectMapper.treeAsTokens(rootNode), AtmosphericPressure.class);
    }

    private Humidity parseHumidity(JsonNode rootNode) {
        return Humidity.withValue((byte) (rootNode.get("humidity").asInt()));
    }

    private Wind parseWind(JsonNode root) throws IOException {
        final JsonNode windNode = root.get("wind");
        return objectMapper.readValue(objectMapper.treeAsTokens(windNode), Wind.class);
    }

    private Rain parseRain(JsonNode root) {
        final JsonNode rainNode = root.get("rain");
        if (rainNode != null) {
            final JsonNode threeHourNode = rainNode.get("3h");
            if (threeHourNode != null) {
                return Rain.withThreeHourLevelValue(threeHourNode.asDouble());
            }
        }
        return null;
    }

    private Snow parseSnow(JsonNode root) {
        final JsonNode snowNode = root.get("snow");
        if (snowNode != null) {
            final JsonNode threeHourNode = snowNode.get("3h");
            if (threeHourNode != null) {
                return Snow.withThreeHourLevelValue(threeHourNode.asDouble());
            }
        }
        return null;
    }

    private Clouds parseClouds(JsonNode rootNode) {
        final JsonNode cloudsNode = rootNode.get("clouds");
        final JsonNode allValueNode = cloudsNode.get("all");
        if (allValueNode != null) {
            return Clouds.withValue((byte) allValueNode.asInt());
        }

        return null;
    }

    private Location parseLocation(JsonNode rootNode) {
        final Location location = Location.withValues(rootNode.get("id").asInt(), rootNode.get("name").asText());

        final JsonNode timezoneNode = rootNode.get("timezone");
        if (timezoneNode != null) {
            location.setZoneOffset(ZoneOffset.ofTotalSeconds(timezoneNode.asInt()));
        }

        final JsonNode countryNode = rootNode.get("country");
        if (countryNode != null) {
            location.setCountryCode(countryNode.asText());
        }

        final JsonNode sunriseNode = rootNode.get("sunrise");
        final JsonNode sunsetNode = rootNode.get("sunset");
        if (sunriseNode != null) {
            location.setSunriseTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseNode.asLong()), TimeZone.getDefault().toZoneId()));
        }
        if (sunsetNode != null) {
            location.setSunsetTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetNode.asLong()), TimeZone.getDefault().toZoneId()));
        }

        final JsonNode coordNode = rootNode.get("coord");
        if (coordNode != null) {
            location.setCoordinates(parseCoordinate(coordNode));
        }

        final JsonNode populationNode = rootNode.get("population");
        if (populationNode != null) {
            location.setPopulation(populationNode.asLong());
        }

        return location;
    }

    private Coordinates parseCoordinate(JsonNode rootNode) {
        final JsonNode latitudeNode = rootNode.get("lat");
        final JsonNode longitudeNode = rootNode.get("lon");
        if (latitudeNode != null && longitudeNode != null) {
            return Coordinates.of(latitudeNode.asDouble(), longitudeNode.asDouble());
        }
        return null;
    }
}

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.forecast.daily.*;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Official API response documentation: <a href="https://openweathermap.org/forecast16#JSON">https://openweathermap.org/forecast16#JSON</a>.
 */
public class DailyForecastResponseMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UnitSystem unitSystem;

    public DailyForecastResponseMapper(UnitSystem unitSystem) {
        this.unitSystem = unitSystem;
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
            throw new RuntimeException("Cannot parse Forecast response");
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

        weatherForecast.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(rootNode.get("dt").asLong()), TimeZone.getDefault().toZoneId()));

        return weatherForecast;
    }

    private WeatherState parseWeatherState(JsonNode weatherNode) throws IOException {
        if (weatherNode == null) {
            return null;
        }
        return objectMapper.readValue(objectMapper.treeAsTokens(weatherNode), WeatherState.class);
    }

    private Temperature parseTemperature(JsonNode rootNode) {
        final Temperature temperature = new Temperature();
        final JsonNode tempNode = rootNode.get("temp");
        temperature.setMorning(tempNode.get("morn").asDouble());
        temperature.setDay(tempNode.get("day").asDouble());
        temperature.setEve(tempNode.get("eve").asDouble());
        temperature.setNight(tempNode.get("night").asDouble());
        temperature.setMin(tempNode.get("min").asDouble());
        temperature.setMax(tempNode.get("max").asDouble());

        final JsonNode feelsLikeNode = rootNode.get("feels_like");
        temperature.setMorningFeelsLike(feelsLikeNode.get("morn").asDouble());
        temperature.setDayFeelsLike(feelsLikeNode.get("day").asDouble());
        temperature.setEveFeelsLike(feelsLikeNode.get("eve").asDouble());
        temperature.setNightFeelsLike(feelsLikeNode.get("night").asDouble());

        return temperature;
    }

    private AtmosphericPressure parsePressure(JsonNode rootNode) {
        return AtmosphericPressure.withValue(rootNode.get("pressure").asDouble());
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

        final JsonNode coordNode = rootNode.get("coord");
        if (coordNode != null) {
            location.setCoordinate(parseCoordinate(coordNode));
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

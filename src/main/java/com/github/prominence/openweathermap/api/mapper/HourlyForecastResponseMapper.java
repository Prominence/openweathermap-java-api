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
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.*;
import com.github.prominence.openweathermap.api.deserializer.forecast.hourly.HourlyForecastLocationDeserializer;
import com.github.prominence.openweathermap.api.deserializer.forecast.hourly.HourlyForecastRainDeserializer;
import com.github.prominence.openweathermap.api.deserializer.forecast.hourly.HourlyForecastSnowDeserializer;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.hourly.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Official API response documentation: <a href="https://openweathermap.org/api/hourly-forecast#JSON">https://openweathermap.org/api/hourly-forecast#JSON</a>.
 */
public class HourlyForecastResponseMapper extends AbstractMapper {
    /**
     * Instantiates a new forecast response mapper.
     *
     * @param unitSystem the unit system
     */
    public HourlyForecastResponseMapper(UnitSystem unitSystem) {
        objectMapper.setInjectableValues(new InjectableValues.Std().addValue("unitSystem", unitSystem != null ? unitSystem : UnitSystem.STANDARD));
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(WeatherState.class, new WeatherStateDeserializer());
        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        module.addDeserializer(AtmosphericPressure.class, new AtmosphericPressureDeserializer());
        module.addDeserializer(Wind.class, new WindDeserializer());
        module.addDeserializer(Humidity.class, new HumidityDeserializer());
        module.addDeserializer(Clouds.class, new CloudsDeserializer());
        module.addDeserializer(Rain.class, new HourlyForecastRainDeserializer());
        module.addDeserializer(Snow.class, new HourlyForecastSnowDeserializer());
        module.addDeserializer(Location.class, new HourlyForecastLocationDeserializer());
        objectMapper.registerModule(module);
    }

    /**
     * Maps forecast response into java object.
     *
     * @param json the json string
     * @return the forecast
     */
    public HourlyForecast mapToForecast(String json) {
        HourlyForecast hourlyForecast;
        try {
            final JsonNode root = objectMapper.readTree(json);
            hourlyForecast = mapToForecast(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Forecast response");
        }

        return hourlyForecast;
    }

    private HourlyForecast mapToForecast(JsonNode root) throws IOException {
        final HourlyForecast hourlyForecast = new HourlyForecast();
        hourlyForecast.setLocation(objectMapper.readValue(objectMapper.treeAsTokens(root.get("city")), Location.class));

        final List<WeatherForecast> forecasts = new ArrayList<>(root.get("cnt").asInt());

        final JsonNode forecastListNode = root.get("list");
        for (JsonNode forecastNode : forecastListNode) {
            forecasts.add(parseWeatherForecast(forecastNode));
        }

        hourlyForecast.setWeatherForecasts(forecasts);

        return hourlyForecast;
    }

    private WeatherForecast parseWeatherForecast(JsonNode rootNode) throws IOException {
        final WeatherForecast weatherForecast = new WeatherForecast();

        final JsonNode weatherArrayNode = rootNode.get("weather");
        if (weatherArrayNode != null && weatherArrayNode.isArray()) {
            List<WeatherState> weatherStateList = new ArrayList<>();
            for (JsonNode weatherNode : weatherArrayNode) {
                weatherStateList.add(objectMapper.readValue(objectMapper.treeAsTokens(weatherNode), WeatherState.class));
            }

            weatherForecast.setWeatherStates(weatherStateList);
        }

        final JsonNode mainNode = rootNode.get("main");
        weatherForecast.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(mainNode), Temperature.class));
        weatherForecast.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(mainNode), AtmosphericPressure.class));
        weatherForecast.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(mainNode), Humidity.class));
        weatherForecast.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("clouds")), Clouds.class));
        weatherForecast.setWind(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("wind")), Wind.class));
        if (rootNode.has("rain")) {
            weatherForecast.setRain(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("rain")), Rain.class));
        }
        if (rootNode.has("snow")) {
            weatherForecast.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("snow")), Snow.class));
        }

        final JsonNode sysNode = rootNode.get("sys");
        if (sysNode != null) {
            weatherForecast.setDayTime("d".equals(sysNode.get("pod").asText()) ? DayTime.DAY : DayTime.NIGHT);
        }

        if (rootNode.has("visibility")) {
            weatherForecast.setAverageVisibilityInMetres(rootNode.get("visibility").asInt());
        }

        if (rootNode.has("pop")) {
            weatherForecast.setProbabilityOfPrecipitation(rootNode.get("pop").asDouble());
        }

        weatherForecast.setForecastTime(parseDateTime(rootNode.get("dt")));
        weatherForecast.setForecastTimeISO(rootNode.get("dt_txt").asText());

        return weatherForecast;
    }
}
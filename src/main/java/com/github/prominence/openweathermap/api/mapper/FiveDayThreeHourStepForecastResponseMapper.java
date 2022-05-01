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
import com.github.prominence.openweathermap.api.deserializer.forecast.free.FreeForecastLocationDeserializer;
import com.github.prominence.openweathermap.api.deserializer.forecast.free.FreeForecastRainDeserializer;
import com.github.prominence.openweathermap.api.deserializer.forecast.free.FreeForecastSnowDeserializer;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.free.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Official API response documentation: <a href="https://openweathermap.org/forecast5#JSON">https://openweathermap.org/forecast5#JSON</a>.
 */
public class FiveDayThreeHourStepForecastResponseMapper extends AbstractMapper {
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
        module.addDeserializer(Humidity.class, new HumidityDeserializer());
        module.addDeserializer(Clouds.class, new CloudsDeserializer());
        module.addDeserializer(Wind.class, new WindDeserializer());
        module.addDeserializer(Rain.class, new FreeForecastRainDeserializer());
        module.addDeserializer(Snow.class, new FreeForecastSnowDeserializer());
        module.addDeserializer(Location.class, new FreeForecastLocationDeserializer());
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

    private Forecast mapToForecast(JsonNode rootNode) throws IOException {
        final Forecast forecast = new Forecast();
        forecast.setLocation(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("city")), Location.class));

        final List<WeatherForecast> forecasts = new ArrayList<>(rootNode.get("cnt").asInt());

        final JsonNode forecastListNode = rootNode.get("list");
        for (JsonNode forecastNode : forecastListNode) {
            forecasts.add(parseWeatherForecast(forecastNode));
        }

        forecast.setWeatherForecasts(forecasts);

        return forecast;
    }

    private WeatherForecast parseWeatherForecast(JsonNode rootNode) throws IOException {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final JsonNode weatherArrayNode = rootNode.get("weather");
        weatherForecast.setWeatherStates(parseWeatherStates(weatherArrayNode));

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

        final JsonNode visibilityNode = rootNode.get("visibility");
        if (visibilityNode != null) {
            weatherForecast.setVisibilityInMetres(visibilityNode.asDouble());
        }
        final JsonNode popNode = rootNode.get("pop");
        if (popNode != null) {
            weatherForecast.setProbabilityOfPrecipitation(popNode.asDouble());
        }

        weatherForecast.setForecastTime(parseDateTime(rootNode.get("dt")));
        weatherForecast.setForecastTimeISO(rootNode.get("dt_txt").asText());

        return weatherForecast;
    }
}

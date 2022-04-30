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
import com.github.prominence.openweathermap.api.deserializer.HumidityDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WeatherStateDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WindDeserializer;
import com.github.prominence.openweathermap.api.deserializer.forecast.daily.*;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.forecast.daily.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Official API response documentation: <a href="https://openweathermap.org/forecast16#JSON">https://openweathermap.org/forecast16#JSON</a>.
 */
public class DailyForecastResponseMapper extends AbstractMapper {
    public DailyForecastResponseMapper(UnitSystem unitSystem) {
        objectMapper.setInjectableValues(new InjectableValues.Std().addValue("unitSystem", unitSystem != null ? unitSystem : UnitSystem.STANDARD));
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(WeatherState.class, new WeatherStateDeserializer());
        module.addDeserializer(Temperature.class, new DailyForecastTemperatureDeserializer());
        module.addDeserializer(AtmosphericPressure.class, new DailyForecastAtmosphericPressureDeserializer());
        module.addDeserializer(Humidity.class, new HumidityDeserializer());
        module.addDeserializer(Clouds.class, new DailyForecastCloudsDeserializer());
        module.addDeserializer(Rain.class, new DailyForecastRainDeserializer());
        module.addDeserializer(Snow.class, new DailyForecastSnowDeserializer());
        module.addDeserializer(Wind.class, new WindDeserializer());
        module.addDeserializer(Location.class, new DailyForecastLocationDeserializer());
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
        forecast.setLocation(objectMapper.readValue(objectMapper.treeAsTokens(root.get("city")), Location.class));

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

        if (rootNode.has("pop")) {
            weatherForecast.setProbabilityOfPrecipitation(rootNode.get("pop").asDouble());
        }

        return weatherForecast;
    }
}

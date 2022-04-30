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
import com.github.prominence.openweathermap.api.deserializer.weather.WeatherLocationDeserializer;
import com.github.prominence.openweathermap.api.deserializer.weather.WeatherRainDeserializer;
import com.github.prominence.openweathermap.api.deserializer.weather.WeatherSnowDeserializer;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.weather.Location;
import com.github.prominence.openweathermap.api.model.weather.Rain;
import com.github.prominence.openweathermap.api.model.weather.Snow;
import com.github.prominence.openweathermap.api.model.weather.Weather;

import java.io.IOException;

/**
 * Official API response documentation: <a href="https://openweathermap.org/current#current_JSON">https://openweathermap.org/current#current_JSON</a>.
 * Ignored internal parameters: "root.cod", "sys.type", "sys.id", "sys.message".
 */
public class CurrentWeatherResponseMapper extends AbstractMapper {
    /**
     * Instantiates a new Current weather response mapper.
     *
     * @param unitSystem the unit system
     */
    public CurrentWeatherResponseMapper(UnitSystem unitSystem) {
        objectMapper.setInjectableValues(new InjectableValues.Std().addValue("unitSystem", unitSystem != null ? unitSystem : UnitSystem.STANDARD));
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(WeatherState.class, new WeatherStateDeserializer());
        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        module.addDeserializer(AtmosphericPressure.class, new AtmosphericPressureDeserializer());
        module.addDeserializer(Humidity.class, new HumidityDeserializer());
        module.addDeserializer(Wind.class, new WindDeserializer());
        module.addDeserializer(Rain.class, new WeatherRainDeserializer());
        module.addDeserializer(Snow.class, new WeatherSnowDeserializer());
        module.addDeserializer(Clouds.class, new CloudsDeserializer());
        module.addDeserializer(Location.class, new WeatherLocationDeserializer());
        objectMapper.registerModule(module);
    }

    /**
     * Gets single result.
     *
     * @param json the json string
     * @return the weather object
     */
    public Weather mapToWeather(String json) {
        Weather weather;
        try {
            final JsonNode root = objectMapper.readTree(json);
            weather = mapToWeather(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Weather response", e);
        }

        return weather;
    }

    private Weather mapToWeather(JsonNode rootNode) throws IOException {
        final JsonNode weatherArrayNode = rootNode.get("weather");
        final Weather weather = new Weather();

        weather.setWeatherStates(parseWeatherStates(weatherArrayNode));

        weather.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("main")), Temperature.class));
        weather.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("main")), AtmosphericPressure.class));
        weather.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("main")), Humidity.class));
        weather.setWind(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("wind")), Wind.class));
        if (rootNode.has("rain")) {
            weather.setRain(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("rain")), Rain.class));
        }
        if (rootNode.has("snow")) {
            weather.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("snow")), Snow.class));
        }
        weather.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("clouds")), Clouds.class));
        weather.setLocation(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Location.class));

        final JsonNode dtNode = rootNode.get("dt");
        if (dtNode != null) {
            weather.setCalculationTime(parseDateTime(dtNode));
        }

        weather.setBase(rootNode.get("base").asText());

        return weather;
    }
}

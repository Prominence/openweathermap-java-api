/*
 * Copyright (c) 2021-present Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.EpochSecondsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.VisibilityDeserializer;
import com.github.prominence.openweathermap.api.deserializer.ZoneOffsetDeserializer;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.MainMetrics;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.model.Visibility;
import com.github.prominence.openweathermap.api.model.generic.location.BaseLocation;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;
import com.github.prominence.openweathermap.api.model.generic.wind.WindModel;
import lombok.Data;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents weather information.
 */
@Data
public class CurrentWeatherModel implements BaseLocation, SunlightStages, CurrentWeather {

    @JsonProperty("coord")
    private Coordinates coordinates;
    @JsonProperty("weather")
    private List<WeatherCondition> weatherStates = new ArrayList<>();
    @JsonProperty("base")
    private String base;
    @JsonProperty("main")
    private MainMetrics main;
    @JsonDeserialize(using = VisibilityDeserializer.class)
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonProperty("wind")
    private WindModel windModel;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("rain")
    private Precipitation rain;
    @JsonProperty("snow")
    private Precipitation snow;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("dt")
    private OffsetDateTime forecastTime;
    @JsonProperty("sys")
    private City city;
    @JsonDeserialize(using = ZoneOffsetDeserializer.class)
    @JsonProperty("timezone")
    private ZoneOffset timeZone;
    @JsonProperty("id")
    private long cityId;
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("cod")
    private long cod;

    @Override
    @JsonIgnore
    public Temperature getTemperature() {
        return main;
    }

    @Override
    @JsonIgnore
    public Humidity getHumidity() {
        return main;
    }

    @Override
    @JsonIgnore
    public AtmosphericPressure getAtmosphericPressure() {
        return main;
    }

    @Override
    @JsonIgnore
    public BaseLocation getLocation() {
        return this;
    }

    @JsonIgnore
    public DetailedWindInfo getWind() {
        return windModel;
    }

    @Override
    @JsonIgnore
    public String getCountryCode() {
        return Optional.ofNullable(city).map(City::getCountryCode).orElse(null);
    }

    @Override
    @JsonIgnore
    public OffsetDateTime getSunriseTime() {
        return Optional.ofNullable(city).map(City::getSunriseTime).orElse(null);
    }

    @Override
    @JsonIgnore
    public OffsetDateTime getSunsetTime() {
        return Optional.ofNullable(city).map(City::getSunsetTime).orElse(null);
    }
}

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

package com.github.prominence.openweathermap.api.model.forecast.hourly;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.EpochSecondsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.ZoneOffsetDeserializer;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Location;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents information about forecast for different timestamps.
 */
@Data
public class HourlyForecastModel implements Location, HourlyForecast {

    @JsonProperty("cod")
    private long cod;
    @JsonProperty("message")
    private BigDecimal message;
    @JsonProperty("list")
    private List<WeatherForecast> forecasts;
    @JsonProperty("city")
    private LocationModel locationModel;
    @JsonProperty("country")
    private String countryCode;
    @JsonDeserialize(using = ZoneOffsetDeserializer.class)
    @JsonProperty("timezone")
    private ZoneOffset timeZone;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunrise")
    private OffsetDateTime sunriseTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunset")
    private OffsetDateTime sunsetTime;

    @Override
    @JsonIgnore
    public Location getLocation() {
        return this;
    }

    @Override
    @JsonIgnore
    public List<Weather> getWeatherForecasts() {
        return forecasts.stream().map(Weather.class::cast).collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public Coordinates getCoordinates() {
        return Optional.ofNullable(locationModel).map(LocationModel::getCoordinates).orElse(null);
    }

    @Override
    @JsonIgnore
    public long getCityId() {
        return Optional.ofNullable(locationModel).map(LocationModel::getId).orElse(-1);
    }

    @Override
    @JsonIgnore
    public String getCityName() {
        return Optional.ofNullable(locationModel).map(LocationModel::getName).orElse(null);
    }
}

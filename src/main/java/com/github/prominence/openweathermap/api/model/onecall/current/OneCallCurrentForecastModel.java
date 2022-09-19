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

package com.github.prominence.openweathermap.api.model.onecall.current;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.ZoneIdDeserializer;
import com.github.prominence.openweathermap.api.deserializer.ZoneOffsetDeserializer;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.onecall.Measurement;
import com.github.prominence.openweathermap.api.model.onecall.OneCallMeasurement;
import lombok.Data;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Current weather data.
 */
@Data
public class OneCallCurrentForecastModel implements OneCallCurrentForecast {
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lon")
    private double longitude;
    @JsonDeserialize(using = ZoneIdDeserializer.class)
    @JsonProperty("timezone")
    private ZoneId timezone;
    @JsonDeserialize(using = ZoneOffsetDeserializer.class)
    @JsonProperty("timezone_offset")
    private ZoneOffset timezoneOffset;
    @JsonProperty("current")
    private Measurement current;
    @JsonProperty("minutely")
    private List<Minutely> minutelyList;
    @JsonProperty("hourly")
    private List<Hourly> hourlyList;
    @JsonProperty("daily")
    private List<Daily> dailyList;
    @JsonProperty("alerts")
    private List<Alert> alerts;

    @Override
    @JsonIgnore
    public Coordinates getCoordinates() {
        return new Coordinates(latitude, longitude);
    }

    @Override
    @JsonIgnore
    public OneCallMeasurement getCurrentWeather() {
        return current;
    }

    @Override
    @JsonIgnore
    public List<OneCallMinutelyWeather> getMinutelyForecast() {
        return Optional.ofNullable(minutelyList)
                .map(List::stream)
                .map(s -> s.map(OneCallMinutelyWeather.class::cast).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    @JsonIgnore
    public List<OneCallHourlyWeather> getHourlyForecast() {
        return Optional.ofNullable(hourlyList)
                .map(List::stream)
                .map(s -> s.map(OneCallHourlyWeather.class::cast).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    @JsonIgnore
    public List<OneCallDailyWeather> getDailyForecast() {
        return Optional.ofNullable(dailyList)
                .map(List::stream)
                .map(s -> s.map(OneCallDailyWeather.class::cast).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}

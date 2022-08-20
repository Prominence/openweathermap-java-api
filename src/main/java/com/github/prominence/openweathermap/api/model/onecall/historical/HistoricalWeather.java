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

package com.github.prominence.openweathermap.api.model.onecall.historical;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.ZoneIdDeserializer;
import com.github.prominence.openweathermap.api.deserializer.ZoneOffsetDeserializer;
import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
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
public class HistoricalWeather implements OneCallHistoricalWeather {
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
    @JsonProperty("data")
    private List<Measurement> data;

    /**
     * Gets coordinate.
     *
     * @return the coordinate
     */
    @JsonIgnore
    public Coordinates getCoordinates() {
        return new Coordinates(latitude, longitude);
    }

    @Override
    @JsonIgnore
    public List<OneCallMeasurement> getDataPoints() {
        return Optional.ofNullable(data)
                .map(List::stream)
                .map(s -> s.map(OneCallMeasurement.class::cast).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}

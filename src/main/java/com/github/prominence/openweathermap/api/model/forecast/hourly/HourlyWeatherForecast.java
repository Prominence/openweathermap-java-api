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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.EpochSecondsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.PercentageZeroToOneDeserializer;
import com.github.prominence.openweathermap.api.deserializer.VisibilityDeserializer;
import com.github.prominence.openweathermap.api.enums.DayTime;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.MainMetrics;
import com.github.prominence.openweathermap.api.model.TimeAware;
import com.github.prominence.openweathermap.api.model.Visibility;
import com.github.prominence.openweathermap.api.model.forecast.MetaData;
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.clouds.Clouds;
import com.github.prominence.openweathermap.api.model.generic.precipitation.Humidity;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationForecast;
import com.github.prominence.openweathermap.api.model.generic.pressure.DetailedAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureWithRange;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;
import com.github.prominence.openweathermap.api.model.generic.wind.WindModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents weather forecast information for a particular timestamp.
 */
@Data
@JsonIgnoreProperties(value = {"dt_txt"})
public class HourlyWeatherForecast implements TimeAware, HourlyWeather, PrecipitationForecast {

    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("dt")
    private OffsetDateTime forecastTime;
    @JsonProperty("main")
    private MainMetrics mainMetrics;
    @JsonProperty("weather")
    private List<WeatherCondition> weatherStates = new ArrayList<>();
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("wind")
    private WindModel windModel;
    @JsonProperty("rain")
    private BasePrecipitation rainModel;
    @JsonProperty("snow")
    private BasePrecipitation snowModel;
    @JsonDeserialize(using = VisibilityDeserializer.class)
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonDeserialize(using = PercentageZeroToOneDeserializer.class)
    @JsonProperty("pop")
    private Integer probabilityOfPrecipitation;
    @JsonProperty("sys")
    private MetaData sysMeta;

    @Override
    @JsonIgnore
    public TemperatureWithRange getTemperature() {
        return mainMetrics;
    }

    @Override
    @JsonIgnore
    public Humidity getHumidity() {
        return mainMetrics;
    }

    @Override
    @JsonIgnore
    public DetailedAtmosphericPressure getAtmosphericPressure() {
        return mainMetrics;
    }

    @Override
    @JsonIgnore
    public DetailedWindInfo getWind() {
        return windModel;
    }

    @Override
    @JsonIgnore
    public PrecipitationForecast getHourlyPrecipitation() {
        return this;
    }

    @Override
    @JsonIgnore
    public BigDecimal getRain() {
        return Optional.ofNullable(getRainModel()).map(BasePrecipitation::getOneHourLevel).orElse(null);
    }

    @Override
    @JsonIgnore
    public BigDecimal getSnow() {
        return Optional.ofNullable(getSnowModel()).map(BasePrecipitation::getOneHourLevel).orElse(null);
    }

    @Override
    public CloudCoverage getClouds() {
        return clouds;
    }

    @Override
    @JsonIgnore
    public DayTime getPartOfDay() {
        return Optional.ofNullable(sysMeta).map(MetaData::getPartOfDay).orElse(null);
    }
}
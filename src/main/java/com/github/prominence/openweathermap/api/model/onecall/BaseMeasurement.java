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

package com.github.prominence.openweathermap.api.model.onecall;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.EpochSecondsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.RequiredPercentageDeserializer;
import com.github.prominence.openweathermap.api.deserializer.TemperatureValueDeserializer;
import com.github.prominence.openweathermap.api.deserializer.VisibilityDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WindSpeedDeserializer;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.generic.TimeAware;
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.precipitation.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.generic.precipitation.Humidity;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationValues;
import com.github.prominence.openweathermap.api.model.generic.pressure.SeaLevelAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.generic.temperature.DewPointAwareTemperature;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureValue;
import com.github.prominence.openweathermap.api.model.generic.visibility.Visibility;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The current weather measurements.
 */
@Data
public class BaseMeasurement
        implements TimeAware, SunlightStages, DetailedWindInfo, CloudCoverage, Humidity,
        PrecipitationValues, SeaLevelAtmosphericPressure, DewPointAwareTemperature, OneCallBaseMeasurementCore {

    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("dt")
    private OffsetDateTime forecastTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunrise")
    private OffsetDateTime sunriseTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunset")
    private OffsetDateTime sunsetTime;
    @JsonDeserialize(using = TemperatureValueDeserializer.class)
    @JsonProperty("temp")
    private TemperatureValue temperatureMeasured;
    @JsonDeserialize(using = TemperatureValueDeserializer.class)
    @JsonProperty("feels_like")
    private TemperatureValue feelsLike;
    @JsonProperty("pressure")
    private BigDecimal seaLevel;
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("humidity")
    private int humidityPercentage;
    @JsonDeserialize(using = TemperatureValueDeserializer.class)
    @JsonProperty("dew_point")
    private TemperatureValue dewPoint;
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("clouds")
    private Integer coveragePercentage;
    @JsonProperty("uvi")
    private BigDecimal uvIndex;
    @JsonDeserialize(using = VisibilityDeserializer.class)
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("wind_speed")
    private WindSpeed speed;
    @JsonProperty("wind_deg")
    private Integer directionDegrees;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("wind_gust")
    private WindSpeed gust;
    @JsonProperty("rain")
    private BasePrecipitation rainModel;
    @JsonProperty("snow")
    private BasePrecipitation snowModel;
    @JsonProperty("weather")
    private List<WeatherCondition> weatherStates = new ArrayList<>();

    @Override
    @JsonIgnore
    public DetailedWindInfo getWind() {
        return this;
    }

    @Override
    @JsonIgnore
    public SunlightStages getSunlightStages() {
        return this;
    }

    @Override
    @JsonIgnore
    public CloudCoverage getClouds() {
        return this;
    }

    @Override
    @JsonIgnore
    public Humidity getHumidity() {
        return this;
    }

    @Override
    public BigDecimal getUvIndex() {
        return uvIndex;
    }

    @Override
    public Visibility getVisibility() {
        return visibility;
    }

    @Override
    public List<WeatherCondition> getWeatherStates() {
        return weatherStates;
    }

    @Override
    @JsonIgnore
    public DewPointAwareTemperature getTemperatures() {
        return this;
    }

    @Override
    @JsonIgnore
    public TemperatureValue getTemperature() {
        return temperatureMeasured;
    }

    @Override
    @JsonIgnore
    public BigDecimal getRain() {
        return Optional.ofNullable(rainModel).map(BasePrecipitation::getOneHourLevel).orElse(null);
    }

    @Override
    @JsonIgnore
    public BigDecimal getSnow() {
        return Optional.ofNullable(snowModel).map(BasePrecipitation::getOneHourLevel).orElse(null);
    }

    @Override
    @JsonIgnore
    public SeaLevelAtmosphericPressure getAtmosphericPressure() {
        return this;
    }

}

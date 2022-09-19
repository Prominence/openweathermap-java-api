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
import com.github.prominence.openweathermap.api.deserializer.EpochSecondsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.PercentageZeroToOneDeserializer;
import com.github.prominence.openweathermap.api.deserializer.RequiredPercentageDeserializer;
import com.github.prominence.openweathermap.api.deserializer.TemperatureValueDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WindSpeedDeserializer;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.TimeAware;
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.location.MoonlightStages;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.precipitation.Humidity;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationForecast;
import com.github.prominence.openweathermap.api.model.generic.pressure.SeaLevelAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureDailyBasic;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureDailyDetailed;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureValue;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The daily weather forecast type.
 */
@Data
public class Daily
        implements TimeAware, SunlightStages, MoonlightStages, DetailedWindInfo, OneCallDailyTemperature, PrecipitationForecast,
        Humidity, SeaLevelAtmosphericPressure, CloudCoverage, OneCallDailyWeather {

    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("dt")
    private OffsetDateTime forecastTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunrise")
    private OffsetDateTime sunriseTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunset")
    private OffsetDateTime sunsetTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("moonrise")
    private OffsetDateTime moonriseTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("moonset")
    private OffsetDateTime moonsetTime;
    @JsonProperty("moon_phase")
    private MoonPhase moonPhase;
    @JsonProperty("temp")
    private TemperatureDailyDetailed temperatureMeasured;
    @JsonProperty("feels_like")
    private TemperatureDailyBasic feelsLike;
    @JsonProperty("pressure")
    private BigDecimal seaLevel;
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("humidity")
    private int humidityPercentage;
    @JsonDeserialize(using = TemperatureValueDeserializer.class)
    @JsonProperty("dew_point")
    private TemperatureValue dewPoint;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("wind_speed")
    private WindSpeed speed;
    @JsonProperty("wind_deg")
    private Integer directionDegrees;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("wind_gust")
    private WindSpeed gust;
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("clouds")
    private Integer coveragePercentage;
    @JsonProperty("uvi")
    private BigDecimal uvIndex;
    @JsonDeserialize(using = PercentageZeroToOneDeserializer.class)
    @JsonProperty("pop")
    private Integer probabilityOfPrecipitation;
    @JsonProperty("rain")
    private BigDecimal rain;
    @JsonProperty("snow")
    private BigDecimal snow;
    @JsonProperty("weather")
    private List<WeatherCondition> weatherStates = new ArrayList<>();

    @Override
    @JsonIgnore
    public TemperatureValue getMorning() {
        return Optional.ofNullable(temperatureMeasured).map(TemperatureDailyDetailed::getMorning).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getDay() {
        return Optional.ofNullable(temperatureMeasured).map(TemperatureDailyDetailed::getDay).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getEve() {
        return Optional.ofNullable(temperatureMeasured).map(TemperatureDailyDetailed::getEve).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getNight() {
        return Optional.ofNullable(temperatureMeasured).map(TemperatureDailyDetailed::getNight).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getMin() {
        return Optional.ofNullable(temperatureMeasured).map(TemperatureDailyDetailed::getMin).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getMax() {
        return Optional.ofNullable(temperatureMeasured).map(TemperatureDailyDetailed::getMax).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getMorningFeelsLike() {
        return Optional.ofNullable(feelsLike).map(TemperatureDailyBasic::getMorning).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getDayFeelsLike() {
        return Optional.ofNullable(feelsLike).map(TemperatureDailyBasic::getDay).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getEveFeelsLike() {
        return Optional.ofNullable(feelsLike).map(TemperatureDailyBasic::getEve).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getNightFeelsLike() {
        return Optional.ofNullable(feelsLike).map(TemperatureDailyBasic::getNight).orElse(null);
    }

    @Override
    @JsonIgnore
    public SunlightStages getSunlightStages() {
        return this;
    }

    @Override
    @JsonIgnore
    public MoonlightStages getMoonLightStages() {
        return this;
    }

    @Override
    @JsonIgnore
    public DetailedWindInfo getWind() {
        return this;
    }

    @Override
    @JsonIgnore
    public OneCallDailyTemperature getTemperature() {
        return this;
    }

    @Override
    @JsonIgnore
    public PrecipitationForecast getPrecipitation() {
        return this;
    }

    @Override
    @JsonIgnore
    public Humidity getHumidity() {
        return this;
    }

    @Override
    @JsonIgnore
    public SeaLevelAtmosphericPressure getAtmosphericPressure() {
        return this;
    }

    @Override
    @JsonIgnore
    public CloudCoverage getClouds() {
        return this;
    }
}

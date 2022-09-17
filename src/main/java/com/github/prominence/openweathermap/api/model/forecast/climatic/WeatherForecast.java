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

package com.github.prominence.openweathermap.api.model.forecast.climatic;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.EpochSecondsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.RequiredPercentageDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WindSpeedDeserializer;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.BaseAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.TemperatureDailyBasic;
import com.github.prominence.openweathermap.api.model.TemperatureDailyDetailed;
import com.github.prominence.openweathermap.api.model.TemperatureValue;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationValues;
import com.github.prominence.openweathermap.api.model.generic.temperature.DailyTemperature;
import com.github.prominence.openweathermap.api.model.generic.wind.BasicWind;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
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
public class WeatherForecast implements WeatherForecastDay, DailyTemperature, BaseAtmosphericPressure, BasicWind, Humidity,
        PrecipitationValues, SunlightStages {
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("dt")
    private OffsetDateTime forecastTime;
    @JsonProperty("temp")
    private TemperatureDailyDetailed temperature;
    @JsonProperty("feels_like")
    private TemperatureDailyBasic feelsLike;
    @JsonProperty("weather")
    private List<WeatherCondition> weatherStates = new ArrayList<>();
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("humidity")
    private int humidityPercentage;
    @JsonProperty("pressure")
    private BigDecimal pressure;
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("clouds")
    private int clouds;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("speed")
    private WindSpeed speed;
    @JsonProperty("deg")
    private Integer directionDegrees;
    @JsonProperty("rain")
    private BigDecimal rain;
    @JsonProperty("snow")
    private BigDecimal snow;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunrise")
    private OffsetDateTime sunriseTime;
    @JsonDeserialize(using = EpochSecondsDeserializer.class)
    @JsonProperty("sunset")
    private OffsetDateTime sunsetTime;

    @Override
    @JsonIgnore
    public DailyTemperature getTemperature() {
        return this;
    }

    @Override
    @JsonIgnore
    public BasicWind getWind() {
        return this;
    }

    @Override
    @JsonIgnore
    public TemperatureValue getMorning() {
        return Optional.ofNullable(temperature).map(TemperatureDailyBasic::getMorning).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getDay() {
        return Optional.ofNullable(temperature).map(TemperatureDailyBasic::getDay).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getEve() {
        return Optional.ofNullable(temperature).map(TemperatureDailyBasic::getEve).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getNight() {
        return Optional.ofNullable(temperature).map(TemperatureDailyBasic::getNight).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getMin() {
        return Optional.ofNullable(temperature).map(TemperatureDailyDetailed::getMin).orElse(null);
    }

    @Override
    @JsonIgnore
    public TemperatureValue getMax() {
        return Optional.ofNullable(temperature).map(TemperatureDailyDetailed::getMax).orElse(null);
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
    public Humidity getHumidity() {
        return this;
    }

    @Override
    @JsonIgnore
    public BaseAtmosphericPressure getAtmosphericPressure() {
        return this;
    }

    @Override
    @JsonIgnore
    public Clouds getCloudCoverage() {
        return new Clouds(this.clouds);
    }

    @Override
    @JsonIgnore
    public PrecipitationValues getPrecipitation() {
        return this;
    }

    @Override
    @JsonIgnore
    public SunlightStages getSunlightStages() {
        return this;
    }
}

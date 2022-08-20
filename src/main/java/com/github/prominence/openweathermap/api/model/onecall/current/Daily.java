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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.EpochSecondsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.RequiredPercentageDeserializer;
import com.github.prominence.openweathermap.api.deserializer.TemperatureValueDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WindSpeedDeserializer;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.TemperatureValue;
import com.github.prominence.openweathermap.api.model.TimeAware;
import com.github.prominence.openweathermap.api.model.WindSpeed;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * The daily weather forecast type.
 */
@Data
public class Daily implements TimeAware {

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
    private TemperatureDailyExtended temperature;
    @JsonProperty("feels_like")
    private TemperatureDaily feelsLike;
    @JsonProperty("pressure")
    private BigDecimal atmosphericPressureSeaLevel;
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("humidity")
    private int humidityPercentage;
    @JsonDeserialize(using = TemperatureValueDeserializer.class)
    @JsonProperty("dew_point")
    private TemperatureValue dewPoint;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("wind_speed")
    private WindSpeed windSpeed;
    @JsonProperty("wind_deg")
    private Integer windDirectionDegrees;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("wind_gust")
    private WindSpeed windSpeedGust;
    @JsonDeserialize(using = RequiredPercentageDeserializer.class)
    @JsonProperty("clouds")
    private int cloudsPercentage;
    @JsonProperty("uvi")
    private BigDecimal uvIndex;
    @JsonProperty("pop")
    private BigDecimal probabilityOfPrecipitation;
    @JsonProperty("rain")
    private BigDecimal rain;
    @JsonProperty("snow")
    private BigDecimal snow;
    @JsonProperty("weather")
    private List<WeatherCondition> weatherStates;

}

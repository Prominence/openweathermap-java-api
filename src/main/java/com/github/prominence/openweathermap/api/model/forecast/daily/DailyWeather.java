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

package com.github.prominence.openweathermap.api.model.forecast.daily;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.TimeAware;
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.precipitation.Humidity;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationForecast;
import com.github.prominence.openweathermap.api.model.generic.pressure.BaseAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.generic.temperature.DailyTemperature;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;

import java.util.List;

/**
 * Interface for weather forecast data used by the SixteenDaysDailyForecast.
 */
public interface DailyWeather extends TimeAware {

    /**
     * The temperature data,
     *
     * @return temp
     */
    @JsonIgnore
    DailyTemperature getTemperature();

    /**
     * The humidity.
     *
     * @return humidity
     */
    @JsonIgnore
    Humidity getHumidity();

    /**
     * The atmospheric pressure.
     *
     * @return pressure
     */
    @JsonIgnore
    BaseAtmosphericPressure getAtmosphericPressure();

    /**
     * The wind conditions.
     *
     * @return wind
     */
    @JsonIgnore
    DetailedWindInfo getWind();

    /**
     * The weather states.
     *
     * @return weather
     */
    List<WeatherCondition> getWeatherStates();

    /**
     * The cloud coverage.
     *
     * @return louds
     */
    @JsonIgnore
    CloudCoverage getCloudCoverage();

    /**
     * The precipitation.
     *
     * @return precipitation
     */
    @JsonIgnore
    PrecipitationForecast getPrecipitation();

    /**
     * The sunlight stages.
     *
     * @return sunlight
     */
    @JsonIgnore
    SunlightStages getSunlightStages();
}
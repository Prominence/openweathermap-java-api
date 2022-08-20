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
import com.github.prominence.openweathermap.api.enums.DayTime;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.generic.TimeAware;
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.precipitation.Humidity;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationForecast;
import com.github.prominence.openweathermap.api.model.generic.pressure.DetailedAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureWithRange;
import com.github.prominence.openweathermap.api.model.generic.visibility.Visibility;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;

import java.util.List;

/**
 * Forecast details for a single hour.
 */
public interface HourlyWeather extends TimeAware {
    /**
     * The temperature forecast.
     *
     * @return temperature
     */
    @JsonIgnore
    TemperatureWithRange getTemperature();

    /**
     * The humidity forecast.
     *
     * @return humidity
     */
    @JsonIgnore
    Humidity getHumidity();

    /**
     * The atmospheric pressure forecast.
     *
     * @return pressure
     */
    @JsonIgnore
    DetailedAtmosphericPressure getAtmosphericPressure();

    /**
     * The wind forecast.
     *
     * @return wind
     */
    DetailedWindInfo getWind();

    /**
     * The weather states.
     *
     * @return states
     */
    List<WeatherCondition> getWeatherStates();

    /**
     * The forecasted cloud cover.
     *
     * @return clouds
     */
    CloudCoverage getClouds();

    /**
     * The precipitation forecast.
     *
     * @return precipitation
     */
    @JsonIgnore
    PrecipitationForecast getHourlyPrecipitation();

    /**
     * The visibility forecast.
     *
     * @return visibility
     */
    Visibility getVisibility();

    /**
     * The part of day.
     *
     * @return part of day
     */
    @JsonIgnore
    DayTime getPartOfDay();
}

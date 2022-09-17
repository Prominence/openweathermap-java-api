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

package com.github.prominence.openweathermap.api.model.forecast.free;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.prominence.openweathermap.api.enums.DayTime;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.model.TimeAware;
import com.github.prominence.openweathermap.api.model.Visibility;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationForecast;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;

import java.util.List;

/**
 * Weather forecasts for 3 hours.
 */
public interface ThreeHourWeather extends TimeAware {
    /**
     * The temperature forecast.
     *
     * @return temperature
     */
    @JsonIgnore
    Temperature getTemperature();

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
    AtmosphericPressure getAtmosphericPressure();

    /**
     * The wind forecast.
     *
     * @return wind
     */
    @JsonIgnore
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
    Clouds getClouds();

    /**
     * The precipitation forecast.
     *
     * @return precipitation
     */
    @JsonIgnore
    PrecipitationForecast getThreeHoursPrecipitation();

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

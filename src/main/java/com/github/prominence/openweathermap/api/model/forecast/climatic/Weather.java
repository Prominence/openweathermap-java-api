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
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.BaseAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.BaseWind;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.TimeAware;

import java.time.OffsetDateTime;
import java.util.List;

public interface Weather extends TimeAware {
    @JsonIgnore
    Temperature getTemperature();

    @JsonIgnore
    Humidity getHumidity();

    @JsonIgnore
    BaseAtmosphericPressure getAtmosphericPressure();

    @JsonIgnore
    BaseWind getWind();

    List<WeatherCondition> getWeatherStates();

    @JsonIgnore
    Clouds getCloudCoverage();

    @JsonIgnore
    DailyPrecipitation getPrecipitation();

    OffsetDateTime getSunriseTime();

    OffsetDateTime getSunsetTime();
}

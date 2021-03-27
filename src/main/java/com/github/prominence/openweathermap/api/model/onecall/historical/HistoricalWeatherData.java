/*
 * Copyright (c) 2021 Alexey Zinchenko
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

import com.github.prominence.openweathermap.api.model.Coordinate;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public class HistoricalWeatherData {
    private Coordinate coordinate;
    private ZoneId timezone;
    private ZoneOffset timezoneOffset;

    private HistoricalWeather historicalWeather;
    private List<HourlyHistorical> hourlyList;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public void setTimezone(ZoneId timezone) {
        this.timezone = timezone;
    }

    public ZoneOffset getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(ZoneOffset timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public HistoricalWeather getHistoricalWeather() {
        return historicalWeather;
    }

    public void setHistoricalWeather(HistoricalWeather historicalWeather) {
        this.historicalWeather = historicalWeather;
    }

    public List<HourlyHistorical> getHourlyList() {
        return hourlyList;
    }

    public void setHourlyList(List<HourlyHistorical> hourlyList) {
        this.hourlyList = hourlyList;
    }
}

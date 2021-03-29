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

package com.github.prominence.openweathermap.api.model.onecall.current;

import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.onecall.Current;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

/**
 * The type Current weather data.
 */
public class CurrentWeatherData {
    private Coordinate coordinate;
    private ZoneId timezone;
    private ZoneOffset timezoneOffset;

    private Current current;
    private List<Minutely> minutelyList;
    private List<Hourly> hourlyList;
    private List<Daily> dailyList;
    private List<Alert> alerts;

    /**
     * Gets coordinate.
     *
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Sets coordinate.
     *
     * @param coordinate the coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Gets timezone.
     *
     * @return the timezone
     */
    public ZoneId getTimezone() {
        return timezone;
    }

    /**
     * Sets timezone.
     *
     * @param timezone the timezone
     */
    public void setTimezone(ZoneId timezone) {
        this.timezone = timezone;
    }

    /**
     * Gets timezone offset.
     *
     * @return the timezone offset
     */
    public ZoneOffset getTimezoneOffset() {
        return timezoneOffset;
    }

    /**
     * Sets timezone offset.
     *
     * @param timezoneOffset the timezone offset
     */
    public void setTimezoneOffset(ZoneOffset timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    /**
     * Gets current.
     *
     * @return the current
     */
    public Current getCurrent() {
        return current;
    }

    /**
     * Sets current.
     *
     * @param current the current
     */
    public void setCurrent(Current current) {
        this.current = current;
    }

    /**
     * Gets minutely list.
     *
     * @return the minutely list
     */
    public List<Minutely> getMinutelyList() {
        return minutelyList;
    }

    /**
     * Sets minutely list.
     *
     * @param minutelyList the minutely list
     */
    public void setMinutelyList(List<Minutely> minutelyList) {
        this.minutelyList = minutelyList;
    }

    /**
     * Gets hourly list.
     *
     * @return the hourly list
     */
    public List<Hourly> getHourlyList() {
        return hourlyList;
    }

    /**
     * Sets hourly list.
     *
     * @param hourlyList the hourly list
     */
    public void setHourlyList(List<Hourly> hourlyList) {
        this.hourlyList = hourlyList;
    }

    /**
     * Gets daily list.
     *
     * @return the daily list
     */
    public List<Daily> getDailyList() {
        return dailyList;
    }

    /**
     * Sets daily list.
     *
     * @param dailyList the daily list
     */
    public void setDailyList(List<Daily> dailyList) {
        this.dailyList = dailyList;
    }

    /**
     * Gets alerts.
     *
     * @return the alerts
     */
    public List<Alert> getAlerts() {
        return alerts;
    }

    /**
     * Sets alerts.
     *
     * @param alerts the alerts
     */
    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentWeatherData that = (CurrentWeatherData) o;
        return Objects.equals(coordinate, that.coordinate) &&
                Objects.equals(timezone, that.timezone) &&
                Objects.equals(timezoneOffset, that.timezoneOffset) &&
                Objects.equals(current, that.current) &&
                Objects.equals(minutelyList, that.minutelyList) &&
                Objects.equals(hourlyList, that.hourlyList) &&
                Objects.equals(dailyList, that.dailyList) &&
                Objects.equals(alerts, that.alerts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, timezone, timezoneOffset, current, minutelyList, hourlyList, dailyList, alerts);
    }

    @Override
    public String toString() {
        return "Current weather data for " + coordinate + ".";
    }
}

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

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.onecall.BaseMeasurement;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherDataUnitTest {
    @Test
    public void getCoordinates() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final Coordinates coordinates = new Coordinates(11.2, 43.2);
        currentWeatherData.setLatitude(coordinates.getLatitude());
        currentWeatherData.setLongitude(coordinates.getLongitude());

        assertEquals(coordinates, currentWeatherData.getCoordinates());
    }

    @Test
    public void getTimezone() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final ZoneId timeZone = ZoneId.of("GMT");
        currentWeatherData.setTimezone(timeZone);

        assertEquals(timeZone, currentWeatherData.getTimezone());
    }

    @Test
    public void getTimezoneOffset() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final ZoneOffset offset = ZoneOffset.UTC;
        currentWeatherData.setTimezoneOffset(offset);

        assertEquals(offset, currentWeatherData.getTimezoneOffset());
    }

    @Test
    public void getCurrent() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final BaseMeasurement current = new BaseMeasurement();
        currentWeatherData.setCurrent(current);

        assertEquals(current, currentWeatherData.getCurrent());
    }

    @Test
    public void getMinutelyList() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final List<Minutely> minutelyList = new ArrayList<>();
        currentWeatherData.setMinutelyList(minutelyList);

        assertEquals(minutelyList, currentWeatherData.getMinutelyList());
    }

    @Test
    public void getHourlyList() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final List<Hourly> hourlyList = new ArrayList<>();
        currentWeatherData.setHourlyList(hourlyList);

        assertEquals(hourlyList, currentWeatherData.getHourlyList());
    }

    @Test
    public void getDailyList() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final List<Daily> dailyList = new ArrayList<>();
        currentWeatherData.setDailyList(dailyList);

        assertEquals(dailyList, currentWeatherData.getDailyList());
    }

    @Test
    public void getAlerts() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final List<Alert> alerts = new ArrayList<>();
        currentWeatherData.setAlerts(alerts);

        assertEquals(alerts, currentWeatherData.getAlerts());
    }

}

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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CurrentWeatherDataUnitTest {
    @Test
    public void getCoordinates() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        final Coordinates coordinates = new Coordinates(11.2, 43.2);
        currentWeatherData.setCoordinates(coordinates);

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

    @Test
    public void getEquals() {
        final CurrentWeather first = new CurrentWeather();
        final CurrentWeather second = new CurrentWeather();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final Coordinates coordinates = new Coordinates(11, 12);
        final ZoneId timeZone = ZoneId.of("GMT");
        final ZoneOffset offset = ZoneOffset.UTC;
        final BaseMeasurement current = new BaseMeasurement();
        final List<Minutely> minutelyList = new ArrayList<>();
        final List<Hourly> hourlyList = new ArrayList<>();
        final List<Daily> dailyList = new ArrayList<>();
        final List<Alert> alerts = new ArrayList<>();

        assertEquals(first, second);

        first.setCoordinates(coordinates);

        assertNotEquals(first, second);

        second.setCoordinates(coordinates);

        assertEquals(first, second);

        first.setTimezone(timeZone);

        assertNotEquals(first, second);

        second.setTimezone(timeZone);

        assertEquals(first, second);

        first.setTimezoneOffset(offset);

        assertNotEquals(first, second);

        second.setTimezoneOffset(offset);

        assertEquals(first, second);

        first.setCurrent(current);

        assertNotEquals(first, second);

        second.setCurrent(current);

        assertEquals(first, second);

        first.setMinutelyList(minutelyList);

        assertNotEquals(first, second);

        second.setMinutelyList(minutelyList);

        assertEquals(first, second);

        first.setHourlyList(hourlyList);

        assertNotEquals(first, second);

        second.setHourlyList(hourlyList);

        assertEquals(first, second);

        first.setDailyList(dailyList);

        assertNotEquals(first, second);

        second.setDailyList(dailyList);

        assertEquals(first, second);

        first.setAlerts(alerts);

        assertNotEquals(first, second);

        second.setAlerts(alerts);

        assertEquals(first, second);
    }

    @Test
    public void getHashCode() {
        final CurrentWeather first = new CurrentWeather();
        final CurrentWeather second = new CurrentWeather();

        assertEquals(first.hashCode(), second.hashCode());

        first.setCoordinates(new Coordinates(11, 42));

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void getToString() {
        final CurrentWeather currentWeatherData = new CurrentWeather();
        currentWeatherData.setCoordinates(new Coordinates(32, 22));

        assertNotNull(currentWeatherData.toString());
        assertNotEquals("", currentWeatherData.toString());
    }
}

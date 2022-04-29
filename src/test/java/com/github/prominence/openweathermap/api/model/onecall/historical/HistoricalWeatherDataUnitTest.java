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

import com.github.prominence.openweathermap.api.model.Coordinates;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricalWeatherDataUnitTest {
    @Test
    public void getCoordinate() {
        final HistoricalWeatherData historicalWeatherData = new HistoricalWeatherData();
        final Coordinates coordinates = Coordinates.of(11.2, 43.2);
        historicalWeatherData.setCoordinate(coordinates);

        assertEquals(coordinates, historicalWeatherData.getCoordinate());
    }

    @Test
    public void getTimezone() {
        final HistoricalWeatherData historicalWeatherData = new HistoricalWeatherData();
        final ZoneId timeZone = ZoneId.of("GMT");
        historicalWeatherData.setTimezone(timeZone);

        assertEquals(timeZone, historicalWeatherData.getTimezone());
    }

    @Test
    public void getTimezoneOffset() {
        final HistoricalWeatherData historicalWeatherData = new HistoricalWeatherData();
        final ZoneOffset offset = ZoneOffset.UTC;
        historicalWeatherData.setTimezoneOffset(offset);

        assertEquals(offset, historicalWeatherData.getTimezoneOffset());
    }

    @Test
    public void getHistoricalWeather() {
        final HistoricalWeatherData historicalWeatherData = new HistoricalWeatherData();
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        historicalWeatherData.setHistoricalWeather(historicalWeather);

        assertEquals(historicalWeather, historicalWeatherData.getHistoricalWeather());
    }

    @Test
    public void getHourlyList() {
        final HistoricalWeatherData historicalWeatherData = new HistoricalWeatherData();
        final List<HourlyHistorical> hourlyList = new ArrayList<>();
        historicalWeatherData.setHourlyList(hourlyList);

        assertEquals(hourlyList, historicalWeatherData.getHourlyList());
    }

    @Test
    public void getEquals() {
        final HistoricalWeatherData first = new HistoricalWeatherData();
        final HistoricalWeatherData second = new HistoricalWeatherData();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final Coordinates coordinates = Coordinates.of(11, 12);
        final ZoneId timeZone = ZoneId.of("GMT");
        final ZoneOffset offset = ZoneOffset.UTC;
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final List<HourlyHistorical> hourlyList = new ArrayList<>();

        assertEquals(first, second);

        first.setCoordinate(coordinates);

        assertNotEquals(first, second);

        second.setCoordinate(coordinates);

        assertEquals(first, second);

        first.setTimezone(timeZone);

        assertNotEquals(first, second);

        second.setTimezone(timeZone);

        assertEquals(first, second);

        first.setTimezoneOffset(offset);

        assertNotEquals(first, second);

        second.setTimezoneOffset(offset);

        assertEquals(first, second);

        first.setHistoricalWeather(historicalWeather);

        assertNotEquals(first, second);

        second.setHistoricalWeather(historicalWeather);

        assertEquals(first, second);

        first.setHourlyList(hourlyList);

        assertNotEquals(first, second);

        second.setHourlyList(hourlyList);

        assertEquals(first, second);
    }

    @Test
    public void getHashCode() {
        final HistoricalWeatherData first = new HistoricalWeatherData();
        final HistoricalWeatherData second = new HistoricalWeatherData();

        assertEquals(first.hashCode(), second.hashCode());

        first.setCoordinate(Coordinates.of(11, 42));

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void getToString() {
        final HistoricalWeatherData historicalWeatherData = new HistoricalWeatherData();
        historicalWeatherData.setCoordinate(Coordinates.of(32, 22));

        assertNotNull(historicalWeatherData.toString());
        assertNotEquals("", historicalWeatherData.toString());
    }
}
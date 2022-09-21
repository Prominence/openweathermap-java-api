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

import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import com.github.prominence.openweathermap.api.model.onecall.Measurement;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class CurrentWeatherDataUnitTest {
    @Test
    public void getCoordinates() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final Coordinates coordinates = new Coordinates(11.2, 43.2);
        currentWeatherData.setLatitude(coordinates.getLatitude());
        currentWeatherData.setLongitude(coordinates.getLongitude());

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertEquals(coordinates, underTest.getCoordinates());
    }

    @Test
    public void getTimezone() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final ZoneId timeZone = ZoneId.of("GMT");
        currentWeatherData.setTimezone(timeZone);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertEquals(timeZone, underTest.getTimezone());
    }

    @Test
    public void getTimezoneOffset() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final ZoneOffset offset = ZoneOffset.UTC;
        currentWeatherData.setTimezoneOffset(offset);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertEquals(offset, underTest.getTimezoneOffset());
    }

    @Test
    public void getCurrent() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final Measurement current = new Measurement();
        currentWeatherData.setCurrent(current);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertEquals(current, underTest.getCurrentWeather());
    }

    @Test
    public void getMinutelyList() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final List<Minutely> minutelyList = new ArrayList<>();
        minutelyList.add(new Minutely());
        currentWeatherData.setMinutelyList(minutelyList);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertIterableEquals(minutelyList, underTest.getMinutelyForecast());
    }

    @Test
    public void getHourlyList() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final List<Hourly> hourlyList = new ArrayList<>();
        hourlyList.add(new Hourly());
        currentWeatherData.setHourlyList(hourlyList);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertIterableEquals(hourlyList, underTest.getHourlyForecast());
    }

    @Test
    public void getDailyList() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final List<Daily> dailyList = new ArrayList<>();
        dailyList.add(new Daily());
        currentWeatherData.setDailyList(dailyList);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertIterableEquals(dailyList, underTest.getDailyForecast());
    }

    @Test
    public void getAlerts() {
        final OneCallCurrentForecastModel currentWeatherData = new OneCallCurrentForecastModel();
        final List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert());
        currentWeatherData.setAlerts(alerts);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallCurrentForecast underTest = currentWeatherData;

        assertIterableEquals(alerts, underTest.getAlerts());
    }

}

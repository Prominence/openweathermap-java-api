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

import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.onecall.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.onecall.Rain;
import com.github.prominence.openweathermap.api.model.onecall.Snow;
import com.github.prominence.openweathermap.api.model.onecall.Temperature;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HourlyHistoricalUnitTest {
    @Test
    public void getForecastTime() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final LocalDateTime now = LocalDateTime.now();
        hourlyHistorical.setForecastTime(now);

        assertEquals(now, hourlyHistorical.getForecastTime());
    }

    @Test
    public void getWeatherState() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        hourlyHistorical.setWeatherStates(List.of(weatherState));

        assertEquals(weatherState, hourlyHistorical.getWeatherStates().get(0));
    }

    @Test
    public void getTemperature() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final Temperature temperature = Temperature.withValue(10, "K");
        hourlyHistorical.setTemperature(temperature);

        assertEquals(temperature, hourlyHistorical.getTemperature());
    }

    @Test
    public void getAtmosphericPressure() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(100);
        hourlyHistorical.setAtmosphericPressure(atmosphericPressure);

        assertEquals(atmosphericPressure, hourlyHistorical.getAtmosphericPressure());
    }

    @Test
    public void getHumidity() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final Humidity humidity = Humidity.withValue((byte) 20);
        hourlyHistorical.setHumidity(humidity);

        assertEquals(humidity, hourlyHistorical.getHumidity());
    }

    @Test
    public void getClouds() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final Clouds clouds = Clouds.withValue((byte) 60);
        hourlyHistorical.setClouds(clouds);

        assertEquals(clouds, hourlyHistorical.getClouds());
    }

    @Test
    public void getVisibilityInMetres() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final double vim = 40.0;
        hourlyHistorical.setVisibilityInMetres(vim);

        assertEquals(vim, hourlyHistorical.getVisibilityInMetres(), 0.00001);
    }

    @Test
    public void getWind() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final Wind wind = Wind.withValue(200, "m/s");
        hourlyHistorical.setWind(wind);

        assertEquals(wind, hourlyHistorical.getWind());
    }

    @Test
    public void getRain() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final Rain rain = Rain.withOneHourLevelValue(100);
        hourlyHistorical.setRain(rain);

        assertEquals(rain, hourlyHistorical.getRain());
    }

    @Test
    public void getSnow() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        final Snow snow = Snow.withOneHourLevelValue(29);
        hourlyHistorical.setSnow(snow);

        assertEquals(snow, hourlyHistorical.getSnow());
    }

    @Test
    public void testEquals() {
        final HourlyHistorical first = new HourlyHistorical();
        final HourlyHistorical second = new HourlyHistorical();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final LocalDateTime forecastTime = LocalDateTime.now();
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        final Temperature temperature = Temperature.withValue(10, "K");
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(200);
        final Humidity humidity = Humidity.withValue((byte) 13);
        final Clouds clouds = Clouds.withValue((byte) 20);
        final double vim = 20;
        final Wind wind = Wind.withValue(20, "m/s");
        final Rain rain = Rain.withOneHourLevelValue(40);
        final Snow snow = Snow.withOneHourLevelValue(11);

        assertEquals(first, second);

        first.setForecastTime(forecastTime);

        assertNotEquals(first, second);

        second.setForecastTime(forecastTime);

        assertEquals(first, second);

        first.setWeatherStates(List.of(weatherState));

        assertNotEquals(first, second);

        second.setWeatherStates(List.of(weatherState));

        assertEquals(first, second);

        first.setTemperature(temperature);

        assertNotEquals(first, second);

        second.setTemperature(temperature);

        assertEquals(first, second);

        first.setAtmosphericPressure(atmosphericPressure);

        assertNotEquals(first, second);

        second.setAtmosphericPressure(atmosphericPressure);

        assertEquals(first, second);

        first.setHumidity(humidity);

        assertNotEquals(first, second);

        second.setHumidity(humidity);

        assertEquals(first, second);

        first.setClouds(clouds);

        assertNotEquals(first, second);

        second.setClouds(clouds);

        assertEquals(first, second);

        first.setVisibilityInMetres(vim);

        assertNotEquals(first, second);

        second.setVisibilityInMetres(vim);

        assertEquals(first, second);

        first.setWind(wind);

        assertNotEquals(first, second);

        second.setWind(wind);

        assertEquals(first, second);

        first.setRain(rain);

        assertNotEquals(first, second);

        second.setRain(rain);

        assertEquals(first, second);

        first.setSnow(snow);

        assertNotEquals(first, second);

        second.setSnow(snow);

        assertEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final HourlyHistorical first = new HourlyHistorical();
        final HourlyHistorical second = new HourlyHistorical();

        assertEquals(first.hashCode(), second.hashCode());

        first.setForecastTime(LocalDateTime.now());

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final HourlyHistorical hourlyHistorical = new HourlyHistorical();
        hourlyHistorical.setForecastTime(LocalDateTime.now());

        assertNotNull(hourlyHistorical.toString());
        assertNotEquals("", hourlyHistorical.toString());
    }
}
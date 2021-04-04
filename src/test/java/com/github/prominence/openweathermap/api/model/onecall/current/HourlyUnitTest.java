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

import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.onecall.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class HourlyUnitTest {
    @Test
    public void getForecastTime() {
        final Hourly hourly = new Hourly();
        final LocalDateTime now = LocalDateTime.now();
        hourly.setForecastTime(now);

        assertEquals(now, hourly.getForecastTime());
    }

    @Test
    public void getWeatherState() {
        final Hourly hourly = new Hourly();
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        hourly.setWeatherState(weatherState);

        assertEquals(weatherState, hourly.getWeatherState());
    }

    @Test
    public void getTemperature() {
        final Hourly hourly = new Hourly();
        final Temperature temperature = Temperature.withValue(10, "K");
        hourly.setTemperature(temperature);

        assertEquals(temperature, hourly.getTemperature());
    }

    @Test
    public void getAtmosphericPressure() {
        final Hourly hourly = new Hourly();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(100);
        hourly.setAtmosphericPressure(atmosphericPressure);

        assertEquals(atmosphericPressure, hourly.getAtmosphericPressure());
    }

    @Test
    public void getHumidity() {
        final Hourly hourly = new Hourly();
        final Humidity humidity = Humidity.withValue((byte) 20);
        hourly.setHumidity(humidity);

        assertEquals(humidity, hourly.getHumidity());
    }

    @Test
    public void getUvIndex() {
        final Hourly hourly = new Hourly();
        final double uvIndex = 20.0;
        hourly.setUvIndex(uvIndex);

        assertEquals(uvIndex, hourly.getUvIndex(), 0.00001);
    }

    @Test
    public void getClouds() {
        final Hourly hourly = new Hourly();
        final Clouds clouds = Clouds.withValue((byte) 60);
        hourly.setClouds(clouds);

        assertEquals(clouds, hourly.getClouds());
    }

    @Test
    public void getVisibilityInMetres() {
        final Hourly hourly = new Hourly();
        final double vim = 40.0;
        hourly.setVisibilityInMetres(vim);

        assertEquals(vim, hourly.getVisibilityInMetres(), 0.00001);
    }

    @Test
    public void getWind() {
        final Hourly hourly = new Hourly();
        final Wind wind = Wind.withValue(200, "m/s");
        hourly.setWind(wind);

        assertEquals(wind, hourly.getWind());
    }

    @Test
    public void getProbabilityOfPrecipitation() {
        final Hourly hourly = new Hourly();
        final double pop = 42.0;
        hourly.setProbabilityOfPrecipitation(pop);

        assertEquals(pop, hourly.getProbabilityOfPrecipitation(), 0.00001);
    }

    @Test
    public void getRain() {
        final Hourly hourly = new Hourly();
        final Rain rain = Rain.withOneHourLevelValue(100);
        hourly.setRain(rain);

        assertEquals(rain, hourly.getRain());
    }

    @Test
    public void getSnow() {
        final Hourly hourly = new Hourly();
        final Snow snow = Snow.withOneHourLevelValue(29);
        hourly.setSnow(snow);

        assertEquals(snow, hourly.getSnow());
    }

    @Test
    public void testEquals() {
        final Hourly first = new Hourly();
        final Hourly second = new Hourly();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final LocalDateTime forecastTime = LocalDateTime.now();
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        final Temperature temperature = Temperature.withValue(10, "K");
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(200);
        final Humidity humidity = Humidity.withValue((byte) 13);
        final double uvIndex = 10.0;
        final Clouds clouds = Clouds.withValue((byte) 20);
        final double vim = 20;
        final Wind wind = Wind.withValue(20, "m/s");
        final double pop = 30;
        final Rain rain = Rain.withOneHourLevelValue(40);
        final Snow snow = Snow.withOneHourLevelValue(11);

        assertEquals(first, second);

        first.setForecastTime(forecastTime);

        assertNotEquals(first, second);

        second.setForecastTime(forecastTime);

        assertEquals(first, second);

        first.setWeatherState(weatherState);

        assertNotEquals(first, second);

        second.setWeatherState(weatherState);

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

        first.setUvIndex(uvIndex);

        assertNotEquals(first, second);

        second.setUvIndex(uvIndex);

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

        first.setProbabilityOfPrecipitation(pop);

        assertNotEquals(first, second);

        second.setProbabilityOfPrecipitation(pop);

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
        final Hourly first = new Hourly();
        final Hourly second = new Hourly();

        assertEquals(first.hashCode(), second.hashCode());

        first.setForecastTime(LocalDateTime.now());

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final Hourly hourly = new Hourly();
        hourly.setForecastTime(LocalDateTime.now());

        assertNotNull(hourly.toString());
        assertNotEquals("", hourly.toString());
    }
}
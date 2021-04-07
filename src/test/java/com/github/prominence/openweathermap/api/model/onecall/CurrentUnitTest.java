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

package com.github.prominence.openweathermap.api.model.onecall;

import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.WeatherState;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentUnitTest {
    @Test
    public void getForecastTime() {
        final Current current = new Current();
        final LocalDateTime now = LocalDateTime.now();
        current.setForecastTime(now);

        assertEquals(now, current.getForecastTime());
    }

    @Test
    public void getSunriseTime() {
        final Current current = new Current();
        final LocalDateTime now = LocalDateTime.now();
        current.setSunriseTime(now);

        assertEquals(now, current.getSunriseTime());
    }

    @Test
    public void getSunsetTime() {
        final Current current = new Current();
        final LocalDateTime now = LocalDateTime.now();
        current.setSunsetTime(now);

        assertEquals(now, current.getSunsetTime());
    }

    @Test
    public void getWeatherState() {
        final Current current = new Current();
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        current.setWeatherState(weatherState);

        assertEquals(weatherState, current.getWeatherState());
    }

    @Test
    public void getTemperature() {
        final Current current = new Current();
        final Temperature temperature = Temperature.withValue(10, "K");

        current.setTemperature(temperature);

        assertEquals(temperature, current.getTemperature());
    }

    @Test
    public void getAtmosphericPressure() {
        final Current current = new Current();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.3);
        current.setAtmosphericPressure(atmosphericPressure);

        assertEquals(atmosphericPressure, current.getAtmosphericPressure());
    }

    @Test
    public void getHumidity() {
        final Current current = new Current();
        final Humidity humidity = Humidity.withValue((byte) 10);
        current.setHumidity(humidity);

        assertEquals(humidity, current.getHumidity());
    }

    @Test
    public void getWind() {
        final Current current = new Current();
        final Wind wind = Wind.withValue(13.2, "m/s");
        current.setWind(wind);

        assertEquals(wind, current.getWind());
    }

    @Test
    public void getClouds() {
        final Current current = new Current();
        final Clouds clouds = Clouds.withValue((byte) 25);
        current.setClouds(clouds);

        assertEquals(clouds, current.getClouds());
    }

    @Test
    public void getUvIndex() {
        final Current current = new Current();
        final double uvIndex = 22.4;
        current.setUvIndex(uvIndex);

        assertEquals(uvIndex, current.getUvIndex(), 0.00001);

        current.setUvIndex(null);

        assertNull(current.getUvIndex());
    }

    @Test
    public void getIllegalUvIndexValue() {
        final Current current = new Current();

        assertThrows(IllegalArgumentException.class, () -> current.setUvIndex(-1.2));
    }

    @Test
    public void getProbabilityOfPrecipitation() {
        final Current current = new Current();
        final double vim = 120;
        current.setVisibilityInMetres(vim);

        assertEquals(vim, current.getVisibilityInMetres(), 0.00001);

        current.setVisibilityInMetres(null);

        assertNull(current.getVisibilityInMetres());
    }

    @Test
    public void getIllegalProbabilityOfPrecipitationValue_negative() {
        final Current current = new Current();

        assertThrows(IllegalArgumentException.class, () -> current.setVisibilityInMetres(-20.0));
    }

    @Test
    public void getRain() {
        final Current current = new Current();
        final Rain rain = Rain.withOneHourLevelValue(20.2);
        current.setRain(rain);

        assertEquals(rain, current.getRain());
    }

    @Test
    public void getSnow() {
        final Current current = new Current();
        final Snow snow = Snow.withOneHourLevelValue(25.0);
        current.setSnow(snow);

        assertEquals(snow, current.getSnow());
    }

    @Test
    public void getEquals() {
        final LocalDateTime now = LocalDateTime.now();
        final Current first = new Current();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final Current second = new Current();

        assertEquals(first, second);

        first.setForecastTime(now);

        assertNotEquals(first, second);

        second.setForecastTime(now);

        assertEquals(first, second);

        first.setSunriseTime(now);

        assertNotEquals(first, second);

        second.setSunriseTime(now);

        assertEquals(first, second);

        first.setSunsetTime(now);

        assertNotEquals(first, second);

        second.setSunsetTime(now);

        assertEquals(first, second);

        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        first.setWeatherState(weatherState);

        assertNotEquals(first, second);

        second.setWeatherState(weatherState);

        assertEquals(first, second);

        final Temperature temperature = Temperature.withValue(10, "K");

        first.setTemperature(temperature);

        assertNotEquals(first, second);

        second.setTemperature(temperature);

        assertEquals(first, second);

        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.3);

        first.setAtmosphericPressure(atmosphericPressure);

        assertNotEquals(first, second);

        second.setAtmosphericPressure(atmosphericPressure);

        assertEquals(first, second);

        final Humidity humidity = Humidity.withValue((byte) 10);

        first.setHumidity(humidity);

        assertNotEquals(first, second);

        second.setHumidity(humidity);

        assertEquals(first, second);

        final Wind wind = Wind.withValue(13.2, "m/s");

        first.setWind(wind);

        assertNotEquals(first, second);

        second.setWind(wind);

        assertEquals(first, second);

        final Clouds clouds = Clouds.withValue((byte) 25);

        first.setClouds(clouds);

        assertNotEquals(first, second);

        second.setClouds(clouds);

        assertEquals(first, second);

        final double uvIndex = 22.4;

        first.setUvIndex(uvIndex);

        assertNotEquals(first, second);

        second.setUvIndex(uvIndex);

        assertEquals(first, second);

        final double vim = 250;

        first.setVisibilityInMetres(vim);

        assertNotEquals(first, second);

        second.setVisibilityInMetres(vim);

        assertEquals(first, second);

        final Rain rain = Rain.withOneHourLevelValue(20.2);

        first.setRain(rain);

        assertNotEquals(first, second);

        second.setRain(rain);

        assertEquals(first, second);

        final Snow snow = Snow.withOneHourLevelValue(25.0);

        first.setSnow(snow);

        assertNotEquals(first, second);

        second.setSnow(snow);

        assertEquals(first, second);
    }

    @Test
    public void getHashCode() {
        final Current first = new Current();
        final Current second = new Current();

        assertEquals(first.hashCode(), second.hashCode());

        first.setForecastTime(LocalDateTime.now());

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void getToString() {
        final LocalDateTime now = LocalDateTime.now();
        final Current current = new Current();

        current.setForecastTime(now);

        assertNotNull(current.toString());
        assertNotEquals("", current.toString());
    }
}
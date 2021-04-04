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
import com.github.prominence.openweathermap.api.model.onecall.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class HistoricalWeatherUnitTest {
    @Test
    public void getForecastTime() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final LocalDateTime now = LocalDateTime.now();
        historicalWeather.setForecastTime(now);

        assertEquals(now, historicalWeather.getForecastTime());
    }

    @Test
    public void getSunriseTime() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final LocalDateTime now = LocalDateTime.now();
        historicalWeather.setSunriseTime(now);

        assertEquals(now, historicalWeather.getSunriseTime());
    }

    @Test
    public void getSunsetTime() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final LocalDateTime now = LocalDateTime.now();
        historicalWeather.setSunsetTime(now);

        assertEquals(now, historicalWeather.getSunsetTime());
    }

    @Test
    public void getWeatherState() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        historicalWeather.setWeatherState(weatherState);

        assertEquals(weatherState, historicalWeather.getWeatherState());
    }

    @Test
    public void getTemperature() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final Temperature temperature = Temperature.withValue(10, "K");

        historicalWeather.setTemperature(temperature);

        assertEquals(temperature, historicalWeather.getTemperature());
    }

    @Test
    public void getAtmosphericPressure() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.3);
        historicalWeather.setAtmosphericPressure(atmosphericPressure);

        assertEquals(atmosphericPressure, historicalWeather.getAtmosphericPressure());
    }

    @Test
    public void getHumidity() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final Humidity humidity = Humidity.withValue((byte) 10);
        historicalWeather.setHumidity(humidity);

        assertEquals(humidity, historicalWeather.getHumidity());
    }

    @Test
    public void getWind() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final Wind wind = Wind.withValue(13.2, "m/s");
        historicalWeather.setWind(wind);

        assertEquals(wind, historicalWeather.getWind());
    }

    @Test
    public void getClouds() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final Clouds clouds = Clouds.withValue((byte) 25);
        historicalWeather.setClouds(clouds);

        assertEquals(clouds, historicalWeather.getClouds());
    }

    @Test
    public void getUvIndex() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final double uvIndex = 22.4;
        historicalWeather.setUvIndex(uvIndex);

        assertEquals(uvIndex, historicalWeather.getUvIndex(), 0.00001);

        historicalWeather.setUvIndex(null);

        assertNull(historicalWeather.getUvIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getIllegalUvIndexValue() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        historicalWeather.setUvIndex(-1.2);
    }

    @Test
    public void getProbabilityOfPrecipitation() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final double vim = 120;
        historicalWeather.setVisibilityInMetres(vim);

        assertEquals(vim, historicalWeather.getVisibilityInMetres(), 0.00001);

        historicalWeather.setVisibilityInMetres(null);

        assertNull(historicalWeather.getVisibilityInMetres());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getIllegalProbabilityOfPrecipitationValue_negative() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        historicalWeather.setVisibilityInMetres(-20.0);
    }

    @Test
    public void getRain() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final Rain rain = Rain.withOneHourLevelValue(20.2);
        historicalWeather.setRain(rain);

        assertEquals(rain, historicalWeather.getRain());
    }

    @Test
    public void getSnow() {
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        final Snow snow = Snow.withOneHourLevelValue(25.0);
        historicalWeather.setSnow(snow);

        assertEquals(snow, historicalWeather.getSnow());
    }

    @Test
    public void getEquals() {
        final LocalDateTime now = LocalDateTime.now();
        final HistoricalWeather first = new HistoricalWeather();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final HistoricalWeather second = new HistoricalWeather();

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
        final HistoricalWeather first = new HistoricalWeather();
        final HistoricalWeather second = new HistoricalWeather();

        assertEquals(first.hashCode(), second.hashCode());

        first.setForecastTime(LocalDateTime.now());

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void getToString() {
        final LocalDateTime now = LocalDateTime.now();
        final HistoricalWeather historicalWeather = new HistoricalWeather();

        historicalWeather.setForecastTime(now);

        assertNotNull(historicalWeather.toString());
        assertNotEquals("", historicalWeather.toString());
    }
}
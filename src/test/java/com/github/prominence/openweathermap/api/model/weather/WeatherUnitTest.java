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

package com.github.prominence.openweathermap.api.model.weather;

import com.github.prominence.openweathermap.api.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherUnitTest {
    @Test
    public void whenSetRequestedOn_thenValueIsSet() {
        final Weather weather = new Weather();
        final LocalDateTime now = LocalDateTime.now();
        weather.setCalculationTime(now);

        assertEquals(now, weather.getCalculationTime());
    }

    @Test
    public void whenSetTemperature_thenValueIsSet() {
        final Weather weather = new Weather();
        final Temperature temperature = Temperature.withValue(22.3, "a");
        weather.setTemperature(temperature);

        assertEquals(temperature, weather.getTemperature());
    }

    @Test
    public void whenSetPressure_thenValueIsSet() {
        final Weather weather = new Weather();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.2);
        weather.setAtmosphericPressure(atmosphericPressure);

        assertEquals(atmosphericPressure, weather.getAtmosphericPressure());
    }

    @Test
    public void whenSetHumidity_thenValueIsSet() {
        final Weather weather = new Weather();
        final Humidity humidity = Humidity.withValue((byte) 44);
        weather.setHumidity(humidity);

        assertEquals(humidity, weather.getHumidity());
    }

    @Test
    public void whenSetWind_thenValueIsSet() {
        final Weather weather = new Weather();
        final Wind wind = Wind.withValue(22.2, "a");
        weather.setWind(wind);

        assertEquals(wind, weather.getWind());
    }

    @Test
    public void whenSetRain_thenValueIsSet() {
        final Weather weather = new Weather();
        final Rain rain = Rain.withValues(0, 0);
        weather.setRain(rain);

        assertEquals(rain, weather.getRain());
    }

    @Test
    public void whenSetSnow_thenValueIsSet() {
        final Weather weather = new Weather();
        final Snow snow = Snow.withValues(0, 0);
        weather.setSnow(snow);

        assertEquals(snow, weather.getSnow());
    }

    @Test
    public void whenSetClouds_thenValueIsSet() {
        final Weather weather = new Weather();
        final Clouds clouds = Clouds.withValue((byte) 33);
        weather.setClouds(clouds);

        assertEquals(clouds, weather.getClouds());
    }

    @Test
    public void whenSetLocation_thenValueIsSet() {
        final Weather weather = new Weather();
        final Location location = Location.withValues(22, "asd");
        weather.setLocation(location);

        assertEquals(location, weather.getLocation());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Weather weather = new Weather();
        final Location location = Location.withValues(12312, "asd");
        final Temperature temperature = Temperature.withValue(33.2, "asd");
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(44.4);
        final Clouds clouds = Clouds.withValue((byte) 55);
        final Rain rain = Rain.withOneHourLevelValue(33.2);
        final Snow snow = Snow.withOneHourLevelValue(33.1);

        assertEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setLocation(location);
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        location.setCountryCode("3d");
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setTemperature(temperature);
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setAtmosphericPressure(atmosphericPressure);
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setClouds(clouds);
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setRain(rain);
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setRain(Rain.withThreeHourLevelValue(33));
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setSnow(snow);
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());

        weather.setSnow(Snow.withThreeHourLevelValue(44));
        assertNotEquals("", weather.toString());
        assertNotNull(weather.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Weather one = new Weather();
        final Weather two = new Weather();

        assertEquals(one.hashCode(), two.hashCode());

        two.setCalculationTime(LocalDateTime.now());

        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Weather one = new Weather();
        final Weather two = new Weather();

        assertEquals(one, one);
        assertNotEquals(one, new Object());
        assertEquals(one, two);

        final LocalDateTime now = LocalDateTime.now();
        one.setCalculationTime(now);

        assertNotEquals(one, two);

        two.setCalculationTime(now);

        assertEquals(one, two);

        final Temperature temperature = Temperature.withValue(33.2, "as");
        one.setTemperature(temperature);

        assertNotEquals(one, two);

        two.setTemperature(temperature);

        assertEquals(one, two);

        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        one.setWeatherState(weatherState);

        assertNotEquals(one, two);

        two.setWeatherState(weatherState);

        assertEquals(one, two);

        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.33);
        one.setAtmosphericPressure(atmosphericPressure);

        assertNotEquals(one, two);

        two.setAtmosphericPressure(atmosphericPressure);

        assertEquals(one, two);

        final Humidity humidity = Humidity.withValue((byte) 33);
        one.setHumidity(humidity);

        assertNotEquals(one, two);

        two.setHumidity(humidity);

        assertEquals(one, two);

        final Wind wind = Wind.withValue(33.6, "asd");
        one.setWind(wind);

        assertNotEquals(one, two);

        two.setWind(wind);

        assertEquals(one, two);

        final Rain rain = Rain.withValues(0, 0);
        one.setRain(rain);

        assertNotEquals(one, two);

        two.setRain(rain);

        assertEquals(one, two);

        final Snow snow = Snow.withValues(0, 0);
        one.setSnow(snow);

        assertNotEquals(one, two);

        two.setSnow(snow);

        assertEquals(one, two);

        final Clouds clouds = Clouds.withValue((byte) 33);
        one.setClouds(clouds);

        assertNotEquals(one, two);

        two.setClouds(clouds);

        assertEquals(one, two);

        final Location location = Location.withValues(231, "asda");
        one.setLocation(location);

        assertNotEquals(one, two);

        two.setLocation(location);

        assertEquals(one, two);
    }
}

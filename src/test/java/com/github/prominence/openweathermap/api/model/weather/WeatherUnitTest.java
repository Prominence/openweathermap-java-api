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

import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Location;
import com.github.prominence.openweathermap.api.model.Pressure;
import com.github.prominence.openweathermap.api.model.weather.*;
import com.github.prominence.openweathermap.api.model.weather.Temperature;
import org.junit.Test;

import java.time.LocalDateTime;

public class WeatherUnitTest {

    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        new Weather("state", "desc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithoutState_thenThrowAnException() {
        new Weather(null, "desc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithoutDescription_thenThrowAnException() {
        new Weather("state", null);
    }

    @Test
    public void whenSetState_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        weather.setState("test");

        assert "test".equals(weather.getState());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetNullState_thenThrowAnException() {
        final Weather weather = new Weather("state", "desc");
        weather.setState(null);
    }

    @Test
    public void whenSetDescription_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        weather.setDescription("test");

        assert "test".equals(weather.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetNullDescription_thenThrowAnException() {
        final Weather weather = new Weather("state", "desc");
        weather.setDescription(null);
    }

    @Test
    public void whenSetIconUrl_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        weather.setWeatherIconUrl("test");

        assert "test".equals(weather.getWeatherIconUrl());
    }

    @Test
    public void whenSetRequestedOn_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final LocalDateTime now = LocalDateTime.now();
        weather.setRequestedOn(now);

        assert now.equals(weather.getRequestedOn());
    }

    @Test
    public void whenSetTemperature_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Temperature temperature = new Temperature(22.3, "a");
        weather.setTemperature(temperature);

        assert temperature.equals(weather.getTemperature());
    }

    @Test
    public void whenSetPressure_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Pressure pressure = new Pressure(33.2);
        weather.setPressure(pressure);

        assert pressure.equals(weather.getPressure());
    }

    @Test
    public void whenSetHumidity_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Humidity humidity = new Humidity((byte) 44);
        weather.setHumidity(humidity);

        assert humidity.equals(weather.getHumidity());
    }

    @Test
    public void whenSetWind_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Wind wind = new Wind(22.2, "a");
        weather.setWind(wind);

        assert wind.equals(weather.getWind());
    }

    @Test
    public void whenSetRain_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Rain rain = new Rain();
        weather.setRain(rain);

        assert rain.equals(weather.getRain());
    }

    @Test
    public void whenSetSnow_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Snow snow = new Snow();
        weather.setSnow(snow);

        assert snow.equals(weather.getSnow());
    }

    @Test
    public void whenSetClouds_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Clouds clouds = new Clouds((byte) 33);
        weather.setClouds(clouds);

        assert clouds.equals(weather.getClouds());
    }

    @Test
    public void whenSetLocation_thenValueIsSet() {
        final Weather weather = new Weather("state", "desc");
        final Location location = new Location(22, "asd");
        weather.setLocation(location);

        assert location.equals(weather.getLocation());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Weather weather = new Weather("state", "desc");
        final Location location = new Location(12312, "asd");
        final Temperature temperature = new Temperature(33.2, "asd");
        final Pressure pressure = new Pressure(44.4);
        final Clouds clouds = new Clouds((byte) 55);
        final Rain rain = new Rain(33.2, null);
        final Snow snow = new Snow(33.1, null);

        assert !"".equals(weather.toString());
        weather.setLocation(location);
        assert !"".equals(weather.toString());
        location.setCountryCode("3d");
        assert !"".equals(weather.toString());
        weather.setTemperature(temperature);
        assert !"".equals(weather.toString());
        weather.setPressure(pressure);
        assert !"".equals(weather.toString());
        weather.setClouds(clouds);
        assert !"".equals(weather.toString());
        weather.setRain(rain);
        assert !"".equals(weather.toString());
        weather.setSnow(snow);
        assert !"".equals(weather.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Weather one = new Weather("state", "desc");
        final Weather two = new Weather("state", "desc");

        assert one.hashCode() == two.hashCode();

        two.setDescription("112");

        assert one.hashCode() != two.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Weather one = new Weather("state", "desc");
        final Weather two = new Weather("state1", "desc1");

        assert one.equals(one);
        assert !one.equals(new Object());

        assert !one.equals(two);

        two.setState("state");

        assert !one.equals(two);

        two.setDescription("desc");

        assert one.equals(two);

        one.setWeatherIconUrl("1");

        assert !one.equals(two);

        two.setWeatherIconUrl("1");

        assert one.equals(two);

        final LocalDateTime now = LocalDateTime.now();
        one.setRequestedOn(now);

        assert !one.equals(two);

        two.setRequestedOn(now);

        assert one.equals(two);

        final Temperature temperature = new Temperature(33.2, "as");
        one.setTemperature(temperature);

        assert !one.equals(two);

        two.setTemperature(temperature);

        assert one.equals(two);

        final Pressure pressure = new Pressure(33.33);
        one.setPressure(pressure);

        assert !one.equals(two);

        two.setPressure(pressure);

        assert one.equals(two);

        final Humidity humidity = new Humidity((byte) 33);
        one.setHumidity(humidity);

        assert !one.equals(two);

        two.setHumidity(humidity);

        assert one.equals(two);

        final Wind wind = new Wind(33.6, "asd");
        one.setWind(wind);

        assert !one.equals(two);

        two.setWind(wind);

        assert one.equals(two);

        final Rain rain = new Rain();
        one.setRain(rain);

        assert !one.equals(two);

        two.setRain(rain);

        assert one.equals(two);

        final Snow snow = new Snow();
        one.setSnow(snow);

        assert !one.equals(two);

        two.setSnow(snow);

        assert one.equals(two);

        final Clouds clouds = new Clouds((byte) 33);
        one.setClouds(clouds);

        assert !one.equals(two);

        two.setClouds(clouds);

        assert one.equals(two);

        final Location location = new Location(231, "asda");
        one.setLocation(location);

        assert !one.equals(two);

        two.setLocation(location);

        assert one.equals(two);
    }
}

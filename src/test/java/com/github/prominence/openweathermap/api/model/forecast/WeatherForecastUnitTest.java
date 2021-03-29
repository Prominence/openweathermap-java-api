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

package com.github.prominence.openweathermap.api.model.forecast;

import com.github.prominence.openweathermap.api.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class WeatherForecastUnitTest {
    @Test
    public void whenSetForecastTime_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final LocalDateTime now = LocalDateTime.now();
        weatherForecast.setForecastTime(now);

        Assert.assertEquals(now, weatherForecast.getForecastTime());
    }

    @Test
    public void whenSetTemperature_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Temperature temperature = Temperature.withValue(22.3, "a");
        weatherForecast.setTemperature(temperature);

        Assert.assertEquals(temperature, weatherForecast.getTemperature());
    }

    @Test
    public void whenSetPressure_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.2);
        weatherForecast.setAtmosphericPressure(atmosphericPressure);

        Assert.assertEquals(atmosphericPressure, weatherForecast.getAtmosphericPressure());
    }

    @Test
    public void whenSetHumidity_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Humidity humidity = Humidity.withValue((byte) 44);
        weatherForecast.setHumidity(humidity);

        Assert.assertEquals(humidity, weatherForecast.getHumidity());
    }

    @Test
    public void whenSetWind_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Wind wind = Wind.withValue(22.2, "a");
        weatherForecast.setWind(wind);

        Assert.assertEquals(wind, weatherForecast.getWind());
    }

    @Test
    public void whenSetRain_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Rain rain = Rain.withThreeHourLevelValue(0);
        weatherForecast.setRain(rain);

        Assert.assertEquals(rain, weatherForecast.getRain());
    }

    @Test
    public void whenSetSnow_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Snow snow = Snow.withThreeHourLevelValue(0);
        weatherForecast.setSnow(snow);

        Assert.assertEquals(snow, weatherForecast.getSnow());
    }

    @Test
    public void whenSetClouds_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Clouds clouds = Clouds.withValue((byte) 33);
        weatherForecast.setClouds(clouds);

        Assert.assertEquals(clouds, weatherForecast.getClouds());
    }

    @Test
    public void whenSetForecastTimeISO_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();

        Assert.assertNull(weatherForecast.getForecastTimeISO());

        weatherForecast.setForecastTimeISO("1994-2-3");

        Assert.assertEquals("1994-2-3", weatherForecast.getForecastTimeISO());
    }

    @Test
    public void whenSetDayTime_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();

        Assert.assertNull(weatherForecast.getDayTime());

        weatherForecast.setDayTime(DayTime.DAY);

        Assert.assertEquals(DayTime.DAY, weatherForecast.getDayTime());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Location location = Location.withValues(12312, "asd");
        final Temperature temperature = Temperature.withValue(33.2, "asd");
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(44.4);
        final Clouds clouds = Clouds.withValue((byte) 55);
        final Rain rain = Rain.withThreeHourLevelValue(33.2);
        final Snow snow = Snow.withThreeHourLevelValue(33.1);

        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        location.setCountryCode("3d");
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setTemperature(temperature);
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setAtmosphericPressure(atmosphericPressure);
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setClouds(clouds);
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setRain(rain);
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setSnow(snow);
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setRain(null);
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setSnow(null);
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setRain(Rain.withThreeHourLevelValue(0));
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());

        weatherForecast.setSnow(Snow.withThreeHourLevelValue(0));
        Assert.assertNotEquals("", weatherForecast.toString());
        Assert.assertNotNull(weatherForecast.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final WeatherForecast one = new WeatherForecast();
        final WeatherForecast two = new WeatherForecast();

        Assert.assertEquals(one.hashCode(), two.hashCode());

        two.setDayTime(DayTime.NIGHT);

        Assert.assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final WeatherForecast one = new WeatherForecast();
        final WeatherForecast two = new WeatherForecast();

        Assert.assertTrue(one.equals(one));
        Assert.assertFalse(one.equals(null));
        Assert.assertFalse(one.equals(new Object()));
        Assert.assertTrue(one.equals(two));

        final LocalDateTime now = LocalDateTime.now();
        one.setForecastTime(now);

        Assert.assertFalse(one.equals(two));

        two.setForecastTime(now);

        Assert.assertTrue(one.equals(two));

        final Temperature temperature = Temperature.withValue(33.2, "as");
        one.setTemperature(temperature);

        Assert.assertFalse(one.equals(two));

        two.setTemperature(temperature);

        Assert.assertTrue(one.equals(two));

        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.33);
        one.setAtmosphericPressure(atmosphericPressure);

        Assert.assertFalse(one.equals(two));

        two.setAtmosphericPressure(atmosphericPressure);

        Assert.assertTrue(one.equals(two));

        final Humidity humidity = Humidity.withValue((byte) 33);
        one.setHumidity(humidity);

        Assert.assertFalse(one.equals(two));

        two.setHumidity(humidity);

        Assert.assertTrue(one.equals(two));

        final Wind wind = Wind.withValue(33.6, "asd");
        one.setWind(wind);

        Assert.assertFalse(one.equals(two));

        two.setWind(wind);

        Assert.assertTrue(one.equals(two));

        final Rain rain = Rain.withThreeHourLevelValue(0);
        one.setRain(rain);

        Assert.assertFalse(one.equals(two));

        two.setRain(rain);

        Assert.assertTrue(one.equals(two));

        final Snow snow = Snow.withThreeHourLevelValue(0);
        one.setSnow(snow);

        Assert.assertFalse(one.equals(two));

        two.setSnow(snow);

        Assert.assertTrue(one.equals(two));

        final Clouds clouds = Clouds.withValue((byte) 33);
        one.setClouds(clouds);

        Assert.assertFalse(one.equals(two));

        two.setClouds(clouds);

        Assert.assertTrue(one.equals(two));

        one.setForecastTimeISO("test");

        Assert.assertFalse(one.equals(two));

        two.setForecastTimeISO("test");

        Assert.assertTrue(one.equals(two));

        one.setDayTime(DayTime.NIGHT);

        Assert.assertFalse(one.equals(two));

        two.setDayTime(DayTime.DAY);

        Assert.assertFalse(one.equals(two));

        one.setDayTime(DayTime.DAY);

        Assert.assertTrue(one.equals(two));
    }
}

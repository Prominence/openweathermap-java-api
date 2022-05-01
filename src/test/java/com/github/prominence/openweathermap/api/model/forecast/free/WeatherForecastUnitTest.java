/*
 * Copyright (c) 2022 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model.forecast.free;

import com.github.prominence.openweathermap.api.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherForecastUnitTest {
    @Test
    public void whenSetForecastTime_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final LocalDateTime now = LocalDateTime.now();
        weatherForecast.setForecastTime(now);

        assertEquals(now, weatherForecast.getForecastTime());
    }

    @Test
    public void whenSetTemperature_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Temperature temperature = Temperature.withValue(22.3, "a");
        weatherForecast.setTemperature(temperature);

        assertEquals(temperature, weatherForecast.getTemperature());
    }

    @Test
    public void whenSetPressure_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.2);
        weatherForecast.setAtmosphericPressure(atmosphericPressure);

        assertEquals(atmosphericPressure, weatherForecast.getAtmosphericPressure());
    }

    @Test
    public void whenSetHumidity_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Humidity humidity = Humidity.withValue((byte) 44);
        weatherForecast.setHumidity(humidity);

        assertEquals(humidity, weatherForecast.getHumidity());
    }

    @Test
    public void whenSetWind_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Wind wind = Wind.withValue(22.2, "a");
        weatherForecast.setWind(wind);

        assertEquals(wind, weatherForecast.getWind());
    }

    @Test
    public void whenSetRain_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Rain rain = Rain.withThreeHourLevelValue(0);
        weatherForecast.setRain(rain);

        assertEquals(rain, weatherForecast.getRain());
    }

    @Test
    public void whenSetSnow_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Snow snow = Snow.withThreeHourLevelValue(0);
        weatherForecast.setSnow(snow);

        assertEquals(snow, weatherForecast.getSnow());
    }

    @Test
    public void whenSetClouds_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Clouds clouds = Clouds.withValue((byte) 33);
        weatherForecast.setClouds(clouds);

        assertEquals(clouds, weatherForecast.getClouds());
    }

    @Test
    public void whenSetForecastTimeISO_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();

        assertNull(weatherForecast.getForecastTimeISO());

        weatherForecast.setForecastTimeISO("1994-2-3");

        assertEquals("1994-2-3", weatherForecast.getForecastTimeISO());
    }

    @Test
    public void whenSetDayTime_thenValueIsSet() {
        final WeatherForecast weatherForecast = new WeatherForecast();

        assertNull(weatherForecast.getDayTime());

        weatherForecast.setDayTime(DayTime.DAY);

        assertEquals(DayTime.DAY, weatherForecast.getDayTime());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final WeatherForecast weatherForecast = new WeatherForecast();
        final Location location = Location.withValues(12312, "asd");
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        final Temperature temperature = Temperature.withValue(33.2, "asd");
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(44.4);
        final Clouds clouds = Clouds.withValue((byte) 55);
        final Rain rain = Rain.withThreeHourLevelValue(33.2);
        final Snow snow = Snow.withThreeHourLevelValue(33.1);

        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setWeatherStates(List.of(weatherState));
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        location.setCountryCode("3d");
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setTemperature(temperature);
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setAtmosphericPressure(atmosphericPressure);
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setClouds(clouds);
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setRain(rain);
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setSnow(snow);
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setRain(null);
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setSnow(null);
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setRain(Rain.withThreeHourLevelValue(0));
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());

        weatherForecast.setSnow(Snow.withThreeHourLevelValue(0));
        assertNotEquals("", weatherForecast.toString());
        assertNotNull(weatherForecast.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final WeatherForecast first = new WeatherForecast();
        final WeatherForecast second = new WeatherForecast();

        assertEquals(first.hashCode(), second.hashCode());

        second.setDayTime(DayTime.NIGHT);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final WeatherForecast first = new WeatherForecast();
        final WeatherForecast second = new WeatherForecast();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());
        assertEquals(first, second);

        final LocalDateTime now = LocalDateTime.now();
        first.setForecastTime(now);

        assertNotEquals(first, second);

        second.setForecastTime(now);

        assertEquals(first, second);

        final Temperature temperature = Temperature.withValue(33.2, "as");
        first.setTemperature(temperature);

        assertNotEquals(first, second);

        second.setTemperature(temperature);

        assertEquals(first, second);

        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        first.setWeatherStates(List.of(weatherState));

        assertNotEquals(first, second);

        second.setWeatherStates(List.of(weatherState));

        assertEquals(first, second);

        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.33);
        first.setAtmosphericPressure(atmosphericPressure);

        assertNotEquals(first, second);

        second.setAtmosphericPressure(atmosphericPressure);

        assertEquals(first, second);

        final Humidity humidity = Humidity.withValue((byte) 33);
        first.setHumidity(humidity);

        assertNotEquals(first, second);

        second.setHumidity(humidity);

        assertEquals(first, second);

        final Wind wind = Wind.withValue(33.6, "asd");
        first.setWind(wind);

        assertNotEquals(first, second);

        second.setWind(wind);

        assertEquals(first, second);

        final Rain rain = Rain.withThreeHourLevelValue(0);
        first.setRain(rain);

        assertNotEquals(first, second);

        second.setRain(rain);

        assertEquals(first, second);

        final Snow snow = Snow.withThreeHourLevelValue(0);
        first.setSnow(snow);

        assertNotEquals(first, second);

        second.setSnow(snow);

        assertEquals(first, second);

        final Clouds clouds = Clouds.withValue((byte) 33);
        first.setClouds(clouds);

        assertNotEquals(first, second);

        second.setClouds(clouds);

        assertEquals(first, second);

        first.setForecastTimeISO("test");

        assertNotEquals(first, second);

        second.setForecastTimeISO("test");

        assertEquals(first, second);

        first.setDayTime(DayTime.NIGHT);

        assertNotEquals(first, second);

        second.setDayTime(DayTime.DAY);

        assertNotEquals(first, second);

        first.setDayTime(DayTime.DAY);

        assertEquals(first, second);
    }
}

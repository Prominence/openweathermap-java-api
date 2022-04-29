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
import com.github.prominence.openweathermap.api.model.onecall.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Wind;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DailyUnitTest {
    @Test
    public void getForecastTime() {
        final Daily daily = new Daily();
        final LocalDateTime now = LocalDateTime.now();
        daily.setForecastTime(now);

        assertEquals(now, daily.getForecastTime());
    }

    @Test
    public void getSunriseTime() {
        final Daily daily = new Daily();
        final LocalDateTime now = LocalDateTime.now();
        daily.setSunriseTime(now);

        assertEquals(now, daily.getSunriseTime());
    }

    @Test
    public void getSunsetTime() {
        final Daily daily = new Daily();
        final LocalDateTime now = LocalDateTime.now();
        daily.setSunsetTime(now);

        assertEquals(now, daily.getSunsetTime());
    }

    @Test
    public void getWeatherState() {
        final Daily daily = new Daily();
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        daily.setWeatherStates(List.of(weatherState));

        assertEquals(weatherState, daily.getWeatherStates().get(0));
    }

    @Test
    public void getTemperature() {
        final Daily daily = new Daily();
        final DailyTemperature dailyTemperature = new DailyTemperature();
        dailyTemperature.setDay(10.0);
        dailyTemperature.setEveFeelsLike(44.2);

        daily.setTemperature(dailyTemperature);

        assertEquals(dailyTemperature, daily.getTemperature());
    }

    @Test
    public void getAtmosphericPressure() {
        final Daily daily = new Daily();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.3);
        daily.setAtmosphericPressure(atmosphericPressure);

        assertEquals(atmosphericPressure, daily.getAtmosphericPressure());
    }

    @Test
    public void getHumidity() {
        final Daily daily = new Daily();
        final Humidity humidity = Humidity.withValue((byte) 10);
        daily.setHumidity(humidity);

        assertEquals(humidity, daily.getHumidity());
    }

    @Test
    public void getWind() {
        final Daily daily = new Daily();
        final Wind wind = Wind.withValue(13.2, "m/s");
        daily.setWind(wind);

        assertEquals(wind, daily.getWind());
    }

    @Test
    public void getClouds() {
        final Daily daily = new Daily();
        final Clouds clouds = Clouds.withValue((byte) 25);
        daily.setClouds(clouds);

        assertEquals(clouds, daily.getClouds());
    }

    @Test
    public void getUvIndex() {
        final Daily daily = new Daily();
        final double uvIndex = 22.4;
        daily.setUvIndex(uvIndex);

        assertEquals(uvIndex, daily.getUvIndex(), 0.00001);

        daily.setUvIndex(null);

        assertNull(daily.getUvIndex());
    }

    @Test
    public void getIllegalUvIndexValue() {
        final Daily daily = new Daily();

        assertThrows(IllegalArgumentException.class, () -> daily.setUvIndex(-1.2));
    }

    @Test
    public void getProbabilityOfPrecipitation() {
        final Daily daily = new Daily();
        final double pop = 0.84;
        daily.setProbabilityOfPrecipitation(pop);

        assertEquals(pop, daily.getProbabilityOfPrecipitation(), 0.00001);
        assertEquals((byte) 84, daily.getProbabilityOfPrecipitationPercentage());

        daily.setProbabilityOfPrecipitation(null);

        assertNull(daily.getProbabilityOfPrecipitation());
        assertNull(daily.getProbabilityOfPrecipitationPercentage());
    }

    @Test
    public void getIllegalProbabilityOfPrecipitationValue_negative() {
        final Daily daily = new Daily();

        assertThrows(IllegalArgumentException.class, () -> daily.setProbabilityOfPrecipitation(-20.0));
    }

    @Test
    public void getIllegalProbabilityOfPrecipitationValue_tooBig() {
        final Daily daily = new Daily();

        assertThrows(IllegalArgumentException.class, () -> daily.setProbabilityOfPrecipitation(120.0));
    }

    @Test
    public void getRain() {
        final Daily daily = new Daily();
        final DailyRain rain = DailyRain.withValue(20.2);
        daily.setRain(rain);

        assertEquals(rain, daily.getRain());
    }

    @Test
    public void getSnow() {
        final Daily daily = new Daily();
        final DailySnow snow = DailySnow.withValue(25.0);
        daily.setSnow(snow);

        assertEquals(snow, daily.getSnow());
    }

    @Test
    public void getEquals() {
        final LocalDateTime now = LocalDateTime.now();
        final Daily first = new Daily();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final Daily second = new Daily();

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

        first.setWeatherStates(List.of(weatherState));

        assertNotEquals(first, second);

        second.setWeatherStates(List.of(weatherState));

        assertEquals(first, second);

        final DailyTemperature dailyTemperature = new DailyTemperature();

        first.setTemperature(dailyTemperature);

        assertNotEquals(first, second);

        second.setTemperature(dailyTemperature);

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

        final double pop = 70.5;

        first.setProbabilityOfPrecipitation(pop);

        assertNotEquals(first, second);

        second.setProbabilityOfPrecipitation(pop);

        assertEquals(first, second);

        final DailyRain rain = DailyRain.withValue(20.2);

        first.setRain(rain);

        assertNotEquals(first, second);

        second.setRain(rain);

        assertEquals(first, second);

        final DailySnow snow = DailySnow.withValue(25.0);

        first.setSnow(snow);

        assertNotEquals(first, second);

        second.setSnow(snow);

        assertEquals(first, second);
    }

    @Test
    public void getHashCode() {
        final Daily first = new Daily();
        final Daily second = new Daily();

        assertEquals(first.hashCode(), second.hashCode());

        first.setForecastTime(LocalDateTime.now());

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void getToString() {
        final LocalDateTime now = LocalDateTime.now();
        final Daily daily = new Daily();

        daily.setForecastTime(now);

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());

        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        daily.setWeatherStates(List.of(weatherState));

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());

        final DailyTemperature dailyTemperature = new DailyTemperature();

        daily.setTemperature(dailyTemperature);

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());

        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.3);

        daily.setAtmosphericPressure(atmosphericPressure);

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());

        final Wind wind = Wind.withValue(13.2, "m/s");

        daily.setWind(wind);

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());

        final Clouds clouds = Clouds.withValue((byte) 25);

        daily.setClouds(clouds);

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());

        final DailyRain rain = DailyRain.withValue(20.2);

        daily.setRain(rain);

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());

        final DailySnow snow = DailySnow.withValue(25.0);

        daily.setSnow(snow);

        assertNotNull(daily.toString());
        assertNotEquals("", daily.toString());
    }
}
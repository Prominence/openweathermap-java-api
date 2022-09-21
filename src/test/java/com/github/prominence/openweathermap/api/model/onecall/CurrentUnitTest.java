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

package com.github.prominence.openweathermap.api.model.onecall;

import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.generic.precipitation.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureValue;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CurrentUnitTest {
    @Test
    public void getForecastTime() {
        final BaseMeasurement current = new BaseMeasurement();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        current.setForecastTime(now);

        assertEquals(now, current.getForecastTime());
    }

    @Test
    public void getSunriseTime() {
        final BaseMeasurement current = new BaseMeasurement();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        current.setSunriseTime(now);

        assertEquals(now, current.getSunriseTime());
    }

    @Test
    public void getSunsetTime() {
        final BaseMeasurement current = new BaseMeasurement();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        current.setSunsetTime(now);

        assertEquals(now, current.getSunsetTime());
    }

    @Test
    public void getWeatherState() {
        final BaseMeasurement current = new BaseMeasurement();
        final WeatherCondition weatherState = WeatherCondition.getById(800);
        current.setWeatherStates(Collections.singletonList(weatherState));

        assertEquals(weatherState, current.getWeatherStates().get(0));
    }

    @Test
    public void getTemperature() {
        final BaseMeasurement current = new BaseMeasurement();
        final TemperatureValue temperature = new TemperatureValue(BigDecimal.valueOf(10));
        final TemperatureValue temperatureFeelsLike = new TemperatureValue(BigDecimal.valueOf(100));
        current.setTemperatureMeasured(temperature);
        current.setFeelsLike(temperatureFeelsLike);

        assertEquals(temperature, current.getTemperature());
        assertEquals(temperatureFeelsLike, current.getFeelsLike());
    }

    @Test
    public void getAtmosphericPressure() {
        final BaseMeasurement current = new BaseMeasurement();
        final BigDecimal atmosphericPressure = BigDecimal.valueOf(22.3);
        current.setSeaLevel(atmosphericPressure);

        assertEquals(atmosphericPressure, current.getSeaLevel());
    }

    @Test
    public void getHumidity() {
        final BaseMeasurement current = new BaseMeasurement();
        final int humidity = 10;
        current.setHumidityPercentage(humidity);

        assertEquals(humidity, current.getHumidityPercentage());
    }

    @Test
    public void getWind() {
        final BaseMeasurement current = new BaseMeasurement();
        final WindSpeed windSpeed = new WindSpeed(BigDecimal.valueOf(13.2));
        final WindSpeed windSpeedGust = new WindSpeed(BigDecimal.valueOf(15.2));
        final Integer windDirection = 123;
        current.setSpeed(windSpeed);
        current.setGust(windSpeedGust);
        current.setDirectionDegrees(windDirection);

        assertEquals(windSpeed, current.getSpeed());
        assertEquals(windSpeedGust, current.getGust());
        assertEquals(windDirection, current.getDirectionDegrees());
    }

    @Test
    public void getClouds() {
        final BaseMeasurement current = new BaseMeasurement();
        final int clouds = 25;
        current.setCoveragePercentage(clouds);

        assertEquals(clouds, current.getClouds().getCoveragePercentage());
    }

    @Test
    public void getUvIndex() {
        final BaseMeasurement current = new BaseMeasurement();
        final BigDecimal uvIndex = BigDecimal.valueOf(22.4);
        current.setUvIndex(uvIndex);

        assertEquals(uvIndex, current.getUvIndex());

        current.setUvIndex(null);

        assertNull(current.getUvIndex());
    }

    @Test
    public void getRain() {
        final BaseMeasurement current = new BaseMeasurement();
        final BasePrecipitation rain = new BasePrecipitation(BigDecimal.valueOf(20.2));
        current.setRainModel(rain);

        assertEquals(rain.getOneHourLevel(), current.getRain());
    }

    @Test
    public void getSnow() {
        final BaseMeasurement current = new BaseMeasurement();
        final BasePrecipitation snow = new BasePrecipitation(BigDecimal.valueOf(25.0));
        current.setSnowModel(snow);

        assertEquals(snow.getOneHourLevel(), current.getSnow());
    }
}

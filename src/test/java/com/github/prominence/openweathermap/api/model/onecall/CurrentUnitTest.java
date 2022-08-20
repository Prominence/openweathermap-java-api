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
import com.github.prominence.openweathermap.api.model.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.TemperatureValue;
import com.github.prominence.openweathermap.api.model.WindSpeed;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

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
        current.setWeatherStates(List.of(weatherState));

        assertEquals(weatherState, current.getWeatherStates().get(0));
    }

    @Test
    public void getTemperature() {
        final BaseMeasurement current = new BaseMeasurement();
        final TemperatureValue temperature = new TemperatureValue(new BigDecimal("10"));
        final TemperatureValue temperatureFeelsLike = new TemperatureValue(new BigDecimal("100"));
        current.setTemperature(temperature);
        current.setTemperatureFeelsLike(temperatureFeelsLike);

        assertEquals(temperature, current.getTemperature());
        assertEquals(temperatureFeelsLike, current.getTemperatureFeelsLike());
    }

    @Test
    public void getAtmosphericPressure() {
        final BaseMeasurement current = new BaseMeasurement();
        final BigDecimal atmosphericPressure = new BigDecimal("22.3");
        current.setAtmosphericPressureSeaLevel(atmosphericPressure);

        assertEquals(atmosphericPressure, current.getAtmosphericPressureSeaLevel());
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
        final WindSpeed windSpeed = new WindSpeed(new BigDecimal("13.2"));
        final WindSpeed windSpeedGust = new WindSpeed(new BigDecimal("15.2"));
        final Integer windDirection = 123;
        current.setWindSpeed(windSpeed);
        current.setWindSpeedGust(windSpeedGust);
        current.setWindDirectionDegrees(windDirection);

        assertEquals(windSpeed, current.getWindSpeed());
        assertEquals(windSpeedGust, current.getWindSpeedGust());
        assertEquals(windDirection, current.getWindDirectionDegrees());
    }

    @Test
    public void getClouds() {
        final BaseMeasurement current = new BaseMeasurement();
        final int clouds = 25;
        current.setCloudsPercentage(clouds);

        assertEquals(clouds, current.getCloudsPercentage());
    }

    @Test
    public void getUvIndex() {
        final BaseMeasurement current = new BaseMeasurement();
        final BigDecimal uvIndex = new BigDecimal("22.4");
        current.setUvIndex(uvIndex);

        assertEquals(uvIndex, current.getUvIndex());

        current.setUvIndex(null);

        assertNull(current.getUvIndex());
    }

    @Test
    public void getRain() {
        final BaseMeasurement current = new BaseMeasurement();
        final BasePrecipitation rain = new BasePrecipitation(new BigDecimal("20.2"));
        current.setRain(rain);

        assertEquals(rain, current.getRain());
    }

    @Test
    public void getSnow() {
        final BaseMeasurement current = new BaseMeasurement();
        final BasePrecipitation snow = new BasePrecipitation(new BigDecimal("25.0"));
        current.setSnow(snow);

        assertEquals(snow, current.getSnow());
    }
}

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

package com.github.prominence.openweathermap.api.model.onecall.current;

import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.generic.precipitation.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureValue;
import com.github.prominence.openweathermap.api.model.generic.visibility.Visibility;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HourlyUnitTest {
    @Test
    public void getForecastTime() {
        final Hourly hourly = new Hourly();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        hourly.setForecastTime(now);

        assertEquals(now, hourly.getForecastTime());
    }

    @Test
    public void getWeatherState() {
        final Hourly hourly = new Hourly();
        final WeatherCondition weatherState = WeatherCondition.getById(800);
        hourly.setWeatherStates(Collections.singletonList(weatherState));

        assertEquals(weatherState, hourly.getWeatherStates().get(0));
    }

    @Test
    public void getTemperature() {
        final Hourly hourly = new Hourly();
        final TemperatureValue temperature = new TemperatureValue(BigDecimal.valueOf(10));
        hourly.setTemperatureMeasured(temperature);

        assertEquals(temperature, hourly.getTemperature());
    }

    @Test
    public void getAtmosphericPressure() {
        final Hourly hourly = new Hourly();
        final BigDecimal atmosphericPressure = BigDecimal.valueOf(100);
        hourly.setSeaLevel(atmosphericPressure);

        assertEquals(atmosphericPressure, hourly.getSeaLevel());
    }

    @Test
    public void getHumidity() {
        final Hourly hourly = new Hourly();
        final int humidity = 20;
        hourly.setHumidityPercentage(humidity);

        assertEquals(humidity, hourly.getHumidityPercentage());
    }

    @Test
    public void getUvIndex() {
        final Hourly hourly = new Hourly();
        final BigDecimal uvIndex = BigDecimal.valueOf(20.0);
        hourly.setUvIndex(uvIndex);

        assertEquals(uvIndex, hourly.getUvIndex());
    }

    @Test
    public void getClouds() {
        final Hourly hourly = new Hourly();
        final int clouds = 60;
        hourly.setCoveragePercentage(clouds);

        assertEquals(clouds, hourly.getClouds().getCoveragePercentage());
    }

    @Test
    public void getVisibilityInMetres() {
        final Hourly hourly = new Hourly();
        final Visibility vim = new Visibility(BigDecimal.valueOf(40.0));
        hourly.setVisibility(vim);

        assertEquals(vim, hourly.getVisibility());
    }

    @Test
    public void getWind() {
        final Hourly hourly = new Hourly();
        final WindSpeed windSpeed = new WindSpeed(BigDecimal.valueOf(13.2));
        final WindSpeed windSpeedGust = new WindSpeed(BigDecimal.valueOf(15.2));
        final Integer windDirection = 123;
        hourly.setSpeed(windSpeed);
        hourly.setGust(windSpeedGust);
        hourly.setDirectionDegrees(windDirection);

        assertEquals(windSpeed, hourly.getSpeed());
        assertEquals(windSpeedGust, hourly.getGust());
        assertEquals(windDirection, hourly.getDirectionDegrees());
    }

    @Test
    public void getProbabilityOfPrecipitation() {
        final Hourly hourly = new Hourly();
        final Integer pop = 84;
        hourly.setProbabilityOfPrecipitation(pop);

        assertEquals(pop, hourly.getProbabilityOfPrecipitation());
    }


    @Test
    public void getRain() {
        final Hourly hourly = new Hourly();
        final BasePrecipitation rain = new BasePrecipitation(BigDecimal.valueOf(20.2));
        hourly.setRainModel(rain);

        assertEquals(rain.getOneHourLevel(), hourly.getRain());
    }

    @Test
    public void getSnow() {
        final Hourly hourly = new Hourly();
        final BasePrecipitation snow = new BasePrecipitation(BigDecimal.valueOf(25.0));
        hourly.setSnowModel(snow);

        assertEquals(snow.getOneHourLevel(), hourly.getSnow());
    }

}

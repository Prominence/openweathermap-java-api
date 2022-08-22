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

package com.github.prominence.openweathermap.api.model.onecall.historical;

import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.TemperatureValue;
import com.github.prominence.openweathermap.api.model.Visibility;
import com.github.prominence.openweathermap.api.model.WindSpeed;
import com.github.prominence.openweathermap.api.model.onecall.BaseMeasurement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoricalWeatherUnitTest {
    @Test
    public void getForecastTime() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        historicalWeather.setForecastTime(now);

        assertEquals(now, historicalWeather.getForecastTime());
    }

    @Test
    public void getSunriseTime() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        historicalWeather.setSunriseTime(now);

        assertEquals(now, historicalWeather.getSunriseTime());
    }

    @Test
    public void getSunsetTime() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        historicalWeather.setSunsetTime(now);

        assertEquals(now, historicalWeather.getSunsetTime());
    }

    @Test
    public void getWeatherState() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final WeatherCondition weatherState = WeatherCondition.getById(800);
        historicalWeather.setWeatherStates(List.of(weatherState));

        assertEquals(weatherState, historicalWeather.getWeatherStates().get(0));
    }

    @Test
    public void getTemperature() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final TemperatureValue temperature = new TemperatureValue(new BigDecimal("10.0"));
        historicalWeather.setTemperature(temperature);

        assertEquals(temperature, historicalWeather.getTemperature());
    }

    @Test
    public void getAtmosphericPressure() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final BigDecimal atmosphericPressure = new BigDecimal("22.3");
        historicalWeather.setAtmosphericPressureSeaLevel(atmosphericPressure);

        assertEquals(atmosphericPressure, historicalWeather.getAtmosphericPressureSeaLevel());
    }

    @Test
    public void getHumidity() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final int humidity = 10;
        historicalWeather.setHumidityPercentage(humidity);

        assertEquals(humidity, historicalWeather.getHumidityPercentage());
    }

    @Test
    public void getWind() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        WindSpeed windSpeed = new WindSpeed(new BigDecimal("13.2"));
        WindSpeed windSpeedGust = new WindSpeed(new BigDecimal("12.0"));
        int direction = 50;
        historicalWeather.setWindSpeed(windSpeed);
        historicalWeather.setWindSpeedGust(windSpeedGust);
        historicalWeather.setWindDirectionDegrees(direction);

        assertEquals(windSpeed, historicalWeather.getWind().getSpeed());
        assertEquals(windSpeedGust, historicalWeather.getWind().getGust());
        assertEquals(direction, historicalWeather.getWind().getDirectionDegrees());
    }

    @Test
    public void getClouds() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final int clouds = 25;
        historicalWeather.setCloudsPercentage(clouds);

        assertEquals(clouds, historicalWeather.getCloudsPercentage());
    }

    @Test
    public void getUvIndex() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final BigDecimal uvIndex = new BigDecimal("22.4");
        historicalWeather.setUvIndex(uvIndex);

        assertEquals(uvIndex, historicalWeather.getUvIndex());
    }

    @Test
    public void getVisibility() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final Visibility vim = new Visibility(new BigDecimal(120));
        historicalWeather.setVisibility(vim);

        assertEquals(vim, historicalWeather.getVisibility());
    }

    @Test
    public void getRain() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final BasePrecipitation rain = new BasePrecipitation(new BigDecimal("20.2"));
        historicalWeather.setRain(rain);

        assertEquals(rain, historicalWeather.getRain());
    }

    @Test
    public void getSnow() {
        final BaseMeasurement historicalWeather = new BaseMeasurement();
        final BasePrecipitation snow = new BasePrecipitation(new BigDecimal("25.0"));
        historicalWeather.setSnow(snow);

        assertEquals(snow, historicalWeather.getSnow());
    }
}

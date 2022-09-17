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
import com.github.prominence.openweathermap.api.model.TemperatureDailyBasic;
import com.github.prominence.openweathermap.api.model.TemperatureDailyDetailed;
import com.github.prominence.openweathermap.api.model.TemperatureValue;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyUnitTest {
    @Test
    public void getForecastTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setForecastTime(now);

        assertEquals(now, daily.getForecastTime());
    }

    @Test
    public void getSunriseTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setSunriseTime(now);

        assertEquals(now, daily.getSunriseTime());
    }

    @Test
    public void getSunsetTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setSunsetTime(now);

        assertEquals(now, daily.getSunsetTime());
    }

    @Test
    public void getWeatherState() {
        final Daily daily = new Daily();
        final WeatherCondition weatherState = WeatherCondition.getById(800);
        daily.setWeatherStates(Collections.singletonList(weatherState));

        assertEquals(weatherState, daily.getWeatherStates().get(0));
    }

    @Test
    public void getTemperature() {
        final Daily daily = new Daily();
        final TemperatureDailyDetailed dailyTemperature = new TemperatureDailyDetailed();
        dailyTemperature.setDay(new TemperatureValue(BigDecimal.valueOf(10.0)));

        daily.setTemperature(dailyTemperature);

        assertEquals(dailyTemperature, daily.getTemperature());
    }

    @Test
    public void getTemperatureFeelsLike() {
        final Daily daily = new Daily();
        final TemperatureDailyBasic dailyTemperature = new TemperatureDailyBasic();
        dailyTemperature.setEve(new TemperatureValue(BigDecimal.valueOf(44.2)));

        daily.setFeelsLike(dailyTemperature);

        assertEquals(dailyTemperature, daily.getFeelsLike());
    }

    @Test
    public void getAtmosphericPressure() {
        final Daily daily = new Daily();
        final BigDecimal atmosphericPressure = BigDecimal.valueOf(22.3);
        daily.setAtmosphericPressureSeaLevel(atmosphericPressure);

        assertEquals(atmosphericPressure, daily.getAtmosphericPressureSeaLevel());
    }

    @Test
    public void getHumidity() {
        final Daily daily = new Daily();
        final int humidity = 10;
        daily.setHumidityPercentage(humidity);

        assertEquals(humidity, daily.getHumidityPercentage());
    }

    @Test
    public void getWind() {
        final Daily daily = new Daily();
        final WindSpeed windSpeed = new WindSpeed(BigDecimal.valueOf(13.2));
        final WindSpeed windSpeedGust = new WindSpeed(BigDecimal.valueOf(15.2));
        final Integer windDirection = 123;
        daily.setWindSpeed(windSpeed);
        daily.setWindSpeedGust(windSpeedGust);
        daily.setWindDirectionDegrees(windDirection);

        assertEquals(windSpeed, daily.getWindSpeed());
        assertEquals(windSpeedGust, daily.getWindSpeedGust());
        assertEquals(windDirection, daily.getWindDirectionDegrees());
    }

    @Test
    public void getClouds() {
        final Daily daily = new Daily();
        final int clouds = 25;
        daily.setCloudsPercentage(clouds);

        assertEquals(clouds, daily.getCloudsPercentage());
    }

    @Test
    public void getUvIndex() {
        final Daily daily = new Daily();
        final BigDecimal uvIndex = BigDecimal.valueOf(22.4);
        daily.setUvIndex(uvIndex);

        assertEquals(uvIndex, daily.getUvIndex());
    }

    @Test
    public void getProbabilityOfPrecipitation() {
        final Daily daily = new Daily();
        final BigDecimal pop = BigDecimal.valueOf(0.84);
        daily.setProbabilityOfPrecipitation(pop);

        assertEquals(pop, daily.getProbabilityOfPrecipitation());
    }

    @Test
    public void getRain() {
        final Daily daily = new Daily();
        final BigDecimal rain = BigDecimal.valueOf(20.2);
        daily.setRain(rain);

        assertEquals(rain, daily.getRain());
    }

    @Test
    public void getSnow() {
        final Daily daily = new Daily();
        final BigDecimal snow = BigDecimal.valueOf(25.0);
        daily.setSnow(snow);

        assertEquals(snow, daily.getSnow());
    }
}

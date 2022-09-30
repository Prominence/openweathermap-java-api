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
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.location.MoonlightStages;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.precipitation.Humidity;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationForecast;
import com.github.prominence.openweathermap.api.model.generic.pressure.SeaLevelAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureDailyBasic;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureDailyDetailed;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureValue;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DailyUnitTest {
    @Test
    public void getForecastTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setForecastTime(now);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallDailyWeather underTest = daily;

        assertEquals(now, underTest.getForecastTime());
    }

    @Test
    public void getSunriseTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setSunriseTime(now);

        final SunlightStages underTest = daily.getSunlightStages();

        assertEquals(now, underTest.getSunriseTime());
    }

    @Test
    public void getSunsetTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setSunsetTime(now);

        final SunlightStages underTest = daily.getSunlightStages();

        assertEquals(now, underTest.getSunsetTime());
    }

    @Test
    public void getMoonsetTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setMoonsetTime(now);

        final MoonlightStages underTest = daily.getMoonLightStages();

        assertEquals(now, underTest.getMoonsetTime());
    }

    @Test
    public void getMoonriseTime() {
        final Daily daily = new Daily();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        daily.setMoonriseTime(now);

        final MoonlightStages underTest = daily.getMoonLightStages();

        assertEquals(now, underTest.getMoonriseTime());
    }

    @Test
    public void getMoonPhase() {
        final Daily daily = new Daily();
        final MoonPhase phase = new MoonPhase(0.67);
        daily.setMoonPhase(phase);

        final MoonlightStages underTest = daily.getMoonLightStages();

        assertEquals(phase, underTest.getMoonPhase());
    }

    @Test
    public void getWeatherState() {
        final Daily daily = new Daily();
        final WeatherCondition weatherState = WeatherCondition.getById(800);
        final List<WeatherCondition> weatherStates = Collections.singletonList(weatherState);
        daily.setWeatherStates(weatherStates);

        @SuppressWarnings("UnnecessaryLocalVariable") final OneCallDailyWeather underTest = daily;

        assertIterableEquals(weatherStates, underTest.getWeatherStates());
    }

    @Test
    public void getTemperature() {
        final Daily daily = new Daily();
        final TemperatureDailyDetailed dailyTemperature = new TemperatureDailyDetailed();
        dailyTemperature.setDay(new TemperatureValue(BigDecimal.valueOf(10.0)));
        daily.setTemperatureMeasured(dailyTemperature);

        final OneCallDailyTemperature underTest = daily.getTemperature();

        assertNull(underTest.getMorningFeelsLike());
        assertNull(underTest.getDayFeelsLike());
        assertNull(underTest.getEveFeelsLike());
        assertNull(underTest.getNightFeelsLike());
        assertNull(underTest.getMorning());
        assertEquals(dailyTemperature.getDay(), underTest.getDay());
        assertNull(underTest.getEve());
        assertNull(underTest.getNight());
    }

    @Test
    public void getTemperatureFeelsLike() {
        final Daily daily = new Daily();
        final TemperatureDailyBasic dailyTemperature = new TemperatureDailyBasic();
        dailyTemperature.setEve(new TemperatureValue(BigDecimal.valueOf(44.2)));
        daily.setFeelsLike(dailyTemperature);

        final OneCallDailyTemperature underTest = daily.getTemperature();

        assertNull(underTest.getMorningFeelsLike());
        assertNull(underTest.getDayFeelsLike());
        assertEquals(dailyTemperature.getEve(), underTest.getEveFeelsLike());
        assertNull(underTest.getNightFeelsLike());
        assertNull(underTest.getMorning());
        assertNull(underTest.getDay());
        assertNull(underTest.getEve());
        assertNull(underTest.getNight());
    }

    @Test
    public void getAtmosphericPressure() {
        final Daily daily = new Daily();
        final BigDecimal atmosphericPressure = BigDecimal.valueOf(22.3);
        daily.setSeaLevel(atmosphericPressure);

        final SeaLevelAtmosphericPressure underTest = daily.getAtmosphericPressure();

        assertEquals(atmosphericPressure, underTest.getSeaLevel());
    }

    @Test
    public void getHumidity() {
        final Daily daily = new Daily();
        final int humidity = 10;
        daily.setHumidityPercentage(humidity);

        final Humidity underTest = daily.getHumidity();

        assertEquals(humidity, underTest.getHumidityPercentage());
    }

    @Test
    public void getWind() {
        final Daily daily = new Daily();
        final WindSpeed windSpeed = new WindSpeed(BigDecimal.valueOf(13.2));
        final WindSpeed windSpeedGust = new WindSpeed(BigDecimal.valueOf(15.2));
        final Integer windDirection = 123;
        daily.setSpeed(windSpeed);
        daily.setGust(windSpeedGust);
        daily.setDirectionDegrees(windDirection);

        final DetailedWindInfo wind = daily.getWind();

        assertEquals(windSpeed, wind.getSpeed());
        assertEquals(windSpeedGust, wind.getGust());
        assertEquals(windDirection, wind.getDirectionDegrees());
    }

    @Test
    public void getClouds() {
        final Daily daily = new Daily();
        final int clouds = 25;
        daily.setCoveragePercentage(clouds);

        final CloudCoverage coverage = daily.getClouds();

        assertEquals(clouds, coverage.getCoveragePercentage());
    }

    @Test
    public void getUvIndex() {
        final Daily daily = new Daily();
        final BigDecimal uvIndex = BigDecimal.valueOf(22.4);
        daily.setUvIndex(uvIndex);

        @SuppressWarnings("UnnecessaryLocalVariable") OneCallDailyWeather underTest = daily;

        assertEquals(uvIndex, underTest.getUvIndex());
    }

    @Test
    public void getProbabilityOfPrecipitation() {
        final Daily daily = new Daily();
        final int pop = 84;
        daily.setProbabilityOfPrecipitation(pop);

        PrecipitationForecast underTest = daily.getPrecipitation();

        assertEquals(pop, underTest.getProbabilityOfPrecipitation());
    }

    @Test
    public void getRain() {
        final Daily daily = new Daily();
        final BigDecimal rain = BigDecimal.valueOf(20.2);
        daily.setRain(rain);

        PrecipitationForecast underTest = daily.getPrecipitation();

        assertEquals(rain, underTest.getRain());
    }

    @Test
    public void getSnow() {
        final Daily daily = new Daily();
        final BigDecimal snow = BigDecimal.valueOf(25.0);
        daily.setSnow(snow);

        PrecipitationForecast underTest = daily.getPrecipitation();

        assertEquals(snow, underTest.getSnow());
    }
}

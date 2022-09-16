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

package com.github.prominence.openweathermap.api.mapper;

import com.github.prominence.openweathermap.api.enums.DayTime;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Location;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.forecast.hourly.HourlyForecast;
import com.github.prominence.openweathermap.api.model.forecast.hourly.HourlyForecastModel;
import com.github.prominence.openweathermap.api.model.forecast.hourly.Weather;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HourlyForecastResponseMapperTest {

    @Test
    void testDeserialize_ShouldSucceed_WhenCalledWithOfficialExample() throws IOException {
        //given
        final String resource = "/responses/valid/hourly-forecast.json";

        //when
        final HourlyForecast actual = loadDeserializedResourceAs(resource, HourlyForecastModel.class);

        //then
        assertNotNull(actual);

        final Location location = actual.getLocation();
        assertEquals(2643743, location.getCityId());
        assertEquals("London", location.getCityName());
        assertEquals(new Coordinates(51.5085, -0.1258), location.getCoordinates());
        assertEquals("GB", location.getCountryCode());
        assertEquals(ZoneOffset.ofTotalSeconds(0), location.getTimeZone());
        assertEquals(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1568958164), ZoneOffset.UTC), location.getSunriseTime());
        assertEquals(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1569002733), ZoneOffset.UTC), location.getSunsetTime());

        final Weather weatherForecast = actual.getWeatherForecasts().get(0);
        assertEquals(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1596632400), ZoneOffset.UTC), weatherForecast.getForecastTime());
        assertEquals(DayTime.NIGHT, weatherForecast.getPartOfDay());
        assertEquals(new BigDecimal("10000.00"), weatherForecast.getVisibility().asMeters());
        assertEquals(BigDecimal.valueOf(0.04), weatherForecast.getProbabilityOfPrecipitation());

        final Temperature temperature = weatherForecast.getTemperature();
        assertEquals(BigDecimal.valueOf(289), temperature.getTemperature().asKelvin());
        assertEquals(BigDecimal.valueOf(288), temperature.getFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(289), temperature.getMin().asKelvin());
        assertEquals(BigDecimal.valueOf(289), temperature.getMax().asKelvin());

        final AtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(BigDecimal.valueOf(1013), pressure.getPressure());
        assertEquals(BigDecimal.valueOf(1013), pressure.getSeaLevel());
        assertEquals(BigDecimal.valueOf(1010), pressure.getGroundLevel());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(78, humidity.getHumidityPercentage());

        final WeatherCondition weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(804, weatherState.getId());
        assertEquals("Clouds", weatherState.getName());
        assertEquals("overcast clouds: 85-100%", weatherState.getDescription());
        assertEquals("04n", weatherState.getIconId(DayTime.NIGHT));

        final Clouds clouds = weatherForecast.getClouds();
        assertEquals(100, clouds.getValuePercentage());

        final Wind wind = weatherForecast.getWind();
        assertEquals(BigDecimal.valueOf(2.03), wind.getSpeed().asMetersPerSecond());
        assertEquals(252, wind.getDirectionDegrees());
        assertEquals(BigDecimal.valueOf(5.46), wind.getGust().asMetersPerSecond());

        final BasePrecipitation rain = weatherForecast.getRain();
        assertEquals(BigDecimal.valueOf(23.3), rain.getOneHourLevel());

        final BasePrecipitation snow = weatherForecast.getSnow();
        assertEquals(BigDecimal.valueOf(27.945), snow.getOneHourLevel());
    }
}

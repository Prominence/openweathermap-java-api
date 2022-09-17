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

import com.github.prominence.openweathermap.api.context.TestMappingUtils;
import com.github.prominence.openweathermap.api.enums.DayTime;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.BaseAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.forecast.daily.DailyWeather;
import com.github.prominence.openweathermap.api.model.forecast.daily.SixteenDaysDailyForecast;
import com.github.prominence.openweathermap.api.model.forecast.daily.SixteenDaysDailyForecastModel;
import com.github.prominence.openweathermap.api.model.generic.location.DetailedLocationInfo;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.precipitation.PrecipitationForecast;
import com.github.prominence.openweathermap.api.model.generic.temperature.DailyTemperature;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneOffset;

import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DailyForecastResponseMapperTest {

    @Test
    public void testDeserialize_ShouldSucceed_WhenCalledWithFullyPopulatedJson() throws IOException {
        //given
        final String resource = "/responses/valid/16days-daily.json";

        //when
        final SixteenDaysDailyForecast actual = loadDeserializedResourceAs(resource, SixteenDaysDailyForecastModel.class);

        //then
        assertNotNull(actual);

        final DetailedLocationInfo location = actual.getLocation();
        assertNotNull(location);
        assertEquals(new Coordinates(51.5085, -0.1258), location.getCoordinates());
        assertEquals(2643743, location.getCityId());
        assertEquals("London", location.getCityName());
        assertEquals("GB", location.getCountryCode());
        assertEquals(0, location.getPopulation());
        assertEquals(ZoneOffset.ofTotalSeconds(3600), location.getTimeZone());

        assertEquals(1, actual.getWeatherForecasts().size());
        final DailyWeather weatherForecast = actual.getWeatherForecasts().get(0);
        assertEquals(TestMappingUtils.parseDateTime(1568977200), weatherForecast.getForecastTime());
        // TODO: Does the API provide the sunrise and sunset info??? It is not officially described in the API but present in the example.
        final SunlightStages sunlightStages = weatherForecast.getSunlightStages();
        assertEquals(TestMappingUtils.parseDateTime(1568958164), sunlightStages.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1569002733), sunlightStages.getSunsetTime());

        final DailyTemperature temperature = weatherForecast.getTemperature();
        assertEquals(BigDecimal.valueOf(294), temperature.getDay().asKelvin());
        assertEquals(BigDecimal.valueOf(289), temperature.getMin().asKelvin());
        assertEquals(BigDecimal.valueOf(294), temperature.getMax().asKelvin());
        assertEquals(BigDecimal.valueOf(289), temperature.getNight().asKelvin());
        assertEquals(BigDecimal.valueOf(290), temperature.getEve().asKelvin());
        assertEquals(BigDecimal.valueOf(294), temperature.getMorning().asKelvin());
        assertEquals(BigDecimal.valueOf(279), temperature.getDayFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(283), temperature.getNightFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(282), temperature.getEveFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(279), temperature.getMorningFeelsLike().asKelvin());

        final BaseAtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(BigDecimal.valueOf(1025.04), pressure.getPressure());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(42, humidity.getHumidityPercentage());

        final DetailedWindInfo wind = weatherForecast.getWind();
        assertEquals(BigDecimal.valueOf(4.66), wind.getSpeed().asMetersPerSecond());
        assertEquals(102, wind.getDirectionDegrees());
        assertEquals(new BigDecimal("5.30"), wind.getGust().asMetersPerSecond());

        final Clouds clouds = weatherForecast.getCloudCoverage();
        assertEquals(0, clouds.getValuePercentage());

        assertEquals(1, weatherForecast.getWeatherStates().size());
        final WeatherCondition weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(800, weatherState.getId());
        assertEquals("Clear", weatherState.getName());
        assertEquals("clear sky", weatherState.getDescription());
        assertEquals("01d", weatherState.getIconId(DayTime.DAY));

        final PrecipitationForecast precipitation = weatherForecast.getPrecipitation();
        assertEquals(BigDecimal.valueOf(22.2), precipitation.getRain());
        assertEquals(BigDecimal.valueOf(24.2), precipitation.getSnow());
        assertEquals(24, precipitation.getProbabilityOfPrecipitation());
    }
}

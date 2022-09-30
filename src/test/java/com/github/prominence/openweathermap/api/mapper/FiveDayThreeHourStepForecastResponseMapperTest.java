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
import com.github.prominence.openweathermap.api.model.forecast.free.FiveDaysThreeHoursForecast;
import com.github.prominence.openweathermap.api.model.forecast.free.FiveDaysThreeHoursForecastModel;
import com.github.prominence.openweathermap.api.model.forecast.free.ThreeHourWeather;
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import com.github.prominence.openweathermap.api.model.generic.location.DetailedLocationInfo;
import com.github.prominence.openweathermap.api.model.generic.location.SunlightStages;
import com.github.prominence.openweathermap.api.model.generic.precipitation.Humidity;
import com.github.prominence.openweathermap.api.model.generic.pressure.DetailedAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.generic.temperature.TemperatureWithRange;
import com.github.prominence.openweathermap.api.model.generic.wind.DetailedWindInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FiveDayThreeHourStepForecastResponseMapperTest {

    @Test
    void testDeserialize_ShouldSucceed_WhenCalledWithFullyPopulatedJson() throws IOException {
        //given
        final String resource = "/responses/valid/5days3hours.json";

        //when
        final FiveDaysThreeHoursForecast actual = loadDeserializedResourceAs(resource, FiveDaysThreeHoursForecastModel.class);

        //then
        assertNotNull(actual);

        final DetailedLocationInfo location = actual.getLocation();
        assertNotNull(location);
        assertEquals(new Coordinates(51.5073, -0.1277), location.getCoordinates());
        assertEquals(2643743, location.getCityId());
        assertEquals("London", location.getCityName());
        assertEquals("GB", location.getCountryCode());
        assertEquals(1000000, location.getPopulation());
        assertEquals(ZoneOffset.ofTotalSeconds(0), location.getTimeZone());

        final SunlightStages sunlightStages = actual.getSunlightStages();
        assertEquals(TestMappingUtils.parseDateTime(1647324903), sunlightStages.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1647367441), sunlightStages.getSunsetTime());


        final List<ThreeHourWeather> weatherForecastList = actual.getWeatherForecasts();
        assertEquals(2, weatherForecastList.size());

        final ThreeHourWeather weatherForecast = weatherForecastList.get(0);
        assertNotNull(weatherForecast);

        assertEquals(TestMappingUtils.parseDateTime(1647345600), weatherForecast.getForecastTime());
        assertEquals(new BigDecimal("10000.00"), weatherForecast.getVisibility().asMeters());
        assertEquals(0, weatherForecast.getThreeHoursPrecipitation().getProbabilityOfPrecipitation());
        assertEquals(OffsetDateTime.of(2022, 3, 15, 12, 0, 0, 0, ZoneOffset.UTC),
                weatherForecast.getForecastTime());
        assertEquals(DayTime.DAY, weatherForecast.getPartOfDay());

        final TemperatureWithRange temperature = weatherForecast.getTemperature();
        assertNotNull(temperature);
        assertEquals(BigDecimal.valueOf(287), temperature.getTemperature().asKelvin());
        assertEquals(BigDecimal.valueOf(286), temperature.getFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(287), temperature.getMin().asKelvin());
        assertEquals(BigDecimal.valueOf(287), temperature.getMax().asKelvin());

        final DetailedAtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(BigDecimal.valueOf(1021), pressure.getPressure());
        assertEquals(BigDecimal.valueOf(1021), pressure.getSeaLevel());
        assertEquals(BigDecimal.valueOf(1018), pressure.getGroundLevel());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(62, humidity.getHumidityPercentage());

        final CloudCoverage clouds = weatherForecast.getClouds();
        assertEquals(85, clouds.getCoveragePercentage());

        final DetailedWindInfo wind = weatherForecast.getWind();
        assertEquals(BigDecimal.valueOf(3.25), wind.getSpeed().asMetersPerSecond());
        assertEquals(134, wind.getDirectionDegrees());
        assertEquals(BigDecimal.valueOf(4.45), wind.getGust().asMetersPerSecond());

        assertEquals(1, weatherForecast.getWeatherStates().size());

        final WeatherCondition weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(804, weatherState.getId());
        assertEquals("Clouds", weatherState.getName());
        assertEquals("overcast clouds: 85-100%", weatherState.getDescription());
        assertEquals("04d", weatherState.getIconId(weatherForecast.getPartOfDay()));

        final BigDecimal rain = weatherForecast.getThreeHoursPrecipitation().getRain();
        assertEquals(BigDecimal.valueOf(22.1), rain);

        final BigDecimal snow = weatherForecast.getThreeHoursPrecipitation().getSnow();
        assertEquals(BigDecimal.valueOf(13.6), snow);
    }
}

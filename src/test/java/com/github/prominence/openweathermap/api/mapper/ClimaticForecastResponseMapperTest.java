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
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.BaseAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.BaseWind;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.forecast.climatic.Location;
import com.github.prominence.openweathermap.api.model.forecast.climatic.Temperature;
import com.github.prominence.openweathermap.api.model.forecast.climatic.ThirtyDaysDailyForecast;
import com.github.prominence.openweathermap.api.model.forecast.climatic.ThirtyDaysDailyForecastModel;
import com.github.prominence.openweathermap.api.model.forecast.climatic.Weather;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClimaticForecastResponseMapperTest {

    @Test
    public void testDeserialize_ShouldSucceed_WhenCalledWithFullyPopulatedJson() throws IOException {
        //given
        final String resource = "/responses/valid/climatic.json";

        //when
        final ThirtyDaysDailyForecast actual = loadDeserializedResourceAs(resource, ThirtyDaysDailyForecastModel.class);

        //then
        assertNotNull(actual);

        final Location location = actual.getLocation();
        assertNotNull(location);
        assertEquals(new Coordinates(51.5073, -0.1277), location.getCoordinates());
        assertEquals(2643743, location.getCityId());
        assertEquals("London", location.getCityName());
        assertEquals("GB", location.getCountryCode());

        assertEquals(1, actual.getWeatherForecasts().size());
        final Weather weatherForecast = actual.getWeatherForecasts().get(0);
        assertEquals(TestMappingUtils.parseDateTime(1594382400), weatherForecast.getForecastTime());
        assertEquals(TestMappingUtils.parseDateTime(1594353335), weatherForecast.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1594412149), weatherForecast.getSunsetTime());

        final Temperature temperature = weatherForecast.getTemperature();
        assertEquals(BigDecimal.valueOf(287), temperature.getDay().asKelvin());
        assertEquals(BigDecimal.valueOf(285), temperature.getMin().asKelvin());
        assertEquals(BigDecimal.valueOf(288), temperature.getMax().asKelvin());
        assertEquals(BigDecimal.valueOf(285), temperature.getNight().asKelvin());
        assertEquals(BigDecimal.valueOf(288), temperature.getEve().asKelvin());
        assertEquals(BigDecimal.valueOf(287), temperature.getMorning().asKelvin());
        assertEquals(BigDecimal.valueOf(283), temperature.getDayFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(283), temperature.getNightFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(285), temperature.getEveFeelsLike().asKelvin());
        assertEquals(BigDecimal.valueOf(283), temperature.getMorningFeelsLike().asKelvin());

        final BaseAtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(BigDecimal.valueOf(1016), pressure.getPressure());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(84, humidity.getHumidityPercentage());

        final BaseWind wind = weatherForecast.getWind();
        assertEquals(BigDecimal.valueOf(6.78), wind.getSpeed().asMetersPerSecond());
        assertEquals(320, wind.getDirectionDegrees());

        final Clouds clouds = weatherForecast.getCloudCoverage();
        assertEquals(81, clouds.getValuePercentage());

        assertEquals(1, weatherForecast.getWeatherStates().size());
        final WeatherCondition weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(500, weatherState.getId());
        assertEquals("Rain", weatherState.getName());
        assertEquals("light rain", weatherState.getDescription());
        assertEquals("10d", weatherState.getDayIconId());

        final BigDecimal rain = weatherForecast.getPrecipitation().getRain();
        assertEquals(BigDecimal.valueOf(1.96), rain);

        final BigDecimal snow = weatherForecast.getPrecipitation().getSnow();
        assertEquals(BigDecimal.valueOf(2.21), snow);
    }
}

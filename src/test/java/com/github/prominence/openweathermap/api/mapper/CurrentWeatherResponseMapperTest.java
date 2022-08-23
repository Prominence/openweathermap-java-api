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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.model.weather.WeatherModel;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrentWeatherResponseMapperTest {

    @Test
    public void mapToWeatherOfficialJsonExample() throws IOException {
        final String jsonString = IOUtils.resourceToString("/responses/valid/current-weather-official.json", StandardCharsets.UTF_8);

        final Weather weather = deserialize(jsonString);

        assertNotNull(weather);
    }

    @Test
    public void mapToWeather() throws IOException {
        final String jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk.json", StandardCharsets.UTF_8);

        final Weather weather = deserialize(jsonString);

        assertNotNull(weather);
    }

    @Test
    public void mapToWeather_withDamagedJSON() throws IOException {
        final String jsonString = IOUtils.resourceToString("/responses/invalid/current-weather-minsk-invalid.json", StandardCharsets.UTF_8);
        assertThrows(JsonProcessingException.class, () -> deserialize(jsonString));
    }

    @Test
    public void mapToWeather_withoutDt() throws IOException {
        final String jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-dt.json", StandardCharsets.UTF_8);

        final Weather weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNull(weather.getForecastTime());
    }

    @Test
    public void mapToWeather_withTemperatureVariations() throws IOException {
        String jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk.json", StandardCharsets.UTF_8);
        Weather weather = deserialize(jsonString);

        assertNotNull(weather);
        assertEquals(new BigDecimal("2"), weather.getTemperature().getTemperature().asKelvin());
        assertEquals(new BigDecimal("0"), weather.getTemperature().getFeelsLike().asKelvin());
        assertEquals(new BigDecimal("2"), weather.getTemperature().getMin().asKelvin());
        assertEquals(new BigDecimal("2"), weather.getTemperature().getMax().asKelvin());

        // without feels like node
        jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-feelslike.json", StandardCharsets.UTF_8);
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNull(weather.getTemperature().getFeelsLike());
        assertNotNull(weather.getTemperature().getMin());
        assertNotNull(weather.getTemperature().getMax());

        // without min temperature node
        jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-mintemp.json", StandardCharsets.UTF_8);
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getTemperature().getFeelsLike());
        assertNull(weather.getTemperature().getMin());
        assertNotNull(weather.getTemperature().getMax());

        // without max temperature node
        jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-maxtemp.json", StandardCharsets.UTF_8);
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getTemperature().getFeelsLike());
        assertNotNull(weather.getTemperature().getMin());
        assertNull(weather.getTemperature().getMax());
    }

    @Test
    public void mapToWeather_withWindVariations() throws IOException {
        String jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk.json", StandardCharsets.UTF_8);

        Weather weather = deserialize(jsonString);

        assertNotNull(weather);
        assertEquals(new BigDecimal("2.00"), weather.getWind().getSpeed().asMetersPerSecond());
        assertEquals(250, weather.getWind().getDirectionDegrees());
        assertNull(weather.getWind().getGust());

        // without degrees
        jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-wind-direction.json", StandardCharsets.UTF_8);
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getWind());
        assertNull(weather.getWind().getDirectionDegrees());

        // with gust
        jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk-with-gust.json", StandardCharsets.UTF_8);
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getWind());
        assertNotNull(weather.getWind().getDirectionDegrees());
        assertEquals(new BigDecimal("2.44"), weather.getWind().getGust().asMetersPerSecond());
    }

    @Test
    public void mapToWeather_withRainVariations() throws IOException {
        final String jsonWith1Hr = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-3h.json", StandardCharsets.UTF_8);;
        final String jsonWith3Hr = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-1h.json", StandardCharsets.UTF_8);;
        final String jsonWithBoth = IOUtils.resourceToString("/responses/valid/current-weather-minsk.json", StandardCharsets.UTF_8);

        Weather weather = deserialize(jsonWith1Hr);

        // with 1h level only
        assertNotNull(weather.getRain());
        assertEquals(new BigDecimal("0.1"), weather.getRain().getOneHourLevel());
        assertNull(weather.getRain().getThreeHourLevel());

        weather = deserialize(jsonWith3Hr);

        // with 3h level only
        assertNotNull(weather.getRain());
        assertNull(weather.getRain().getOneHourLevel());
        assertEquals(new BigDecimal("0.6"), weather.getRain().getThreeHourLevel());

        weather = deserialize(jsonWithBoth);

        // with both levels
        assertNotNull(weather.getRain());
        assertEquals(new BigDecimal("0.1"), weather.getRain().getOneHourLevel());
        assertEquals(new BigDecimal("0.6"), weather.getRain().getThreeHourLevel());
    }

    @Test
    public void mapToWeather_withSnowVariations() throws IOException {
        final String jsonWith1Hr = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-3h.json", StandardCharsets.UTF_8);;
        final String jsonWith3Hr = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-1h.json", StandardCharsets.UTF_8);;
        final String jsonWithBoth = IOUtils.resourceToString("/responses/valid/current-weather-minsk.json", StandardCharsets.UTF_8);

        Weather weather = deserialize(jsonWith1Hr);

        // with 1h level only
        assertNotNull(weather.getSnow());
        assertEquals(new BigDecimal("0.2"), weather.getSnow().getOneHourLevel());
        assertNull(weather.getSnow().getThreeHourLevel());

        weather = deserialize(jsonWith3Hr);

        // with 3h level only
        assertNotNull(weather.getSnow());
        assertNull(weather.getSnow().getOneHourLevel());
        assertEquals(new BigDecimal("0.7"), weather.getSnow().getThreeHourLevel());

        weather = deserialize(jsonWithBoth);

        // with both levels
        assertNotNull(weather.getSnow());
        assertEquals(new BigDecimal("0.2"), weather.getSnow().getOneHourLevel());
        assertEquals(new BigDecimal("0.7"), weather.getSnow().getThreeHourLevel());
    }

    @Test
    public void mapToWeather_withLocationVariations() throws IOException {
        String jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk.json", StandardCharsets.UTF_8);
        Weather weather = deserialize(jsonString);

        assertNotNull(weather.getLocation().getCoordinates());
        assertNotNull(weather.getLocation().getCountryCode());

        // without coordinates and country code
        jsonString = IOUtils.resourceToString("/responses/valid/current-weather-minsk-missing-coord-and-country.json", StandardCharsets.UTF_8);
        weather = deserialize(jsonString);
        assertNull(weather.getLocation().getCoordinates());
        assertNull(weather.getLocation().getCountryCode());
    }

    private static Weather deserialize(final String jsonString) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, WeatherModel.class);
    }
}

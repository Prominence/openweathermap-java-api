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

package com.github.prominence.openweathermap.api.request.weather;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrentWeatherIntegrationTest extends ApiTest {
    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsJava_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final Weather weather = getClient()
                .currentWeather()
                .byCoordinates(new Coordinates(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getForecastTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsJSON_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String weatherJson = getClient()
                .currentWeather()
                .byCoordinates(new Coordinates(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsXML_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String weatherXml = getClient()
                .currentWeather()
                .byCoordinates(new Coordinates(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsHTML_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String weatherHtml = getClient()
                .currentWeather()
                .byCoordinates(new Coordinates(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asHTML();

        assertTrue(weatherHtml.startsWith("<"));
    }
}

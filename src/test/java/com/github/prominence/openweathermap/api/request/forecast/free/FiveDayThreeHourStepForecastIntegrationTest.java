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

package com.github.prominence.openweathermap.api.request.forecast.free;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.forecast.free.FiveDaysThreeHoursForecast;
import com.github.prominence.openweathermap.api.model.forecast.free.Weather;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiveDayThreeHourStepForecastIntegrationTest extends ApiTest {
    @Test
    public void whenGetForecastByCityNameRequestAsJava_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final FiveDaysThreeHoursForecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (Weather weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherStates());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCityNameRequestAsJSON_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCityNameRequestAsXML_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCityNameAndCountryCodeRequestAsJava_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final FiveDaysThreeHoursForecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (Weather weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherStates());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCityNameAndCountryCodeRequestAsJSON_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCityNameAndCountryCodeRequestAsXML_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCityNameAndStateCodeAndCountryCodeRequestAsJava_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final FiveDaysThreeHoursForecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCityName("New York")
                .language(Language.CHINESE_TRADITIONAL)
                .unitSystem(UnitSystem.STANDARD)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (Weather weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherStates());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCityNameAndStateCodeAndCountryCodeRequestAsJSON_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCityName("New York")
                .language(Language.SPANISH)
                .unitSystem(UnitSystem.IMPERIAL)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCityNameAndStateCodeAndCountryCodeRequestAsXML_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCityName("New York")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCoordinatesRequestAsJava_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final FiveDaysThreeHoursForecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCoordinates(new Coordinates(5, 5))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (Weather weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherStates());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCoordinatesRequestAsJSON_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCoordinates(new Coordinates(5, 5))
                .language(Language.SPANISH)
                .unitSystem(UnitSystem.IMPERIAL)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCoordinatesRequestAsXML_thenReturnNotNull() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCoordinates(new Coordinates(5, 5))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCityNameAsyncRequestAsJava_thenReturnNotNull() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final CompletableFuture<FiveDaysThreeHoursForecast> forecastFuture = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieveAsync()
                .asJava();

        assertNotNull(forecastFuture);
        System.out.println(forecastFuture.get());
    }

    @Test
    public void whenRequestCurrentWeatherForInvalidLocation_thenThrowAnException() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        assertThrows(NoDataFoundException.class, () ->
                getClient()
                        .forecast5Day3HourStep()
                        .byCityName("invalidCity")
                        .retrieve()
                        .asJava()
        );
    }
}

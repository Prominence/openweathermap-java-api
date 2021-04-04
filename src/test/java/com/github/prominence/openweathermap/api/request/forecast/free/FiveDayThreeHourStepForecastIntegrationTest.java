/*
 * Copyright (c) 2021 Alexey Zinchenko
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
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class FiveDayThreeHourStepForecastIntegrationTest extends ApiTest {
    @Test
    public void whenGetForecastByCityNameRequestAsJava_thenReturnNotNull() {
        final Forecast forecast = getClient()
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
        for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherState());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCityNameRequestAsJSON_thenReturnNotNull() {
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
        final Forecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk", "BY")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherState());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCityNameAndCountryCodeRequestAsJSON_thenReturnNotNull() {
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCityNameAndCountryCodeRequestAsXML_thenReturnNotNull() {
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCityName("Minsk", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCityNameAndStateCodeAndCountryCodeRequestAsJava_thenReturnNotNull() {
        final Forecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCityName("New York", "NY", "US")
                .language(Language.CHINESE_TRADITIONAL)
                .unitSystem(UnitSystem.STANDARD)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherState());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCityNameAndStateCodeAndCountryCodeRequestAsJSON_thenReturnNotNull() {
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCityName("New York", "NY", "US")
                .language(Language.SPANISH)
                .unitSystem(UnitSystem.IMPERIAL)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCityNameAndStateCodeAndCountryCodeRequestAsXML_thenReturnNotNull() {
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCityName("New York", "NY", "US")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCityIdRequestAsJava_thenReturnNotNull() {
        final Forecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCityId(350001514)
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherState());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCityIdRequestAsJSON_thenReturnNotNull() {
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCityId(350001514)
                .language(Language.SPANISH)
                .unitSystem(UnitSystem.IMPERIAL)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCityIdRequestAsXML_thenReturnNotNull() {
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCityId(350001514)
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCoordinatesRequestAsJava_thenReturnNotNull() {
        final Forecast forecast = getClient()
                .forecast5Day3HourStep()
                .byCoordinate(Coordinate.of(5, 5))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherState());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByCoordinatesRequestAsJSON_thenReturnNotNull() {
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byCoordinate(Coordinate.of(5, 5))
                .language(Language.SPANISH)
                .unitSystem(UnitSystem.IMPERIAL)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByCoordinatesRequestAsXML_thenReturnNotNull() {
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byCoordinate(Coordinate.of(5, 5))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByZipCodeInUSARequestAsJava_thenReturnNotNull() {
        final Forecast forecast = getClient()
                .forecast5Day3HourStep()
                .byZipCodeInUSA("10005")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherState());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByZipCodeInUSARequestAsJSON_thenReturnNotNull() {
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byZipCodeInUSA("10005")
                .language(Language.SPANISH)
                .unitSystem(UnitSystem.IMPERIAL)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByZipCodeInUSARequestAsXML_thenReturnNotNull() {
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byZipCodeInUSA("10005")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByZipCodeAndCountryCodeRequestAsJava_thenReturnNotNull() {
        final Forecast forecast = getClient()
                .forecast5Day3HourStep()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieve()
                .asJava();

        assertNotNull(forecast);
        assertNotNull(forecast.getLocation());
        assertNotNull(forecast.getWeatherForecasts());
        for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
            assertNotNull(weatherForecast.getWeatherState());
            assertNotNull(weatherForecast.getForecastTime());
            assertNotNull(weatherForecast.getTemperature());
            assertNotNull(weatherForecast.getAtmosphericPressure());
            assertNotNull(weatherForecast.getHumidity());
            assertNotNull(weatherForecast.getWind());
        }
    }

    @Test
    public void whenGetForecastByZipCodeAndCountryCodeRequestAsJSON_thenReturnNotNull() {
        final String forecastJson = getClient()
                .forecast5Day3HourStep()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.SPANISH)
                .unitSystem(UnitSystem.IMPERIAL)
                .count(15)
                .retrieve()
                .asJSON();

        assertTrue(forecastJson.startsWith("{"));
    }

    @Test
    public void whenGetForecastByZipCodeAndCountryCodeRequestAsXML_thenReturnNotNull() {
        final String forecastXml = getClient()
                .forecast5Day3HourStep()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(forecastXml.startsWith("<"));
    }

    @Test
    public void whenGetForecastByCityNameAsyncRequestAsJava_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<Forecast> forecastFuture = getClient()
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
    public void whenGetForecastByCityNameAsyncRequestAsJSON_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> forecastFuture = getClient()
                .forecast5Day3HourStep()
                .byCityId(350001514)
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieveAsync()
                .asJSON();

        assertNotNull(forecastFuture);
        System.out.println(forecastFuture.get());
    }

    @Test
    public void whenGetForecastByCityNameAsyncRequestAsXML_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> forecastFuture = getClient()
                .forecast5Day3HourStep()
                .byCityId(350001514)
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .count(15)
                .retrieveAsync()
                .asXML();

        assertNotNull(forecastFuture);
        System.out.println(forecastFuture.get());
    }

    @Test(expected = InvalidAuthTokenException.class)
    public void whenRequestCurrentWeatherWithInvalidApiKey_thenThrowAnException() {
        OpenWeatherMapClient client = new OpenWeatherMapClient("invalidKey");
        client
                .forecast5Day3HourStep()
                .byCityId(350001514)
                .retrieve()
                .asJSON();
    }

    @Test(expected = NoDataFoundException.class)
    public void whenRequestCurrentWeatherForInvalidLocation_thenThrowAnException() {
        getClient()
                .forecast5Day3HourStep()
                .byCityName("invalidCity")
                .retrieve()
                .asJava();
    }
}

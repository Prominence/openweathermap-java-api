/*
 * Copyright (c) 2022 Alexey Zinchenko
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
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrentWeatherIntegrationTest extends ApiTest {
    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .byCoordinates(Coordinates.of(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getCalculationTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .byCoordinates(Coordinates.of(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .byCoordinates(Coordinates.of(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsHTML_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .byCoordinates(Coordinates.of(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asHTML();

        assertTrue(weatherHtml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .byCityName("Minsk")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getCalculationTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .byCityName("Minsk")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .byCityName("Minsk")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.STANDARD)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameRequestAsHTML_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .byCityName("Minsk")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asHTML();

        assertTrue(weatherHtml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndCountryCodeRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .byCityName("Minsk", "BY")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getCalculationTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndCountryCodeRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .byCityName("Minsk", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndCountryCodeRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .byCityName("Minsk", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndCountryCodeRequestAsHTML_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .byCityName("Minsk", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.STANDARD)
                .retrieve()
                .asHTML();

        assertTrue(weatherHtml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndStateCodeAndCountryCodeRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .byCityName("New York", "ny", "us")
                .language(Language.SLOVAK)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getCalculationTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndStateCodeAndCountryCodeRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .byCityName("New York", "ny", "us")
                .language(Language.HUNGARIAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndStateCodeAndCountryCodeRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .byCityName("New York", "ny", "us")
                .language(Language.ROMANIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityNameAndStateCodeAndCountryCodeRequestAsHTML_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .byCityName("New York", "ny", "us")
                .language(Language.ARABIC)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asHTML();

        assertTrue(weatherHtml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityIdRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .byCityId(350001514)
                .language(Language.GERMAN)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getCalculationTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityIdRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .byCityId(350001514)
                .language(Language.GERMAN)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityIdRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .byCityId(350001514)
                .language(Language.GERMAN)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByCityIdRequestAsHTML_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .byCityId(350001514)
                .language(Language.GERMAN)
                .retrieve()
                .asXML();

        assertTrue(weatherHtml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeAndCountryRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getCalculationTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeAndCountryRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeAndCountryRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeAndCountryRequestAsHTML_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asHTML();

        assertTrue(weatherHtml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeInUSARequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .byZipCodeInUSA("10006")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(weather);
        assertNotNull(weather.getWeatherStates());
        assertNotNull(weather.getCalculationTime());
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getLocation());
        assertNotNull(weather.getAtmosphericPressure());
        assertNotNull(weather.getHumidity());
        assertNotNull(weather.getWind());
        System.out.println(weather);
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeInUSARequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .byZipCodeInUSA("10006")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeInUSARequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .byZipCodeInUSA("10006")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetSingleCurrentWeatherByZipCodeInUSARequestAsHTML_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .byZipCodeInUSA("10006")
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asHTML();

        assertTrue(weatherHtml.startsWith("<"));
    }

    @Test
    @Disabled
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsJava_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<Weather> weatherFuture = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJava();

        assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }

    @Test
    @Disabled
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsJson_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> weatherFuture = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJSON();

        assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }

    @Test
    @Disabled
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsXml_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> weatherXmlFuture = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asXML();

        assertNotNull(weatherXmlFuture);
        System.out.println(weatherXmlFuture.get());
    }

    @Test
    @Disabled
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsHtml_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> weatherFuture = getClient()
                .currentWeather()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asHTML();

        assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }
}

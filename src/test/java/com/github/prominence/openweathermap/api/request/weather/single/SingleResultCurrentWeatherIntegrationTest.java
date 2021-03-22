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

package com.github.prominence.openweathermap.api.request.weather.single;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SingleResultCurrentWeatherIntegrationTest extends ApiTest {

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .single()
                .byCoordinate(Coordinate.forValues(5, 5))
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weather);
        System.out.println(weather);
    }

    @Test
    public void whenGetSingleCurrentWeatherByCityIdRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .single()
                .byCityId(350001514)
                .language(Language.GERMAN)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weather);
        System.out.println(weather);
    }

    @Test
    public void whenGetSingleCurrentWeatherByCityNameRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .single()
                .byCityName("Minsk")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weather);
        System.out.println(weather);
    }

    @Test
    public void whenGetSingleCurrentWeatherByCityNameAndCountryCodeRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .single()
                .byCityName("Moscow", "ru")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weather);
        System.out.println(weather);
    }

    @Test
    public void whenGetSingleCurrentWeatherByZipCodeAndCountryRequestAsJava_thenReturnNotNull() {
        final Weather weather = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weather);
        System.out.println(weather);
    }

    @Test
    public void whenGetAnySingleCurrentRequestWeatherAsJson_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        Assert.assertNotNull(weatherJson);
        System.out.println(weatherJson);
    }

    @Test
    public void whenGetAnySingleCurrentRequestWeatherAsXml_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asXML();

        Assert.assertNotNull(weatherXml);
        System.out.println(weatherXml);
    }

    @Test
    public void whenGetAnySingleCurrentWeatherRequestAsHtml_thenReturnNotNull() {
        final String weatherHtml = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asHTML();

        Assert.assertNotNull(weatherHtml);
        System.out.println(weatherHtml);
    }

    @Test
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsXml_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> weatherXmlFuture = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asXML();

        Assert.assertNotNull(weatherXmlFuture);
        System.out.println(weatherXmlFuture.get());
    }

    @Test
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsJava_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<Weather> weatherFuture = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJava();

        Assert.assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }

    @Test
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsJson_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> weatherFuture = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJSON();

        Assert.assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }

    @Test
    public void whenGetAnySingleCurrentWeatherAsyncRequestAsHtml_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> weatherFuture = getClient()
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asHTML();

        Assert.assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }

    @Test(expected = InvalidAuthTokenException.class)
    public void whenRequestCurrentWeatherWithInvalidApiKey_thenThrowAnException() {
        OpenWeatherMapClient client = new OpenWeatherMapClient("invalidKey");
        client
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.forValues(34.53, 66.74), 10)
                .retrieve()
                .asJSON();
    }

    @Test(expected = NoDataFoundException.class)
    public void whenRequestCurrentWeatherForInvalidLocation_thenThrowAnException() {
        getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.forValues(90.00, 66.74), 10)
                .retrieve()
                .asJava();
    }
}

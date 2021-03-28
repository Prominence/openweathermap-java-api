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

package com.github.prominence.openweathermap.api.request.weather.multiple;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.CoordinateRectangle;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MultipleResultCurrentWeatherIntegrationTest extends ApiTest {
    @Test
    public void whenGetMultipleCurrentWeatherByCoordinateRectangleRequestAsJava_thenReturnNotNull() {
        final List<Weather> weatherList = getClient()
                .currentWeather()
                .multiple()
                .byRectangle(
                        new CoordinateRectangle.Builder()
                                .setLongitudeLeft(12)
                                .setLatitudeBottom(32)
                                .setLongitudeRight(15)
                                .setLatitudeTop(37)
                                .build(),
                        10
                )
                .language(Language.ROMANIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weatherList);
        for (Weather weather : weatherList) {
            Assert.assertNotNull(weather);
            Assert.assertNotNull(weather.getState());
            Assert.assertNotNull(weather.getDescription());
            Assert.assertNotNull(weather.getCalculationTime());
            Assert.assertNotNull(weather.getTemperature());
            Assert.assertNotNull(weather.getLocation());
            Assert.assertNotNull(weather.getAtmosphericPressure());
            Assert.assertNotNull(weather.getHumidity());
            Assert.assertNotNull(weather.getWind());
        }
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCoordinateRectangleRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .multiple()
                .byRectangle(CoordinateRectangle.withValues(12, 32, 15, 37), 10)
                .language(Language.ROMANIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        Assert.assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCitiesInCycleRequestAsJava_thenReturnNotNull() {
        final List<Weather> weatherList = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5))
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weatherList);
        for (Weather weather : weatherList) {
            System.out.println(weather);
            Assert.assertNotNull(weather);
            Assert.assertNotNull(weather.getState());
            Assert.assertNotNull(weather.getDescription());
            Assert.assertNotNull(weather.getCalculationTime());
            Assert.assertNotNull(weather.getTemperature());
            Assert.assertNotNull(weather.getLocation());
            Assert.assertNotNull(weather.getAtmosphericPressure());
            Assert.assertNotNull(weather.getHumidity());
            Assert.assertNotNull(weather.getWind());
        }
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCitiesInCycleRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5))
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asJSON();

        Assert.assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCitiesInCycleRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5))
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asXML();

        Assert.assertTrue(weatherXml.startsWith("<"));
        System.out.println(weatherXml);
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCitiesInCycleAndCountRequestAsJava_thenReturnNotNull() {
        final List<Weather> weatherList = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5), 10)
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asJava();

        Assert.assertNotNull(weatherList);
        for (Weather weather : weatherList) {
            System.out.println(weather);
            Assert.assertNotNull(weather);
            Assert.assertNotNull(weather.getState());
            Assert.assertNotNull(weather.getDescription());
            Assert.assertNotNull(weather.getCalculationTime());
            Assert.assertNotNull(weather.getTemperature());
            Assert.assertNotNull(weather.getLocation());
            Assert.assertNotNull(weather.getAtmosphericPressure());
            Assert.assertNotNull(weather.getHumidity());
            Assert.assertNotNull(weather.getWind());
        }
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCitiesInCycleAndCountRequestAsJSON_thenReturnNotNull() {
        final String weatherJson = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5), 10)
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asJSON();

        Assert.assertTrue(weatherJson.startsWith("{"));
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCitiesInCycleAndCountRequestAsXML_thenReturnNotNull() {
        final String weatherXml = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5), 10)
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieve()
                .asXML();

        Assert.assertTrue(weatherXml.startsWith("<"));
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCoordinateAsyncRequestAsJava_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<List<Weather>> weatherListFuture = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5), 10)
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieveAsync()
                .asJava();

        Assert.assertNotNull(weatherListFuture);
        List<Weather> weatherList = weatherListFuture.get();
        Assert.assertTrue(weatherList.size() > 0);
        System.out.println(weatherList);
    }

    @Test
    public void whenGetMultipleCurrentWeatherByCoordinateAsyncRequestAsJson_thenReturnNotNull() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> weatherFuture = getClient()
                .currentWeather()
                .multiple()
                .byCitiesInCycle(Coordinate.of(55.5, 37.5), 10)
                .language(Language.GERMAN)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieveAsync()
                .asJSON();

        Assert.assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }

    @Test(expected = InvalidAuthTokenException.class)
    public void whenRequestCurrentWeatherWithInvalidApiKey_thenThrowAnException() {
        OpenWeatherMapClient client = new OpenWeatherMapClient("invalidKey");
        client
                .currentWeather()
                .single()
                .byCityName("London")
                .retrieve()
                .asJSON();
    }

    @Test(expected = NoDataFoundException.class)
    public void whenRequestCurrentWeatherForInvalidLocation_thenThrowAnException() {
        getClient()
                .currentWeather()
                .single()
                .byCityName("InvalidCity")
                .retrieve()
                .asJava();
    }
}

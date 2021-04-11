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
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

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

        assertNotNull(weatherList);
        for (Weather weather : weatherList) {
            assertNotNull(weather);
            assertNotNull(weather.getWeatherState());
            assertNotNull(weather.getCalculationTime());
            assertNotNull(weather.getTemperature());
            assertNotNull(weather.getLocation());
            assertNotNull(weather.getAtmosphericPressure());
            assertNotNull(weather.getHumidity());
            assertNotNull(weather.getWind());
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

        assertTrue(weatherJson.startsWith("{"));
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

        assertNotNull(weatherList);
        for (Weather weather : weatherList) {
            System.out.println(weather);
            assertNotNull(weather);
            assertNotNull(weather.getWeatherState());
            assertNotNull(weather.getCalculationTime());
            assertNotNull(weather.getTemperature());
            assertNotNull(weather.getLocation());
            assertNotNull(weather.getAtmosphericPressure());
            assertNotNull(weather.getHumidity());
            assertNotNull(weather.getWind());
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

        assertTrue(weatherJson.startsWith("{"));
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

        assertTrue(weatherXml.startsWith("<"));
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

        assertNotNull(weatherList);
        for (Weather weather : weatherList) {
            System.out.println(weather);
            assertNotNull(weather);
            assertNotNull(weather.getWeatherState());
            assertNotNull(weather.getCalculationTime());
            assertNotNull(weather.getTemperature());
            assertNotNull(weather.getLocation());
            assertNotNull(weather.getAtmosphericPressure());
            assertNotNull(weather.getHumidity());
            assertNotNull(weather.getWind());
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

        assertTrue(weatherJson.startsWith("{"));
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

        assertTrue(weatherXml.startsWith("<"));
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

        assertNotNull(weatherListFuture);
        List<Weather> weatherList = weatherListFuture.get();
        assertTrue(weatherList.size() > 0);
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

        assertNotNull(weatherFuture);
        System.out.println(weatherFuture.get());
    }

    @Test
    public void whenRequestCurrentWeatherWithInvalidApiKey_thenThrowAnException() {
        OpenWeatherMapClient client = new OpenWeatherMapClient("invalidKey");
        assertThrows(InvalidAuthTokenException.class, () ->
                client
                        .currentWeather()
                        .single()
                        .byCityName("London")
                        .retrieve()
                        .asJSON()
        );
    }

    @Test
    public void whenRequestCurrentWeatherForInvalidLocation_thenThrowAnException() {
        assertThrows(NoDataFoundException.class, () ->
                getClient()
                        .currentWeather()
                        .single()
                        .byCityName("InvalidCity")
                        .retrieve()
                        .asJava()
        );
    }
}

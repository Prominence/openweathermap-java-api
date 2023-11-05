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

package com.github.prominence.openweathermap.api.request.onecall.current;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.OneCallResultOptions;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.onecall.current.CurrentWeatherData;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentWeatherOneCallApi3IntegrationTest extends ApiTest {
    @Test
    public void whenRetrieveCurrentOneCallResponseAsJava_thenOk() {
        final CurrentWeatherData currentWeatherData = getClient()
                .oneCall3()
                .current()
                .byCoordinate(Coordinate.of(53.54, 27.34))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        assertNotNull(currentWeatherData);
        assertNotNull(currentWeatherData.getDailyList().get(0).getSummary());
    }

    @Test
    public void whenRetrieveCurrentOneCallResponseAsJSON_thenOk() {
        final String responseJson = getClient()
                .oneCall3()
                .current()
                .byCoordinate(Coordinate.of(53.54, 27.34))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        assertNotNull(responseJson);
        assertNotEquals("", responseJson);
    }

    @Test
    public void whenRetrieveCurrentOneCallResponseWithExclusionAsJava_thenOk() {
        final CurrentWeatherData currentWeatherData = getClient()
                .oneCall3()
                .current()
                .byCoordinate(Coordinate.of(53.54, 27.34))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .exclude(OneCallResultOptions.CURRENT, OneCallResultOptions.MINUTELY)
                .retrieve()
                .asJava();

        assertNotNull(currentWeatherData);
        assertNull(currentWeatherData.getCurrent());
        assertNull(currentWeatherData.getMinutelyList());
        assertNotNull(currentWeatherData.getDailyList().get(0).getSummary());
    }

    @Test
    public void whenRetrieveCurrentOneCallAsyncResponseAsJava_thenOk() throws ExecutionException, InterruptedException {
        final CompletableFuture<CurrentWeatherData> currentWeatherDataFuture = getClient()
                .oneCall3()
                .current()
                .byCoordinate(Coordinate.of(53.54, 27.34))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJava();

        assertNotNull(currentWeatherDataFuture);
        assertNotNull(currentWeatherDataFuture.get());
        assertNotNull(currentWeatherDataFuture.get().getDailyList().get(0).getSummary());
    }

    @Test
    public void whenRetrieveCurrentOneCallAsyncResponseAsJSON_thenOk() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> responseJsonFuture = getClient()
                .oneCall3()
                .current()
                .byCoordinate(Coordinate.of(53.54, 27.34))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJSON();

        assertNotNull(responseJsonFuture);
        final String responseJson = responseJsonFuture.get();
        assertNotNull(responseJson);
    }

    @Test
    public void whenRequestOnecallWithInvalidApiKey_thenThrowAnException() {
        OpenWeatherMapClient client = new OpenWeatherMapClient("invalidKey");
        assertThrows(InvalidAuthTokenException.class, () ->
                client
                        .oneCall3()
                        .current()
                        .byCoordinate(Coordinate.of(53.54, 27.34))
                        .language(Language.ENGLISH)
                        .unitSystem(UnitSystem.METRIC)
                        .retrieve()
                        .asJSON()
        );
    }
}

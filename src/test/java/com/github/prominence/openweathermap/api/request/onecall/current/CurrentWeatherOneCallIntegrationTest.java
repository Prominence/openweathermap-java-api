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

package com.github.prominence.openweathermap.api.request.onecall.current;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.core.net.HttpClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.OneCallResultOptions;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import com.github.prominence.openweathermap.api.model.onecall.current.OneCallCurrentForecast;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrentWeatherOneCallIntegrationTest extends ApiTest {

    @Test
    public void testBuilder_ShouldGenerateExpectedUrl_whenCalledWithValidData() {
        //given
        final HttpClient httpClient = mock(HttpClient.class);
        final ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        final ApiConfiguration configuration = ApiConfiguration.builder().apiKey("apiKeyValue").httpClient(httpClient).build();
        when(httpClient.executeGetRequest(urlCaptor.capture())).thenReturn("{}");

        //when
        final OneCallCurrentForecast actual = new OpenWeatherMapClient(configuration)
                .oneCall()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .language(Language.ENGLISH)
                .exclude(OneCallResultOptions.CURRENT, OneCallResultOptions.MINUTELY)
                .retrieve()
                .asJava();

        //then
        assertEquals("https://api.openweathermap.org/data/3.0/onecall?mode=json&appid=apiKeyValue&lon=27.34&exclude=current,minutely&units=standard&lang=en&lat=53.54", urlCaptor.getValue());
    }

    @Test
    public void whenRetrieveCurrentOneCallResponseAsJava_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        Assumptions.assumeTrue(System.getenv(RUN_ONE_CALL) != null, "Skipping one-call API calls.");
        final OneCallCurrentForecast currentWeatherData = getClient()
                .oneCall()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .language(Language.ENGLISH)
                .retrieve()
                .asJava();

        assertNotNull(currentWeatherData);
    }

    @Test
    public void whenRetrieveCurrentOneCallResponseAsJSON_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        Assumptions.assumeTrue(System.getenv(RUN_ONE_CALL) != null, "Skipping one-call API calls.");
        final String responseJson = getClient()
                .oneCall()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .language(Language.ENGLISH)
                .retrieve()
                .asJSON(UnitSystem.METRIC);

        assertNotNull(responseJson);
        assertNotEquals("", responseJson);
        System.out.println(responseJson);
    }

    @Test
    public void whenRetrieveCurrentOneCallResponseWithExclusionAsJava_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        Assumptions.assumeTrue(System.getenv(RUN_ONE_CALL) != null, "Skipping one-call API calls.");
        final OneCallCurrentForecast currentWeatherData = getClient()
                .oneCall()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .language(Language.ENGLISH)
                .exclude(OneCallResultOptions.CURRENT, OneCallResultOptions.MINUTELY)
                .retrieve()
                .asJava();

        assertNotNull(currentWeatherData);
        assertNull(currentWeatherData.getCurrentWeather());
        assertNull(currentWeatherData.getMinutelyForecast());
    }

    @Test
    public void whenRetrieveCurrentOneCallAsyncResponseAsJava_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        Assumptions.assumeTrue(System.getenv(RUN_ONE_CALL) != null, "Skipping one-call API calls.");
        final CompletableFuture<OneCallCurrentForecast> currentWeatherDataFuture = getClient()
                .oneCall()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .language(Language.ENGLISH)
                .retrieveAsync()
                .asJava();

        assertNotNull(currentWeatherDataFuture);
        assertNotNull(currentWeatherDataFuture.get());
    }

    @Test
    public void whenRetrieveCurrentOneCallAsyncResponseAsJSON_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        Assumptions.assumeTrue(System.getenv(RUN_ONE_CALL) != null, "Skipping one-call API calls.");
        final CompletableFuture<String> responseJsonFuture = getClient()
                .oneCall()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .language(Language.ENGLISH)
                .retrieveAsync()
                .asJSON(UnitSystem.METRIC);

        assertNotNull(responseJsonFuture);
        final String responseJson = responseJsonFuture.get();
        assertNotNull(responseJson);
        System.out.println(responseJson);
    }

    @Test
    public void whenRequestOnecallWithInvalidApiKey_thenThrowAnException() {
        final HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.executeGetRequest(anyString())).thenThrow(InvalidAuthTokenException.class);
        final OpenWeatherMapClient client = new OpenWeatherMapClient(ApiConfiguration.builder()
                .apiKey("invalidKey")
                .httpClient(httpClient)
                .build());
        assertThrows(InvalidAuthTokenException.class, () ->
                client.oneCall()
                        .current()
                        .byCoordinates(new Coordinates(53.54, 27.34))
                        .language(Language.ENGLISH)
                        .retrieve()
                        .asJSON(UnitSystem.METRIC)
        );
    }
}

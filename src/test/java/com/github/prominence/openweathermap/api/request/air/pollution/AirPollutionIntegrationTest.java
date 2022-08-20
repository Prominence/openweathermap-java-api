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

package com.github.prominence.openweathermap.api.request.air.pollution;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionDetails;
import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AirPollutionIntegrationTest extends ApiTest {
    @Test
    public void whenRetrieveCurrentAirPollutionResponseAsJava_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final AirPollutionDetails airPollutionDetails = getClient()
                .airPollution()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieve()
                .asJava();

        assertNotNull(airPollutionDetails);
        airPollutionDetails.getAirPollutionConcentration().forEach(airPollutionRecord -> {
            assertNotNull(airPollutionRecord);
            System.out.println(airPollutionRecord);
        });
    }

    @Test
    public void whenRetrieveCurrentAirPollutionResponseAsJSON_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String jsonString = getClient()
                .airPollution()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieve()
                .asJSON();

        assertNotNull(jsonString);
        System.out.println(jsonString);
    }

    @Test
    public void whenRetrieveCurrentAirPollutionAsyncResponseAsJava_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final CompletableFuture<AirPollutionDetails> pollutionDetailsFuture = getClient()
                .airPollution()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieveAsync()
                .asJava();

        assertNotNull(pollutionDetailsFuture);
        assertNotNull(pollutionDetailsFuture.get());
    }

    @Test
    public void whenRetrieveCurrentAirPollutionAsyncResponseAsJSON_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final CompletableFuture<String> jsonStringFuture = getClient()
                .airPollution()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieveAsync()
                .asJSON();

        assertNotNull(jsonStringFuture);
        final String jsonString = jsonStringFuture.get();
        assertNotNull(jsonString);
        System.out.println(jsonString);
    }

    @Test
    public void whenRetrieveForecastAirPollutionResponseAsJava_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final AirPollutionDetails airPollutionDetails = getClient()
                .airPollution()
                .forecast()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieve()
                .asJava();

        assertNotNull(airPollutionDetails);
        airPollutionDetails.getAirPollutionConcentration().forEach(airPollutionRecord -> {
            assertNotNull(airPollutionRecord);
            System.out.println(airPollutionRecord);
        });
    }

    @Test
    public void whenRetrieveForecastAirPollutionResponseAsJSON_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String jsonString = getClient()
                .airPollution()
                .forecast()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieve()
                .asJSON();

        assertNotNull(jsonString);
        System.out.println(jsonString);
    }

    @Test
    public void whenRetrieveForecastAirPollutionAsyncResponseAsJava_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final CompletableFuture<AirPollutionDetails> pollutionDetailsFuture = getClient()
                .airPollution()
                .forecast()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieveAsync()
                .asJava();

        assertNotNull(pollutionDetailsFuture);
        assertNotNull(pollutionDetailsFuture.get());
    }

    @Test
    public void whenRetrieveForecastAirPollutionAsyncResponseAsJSON_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final CompletableFuture<String> jsonStringFuture = getClient()
                .airPollution()
                .forecast()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieveAsync()
                .asJSON();

        assertNotNull(jsonStringFuture);
        final String jsonString = jsonStringFuture.get();
        assertNotNull(jsonString);
        System.out.println(jsonString);
    }

    @Test
    public void whenRetrieveHistoricalAirPollutionResponseAsJava_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final AirPollutionDetails airPollutionDetails = getClient()
                .airPollution()
                .historical()
                .byCoordinateAndPeriod(new Coordinates(53.54, 27.34), 1606223802, 1606482999)
                .retrieve()
                .asJava();

        assertNotNull(airPollutionDetails);
        airPollutionDetails.getAirPollutionConcentration().forEach(airPollutionRecord -> {
            assertNotNull(airPollutionRecord);
            System.out.println(airPollutionRecord);
        });
    }

    @Test
    public void whenRetrieveHistoricalAirPollutionResponseAsJSON_thenOk() {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final String jsonString = getClient()
                .airPollution()
                .historical()
                .byCoordinateAndPeriod(new Coordinates(53.54, 27.34), 1606223802, 1606482999)
                .retrieve()
                .asJSON();

        assertNotNull(jsonString);
        System.out.println(jsonString);
    }

    @Test
    public void whenRetrieveHistoricalAirPollutionAsyncResponseAsJava_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final CompletableFuture<AirPollutionDetails> pollutionDetailsFuture = getClient()
                .airPollution()
                .historical()
                .byCoordinateAndPeriod(new Coordinates(53.54, 27.34), 1606223802, 1606482999)
                .retrieveAsync()
                .asJava();

        assertNotNull(pollutionDetailsFuture);
        assertNotNull(pollutionDetailsFuture.get());
    }

    @Test
    public void whenRetrieveHistoricalAirPollutionAsyncResponseAsJSON_thenOk() throws ExecutionException, InterruptedException {
        Assumptions.assumeTrue(System.getenv(OPENWEATHER_API_KEY) != null, "Api key is not set, skip.");
        final CompletableFuture<String> jsonStringFuture = getClient()
                .airPollution()
                .historical()
                .byCoordinateAndPeriod(new Coordinates(53.54, 27.34), 1606223802, 1606482999)
                .retrieveAsync()
                .asJSON();

        assertNotNull(jsonStringFuture);
        final String jsonString = jsonStringFuture.get();
        assertNotNull(jsonString);
        System.out.println(jsonString);
    }
}

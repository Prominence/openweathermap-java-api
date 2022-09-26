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

package com.github.prominence.openweathermap.api.request.radiation;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.core.net.HttpClient;
import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiation;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RadiationIntegrationTest {

    @Test
    public void testBuilder_ShouldGenerateExpectedUrl_whenCalledWithValidData() {
        //given
        final HttpClient httpClient = mock(HttpClient.class);
        final ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        final ApiConfiguration configuration = ApiConfiguration.builder().apiKey("apiKeyValue").httpClient(httpClient).build();
        when(httpClient.executeGetRequest(urlCaptor.capture())).thenReturn("{}");

        //when
        final SolarRadiation actual = new OpenWeatherMapClient(configuration)
                .solarRadiation()
                .current()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .retrieve()
                .asJava();

        //then
        assertEquals("https://api.openweathermap.org/data/2.5/solar_radiation?mode=json&lon=27.34&units=standard&lat=53.54&appid=apiKeyValue", urlCaptor.getValue());
    }

    @Test
    public void testBuilder_ShouldGenerateExpectedHistoricalUrl_whenCalledWithValidData() {
        //given
        final HttpClient httpClient = mock(HttpClient.class);
        final ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        final ApiConfiguration configuration = ApiConfiguration.builder().apiKey("apiKeyValue").httpClient(httpClient).build();
        when(httpClient.executeGetRequest(urlCaptor.capture())).thenReturn("{}");

        //when
        final SolarRadiation actual = new OpenWeatherMapClient(configuration)
                .solarRadiation()
                .historical()
                .byCoordinates(new Coordinates(53.54, 27.34), LocalDateTime.MIN, LocalDateTime.MAX)
                .retrieve()
                .asJava();

        //then
        assertEquals("https://api.openweathermap.org/data/2.5/solar_radiation/history?mode=json&appid=apiKeyValue&start=-31557014135601380&lon=27.34&end=31556889832777199&units=standard&lat=53.54", urlCaptor.getValue());
    }
}

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

package com.github.prominence.openweathermap.api.request.forecast.climatic;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.core.net.HttpClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.climatic.ThirtyDaysDailyForecast;
import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClimaticForecastIntegrationTest {

    @Test
    public void testBuilder_ShouldGenerateExpectedUrl_whenCalledWithValidData() {
        //given
        final HttpClient httpClient = mock(HttpClient.class);
        final ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        final ApiConfiguration configuration = ApiConfiguration.builder().apiKey("apiKeyValue").httpClient(httpClient).build();
        when(httpClient.executeGetRequest(urlCaptor.capture())).thenReturn("{}");

        //when
        final ThirtyDaysDailyForecast actual = new OpenWeatherMapClient(configuration)
                .climaticForecast30Days()
                .byCoordinates(new Coordinates(53.54, 27.34))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        //then
        assertEquals("https://pro.openweathermap.org/data/2.5/forecast/climate?mode=json&lon=27.34&units=standard&lang=en&lat=53.54&appid=apiKeyValue", urlCaptor.getValue());
    }
}

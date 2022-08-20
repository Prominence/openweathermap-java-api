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

package com.github.prominence.openweathermap.api.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.core.net.HttpURLConnectionBasedHttpClient;
import com.github.prominence.openweathermap.api.enums.ApiVariant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

class ApiConfigurationTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    void testBuilder_ShouldThrowException_WhenNullIsPassedAsApiKey() {
        //given
        final ApiConfiguration.ApiConfigurationBuilder builder = ApiConfiguration.builder();

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder.apiKey(null));

        //then + exception
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testBuilder_ShouldThrowException_WhenNullIsPassedAsHttpClient() {
        //given
        final ApiConfiguration.ApiConfigurationBuilder builder = ApiConfiguration.builder();

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder.httpClient(null));

        //then + exception
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testBuilder_ShouldThrowException_WhenNullIsPassedAsObjectMapper() {
        //given
        final ApiConfiguration.ApiConfigurationBuilder builder = ApiConfiguration.builder();

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder.objectMapper(null));

        //then + exception
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testBuilder_ShouldThrowException_WhenNullIsPassedAsDefaultTimeoutSettings() {
        //given
        final ApiConfiguration.ApiConfigurationBuilder builder = ApiConfiguration.builder();

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder.defaultTimeoutSettings(null));

        //then + exception
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testBuilder_ShouldThrowException_WhenNullIsPassedAsBaseUriMap() {
        //given
        final ApiConfiguration.ApiConfigurationBuilder builder = ApiConfiguration.builder();

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder.baseUrls(null));

        //then + exception
    }

    @Test
    void testBuilder_ShouldThrowException_WhenBaseUriMapDoesNotContainAllKeys() {
        //given
        final ApiConfiguration.ApiConfigurationBuilder builder = ApiConfiguration.builder();

        //when
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> builder.baseUrls(Collections.singletonMap(ApiVariant.BASE, ApiVariant.BASE.getBaseUrl())));

        //then + exception
    }

    @Test
    void testBuilderBuild_ShouldSetEverything_WhenEveryFieldIsPopulatedWithValidData() {
        //given
        final String apiKey = "apiKey";
        final TimeoutSettings defaultTimeoutSettings = new TimeoutSettings(40, 100);
        final Map<ApiVariant, String> baseUrls = new TreeMap<>();
        baseUrls.put(ApiVariant.BASE, "https://base.openweathermap.org/");
        baseUrls.put(ApiVariant.PRO, "https://premium.openweathermap.org/");
        final HttpURLConnectionBasedHttpClient httpClient = new HttpURLConnectionBasedHttpClient();
        final ObjectMapper objectMapper = new ObjectMapper();

        //when
        final ApiConfiguration actual = ApiConfiguration.builder()
                .apiKey(apiKey)
                .baseUrls(baseUrls)
                .defaultTimeoutSettings(defaultTimeoutSettings)
                .httpClient(httpClient)
                .objectMapper(objectMapper)
                .build();


        //then
        Assertions.assertEquals(apiKey, actual.getApiKey());
        Assertions.assertEquals(httpClient, actual.getHttpClient());
        Assertions.assertNotNull(actual.getObjectReader());
        Assertions.assertNotNull(actual.getObjectWriter());
        baseUrls.keySet().forEach(key -> {
            Assertions.assertEquals(baseUrls.get(key), actual.getBaseUrls().get(key));
        });
        Assertions.assertEquals(defaultTimeoutSettings, actual.getDefaultTimeoutSettings());
    }
}

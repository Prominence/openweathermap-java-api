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
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.core.net.HttpClient;
import com.github.prominence.openweathermap.api.core.net.HttpURLConnectionBasedHttpClient;
import com.github.prominence.openweathermap.api.enums.ApiVariant;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class ApiConfiguration {
    @NonNull
    private final String apiKey;
    @NonNull
    private final Map<ApiVariant, String> baseUrls;
    @NonNull
    private final HttpClient httpClient;
    @NonNull
    private final TimeoutSettings defaultTimeoutSettings;
    private final ObjectReader objectReader;
    private final ObjectWriter objectWriter;

    public ApiConfiguration(@NonNull String apiKey, @NonNull Map<ApiVariant, String> baseUrls, @NonNull HttpClient httpClient,
                            @NonNull ObjectMapper objectMapper, @NonNull TimeoutSettings defaultTimeoutSettings) {
        this.apiKey = apiKey;
        this.baseUrls = Collections.unmodifiableMap(baseUrls);
        this.httpClient = httpClient;
        this.defaultTimeoutSettings = defaultTimeoutSettings;
        this.objectReader = objectMapper.reader();
        this.objectWriter = objectMapper.writer();
    }

    public static ApiConfigurationBuilder builder() {
        return new ApiConfigurationBuilder();
    }

    public static class ApiConfigurationBuilder {
        private String apiKey;
        private Map<ApiVariant, String> baseUrls;
        private HttpClient httpClient = new HttpURLConnectionBasedHttpClient();
        private TimeoutSettings defaultTimeoutSettings = new TimeoutSettings();

        private ObjectMapper objectMapper = new ObjectMapper();

        public ApiConfigurationBuilder() {
            baseUrls = Arrays.stream(ApiVariant.values())
                    .collect(Collectors.toMap(Function.identity(), ApiVariant::getBaseUrl));
        }

        public ApiConfigurationBuilder apiKey(@NonNull String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public ApiConfigurationBuilder baseUrls(@NonNull Map<ApiVariant, String> baseUrls) {
            final List<ApiVariant> variants = Arrays.stream(ApiVariant.values()).collect(Collectors.toList());
            if (!baseUrls.keySet().containsAll(variants)) {
                throw new IllegalArgumentException("Not all API variants were found: " + baseUrls.keySet() + " , expected: " + variants);
            }
            this.baseUrls = new HashMap<>(baseUrls);
            return this;
        }

        public ApiConfigurationBuilder httpClient(@NonNull HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public ApiConfigurationBuilder objectMapper(@NonNull ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public ApiConfigurationBuilder defaultTimeoutSettings(@NonNull TimeoutSettings defaultTimeoutSettings) {
            this.defaultTimeoutSettings = new TimeoutSettings(defaultTimeoutSettings);
            return this;
        }

        public ApiConfiguration build() {
            return new ApiConfiguration(apiKey, baseUrls, httpClient, objectMapper, defaultTimeoutSettings);
        }

        public String toString() {
            return "ApiConfiguration.ApiConfigurationBuilder(apiKey=" + this.apiKey + ")";
        }
    }
}


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

package com.github.prominence.openweathermap.api.core.net;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.request.RequestSettings;

import java.net.URL;
import java.util.stream.Collectors;

public final class RequestExecutor {
    private static final String OWM_URL_BASE = "https://SUBDOMAIN.openweathermap.org/";

    private final RequestSettings requestSettings;

    public RequestExecutor(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public String getResponse() {
        return getResponse(Method.GET);
    }

    public String getResponse(Method httpMethod) {
        return getResponse(buildRequestUrl(), httpMethod);
    }

    /**
     * Executes call to provided API url and retrieves response in <code>String</code> representation.
     *
     * @param url             the url to make API request.
     * @param httpMethod      HTTP method to execute.
     * @return response from the request in <code>String</code> representation.
     * @throws IllegalArgumentException in case if provided parameter isn't a valid url for {@link URL} instance.
     */
    private String getResponse(String url, Method httpMethod) {
        final HttpClient httpClient = requestSettings.getHttpClient();
        httpClient.setTimeoutSettings(requestSettings.getTimeoutSettings());

        if (httpMethod == Method.GET) {
            return httpClient.executeGetRequest(url);
        } else {
            return httpClient.executePostRequest(url, getSerializedPayload());
        }
    }

    private String buildRequestUrl() {
        String baseUrl = OWM_URL_BASE.replace("SUBDOMAIN", requestSettings.getSubdomain());
        if (requestSettings.isUseInsecureConnection()) {
            baseUrl = baseUrl.replace("https", "http");
        }
        StringBuilder requestUrlBuilder = new StringBuilder(baseUrl);
        requestUrlBuilder.append(requestSettings.getUrlAppender());
        requestUrlBuilder.append('?');
        String parameters = requestSettings.getRequestParameters().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        requestUrlBuilder.append(parameters);
        return requestUrlBuilder.toString();
    }

    private String getSerializedPayload() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addSerializer(requestSettings.getPayloadClass(), requestSettings.getPayloadSerializer());
        objectMapper.registerModule(module);

        try {
            return objectMapper.writeValueAsString(requestSettings.getPayloadObject());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Method {
        GET,
        POST
    }
}

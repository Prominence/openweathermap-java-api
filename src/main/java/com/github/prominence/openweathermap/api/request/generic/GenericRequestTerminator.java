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

package com.github.prominence.openweathermap.api.request.generic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.prominence.openweathermap.api.core.net.RequestExecutor;
import com.github.prominence.openweathermap.api.enums.ApiVariant;
import com.github.prominence.openweathermap.api.enums.ResponseType;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.request.RequestSettings;

import java.io.IOException;

/**
 * Generic implementation of the request termination logic.
 *
 * @param <T> Type of the response object
 * @param <I> Type of the internal type we map the raw response to
 */
public abstract class GenericRequestTerminator<T, I extends T> {
    protected final RequestSettings requestSettings;

    public GenericRequestTerminator(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public T asJava() {
        requestSettings.setUnitSystem(UnitSystem.STANDARD);
        requestSettings.setResponseType(ResponseType.JSON);
        return mapToWeather(getRawResponse());
    }

    public String asJSON() {
        requestSettings.setResponseType(ResponseType.JSON);
        return getRawResponse();
    }

    public String asXML() {
        requestSettings.setResponseType(ResponseType.XML);
        return getRawResponse();
    }

    public String asHTML() {
        requestSettings.setResponseType(ResponseType.HTML);
        return getRawResponse();
    }

    protected String getRawResponse() {
        return new RequestExecutor(requestSettings).getResponse(ApiVariant.BASE);
    }

    private I mapToWeather(String json) {
        try {
            return requestSettings.getApiConfiguration().getObjectReader().forType(new InnerTypeReference<T, I>()).readValue(json);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Weather response", e);
        }
    }

    private static class InnerTypeReference<T, I extends T> extends TypeReference<I> {
    }
}

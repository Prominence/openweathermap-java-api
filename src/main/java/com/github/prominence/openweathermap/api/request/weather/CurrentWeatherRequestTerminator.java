/*
 * Copyright (c) 2022 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.request.weather;

import com.github.prominence.openweathermap.api.core.net.RequestExecutor;
import com.github.prominence.openweathermap.api.enums.ResponseType;
import com.github.prominence.openweathermap.api.mapper.CurrentWeatherResponseMapper;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.request.RequestSettings;

/**
 * The type Single result current weather request terminator.
 */
public class CurrentWeatherRequestTerminator {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new Single result current weather request terminator.
     *
     * @param requestSettings request settings object.
     */
    CurrentWeatherRequestTerminator(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public Weather asJava() {
        return new CurrentWeatherResponseMapper(requestSettings.getUnitSystem()).mapToWeather(asJSON());
    }

    public String asJSON() {
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

    private String getRawResponse() {
        return new RequestExecutor(requestSettings).getResponse();
    }
}

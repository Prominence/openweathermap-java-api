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

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.OneCallResultOptions;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.onecall.current.OneCallCurrentForecast;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.JsonApiTerminator;
import com.github.prominence.openweathermap.api.request.generic.JsonAsyncApiTerminator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type One call current weather request customizer.
 */
class OneCallCurrentWeatherRequestCustomizer {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new One call current weather request customizer.
     *
     * @param requestSettings request settings object.
     */
    OneCallCurrentWeatherRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public OneCallCurrentWeatherRequestCustomizer language(Language language) {
        requestSettings.setLanguage(language);
        return this;
    }

    public OneCallCurrentWeatherRequestCustomizer unitSystem(UnitSystem unitSystem) {
        this.requestSettings.setUnitSystem(unitSystem);
        return this;
    }

    public OneCallCurrentWeatherRequestCustomizer exclude(OneCallResultOptions... excludeOptions) {
        if (excludeOptions != null && excludeOptions.length > 0) {
            requestSettings.putRequestParameter("exclude", Stream.of(excludeOptions).map(OneCallResultOptions::getValue).collect(Collectors.joining(",")));
        } else {
            requestSettings.removeRequestParameter("exclude");
        }
        return this;
    }

    public JsonApiTerminator<OneCallCurrentForecast> retrieve() {
        return new OneCallCurrentWeatherRequestTerminator(requestSettings);
    }

    public JsonAsyncApiTerminator<OneCallCurrentForecast> retrieveAsync() {
        return new OneCallCurrentWeatherAsyncRequestTerminator(requestSettings);
    }
}

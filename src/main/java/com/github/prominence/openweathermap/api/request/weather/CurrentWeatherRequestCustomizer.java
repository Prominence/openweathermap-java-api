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

package com.github.prominence.openweathermap.api.request.weather;

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.CurrentWeather;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.UniversalFormatApiTerminator;
import com.github.prominence.openweathermap.api.request.generic.UniversalFormatAsyncApiTerminator;

/**
 * The type Single result current weather request customizer.
 */
public class CurrentWeatherRequestCustomizer {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new Single result current weather request customizer.
     *
     * @param requestSettings request settings object.
     */
    CurrentWeatherRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public CurrentWeatherRequestCustomizer language(Language language) {
        requestSettings.setLanguage(language);
        return this;
    }

    public CurrentWeatherRequestCustomizer unitSystem(UnitSystem unitSystem) {
        requestSettings.setUnitSystem(unitSystem);
        return this;
    }

    public UniversalFormatApiTerminator<CurrentWeather> retrieve() {
        return new CurrentWeatherRequestTerminator(requestSettings);
    }

    public UniversalFormatAsyncApiTerminator<CurrentWeather> retrieveAsync() {
        return new CurrentWeatherAsyncRequestTerminator(requestSettings);
    }
}

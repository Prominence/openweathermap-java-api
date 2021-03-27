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

package com.github.prominence.openweathermap.api.request.weather.multiple;

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.request.RequestUrlBuilder;

/**
 * The type Multiple result current weather request customizer.
 */
public class MultipleResultCitiesInCircleCurrentWeatherRequestCustomizerImpl implements MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer {
    private final RequestUrlBuilder urlBuilder;

    private Language language;
    private UnitSystem unitSystem = UnitSystem.STANDARD;

    /**
     * Instantiates a new Multiple result current weather request customizer.
     *
     * @param urlBuilder the url builder
     */
    MultipleResultCitiesInCircleCurrentWeatherRequestCustomizerImpl(RequestUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer language(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer unitSystem(UnitSystem unitSystem) {
        this.unitSystem = unitSystem;
        return this;
    }

    @Override
    public MultipleResultCitiesInCircleCurrentWeatherRequestTerminator retrieve() {
        urlBuilder.applyCustomization(language, unitSystem);
        return new MultipleResultCitiesInCircleCurrentWeatherRequestTerminatorImpl(urlBuilder, unitSystem);
    }

    @Override
    public MultipleResultCitiesInCircleCurrentWeatherAsyncRequestTerminator retrieveAsync() {
        urlBuilder.applyCustomization(language, unitSystem);
        return new MultipleResultCitiesInCircleCurrentWeatherAsyncRequestTerminatorImpl(urlBuilder, unitSystem);
    }
}
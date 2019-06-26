/*
 * Copyright (c) 2019 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.impl;

import com.github.prominence.openweathermap.api.SingleResultCurrentWeatherAsyncRequestTerminator;
import com.github.prominence.openweathermap.api.SingleResultCurrentWeatherRequestCustomizer;
import com.github.prominence.openweathermap.api.SingleResultCurrentWeatherRequestTerminator;
import com.github.prominence.openweathermap.api.enums.Accuracy;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;

public class SingleResultCurrentWeatherRequestCustomizerImpl implements SingleResultCurrentWeatherRequestCustomizer {

    private RequestUrlBuilder urlBuilder;

    private Accuracy accuracy;
    private Language language;
    private UnitSystem unitSystem;

    SingleResultCurrentWeatherRequestCustomizerImpl(RequestUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public SingleResultCurrentWeatherRequestTerminator retrieve() {
        applyCustomization();
        return new SingleResultCurrentWeatherRequestTerminatorImpl(urlBuilder, unitSystem);
    }

    @Override
    public SingleResultCurrentWeatherAsyncRequestTerminator retrieveAsync() {
        applyCustomization();
        return new SingleResultCurrentWeatherAsyncRequestTerminatorImpl(urlBuilder, unitSystem);
    }

    @Override
    public SingleResultCurrentWeatherRequestCustomizer accuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    @Override
    public SingleResultCurrentWeatherRequestCustomizer language(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public SingleResultCurrentWeatherRequestCustomizer unitSystem(UnitSystem unitSystem) {
        this.unitSystem = unitSystem;
        return this;
    }

    private void applyCustomization() {
        if (accuracy != null) {
            urlBuilder.addRequestParameter("type", accuracy.getValue());
        }
        if (language != null) {
            urlBuilder.addRequestParameter("lang", language.getValue());
        }
        if (unitSystem != null && unitSystem != UnitSystem.STANDARD) {
            urlBuilder.addRequestParameter("units", unitSystem.getValue());
        }
    }
}
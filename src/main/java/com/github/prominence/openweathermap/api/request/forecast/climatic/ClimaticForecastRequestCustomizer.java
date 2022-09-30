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

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.model.forecast.climatic.ThirtyDaysDailyForecast;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.JsonXmlApiTerminator;
import com.github.prominence.openweathermap.api.request.generic.JsonXmlAsyncApiTerminator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.prominence.openweathermap.api.request.RequestSettings.COUNT_PARAM;

public class ClimaticForecastRequestCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(ClimaticForecastRequestCustomizer.class);

    private final RequestSettings requestSettings;

    public ClimaticForecastRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public ClimaticForecastRequestCustomizer language(Language language) {
        requestSettings.setLanguage(language);
        return this;
    }

    public ClimaticForecastRequestCustomizer numberOfDays(int numberOfDays) {
        int days = numberOfDays;
        if (days > 30) {
            logger.warn("Cannot use more than 30 days for this API request. Please, specify 30 or less days. !!! Requesting information for 30 days...");
            days = 30;
        }
        requestSettings.putRequestParameter(COUNT_PARAM, Integer.toString(days));
        return this;
    }

    public JsonXmlApiTerminator<ThirtyDaysDailyForecast> retrieve() {
        return new ClimaticForecastRequestTerminator(requestSettings);
    }

    public JsonXmlAsyncApiTerminator<ThirtyDaysDailyForecast> retrieveAsync() {
        return new ClimaticForecastAsyncRequestTerminator(requestSettings);
    }
}

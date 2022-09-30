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

package com.github.prominence.openweathermap.api.request.forecast.daily;

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.model.forecast.daily.SixteenDaysDailyForecast;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.JsonXmlApiTerminator;
import com.github.prominence.openweathermap.api.request.generic.JsonXmlAsyncApiTerminator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.prominence.openweathermap.api.request.RequestSettings.COUNT_PARAM;

class DailyForecastRequestCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(DailyForecastRequestCustomizer.class);

    private final RequestSettings requestSettings;

    public DailyForecastRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public DailyForecastRequestCustomizer language(Language language) {
        requestSettings.setLanguage(language);
        return this;
    }

    public DailyForecastRequestCustomizer numberOfDays(int numberOfDays) {
        int days = numberOfDays;
        if (days > 16) {
            logger.warn("Cannot use more than 16 days for this API request. Please, specify 16 or less days. !!! Requesting information for 16 days...");
            days = 16;
        }
        requestSettings.putRequestParameter(COUNT_PARAM, Integer.toString(days));
        return this;
    }

    public JsonXmlApiTerminator<SixteenDaysDailyForecast> retrieve() {
        return new DailyForecastRequestTerminator(requestSettings);
    }

    public JsonXmlAsyncApiTerminator<SixteenDaysDailyForecast> retrieveAsync() {
        return new DailyForecastAsyncRequestTerminator(requestSettings);
    }
}

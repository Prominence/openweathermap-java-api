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

package com.github.prominence.openweathermap.api.request.forecast.hourly;

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.model.forecast.hourly.FourDaysHourlyForecast;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.JsonXmlApiTerminator;
import com.github.prominence.openweathermap.api.request.generic.JsonXmlAsyncApiTerminator;

import static com.github.prominence.openweathermap.api.request.RequestSettings.COUNT_PARAM;

class FourDaysHourlyForecastRequestCustomizer {
    private final RequestSettings requestSettings;

    public FourDaysHourlyForecastRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public FourDaysHourlyForecastRequestCustomizer language(Language language) {
        requestSettings.setLanguage(language);
        return this;
    }

    public FourDaysHourlyForecastRequestCustomizer count(int numberOfTimestamps) {
        requestSettings.putRequestParameter(COUNT_PARAM, Integer.toString(numberOfTimestamps));
        return this;
    }

    public JsonXmlApiTerminator<FourDaysHourlyForecast> retrieve() {
        return new FourDaysHourlyForecastRequestTerminator(requestSettings);
    }

    public JsonXmlAsyncApiTerminator<FourDaysHourlyForecast> retrieveAsync() {
        return new FourDaysHourlyForecastAsyncRequestTerminator(requestSettings);
    }
}

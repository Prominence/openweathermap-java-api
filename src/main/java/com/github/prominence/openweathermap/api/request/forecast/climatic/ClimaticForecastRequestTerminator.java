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

import com.github.prominence.openweathermap.api.core.net.RequestExecutor;
import com.github.prominence.openweathermap.api.enums.ApiVariant;
import com.github.prominence.openweathermap.api.model.forecast.climatic.ThirtyDaysDailyForecast;
import com.github.prominence.openweathermap.api.model.forecast.climatic.ThirtyDaysDailyForecastModel;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.GenericRequestTerminator;
import com.github.prominence.openweathermap.api.request.generic.JsonXmlApiTerminator;

class ClimaticForecastRequestTerminator
        extends GenericRequestTerminator<ThirtyDaysDailyForecast, ThirtyDaysDailyForecastModel>
        implements JsonXmlApiTerminator<ThirtyDaysDailyForecast> {

    ClimaticForecastRequestTerminator(RequestSettings requestSettings)  {
        super(requestSettings);
    }

    @Override
    public String asHTML() {
        //Method meant to be hidden as only JsonApiTerminator is exposed
        throw new UnsupportedOperationException("HTML format not supported for this API.");
    }

    protected String getRawResponse() {
        return new RequestExecutor(requestSettings).getResponse(ApiVariant.PRO);
    }

    @Override
    protected Class<ThirtyDaysDailyForecastModel> getValueType() {
        return ThirtyDaysDailyForecastModel.class;
    }
}

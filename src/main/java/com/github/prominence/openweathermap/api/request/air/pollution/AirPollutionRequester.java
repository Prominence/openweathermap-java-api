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

package com.github.prominence.openweathermap.api.request.air.pollution;

import com.github.prominence.openweathermap.api.request.air.pollution.current.CurrentAirPollutionRequester;
import com.github.prominence.openweathermap.api.request.air.pollution.forecast.ForecastAirPollutionRequester;
import com.github.prominence.openweathermap.api.request.air.pollution.historical.HistoricalAirPollutionRequester;

/**
 * The interface Air pollution requester.
 */
public interface AirPollutionRequester {
    /**
     * Current current air pollution requester.
     *
     * @return the current air pollution requester
     */
    CurrentAirPollutionRequester current();

    /**
     * Forecast forecast air pollution requester.
     *
     * @return the forecast air pollution requester
     */
    ForecastAirPollutionRequester forecast();

    /**
     * Historical historical air pollution requester.
     *
     * @return the historical air pollution requester
     */
    HistoricalAirPollutionRequester historical();
}

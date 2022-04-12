/*
 *
 *  * Copyright (c) 2021 Alexey Zinchenko
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package com.github.prominence.openweathermap.api.request.air.pollution;

import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.request.RequestUrlBuilder;
import com.github.prominence.openweathermap.api.request.air.pollution.current.CurrentAirPollutionRequester;
import com.github.prominence.openweathermap.api.request.air.pollution.current.CurrentAirPollutionRequesterImpl;
import com.github.prominence.openweathermap.api.request.air.pollution.forecast.ForecastAirPollutionRequester;
import com.github.prominence.openweathermap.api.request.air.pollution.forecast.ForecastAirPollutionRequesterImpl;
import com.github.prominence.openweathermap.api.request.air.pollution.historical.HistoricalAirPollutionRequester;
import com.github.prominence.openweathermap.api.request.air.pollution.historical.HistoricalAirPollutionRequesterImpl;

/**
 * The type Air pollution requester.
 */
public class AirPollutionRequesterImpl implements AirPollutionRequester {
    private final RequestUrlBuilder urlBuilder;

    /**
     * Instantiates a new Air pollution requester.
     *
     * @param apiKey the api key
     */
    public AirPollutionRequesterImpl(String apiKey) {
        urlBuilder =  new RequestUrlBuilder(apiKey);
    }

    @Override
    public CurrentAirPollutionRequester current() {
        urlBuilder.append("air_pollution");
        return new CurrentAirPollutionRequesterImpl(urlBuilder);
    }

    @Override
    public ForecastAirPollutionRequester forecast() {
        urlBuilder.append("air_pollution/forecast");
        return new ForecastAirPollutionRequesterImpl(urlBuilder);
    }

    @Override
    public HistoricalAirPollutionRequester historical() {
        urlBuilder.append("air_pollution/history");
        return new HistoricalAirPollutionRequesterImpl(urlBuilder);
    }
}

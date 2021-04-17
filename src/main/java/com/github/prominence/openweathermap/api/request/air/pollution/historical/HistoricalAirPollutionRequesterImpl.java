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

package com.github.prominence.openweathermap.api.request.air.pollution.historical;

import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.request.RequestUrlBuilder;
import com.github.prominence.openweathermap.api.request.air.pollution.AirPollutionRequestCustomizer;
import com.github.prominence.openweathermap.api.request.air.pollution.AirPollutionRequestCustomizerImpl;

/**
 * The type Historical air pollution requester.
 */
public class HistoricalAirPollutionRequesterImpl implements HistoricalAirPollutionRequester {
    private final RequestUrlBuilder urlBuilder;

    /**
     * Instantiates a new Historical air pollution requester.
     *
     * @param urlBuilder the url builder
     */
    public HistoricalAirPollutionRequesterImpl(RequestUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public AirPollutionRequestCustomizer byCoordinateAndPeriod(Coordinate coordinate, long startUnixTime, long endUnixTime) {
        urlBuilder.addRequestParameter("lat", String.valueOf(coordinate.getLatitude()));
        urlBuilder.addRequestParameter("lon", String.valueOf(coordinate.getLongitude()));
        urlBuilder.addRequestParameter("start", String.valueOf(startUnixTime));
        urlBuilder.addRequestParameter("end", String.valueOf(endUnixTime));
        return new AirPollutionRequestCustomizerImpl(urlBuilder);
    }
}

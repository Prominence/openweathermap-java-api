/*
 * Copyright (c) 2022 Alexey Zinchenko
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

import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.request.RequestSettings;

/**
 * The type Historical air pollution requester.
 */
public class HistoricalAirPollutionRequester {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new Historical air pollution requester.
     *
     * @param requestSettings request settings object.
     */
    public HistoricalAirPollutionRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public AirPollutionRequestCustomizer byCoordinateAndPeriod(Coordinate coordinate, long startUnixTime, long endUnixTime) {
        requestSettings.putRequestParameter("lat", String.valueOf(coordinate.getLatitude()));
        requestSettings.putRequestParameter("lon", String.valueOf(coordinate.getLongitude()));
        requestSettings.putRequestParameter("start", String.valueOf(startUnixTime));
        requestSettings.putRequestParameter("end", String.valueOf(endUnixTime));
        return new AirPollutionRequestCustomizer(requestSettings);
    }
}

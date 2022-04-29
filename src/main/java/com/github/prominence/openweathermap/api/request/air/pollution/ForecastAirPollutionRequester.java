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

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.request.RequestSettings;

/**
 * The type Forecast air pollution requester.
 */
public class ForecastAirPollutionRequester {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new Forecast air pollution requester.
     *
     * @param requestSettings request settings object.
     */
    ForecastAirPollutionRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public AirPollutionRequestCustomizer byCoordinates(Coordinates coordinates) {
        requestSettings.putRequestParameter("lat", String.valueOf(coordinates.getLatitude()));
        requestSettings.putRequestParameter("lon", String.valueOf(coordinates.getLongitude()));
        return new AirPollutionRequestCustomizer(requestSettings);
    }
}

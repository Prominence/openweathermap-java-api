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

package com.github.prominence.openweathermap.api.request.weather.multiple;

import com.github.prominence.openweathermap.api.request.RequestUrlBuilder;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.CoordinateRectangle;

/**
 * The type Multiple locations current weather requester.
 */
public class MultipleLocationsCurrentWeatherRequesterImpl implements MultipleLocationsCurrentWeatherRequester {
    private final RequestUrlBuilder urlBuilder;

    /**
     * Instantiates a new Multiple locations current weather requester.
     *
     * @param urlBuilder the url builder
     */
    public MultipleLocationsCurrentWeatherRequesterImpl(RequestUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public MultipleResultCurrentWeatherRequestCustomizer byRectangle(CoordinateRectangle rectangle, int zoom) {
        String coordinates = rectangle.getFormattedRequestString() + "," + zoom;
        urlBuilder.append("box/city");
        urlBuilder.addRequestParameter("bbox", coordinates);

        return new MultipleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer byCitiesInCycle(Coordinate point, int citiesCount) {
        urlBuilder.append("find");
        urlBuilder.addRequestParameter("lat", Double.toString(point.getLatitude()));
        urlBuilder.addRequestParameter("lon", Double.toString(point.getLongitude()));
        urlBuilder.addRequestParameter("cnt", Integer.toString(citiesCount));

        return new MultipleResultCitiesInCircleCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer byCitiesInCycle(Coordinate point) {
        urlBuilder.append("find");
        urlBuilder.addRequestParameter("lat", Double.toString(point.getLatitude()));
        urlBuilder.addRequestParameter("lon", Double.toString(point.getLongitude()));

        return new MultipleResultCitiesInCircleCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }
}

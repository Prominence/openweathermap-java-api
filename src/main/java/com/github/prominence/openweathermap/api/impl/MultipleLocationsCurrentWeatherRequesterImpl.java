/*
 * Copyright (c) 2019 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.impl;

import com.github.prominence.openweathermap.api.MultipleLocationsWeatherRequester;
import com.github.prominence.openweathermap.api.MultipleResultCurrentWeatherRequestCustomizer;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.CoordinateRectangle;

public class MultipleLocationsCurrentWeatherRequesterImpl implements MultipleLocationsWeatherRequester {

    private RequestUrlBuilder urlBuilder;

    MultipleLocationsCurrentWeatherRequesterImpl(RequestUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public MultipleResultCurrentWeatherRequestCustomizer byRectangle(CoordinateRectangle rectangle, int zoom) {
        String coordinates = rectangle.getValue() + "," + zoom;
        urlBuilder.append("box/city");
        urlBuilder.addRequestParameter("bbox", coordinates);

        return new MultipleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public MultipleResultCurrentWeatherRequestCustomizer byRectangle(CoordinateRectangle rectangle, int zoom, boolean useServerClustering) {
        String coordinates = rectangle.getValue() + "," + zoom;
        urlBuilder.append("box/city");
        urlBuilder.addRequestParameter("bbox", coordinates);
        urlBuilder.addRequestParameter("cluster", useServerClustering ? "yes" : "no");

        return new MultipleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public MultipleResultCurrentWeatherRequestCustomizer byCitiesInCycle(Coordinate point, int citiesCount) {
        urlBuilder.append("find");
        urlBuilder.addRequestParameter("lat", Double.toString(point.getLatitude()));
        urlBuilder.addRequestParameter("lon", Double.toString(point.getLongitude()));
        urlBuilder.addRequestParameter("cnt", Integer.toString(citiesCount));

        return new MultipleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public MultipleResultCurrentWeatherRequestCustomizer byCitiesInCycle(Coordinate point, int citiesCount, boolean useServerClustering) {
        urlBuilder.append("find");
        urlBuilder.addRequestParameter("lat", Double.toString(point.getLatitude()));
        urlBuilder.addRequestParameter("lon", Double.toString(point.getLongitude()));
        urlBuilder.addRequestParameter("cnt", Integer.toString(citiesCount));
        urlBuilder.addRequestParameter("cluster", useServerClustering ? "yes" : "no");
        return new MultipleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }
}

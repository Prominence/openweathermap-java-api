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

import com.github.prominence.openweathermap.api.SingleLocationWeatherRequester;
import com.github.prominence.openweathermap.api.model.Coordinate;

public class SingleLocationCurrentWeatherRequesterImpl implements SingleLocationWeatherRequester {

    private RequestUrlBuilder urlBuilder;

    SingleLocationCurrentWeatherRequesterImpl(RequestUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
        urlBuilder.append("weather");
    }

    public SingleResultCurrentWeatherRequestCustomizerImpl byCityName(String cityName) {
        urlBuilder.addRequestParameter("q", cityName);
        return new SingleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    public SingleResultCurrentWeatherRequestCustomizerImpl byCityName(String cityName, String countryCode) {
        urlBuilder.addRequestParameter("q", cityName + "," + countryCode);
        return new SingleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    public SingleResultCurrentWeatherRequestCustomizerImpl byCityId(long cityId) {
        urlBuilder.addRequestParameter("id", cityId);
        return new SingleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    public SingleResultCurrentWeatherRequestCustomizerImpl byCoordinate(Coordinate coordinate) {
        urlBuilder.addRequestParameter("lat", String.valueOf(coordinate.getLatitude()));
        urlBuilder.addRequestParameter("lon", String.valueOf(coordinate.getLongitude()));
        return new SingleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }

    public SingleResultCurrentWeatherRequestCustomizerImpl byZipCodeAndCountry(String zipCode, String countryCode) {
        urlBuilder.addRequestParameter("zip", zipCode + "," + countryCode);
        return new SingleResultCurrentWeatherRequestCustomizerImpl(urlBuilder);
    }
}

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

package com.github.prominence.openweathermap.api.request.forecast.free;

import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.request.RequestUrlBuilder;

/**
 * The forecast requester.
 */
public class FiveDayThreeHourStepForecastRequesterImpl implements FiveDayThreeHourStepForecastRequester {

    private final RequestUrlBuilder urlBuilder;

    /**
     * Instantiates a new forecast requester.
     *
     * @param apiKey the api key
     */
    public FiveDayThreeHourStepForecastRequesterImpl(String apiKey) {
        urlBuilder =  new RequestUrlBuilder(apiKey);
        urlBuilder.append("forecast");
    }

    @Override
    public FiveDayThreeHourStepForecastRequestCustomizer byCityName(String cityName) {
        urlBuilder.addRequestParameter("q", cityName);
        return new FiveDayThreeHourStepForecastRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public FiveDayThreeHourStepForecastRequestCustomizer byCityName(String cityName, String stateCode) {
        urlBuilder.addRequestParameter("q", cityName + "," + stateCode);
        return new FiveDayThreeHourStepForecastRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public FiveDayThreeHourStepForecastRequestCustomizer byCityName(String cityName, String stateCode, String countryCode) {
        urlBuilder.addRequestParameter("q", cityName + "," + stateCode + "," + countryCode);
        return new FiveDayThreeHourStepForecastRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public FiveDayThreeHourStepForecastRequestCustomizer byCityId(long cityId) {
        urlBuilder.addRequestParameter("id", cityId);
        return new FiveDayThreeHourStepForecastRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public FiveDayThreeHourStepForecastRequestCustomizer byCoordinate(Coordinate coordinate) {
        urlBuilder.addRequestParameter("lat", String.valueOf(coordinate.getLatitude()));
        urlBuilder.addRequestParameter("lon", String.valueOf(coordinate.getLongitude()));
        return new FiveDayThreeHourStepForecastRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public FiveDayThreeHourStepForecastRequestCustomizer byZipCodeAndCountry(String zipCode, String countryCode) {
        urlBuilder.addRequestParameter("zip", zipCode + "," + countryCode);
        return new FiveDayThreeHourStepForecastRequestCustomizerImpl(urlBuilder);
    }

    @Override
    public FiveDayThreeHourStepForecastRequestCustomizer byZipCodeInUSA(String zipCode) {
        urlBuilder.addRequestParameter("zip", zipCode);
        return new FiveDayThreeHourStepForecastRequestCustomizerImpl(urlBuilder);
    }
}

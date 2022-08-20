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

package com.github.prominence.openweathermap.api.request.forecast.free;

import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.GenericRequester;

import static com.github.prominence.openweathermap.api.request.RequestSettings.LATITUDE_PARAM;
import static com.github.prominence.openweathermap.api.request.RequestSettings.LONGITUDE_PARAM;
import static com.github.prominence.openweathermap.api.request.RequestSettings.QUERY_VALUE_PARAM;

/**
 * The forecast requester.
 */
public class FiveDayThreeHourStepForecastRequester extends GenericRequester<FiveDayThreeHourStepForecastRequester> {

    /**
     * Instantiates a new forecast requester.
     *
     * @param requestSettings request settings object.
     */
    public FiveDayThreeHourStepForecastRequester(RequestSettings requestSettings) {
        super(requestSettings);
        this.requestSettings.appendToURL("data/2.5/forecast");
    }

    public FiveDayThreeHourStepForecastRequestCustomizer byCityName(String cityName) {
        requestSettings.putRequestParameter(QUERY_VALUE_PARAM, cityName);
        return new FiveDayThreeHourStepForecastRequestCustomizer(requestSettings);
    }

    public FiveDayThreeHourStepForecastRequestCustomizer byCoordinates(Coordinates coordinates) {
        requestSettings.putRequestParameter(LATITUDE_PARAM, String.valueOf(coordinates.getLatitude()));
        requestSettings.putRequestParameter(LONGITUDE_PARAM, String.valueOf(coordinates.getLongitude()));
        return new FiveDayThreeHourStepForecastRequestCustomizer(requestSettings);
    }
}

/*
 * Copyright (c) 2018 Alexey Zinchenko
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
 *
 */

package com.github.prominence.openweathermap.api;

import com.github.prominence.openweathermap.api.constants.TimeFrame;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.response.AirPollution;
import com.github.prominence.openweathermap.api.utils.JSONUtils;
import com.github.prominence.openweathermap.api.utils.RequestUtils;
import com.github.prominence.openweathermap.api.utils.TimeFrameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class AirPollutionRequester extends AuthenticationTokenBasedRequester {

    private Coordinates coordinates;
    private TimeFrame timeFrame;
    private Date date;

    AirPollutionRequester(String authToken) {
        super(authToken);
    }

    public AirPollutionRequester setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public AirPollutionRequester setCoordinates(float latitude, float longitude) {
        this.coordinates = new Coordinates(latitude, longitude);
        return this;
    }

    public AirPollutionRequester setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
        return this;
    }

    public AirPollutionRequester setDate(Date date) {
        this.date = date;
        return this;
    }

    public AirPollution retrieve() throws InvalidAuthTokenException, DataNotFoundException {
        if (coordinates == null || timeFrame == null || date == null) {
            throw new IllegalArgumentException("You must execute 'setCoordinates', 'setTimeFrame' and 'setDate' and least once.");
        }

        String requestParameters = String.format("%s,%s/%s.json", coordinates.getLatitude(), coordinates.getLongitude(), TimeFrameUtils.formatDate(date, timeFrame));

        AirPollution airPollution = null;

        try (InputStream response = executeRequest("pollution/v1/co/", requestParameters)) {
            airPollution = (AirPollution) JSONUtils.parseJSON(response, AirPollution.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return airPollution;
    }

    private InputStream executeRequest(String requestType, String requestSpecificParameters) throws MalformedURLException, InvalidAuthTokenException, DataNotFoundException {

        String url = OPEN_WEATHER_BASE_URL + requestType +
                requestSpecificParameters +
                "?appid=" +
                authToken;
        return RequestUtils.executeGetRequest(new URL(url));
    }

}

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
 */

package com.github.prominence.openweathermap.api;

import com.alibaba.fastjson.TypeReference;
import com.github.prominence.openweathermap.api.constants.System;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.response.UltravioletIndex;
import com.github.prominence.openweathermap.api.utils.JSONUtils;
import com.github.prominence.openweathermap.api.utils.RequestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class UltravioletIndexRequester extends AuthenticationTokenBasedRequester {

    private Coordinates coordinates;

    UltravioletIndexRequester(String authToken) {
        super(authToken);
    }

    public UltravioletIndexRequester setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public UltravioletIndexRequester setCoordinates(float latitude, float longitude) {
        this.coordinates = new Coordinates(latitude, longitude);
        return this;
    }

    public UltravioletIndex getCurrentUVIndex() throws InvalidAuthTokenException, DataNotFoundException {
        String requestParameters = String.format("lat=%f&lon=%f", coordinates.getLatitude(), coordinates.getLongitude());
        return getSingleObject(requestParameters);
    }

    public List<UltravioletIndex> getUVIndexForecast(int amountOfDays) throws InvalidAuthTokenException, DataNotFoundException {
        String requestParameters = String.format("lat=%f&lon=%f&cnt=%d", coordinates.getLatitude(), coordinates.getLongitude(), amountOfDays);
       return getListOfObjects(requestParameters);
    }

    public List<UltravioletIndex> getUVIndexByPeriod(Date from, Date to) throws InvalidAuthTokenException, DataNotFoundException {
        String requestParameters = String.format("lat=%f&lon=%f&start=%d&end=%d", coordinates.getLatitude(), coordinates.getLongitude(), from.getTime() / 1000, to.getTime() / 1000);
        return getListOfObjects(requestParameters);
    }

    private UltravioletIndex getSingleObject(String requestParameters) throws InvalidAuthTokenException, DataNotFoundException {
        UltravioletIndex ultravioletIndex = null;

        try (InputStream response = executeRequest("uvi", requestParameters)) {
            ultravioletIndex = (UltravioletIndex) JSONUtils.parseJSON(response, UltravioletIndex.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return ultravioletIndex;
    }

    private List<UltravioletIndex> getListOfObjects(String requestParameters) throws InvalidAuthTokenException, DataNotFoundException {
        List<UltravioletIndex> ultravioletIndex = null;

        TypeReference<List<UltravioletIndex>> typeRef = new TypeReference<List<UltravioletIndex>>() {};

        try (InputStream response = executeRequest("uvi/forecast", requestParameters)) {
            ultravioletIndex = (List<UltravioletIndex>) JSONUtils.parseJSON(response, typeRef);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return ultravioletIndex;
    }

    private InputStream executeRequest(String requestType, String requestSpecificParameters) throws MalformedURLException, InvalidAuthTokenException, DataNotFoundException {

        StringBuilder urlBuilder = new StringBuilder(OPEN_WEATHER_API_URL);
        urlBuilder.append(requestType);
        urlBuilder.append('?');
        urlBuilder.append(requestSpecificParameters);

        urlBuilder.append("&appid=");
        urlBuilder.append(authToken);

        return RequestUtils.executeGetRequest(new URL(urlBuilder.toString()));
    }

}

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

package by.prominence.openweathermap.api;

import by.prominence.openweathermap.api.constants.System;
import by.prominence.openweathermap.api.constants.Unit;
import by.prominence.openweathermap.api.exception.DataNotFoundException;
import by.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import by.prominence.openweathermap.api.model.Coordinates;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

abstract class BasicRequester<T> extends AuthenticationTokenBasedRequester {

    protected String language;
    protected String unitSystem = Unit.STANDARD_SYSTEM;
    protected String accuracy;

    protected BasicRequester(String authToken) {
        super(authToken);
    }

    public T getByCityId(String id) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?id=" + id);
    }

    public T getByCityName(String name) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?q=" + name);
    }

    public T getByCoordinates(double latitude, double longitude) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?lat=" + latitude + "&lon=" + longitude);
    }

    public T getByCoordinates(Coordinates coordinates) throws InvalidAuthTokenException, DataNotFoundException {
        return getByCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
    }

    public T getByZIPCode(String zipCode, String countryCode) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?zip=" + zipCode + "," + countryCode);
    }

    protected URL buildURL(String requestSpecificParameters) throws MalformedURLException {

        StringBuilder urlBuilder = new StringBuilder(System.OPEN_WEATHER_API_URL);
        urlBuilder.append(getRequestType());
        urlBuilder.append(requestSpecificParameters);

        urlBuilder.append("&appid=");
        urlBuilder.append(authToken);


        if (language != null) {
            urlBuilder.append("&lang=");
            urlBuilder.append(language);
        }

        if (!Unit.STANDARD_SYSTEM.equals(unitSystem)) {
            urlBuilder.append("&units=");
            urlBuilder.append(unitSystem);
        }

        if (accuracy != null) {
            urlBuilder.append("&type=");
            urlBuilder.append(accuracy);
        }

        Map<String, String> additionalParameters = getAdditionalParameters();
        if (additionalParameters != null) {
            additionalParameters.forEach((key, value) -> {
                urlBuilder.append("&");
                urlBuilder.append(key);
                urlBuilder.append("=");
                urlBuilder.append(value);
            });
        }

        return new URL(urlBuilder.toString());
    }

    protected Map<String, String> getAdditionalParameters() {
        return null;
    }

    protected abstract String getRequestType();
    protected abstract T executeRequest(String requestSpecificParamsString) throws InvalidAuthTokenException, DataNotFoundException;

}

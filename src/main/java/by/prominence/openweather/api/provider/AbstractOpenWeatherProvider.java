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

package by.prominence.openweather.api.provider;

import by.prominence.openweather.api.constants.System;
import by.prominence.openweather.api.exception.DataNotFoundException;
import by.prominence.openweather.api.exception.InvalidAuthTokenException;
import by.prominence.openweather.api.model.Coordinates;
import by.prominence.openweather.api.model.OpenWeatherResponse;
import by.prominence.openweather.api.utils.JsonUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public abstract class AbstractOpenWeatherProvider<T extends OpenWeatherResponse> implements OpenWeatherProvider {

    protected final String authToken;

    protected String language;
    protected String unit;
    protected String accuracy;
    private Class<T> type;

    public AbstractOpenWeatherProvider(String authToken) {
        this.authToken = authToken;
        type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public OpenWeatherProvider setLanguage(String language) {
        this.language = language;
        return this;
    }

    public OpenWeatherProvider setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public OpenWeatherProvider setAccuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
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

    protected T executeRequest(String requestSpecificParameters) throws InvalidAuthTokenException, DataNotFoundException {

        T openWeatherResponse = null;

        try {
            URL requestUrl = buildURL(requestSpecificParameters);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            switch (connection.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                    openWeatherResponse = type.cast(JsonUtils.parseJson(connection.getInputStream(), type));
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    throw new InvalidAuthTokenException();
                case HttpURLConnection.HTTP_NOT_FOUND:
                    throw new DataNotFoundException();
            }
        } catch (IOException | ClassCastException ex) {
            ex.printStackTrace();
        }

        return openWeatherResponse;
    }

    protected String getRequestUrl() {
        return System.OPEN_WEATHER_API_URL + getRequestType();
    }

    protected Map<String, String> getAdditionalParameters() {
        return null;
    }

    private URL buildURL(String requestSpecificParameters) throws MalformedURLException {

        StringBuilder urlBuilder = new StringBuilder(getRequestUrl() + requestSpecificParameters + "&appid=" + authToken);

        if (language != null) {
            urlBuilder.append("&lang=");
            urlBuilder.append(language);
        }

        if (unit != null) {
            urlBuilder.append("&units=");
            urlBuilder.append(unit);
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

    protected abstract String getRequestType();
}

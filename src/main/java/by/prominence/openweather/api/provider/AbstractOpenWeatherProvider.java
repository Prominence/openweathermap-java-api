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
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

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

    protected T executeRequest(String parameterString) throws InvalidAuthTokenException, DataNotFoundException {

        String url = getRequestUrl() + parameterString + "&appid=" + authToken;

        if (language != null) {
            url += "&lang=" + language;
        }

        if (unit != null) {
            url += "&units=" + unit;
        }

        if (accuracy != null) {
            url += "&type=" + accuracy;
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        T openWeatherResponse = null;
        if (httpResponse != null) {
            try {
                openWeatherResponse = type.cast(JsonUtils.parseJson(httpResponse.getEntity().getContent(), type));

                if (openWeatherResponse.getResponseCode() == 401) {
                    throw new InvalidAuthTokenException();
                }

                if (openWeatherResponse.getResponseCode() == 404) {
                    throw new DataNotFoundException();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }

        return openWeatherResponse;
    }

    private String getRequestUrl() {
        return System.OPEN_WEATHER_API_URL + getRequestType();
    }

    protected abstract String getRequestType();
}

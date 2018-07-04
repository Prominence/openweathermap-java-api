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

import by.prominence.openweather.api.model.Coordinates;
import by.prominence.openweather.api.model.WeatherResponse;
import by.prominence.openweather.api.utils.JsonUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class DefaultWeatherProvider implements WeatherProvider {

    private static final String OPEN_WEATHER_API_VERSION = "2.5";
    private static final String OPEN_WEATHER_API_URL = "http://api.openweathermap.org/data/" + OPEN_WEATHER_API_VERSION + "/weather";

    private final String authToken;

    public DefaultWeatherProvider(String authToken) {
        this.authToken = authToken;
    }

    public WeatherResponse getByCityId(String id) {
        return executeRequest("?id=" + id);
    }

    public WeatherResponse getByCityName(String name) {
        return executeRequest("?q=" + name);
    }

    public WeatherResponse getByCoordinates(double latitude, double longitude) {
        return executeRequest("?lat=" + latitude + "&lon=" + longitude);
    }

    public WeatherResponse getByCoordinates(Coordinates coordinates) {
        return getByCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
    }

    public WeatherResponse getByZIPCode(String zipCode, String countryCode) {
        return executeRequest("?zip=" + zipCode + "," + countryCode);
    }

    private WeatherResponse executeRequest(String parameterString) {

        String url = OPEN_WEATHER_API_URL + parameterString + "&appid=" + authToken;

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;

        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeatherResponse weatherResponse = null;
        if (response != null) {
            try {
                weatherResponse = (WeatherResponse)JsonUtils.parseJson(response.getEntity().getContent(), WeatherResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return weatherResponse;
    }
}

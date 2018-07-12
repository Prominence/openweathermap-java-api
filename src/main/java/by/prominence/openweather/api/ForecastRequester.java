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

package by.prominence.openweather.api;

import by.prominence.openweather.api.exception.DataNotFoundException;
import by.prominence.openweather.api.exception.InvalidAuthTokenException;
import by.prominence.openweather.api.model.Coordinates;
import by.prominence.openweather.api.model.forecast.ForecastResponse;
import by.prominence.openweather.api.utils.JsonUtils;
import by.prominence.openweather.api.utils.RequestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ForecastRequester extends BasicRequester {

    private int amountOfDays = -1;

    ForecastRequester(String authToken) {
        super(authToken);
    }

    public ForecastRequester setAmountOfDays(int amountOfDays) {
        this.amountOfDays = amountOfDays;
        return this;
    }

    public ForecastRequester setLanguage(String language) {
        this.language = language;
        return this;
    }

    public ForecastRequester setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public ForecastRequester setAccuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public ForecastResponse getByCityId(String id) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?id=" + id);
    }

    public ForecastResponse getByCityName(String name) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?q=" + name);
    }

    public ForecastResponse getByCoordinates(double latitude, double longitude) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?lat=" + latitude + "&lon=" + longitude);
    }

    public ForecastResponse getByCoordinates(Coordinates coordinates) throws InvalidAuthTokenException, DataNotFoundException {
        return getByCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
    }

    public ForecastResponse getByZIPCode(String zipCode, String countryCode) throws InvalidAuthTokenException, DataNotFoundException {
        return executeRequest("?zip=" + zipCode + "," + countryCode);
    }

    @Override
    protected Map<String, String> getAdditionalParameters() {
        Map<String, String> additionalParameters = null;
        if (amountOfDays != -1) {
            additionalParameters = new HashMap<>();
            additionalParameters.put("cnt", String.valueOf(amountOfDays));
        }

        return additionalParameters;
    }

    protected String getRequestType() {
        if (amountOfDays != -1) {
            return "forecast/daily";
        }
        return "forecast";
    }

    private ForecastResponse executeRequest(String requestSpecificParameters) throws InvalidAuthTokenException, DataNotFoundException {

        try {
            InputStream requestResult = RequestUtils.executeGetRequest(buildURL(requestSpecificParameters));
            return (ForecastResponse)JsonUtils.parseJson(requestResult, ForecastResponse.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}

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

import by.prominence.openweathermap.api.constants.Unit;
import by.prominence.openweathermap.api.exception.DataNotFoundException;
import by.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import by.prominence.openweathermap.api.model.response.ForecastResponse;
import by.prominence.openweathermap.api.utils.JsonUtils;
import by.prominence.openweathermap.api.utils.RequestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ForecastRequester extends BasicRequester<ForecastResponse> {

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

    public ForecastRequester setUnitSystem(String unitSystem) {
        this.unitSystem = unitSystem;
        return this;
    }

    public ForecastRequester setAccuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
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

    protected ForecastResponse executeRequest(String requestSpecificParameters) throws InvalidAuthTokenException, DataNotFoundException {

        ForecastResponse forecastResponse = null;

        try {
            InputStream requestResult = RequestUtils.executeGetRequest(buildURL(requestSpecificParameters));
            forecastResponse = (ForecastResponse)JsonUtils.parseJson(requestResult, ForecastResponse.class);

            char temperatureUnit = Unit.getTemperatureUnit(unitSystem);
            String windUnit = Unit.getWindUnit(unitSystem);

            forecastResponse.getForecasts().forEach(forecastInfo -> {
                forecastInfo.getWind().setUnit(windUnit);
                forecastInfo.getMainInfo().setTemperatureUnit(temperatureUnit);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return forecastResponse;
    }

}

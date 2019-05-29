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

import com.github.prominence.openweathermap.api.constants.Unit;
import com.github.prominence.openweathermap.api.model.response.Weather;
import com.github.prominence.openweathermap.api.utils.JSONUtils;
import com.github.prominence.openweathermap.api.utils.RequestUtils;

import java.io.IOException;
import java.io.InputStream;

public class WeatherRequester extends BasicRequester<Weather> {

    WeatherRequester(String authToken) {
        super(authToken);
    }

    public WeatherRequester setLanguage(String language) {
        this.language = language;
        return this;
    }

    public WeatherRequester setUnitSystem(String unitSystem) {
        this.unitSystem = unitSystem;
        return this;
    }

    public WeatherRequester setAccuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    protected String getRequestType() {
        return "weather";
    }

    protected Weather executeRequest(String requestSpecificParameters) {

        Weather weather = null;

        try {
            InputStream requestResult = RequestUtils.executeGetRequest(buildURL(requestSpecificParameters));
            weather = (Weather) JSONUtils.parseJSON(requestResult, Weather.class);

            weather.getWind().setUnit(Unit.getWindUnit(unitSystem));
            weather.getWeatherInfo().setTemperatureUnit(Unit.getTemperatureUnit(unitSystem));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return weather;
    }

}

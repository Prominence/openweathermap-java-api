/*
 * Copyright (c) 2021 Alexey Zinchenko
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

import com.github.prominence.openweathermap.api.annotation.SubscriptionAvailability;
import com.github.prominence.openweathermap.api.request.forecast.free.FiveDayThreeHourStepForecastRequester;
import com.github.prominence.openweathermap.api.request.forecast.free.FiveDayThreeHourStepForecastRequesterImpl;
import com.github.prominence.openweathermap.api.request.weather.CurrentWeatherRequester;
import com.github.prominence.openweathermap.api.request.weather.CurrentWeatherRequesterImpl;

import static com.github.prominence.openweathermap.api.enums.SubscriptionPlan.*;

/**
 * The main public API client to communicate with OpenWeatherMap services.
 * Requires API key for usage. More info on the website <a href="https://openweathermap.org/api">https://openweathermap.org/api</a>.
 */
public class OpenWeatherMapClient {
    private final String apiKey;

    /**
     * Created OpenWeatherMap client object.
     * @param apiKey API key obtained on <a href="https://home.openweathermap.org/api_keys">OpwnWeatherMap site</a>.
     */
    public OpenWeatherMapClient(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Current Weather <a href="https://openweathermap.org/current">API</a>.
     * @return requester for retrieving current weather information.
     */
    @SubscriptionAvailability(plans = ALL)
    public CurrentWeatherRequester currentWeather() {
        return new CurrentWeatherRequesterImpl(apiKey);
    }

    /**
     * 5 Day / 3 Hour Forecast <a href="https://openweathermap.org/forecast5">API</a>.
     * @return requester for retrieving 5 day/3-hour weather forecast information.
     */
    @SubscriptionAvailability(plans = ALL)
    public FiveDayThreeHourStepForecastRequester forecast5Day3HourStep() {
        return new FiveDayThreeHourStepForecastRequesterImpl(apiKey);
    }
}

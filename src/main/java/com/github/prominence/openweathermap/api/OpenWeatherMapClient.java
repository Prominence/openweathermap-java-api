/*
 * Copyright (c) 2021-present Alexey Zinchenko
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
import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.context.ApiConfigurationHolder;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.air.pollution.AirPollutionRequester;
import com.github.prominence.openweathermap.api.request.forecast.climatic.ClimaticForecastRequester;
import com.github.prominence.openweathermap.api.request.forecast.daily.DailyForecastRequester;
import com.github.prominence.openweathermap.api.request.forecast.free.FiveDayThreeHourStepForecastRequester;
import com.github.prominence.openweathermap.api.request.forecast.hourly.FourDaysHourlyForecastRequester;
import com.github.prominence.openweathermap.api.request.geocoding.GeocodingRequester;
import com.github.prominence.openweathermap.api.request.onecall.OneCallWeatherRequester;
import com.github.prominence.openweathermap.api.request.radiation.SolarRadiationRequester;
import com.github.prominence.openweathermap.api.request.roadrisk.RoadRiskRequester;
import com.github.prominence.openweathermap.api.request.weather.CurrentWeatherRequester;
import lombok.NonNull;

import static com.github.prominence.openweathermap.api.enums.SubscriptionPlan.ALL;
import static com.github.prominence.openweathermap.api.enums.SubscriptionPlan.DEVELOPER;
import static com.github.prominence.openweathermap.api.enums.SubscriptionPlan.ENTERPRISE;
import static com.github.prominence.openweathermap.api.enums.SubscriptionPlan.PAID;
import static com.github.prominence.openweathermap.api.enums.SubscriptionPlan.PROFESSIONAL;
import static com.github.prominence.openweathermap.api.enums.SubscriptionPlan.SPECIAL;

/**
 * The main public API client to communicate with OpenWeatherMap services.
 * Requires API key for usage. More info on the website <a href="https://openweathermap.org/api">https://openweathermap.org/api</a>.
 */
public class OpenWeatherMapClient {
    private final ApiConfiguration apiConfiguration;

    public OpenWeatherMapClient() {
        this(ApiConfigurationHolder.getConfiguration());
    }

    /**
     * Created OpenWeatherMap client object.
     *
     * @param apiConfiguration configuration options.
     */
    public OpenWeatherMapClient(@NonNull ApiConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;
    }

    /**
     * Current Weather <a href="https://openweathermap.org/current">API</a>.
     *
     * @return requester for retrieving current weather information.
     */
    @SubscriptionAvailability(plans = ALL)
    public CurrentWeatherRequester currentWeather() {
        return currentWeather(null);
    }

    @SubscriptionAvailability(plans = ALL)
    public CurrentWeatherRequester currentWeather(TimeoutSettings timeoutSettings) {
        return new CurrentWeatherRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * Hourly forecast <a href="https://openweathermap.org/api/hourly-forecast">API</a>.
     *
     * @return requester for retrieving hourly weather forecast information for 4 days.
     */
    @SubscriptionAvailability(plans = {DEVELOPER, PROFESSIONAL, ENTERPRISE})
    public FourDaysHourlyForecastRequester forecastHourly4Days() {
        return forecastHourly4Days(null);
    }

    @SubscriptionAvailability(plans = {DEVELOPER, PROFESSIONAL, ENTERPRISE})
    public FourDaysHourlyForecastRequester forecastHourly4Days(TimeoutSettings timeoutSettings) {
        return new FourDaysHourlyForecastRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * One Call <a href="https://openweathermap.org/api/one-call-api">API</a>.
     * To get information about current weather, minute forecast for 1 hour, hourly forecast for 48 hours, daily forecast for 7 days and government weather alerts.
     *
     * @return requester for retrieving one call weather information.
     */
    @SubscriptionAvailability(plans = ALL)
    public OneCallWeatherRequester oneCall() {
        return oneCall(null);
    }

    @SubscriptionAvailability(plans = ALL)
    public OneCallWeatherRequester oneCall(TimeoutSettings timeoutSettings) {
        return new OneCallWeatherRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * Daily forecast <a href="https://openweathermap.org/api/hourly-forecast">API</a>.
     *
     * @return requester for retrieving daily weather forecast information for 16 days.
     */
    @SubscriptionAvailability(plans = PAID)
    public DailyForecastRequester forecastDaily16Days() {
        return forecastDaily16Days(null);
    }

    @SubscriptionAvailability(plans = PAID)
    public DailyForecastRequester forecastDaily16Days(TimeoutSettings timeoutSettings) {
        return new DailyForecastRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * Climatic forecast <a href="https://openweathermap.org/api/forecast30">API</a>.
     *
     * @return requester for retrieving climatic weather forecast information for 30 days.
     */
    @SubscriptionAvailability(plans = {DEVELOPER, PROFESSIONAL, ENTERPRISE})
    public ClimaticForecastRequester climaticForecast30Days() {
        return climaticForecast30Days(null);
    }

    @SubscriptionAvailability(plans = {DEVELOPER, PROFESSIONAL, ENTERPRISE})
    public ClimaticForecastRequester climaticForecast30Days(TimeoutSettings timeoutSettings) {
        return new ClimaticForecastRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * Solar Radiation <a href="https://openweathermap.org/api/solar-radiation">API</a>.
     *
     * @return requester for retrieving solar radiation information.
     */
    @SubscriptionAvailability(plans = SPECIAL)
    public SolarRadiationRequester solarRadiation() {
        return solarRadiation(null);
    }

    @SubscriptionAvailability(plans = SPECIAL)
    public SolarRadiationRequester solarRadiation(TimeoutSettings timeoutSettings) {
        return new SolarRadiationRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * 5 Day / 3 Hour Forecast <a href="https://openweathermap.org/forecast5">API</a>.
     *
     * @return requester for retrieving 5 day/3-hour weather forecast information.
     */
    @SubscriptionAvailability(plans = ALL)
    public FiveDayThreeHourStepForecastRequester forecast5Day3HourStep() {
        return forecast5Day3HourStep(null);
    }

    @SubscriptionAvailability(plans = ALL)
    public FiveDayThreeHourStepForecastRequester forecast5Day3HourStep(TimeoutSettings timeoutSettings) {
        return new FiveDayThreeHourStepForecastRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * Road Risk <a href="https://openweathermap.org/api/road-risk">API</a>.
     *
     * @return requester for retrieving road risk information.
     */
    @SubscriptionAvailability(plans = SPECIAL)
    public RoadRiskRequester roadRisk() {
        return roadRisk(null);
    }

    @SubscriptionAvailability(plans = SPECIAL)
    public RoadRiskRequester roadRisk(TimeoutSettings timeoutSettings) {
        return new RoadRiskRequester(getRequestSettings(timeoutSettings));
    }

    /**
     * Air Pollution <a href="https://openweathermap.org/api/air-pollution">API</a>.
     * Air Pollution API provides current, forecast and historical air pollution data for any coordinates on the globe.
     *
     * @return requester for air pollution information retrieval.
     */
    @SubscriptionAvailability(plans = ALL)
    public AirPollutionRequester airPollution() {
        return airPollution(null);
    }

    @SubscriptionAvailability(plans = ALL)
    public AirPollutionRequester airPollution(TimeoutSettings timeoutSettings) {
        return new AirPollutionRequester(getRequestSettings(timeoutSettings));
    }

    @SubscriptionAvailability(plans = ALL)
    public GeocodingRequester geocoding() {
        return geocoding(null);
    }

    @SubscriptionAvailability(plans = ALL)
    public GeocodingRequester geocoding(TimeoutSettings timeoutSettings) {
        return new GeocodingRequester(getRequestSettings(timeoutSettings));
    }

    private RequestSettings getRequestSettings(TimeoutSettings timeoutSettings) {
        return new RequestSettings(apiConfiguration, timeoutSettings);
    }
}

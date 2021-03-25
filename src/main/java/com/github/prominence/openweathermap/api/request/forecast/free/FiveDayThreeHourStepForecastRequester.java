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

package com.github.prominence.openweathermap.api.request.forecast.free;

import com.github.prominence.openweathermap.api.model.Coordinate;

/**
 * An interface for <a href="https://openweathermap.org/forecast5">API</a> methods.
 */
public interface FiveDayThreeHourStepForecastRequester {

    /**
     * By city name forecast request customizer.
     *
     * @param cityName the city name
     * @return the forecast request customizer
     */
    FiveDayThreeHourStepForecastRequestCustomizer byCityName(String cityName);

    /**
     * By city name forecast request customizer.
     *
     * @param cityName  the city name
     * @param stateCode the state code
     * @return the forecast request customizer
     */
    FiveDayThreeHourStepForecastRequestCustomizer byCityName(String cityName, String stateCode);

    /**
     * By city name forecast request customizer.
     *
     * @param cityName    the city name
     * @param stateCode   the state code
     * @param countryCode the country code
     * @return the forecast request customizer
     */
    FiveDayThreeHourStepForecastRequestCustomizer byCityName(String cityName, String stateCode, String countryCode);

    /**
     * By city id forecast request customizer.
     *
     * @param cityId the city id
     * @return the forecast request customizer
     */
    FiveDayThreeHourStepForecastRequestCustomizer byCityId(long cityId);

    /**
     * By coordinate forecast request customizer.
     *
     * @param coordinate the coordinate
     * @return the forecast request customizer
     */
    FiveDayThreeHourStepForecastRequestCustomizer byCoordinate(Coordinate coordinate);

    /**
     * By zip code and country forecast request customizer.
     *
     * @param zipCode     the zip code
     * @param countryCode the country code
     * @return the forecast request customizer
     */
    FiveDayThreeHourStepForecastRequestCustomizer byZipCodeAndCountry(String zipCode, String countryCode);

    /**
     * By zip code in USA forecast request customizer.
     *
     * @param zipCode the zip code
     * @return the forecast request customizer
     */
    FiveDayThreeHourStepForecastRequestCustomizer byZipCodeInUSA(String zipCode);
}

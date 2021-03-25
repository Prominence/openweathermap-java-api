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

package com.github.prominence.openweathermap.api.request.weather.single;

import com.github.prominence.openweathermap.api.model.Coordinate;

/**
 * The interface Single location current weather requester.
 */
public interface SingleLocationCurrentWeatherRequester {

    /**
     * By city name current weather request customizer.
     *
     * @param cityName the city name
     * @return the single result current weather request customizer
     */
    SingleResultCurrentWeatherRequestCustomizer byCityName(String cityName);

    /**
     * By city name current weather request customizer.
     *
     * @param cityName    the city name
     * @param countryCode the country code
     * @return the single result current weather request customizer
     */
    SingleResultCurrentWeatherRequestCustomizer byCityName(String cityName, String countryCode);

    /**
     * By city name current weather request customizer.
     *
     * @param cityName    the city name
     * @param stateCode   the state code
     * @param countryCode the country code
     * @return the single result current weather request customizer
     */
    SingleResultCurrentWeatherRequestCustomizer byCityName(String cityName, String stateCode, String countryCode);

    /**
     * By city id current weather request customizer.
     *
     * @param cityId the city id
     * @return the single result current weather request customizer
     */
    SingleResultCurrentWeatherRequestCustomizer byCityId(long cityId);

    /**
     * By coordinate current weather request customizer.
     *
     * @param coordinate the coordinate
     * @return the single result current weather request customizer
     */
    SingleResultCurrentWeatherRequestCustomizer byCoordinate(Coordinate coordinate);

    /**
     * By zip code and country current weather request customizer.
     *
     * @param zipCode     the zip code
     * @param countryCode the country code
     * @return the single result current weather request customizer
     */
    SingleResultCurrentWeatherRequestCustomizer byZipCodeAndCountry(String zipCode, String countryCode);

    /**
     * By zip code in usa current weather request customizer.
     *
     * @param zipCode the zip code
     * @return the single result current weather request customizer
     */
    SingleResultCurrentWeatherRequestCustomizer byZipCodeInUSA(String zipCode);
}

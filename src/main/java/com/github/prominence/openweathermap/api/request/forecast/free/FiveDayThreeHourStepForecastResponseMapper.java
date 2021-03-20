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

import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Weather;

import java.util.List;

/**
 * Official API response documentation:
 * Parameters:
 * --- cod Internal parameter
 * --- message Internal parameter
 * --- cnt A number of timestamps returned in the API response
 * --- list
 *      |- list.dt Time of data forecasted, unix, UTC
 *      |- list.main
 *          |- list.main.temp Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.feels_like This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.temp_min Minimum temperature at the moment of calculation. This is minimal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.temp_max Maximum temperature at the moment of calculation. This is maximal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.pressure Atmospheric pressure on the sea level by default, hPa
 *          |- list.main.sea_level Atmospheric pressure on the sea level, hPa
 *          |- list.main.grnd_level Atmospheric pressure on the ground level, hPa
 *          |- list.main.humidity Humidity, %
 *          |- list.main.temp_kf Internal par
 *      |- list.weather
 *          |- list.weather.id Weather condition id
 *          |- list.weather.main Group of weather parameters (Rain, Snow, Extreme etc.)
 *          |- list.weather.description Weather condition within the group. You can get the output in your language.
 *          |- list.weather.icon Weather icon id
 *      |- list.clouds
 *          |- list.clouds.all Cloudiness, %
 *      |- list.wind
 *          |- list.wind.speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 *          |- list.wind.deg Wind direction, degrees (meteorological)
 *      |- list.visibility Average visibility, metres
 *      |- list.pop Probability of precipitation
 *      |- list.rain
 *          |- list.rain.3h Rain volume for last 3 hours, mm
 *      |- list.snow
 *          |- list.snow.3h Snow volume for last 3 hours
 *      |- list.sys
 *          |- list.sys.pod Part of the day (n - night, d - day)
 *      |- list.dt_txt Time of data forecasted, ISO, UTC
 * --- city
 *      |- city.id City ID
 *      |- city.name City name
 *      |- city.coord
 *          |- city.coord.lat City geo location, latitude
 *          |- city.coord.lon City geo location, longitude
 *      |- city.country Country code (GB, JP etc.)
 *      |- city.timezone Shift in seconds from UTC
 */
public class FiveDayThreeHourStepForecastResponseMapper {

    private final UnitSystem unitSystem;

    public FiveDayThreeHourStepForecastResponseMapper(UnitSystem unitSystem) {
        this.unitSystem = unitSystem;
    }

    public List<Weather> getTest() {
        return null;
    }
}

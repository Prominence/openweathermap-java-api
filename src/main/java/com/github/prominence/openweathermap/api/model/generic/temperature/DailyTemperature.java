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

package com.github.prominence.openweathermap.api.model.generic.temperature;

import com.github.prominence.openweathermap.api.model.TemperatureValue;

/**
 * The type Daily temperature.
 */
public interface DailyTemperature {
    /**
     * Temperature (objective) in the morning.
     *
     * @return temp
     */
    TemperatureValue getMorning();

    /**
     * Temperature (objective) for the day.
     *
     * @return temp
     */
    TemperatureValue getDay();

    /**
     * Temperature (objective) for the evening.
     *
     * @return temp
     */
    TemperatureValue getEve();

    /**
     * Temperature (objective) for the night.
     *
     * @return temp
     */
    TemperatureValue getNight();

    /**
     * Minimum temperature (objective) during the whole day.
     *
     * @return min temp
     */
    TemperatureValue getMin();

    /**
     * Maximum temperature (objective) during the whole day.
     *
     * @return max temp
     */
    TemperatureValue getMax();

    /**
     * Temperature (as it feels like) in the morning.
     *
     * @return feels like temp
     */
    TemperatureValue getMorningFeelsLike();

    /**
     * Temperature (as it feels like) for the day.
     *
     * @return feels like temp
     */
    TemperatureValue getDayFeelsLike();

    /**
     * Temperature (as it feels like) for the evening.
     *
     * @return feels like temp
     */
    TemperatureValue getEveFeelsLike();

    /**
     * Temperature (as it feels like) for the night.
     *
     * @return feels like temp
     */
    TemperatureValue getNightFeelsLike();
}

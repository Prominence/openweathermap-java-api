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

package com.github.prominence.openweathermap.api.constants;

public final class Unit {

    private Unit() {}

    public static final String METRIC_SYSTEM = "metric";
    public static final String IMPERIAL_SYSTEM = "imperial";
    public static final String STANDARD_SYSTEM = "standard";

    public static String getWindUnit(String type) {
        switch (type) {
            case IMPERIAL_SYSTEM:
                return "miles/hour";
            case STANDARD_SYSTEM:
            case METRIC_SYSTEM:
            default:
                return "meter/sec";
        }
    }

    public static char getTemperatureUnit(String type) {
        switch (type) {
            case METRIC_SYSTEM:
                return '℃';
            case IMPERIAL_SYSTEM:
                return '℉';
            case STANDARD_SYSTEM:
            default:
                return 'K';
        }
    }

}

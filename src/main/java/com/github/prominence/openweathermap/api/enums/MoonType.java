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

package com.github.prominence.openweathermap.api.enums;

public enum MoonType {
    NEW_MOON,
    WAXING_CRESCENT,
    FIRST_QUARTER_MOON,
    WAXING_GIBBOUS,
    FULL_MOON,
    WANING_GIBBOUS,
    LAST_QUARTER_MOON,
    WANING_CRESCENT,
    INVALID;

    private static final double precision = 0.000001d;

    public static MoonType valueOf(double numericValue) {
        if (equals(numericValue, 0) || equals(numericValue, 1)) {
            return NEW_MOON;
        } else if (equals(numericValue, 0.25)) {
            return FIRST_QUARTER_MOON;
        } else if (equals(numericValue, 0.5)) {
            return FULL_MOON;
        } else if (equals(numericValue, 0.75)) {
            return LAST_QUARTER_MOON;
        } else if (numericValue > 0 && numericValue < 0.25) {
            return WAXING_CRESCENT;
        } else if (numericValue > 0.25 && numericValue < 0.5) {
            return WAXING_GIBBOUS;
        } else if (numericValue > 0.5 && numericValue < 0.75) {
            return WANING_GIBBOUS;
        } else if (numericValue > 0.75 && numericValue < 1) {
            return WANING_CRESCENT;
        }
        return INVALID;
    }

    private static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) < precision;
    }
}

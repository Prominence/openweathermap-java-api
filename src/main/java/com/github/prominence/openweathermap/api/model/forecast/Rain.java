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

package com.github.prominence.openweathermap.api.model.forecast;

import java.util.Objects;

/**
 * Represents rain information.
 */
public class Rain {
    private static final String DEFAULT_UNIT = "mm";

    private double threeHourRainLevel;

    private Rain(double threeHourRainLevel) {
        this.threeHourRainLevel = threeHourRainLevel;
    }

    /**
     * Creates {@link Rain} object with correctness check.
     * @param threeHourRainLevel 3-hour rain level value
     * @return rain object.
     */
    public static Rain withThreeHourLevelValue(double threeHourRainLevel) {
        if (threeHourRainLevel < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        return new Rain(threeHourRainLevel);
    }

    /**
     * Returns 3-hour rain level value.
     * @return 3-hour rain level value
     */
    public double getThreeHourRainLevel() {
        return threeHourRainLevel;
    }

    /**
     * Sets 3-hour rain level value with correctness check.
     * @param threeHourRainLevel 3-hour rain level value
     */
    public void setThreeHourRainLevel(double threeHourRainLevel) {
        if (threeHourRainLevel < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        this.threeHourRainLevel = threeHourRainLevel;
    }

    /**
     * Returns rain level unit of measure. Currently is constant.
     * @return rain level unit of measure
     */
    public String getUnit() {
        return DEFAULT_UNIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rain rain = (Rain) o;
        return Double.compare(rain.threeHourRainLevel, threeHourRainLevel) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(threeHourRainLevel);
    }

    @Override
    public String toString() {
        return "3-hour rain level: " +
                threeHourRainLevel + ' ' +
                getUnit();
    }
}

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

package com.github.prominence.openweathermap.api.model.onecall;

import java.util.Objects;

/**
 * Represents rain information.
 */
public class Rain {
    private static final String DEFAULT_UNIT = "mm";

    private double oneHourRainLevel;

    private Rain() {
    }

    /**
     * Creates {@link Rain} object with correctness check.
     *
     * @param oneHourRainLevel 1-hour rain level value
     * @return rain object.
     */
    public static Rain withOneHourLevelValue(double oneHourRainLevel) {
        final Rain rain = new Rain();
        rain.setOneHourRainLevel(oneHourRainLevel);
        return rain;
    }

    /**
     * Gets one hour rain level.
     *
     * @return the one hour rain level
     */
    public double getOneHourRainLevel() {
        return oneHourRainLevel;
    }

    /**
     * Sets one hour rain level.
     *
     * @param oneHourRainLevel the one hour rain level
     */
    public void setOneHourRainLevel(double oneHourRainLevel) {
        if (oneHourRainLevel < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        this.oneHourRainLevel = oneHourRainLevel;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return DEFAULT_UNIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rain)) return false;
        Rain rain = (Rain) o;
        return Objects.equals(oneHourRainLevel, rain.oneHourRainLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneHourRainLevel);
    }

    @Override
    public String toString() {
        return "1-hour rain level: " +
                oneHourRainLevel + ' ' +
                getUnit();
    }
}

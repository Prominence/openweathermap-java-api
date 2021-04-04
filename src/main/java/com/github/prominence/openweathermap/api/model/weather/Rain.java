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

package com.github.prominence.openweathermap.api.model.weather;

import java.util.Objects;

/**
 * Represents rain information.
 */
public class Rain {
    private static final String DEFAULT_UNIT = "mm";

    private Double oneHourLevel;
    private Double threeHourLevel;

    private Rain() {
    }

    /**
     * Creates {@link Rain} object with correctness check.
     *
     * @param oneHourLevel 1-hour rain level value
     * @return rain object.
     */
    public static Rain withOneHourLevelValue(double oneHourLevel) {
        final Rain rain = new Rain();
        rain.setOneHourLevel(oneHourLevel);
        return rain;
    }

    /**
     * Creates {@link Rain} object with correctness check.
     *
     * @param threeHourLevel 3-hour rain level value
     * @return rain object.
     */
    public static Rain withThreeHourLevelValue(double threeHourLevel) {
        final Rain rain = new Rain();
        rain.setThreeHourLevel(threeHourLevel);
        return rain;
    }

    /**
     * Creates {@link Rain} object with correctness check.
     *
     * @param oneHourLevel   the one hour rain level
     * @param threeHourLevel the three hour rain level
     * @return the rain
     */
    public static Rain withValues(double oneHourLevel, double threeHourLevel) {
        final Rain rain = new Rain();
        rain.setOneHourLevel(oneHourLevel);
        rain.setThreeHourLevel(threeHourLevel);
        return rain;
    }

    /**
     * Gets one hour rain level.
     *
     * @return the one hour rain level
     */
    public Double getOneHourLevel() {
        return oneHourLevel;
    }

    /**
     * Sets one hour rain level.
     *
     * @param oneHourLevel the one hour rain level
     */
    public void setOneHourLevel(double oneHourLevel) {
        if (oneHourLevel < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        this.oneHourLevel = oneHourLevel;
    }

    /**
     * Gets three hour rain level.
     *
     * @return the three hour rain level
     */
    public Double getThreeHourLevel() {
        return threeHourLevel;
    }

    /**
     * Sets three hour rain level.
     *
     * @param threeHourLevel the three hour rain level
     */
    public void setThreeHourLevel(double threeHourLevel) {
        if (threeHourLevel < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        this.threeHourLevel = threeHourLevel;
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
        return Objects.equals(oneHourLevel, rain.oneHourLevel) &&
                Objects.equals(threeHourLevel, rain.threeHourLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneHourLevel, threeHourLevel);
    }

    @Override
    public String toString() {
        final StringBuilder snowString = new StringBuilder();
        if (oneHourLevel != null) {
            snowString.append("1-hour rain level: ");
            snowString.append(oneHourLevel);
            snowString.append(getUnit());
        }
        if (threeHourLevel != null) {
            if (oneHourLevel != null) {
                snowString.append(", ");
            }
            snowString.append("3-hours rain level: ");
            snowString.append(threeHourLevel);
            snowString.append(getUnit());
        }
        return snowString.toString();
    }
}

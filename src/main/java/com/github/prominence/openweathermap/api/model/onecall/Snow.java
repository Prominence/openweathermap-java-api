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
 * Represents snow information.
 */
public class Snow {
    private static final String DEFAULT_UNIT = "mm";

    private Double oneHourSnowLevel;

    private Snow() {
    }

    /**
     * Creates {@link Snow} object with correctness check.
     *
     * @param oneHourSnowLevel 1-hour snow level value
     * @return snow object.
     */
    public static Snow withOneHourLevelValue(double oneHourSnowLevel) {
        final Snow snow = new Snow();
        snow.setOneHourSnowLevel(oneHourSnowLevel);
        return snow;
    }

    /**
     * Gets one hour snow level.
     *
     * @return the one hour snow level
     */
    public Double getOneHourSnowLevel() {
        return oneHourSnowLevel;
    }

    /**
     * Sets one hour snow level.
     *
     * @param oneHourSnowLevel the one hour snow level
     */
    public void setOneHourSnowLevel(double oneHourSnowLevel) {
        if (oneHourSnowLevel < 0) {
            throw new IllegalArgumentException("Snow level value cannot be negative.");
        }
        this.oneHourSnowLevel = oneHourSnowLevel;
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
        if (!(o instanceof Snow)) return false;
        Snow snow = (Snow) o;
        return Objects.equals(oneHourSnowLevel, snow.oneHourSnowLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneHourSnowLevel);
    }

    @Override
    public String toString() {
        final StringBuilder snowString = new StringBuilder();
        if (oneHourSnowLevel != null) {
            snowString.append("1-hour snow level: ");
            snowString.append(oneHourSnowLevel);
            snowString.append(getUnit());
        }
        return snowString.toString();
    }
}

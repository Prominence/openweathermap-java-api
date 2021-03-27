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

package com.github.prominence.openweathermap.api.model.onecall.current;

import java.util.Objects;

/**
 * Represents rain information.
 */
public class DailyRain {
    private static final String DEFAULT_UNIT = "mm";

    private Double value;

    private DailyRain() {
    }

    /**
     * Creates {@link DailyRain} object with correctness check.
     *
     * @param value 1-hour rain level value
     * @return rain object.
     */
    public static DailyRain withValue(double value) {
        DailyRain rain = new DailyRain();
        rain.setValue(value);
        return rain;
    }

    /**
     * Gets one hour rain level.
     *
     * @return the one hour rain level
     */
    public Double getValue() {
        return value;
    }

    /**
     * Sets one hour rain level.
     *
     * @param value the one hour rain level
     */
    public void setValue(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        this.value = value;
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
        if (!(o instanceof DailyRain)) return false;
        DailyRain rain = (DailyRain) o;
        return Objects.equals(value, rain.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        StringBuilder snowString = new StringBuilder();
        if (value != null) {
            snowString.append("Rain level: ");
            snowString.append(value);
            snowString.append(getUnit());
        }
        return snowString.toString();
    }
}

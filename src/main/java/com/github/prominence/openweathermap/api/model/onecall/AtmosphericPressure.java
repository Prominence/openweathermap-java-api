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
 * The AtmosphericPressure type represents atmospheric pressure value.
 * Its value can only be a double in [0, +∞) range.
 */
public class AtmosphericPressure {
    private static final String DEFAULT_UNIT = "hPa";

    private double seaLevelValue;

    /**
     * Instantiates a new Pressure.
     *
     * @param seaLevelValue the value representing pressure value.
     * @throws IllegalArgumentException in case if provided value isn't in allowed range.
     */
    private AtmosphericPressure(double seaLevelValue) {
        this.seaLevelValue = seaLevelValue;
    }

    /**
     * Static method for {@link AtmosphericPressure} creation with value checking.
     * @param seaLevelValue atmospheric pressure value.
     * @return instantiated {@link AtmosphericPressure} object.
     */
    public static AtmosphericPressure withValue(double seaLevelValue) {
        if (seaLevelValue < 0)  {
            throw new IllegalArgumentException("Atmospheric pressure value must be in [0, +∞) range.");
        }
        return new AtmosphericPressure(seaLevelValue);
    }

    /**
     * Gets sea level value.
     *
     * @return the sea level value.
     */
    public Double getSeaLevelValue() {
        return seaLevelValue;
    }

    /**
     * Sets sea level value.
     *
     * @param seaLevelValue the sea level value.
     * @throws IllegalArgumentException in case if provided value isn't in allowed range.
     */
    public void setSeaLevelValue(double seaLevelValue) {
        if (seaLevelValue < 0)  {
            throw new IllegalArgumentException("Atmospheric pressure value must be in [0, +∞) range.");
        }
        this.seaLevelValue = seaLevelValue;
    }

    /**
     * Returns pressure unitSystem. Constantly equals to 'hPa'.
     *
     * @return the pressure unitSystem.
     */
    public String getUnit() {
        return DEFAULT_UNIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmosphericPressure that = (AtmosphericPressure) o;
        return Double.compare(that.seaLevelValue, seaLevelValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seaLevelValue);
    }

    @Override
    public String toString() {
        return "Pressure: " + seaLevelValue + ' ' + getUnit();
    }
}

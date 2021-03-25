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

package com.github.prominence.openweathermap.api.model;

import java.util.Objects;

/**
 * The AtmosphericPressure type represents atmospheric pressure value.
 * Its value can only be a double in [0, +∞) range.
 */
public class AtmosphericPressure {
    private static final String DEFAULT_UNIT = "hPa";

    private double value;

    private Double seaLevelValue;
    private Double groundLevelValue;

    /**
     * Instantiates a new Pressure.
     *
     * @param value the value representing pressure value.
     * @throws IllegalArgumentException in case if provided value isn't in allowed range.
     */
    private AtmosphericPressure(double value) {
        this.value = value;
    }

    /**
     * Static method for {@link AtmosphericPressure} creation with value checking.
     * @param value atmospheric pressure value.
     * @return instantiated {@link AtmosphericPressure} object.
     */
    public static AtmosphericPressure withValue(double value) {
        if (value < 0)  {
            throw new IllegalArgumentException("Atmospheric pressure value must be in [0, +∞) range.");
        }
        return new AtmosphericPressure(value);
    }

    /**
     * Returns pressure value.
     *
     * @return pressure value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets pressure value.
     *
     * @param value new pressure value.
     * @throws IllegalArgumentException in case if provided value isn't in allowed range.
     */
    public void setValue(double value) {
        if (value < 0)  {
            throw new IllegalArgumentException("Atmospheric pressure value must be in [0, +∞) range.");
        }
        this.value = value;
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
     * Gets ground level value.
     *
     * @return the ground level value.
     */
    public Double getGroundLevelValue() {
        return groundLevelValue;
    }

    /**
     * Sets ground level value.
     *
     * @param groundLevelValue the ground level value.
     * @throws IllegalArgumentException in case if provided value isn't in allowed range.
     */
    public void setGroundLevelValue(double groundLevelValue) {
        if (groundLevelValue < 0)  {
            throw new IllegalArgumentException("Atmospheric pressure value must be in [0, +∞) range.");
        }
        this.groundLevelValue = groundLevelValue;
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
        if (!(o instanceof AtmosphericPressure)) return false;
        AtmosphericPressure atmosphericPressure = (AtmosphericPressure) o;
        return Double.compare(atmosphericPressure.value, value) == 0 &&
                Objects.equals(seaLevelValue, atmosphericPressure.seaLevelValue) &&
                Objects.equals(groundLevelValue, atmosphericPressure.groundLevelValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, seaLevelValue, groundLevelValue);
    }

    @Override
    public String toString() {
        return "Pressure: " + value + ' ' + getUnit();
    }
}

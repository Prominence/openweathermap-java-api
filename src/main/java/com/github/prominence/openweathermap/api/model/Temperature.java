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
 * Represents temperature values and unit.
 */
public class Temperature {
    private double value;
    private Double maxTemperature;
    private Double minTemperature;
    private Double feelsLike;
    private String unit;

    private Temperature(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Creates {@link Temperature} object with correctness check.
     * @param value temperature value
     * @param unit temperature unit
     * @return temperature object
     */
    public static Temperature withValue(double value, String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit must be set.");
        }
        return new Temperature(value, unit);
    }

    /**
     * Returns temperature value.
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets temperature value.
     * @param value temperature
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Returns maximal temperature value.
     * @return maximal temperature value
     */
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * Sets maximal temperature value.
     * @param maxTemperature maximal temperature
     */
    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    /**
     * Returns minimal temperature value.
     * @return minimal temperature value
     */
    public Double getMinTemperature() {
        return minTemperature;
    }

    /**
     * Sets minimal temperature value.
     * @param minTemperature minimal temperature
     */
    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    /**
     * Returns 'feels like' temperature value.
     * @return 'feels like' temperature value
     */
    public Double getFeelsLike() {
        return feelsLike;
    }

    /**
     * Sets 'feels like' temperature value.
     * @param feelsLike 'feels like' temperature
     */
    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     * Returns temperature unit.
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets temperature unit with correctness check.
     * @param unit temperature unit
     */
    public void setUnit(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit must be set.");
        }
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Temperature)) return false;
        Temperature that = (Temperature) o;
        return Double.compare(that.value, value) == 0 &&
                Objects.equals(maxTemperature, that.maxTemperature) &&
                Objects.equals(minTemperature, that.minTemperature) &&
                Objects.equals(feelsLike, that.feelsLike) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, maxTemperature, minTemperature, feelsLike, unit);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Temperature: ");
        stringBuilder.append(value);
        stringBuilder.append(' ');
        stringBuilder.append(unit);
        if (maxTemperature != null) {
            stringBuilder.append(", Maximum value: ");
            stringBuilder.append(maxTemperature);
            stringBuilder.append(' ');
            stringBuilder.append(unit);
        }
        if (minTemperature != null) {
            stringBuilder.append(", Minimum value: ");
            stringBuilder.append(minTemperature);
            stringBuilder.append(' ');
            stringBuilder.append(unit);
        }
        if (feelsLike != null) {
            stringBuilder.append(", Feels like: ");
            stringBuilder.append(feelsLike);
            stringBuilder.append(' ');
            stringBuilder.append(unit);
        }

        return stringBuilder.toString();
    }
}

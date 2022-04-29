/*
 * Copyright (c) 2022 Alexey Zinchenko
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
 * The type Wind.
 */
public class Wind {
    private double speed;
    private Double degrees;
    private Double gust;
    private String unit;

    /**
     * Instantiates a new Wind.
     *
     * @param speed the speed
     * @param unit  the unitSystem
     */
    private Wind(double speed, String unit) {
        this.speed = speed;
        this.unit = unit;
    }

    /**
     * Creates {@link Wind} object with correctness check.
     *
     * @param speed the speed
     * @param unit  the unitSystem
     * @return wind object
     */
    public static Wind withValue(double speed, String unit) {
        if (speed < 0) {
            throw new IllegalArgumentException("Wind speed value must be in positive or zero.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit must be set.");
        }
        return new Wind(speed, unit);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(double speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Wind speed value must be in positive or zero.");
        }
        this.speed = speed;
    }

    /**
     * Gets gust value.
     *
     * @return the gust
     */
    public Double getGust() {
        return gust;
    }

    /**
     * Sets gust value.
     *
     * @param gust the gust.
     */
    public void setGust(double gust) {
        if (gust < 0) {
            throw new IllegalArgumentException("Gust value must be positive or zero.");
        }
        this.gust = gust;
    }

    /**
     * Gets degrees.
     *
     * @return the degrees
     */
    public Double getDegrees() {
        return degrees;
    }

    /**
     * Sets degrees.
     *
     * @param degrees the degrees
     */
    public void setDegrees(double degrees) {
        if (degrees < 0 || degrees > 360) {
            throw new IllegalArgumentException("Wind direction value must be in [0, 360] range.");
        }
        this.degrees = degrees;
    }

    /**
     * Gets unitSystem.
     *
     * @return the unitSystem
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unitSystem.
     *
     * @param unit the unitSystem
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
        if (!(o instanceof Wind)) return false;
        Wind wind = (Wind) o;
        return Double.compare(wind.speed, speed) == 0 &&
                Objects.equals(degrees, wind.degrees) &&
                Objects.equals(gust, wind.gust) &&
                Objects.equals(unit, wind.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, degrees, gust, unit);
    }

    @Override
    public String toString() {
        String output = "Wind speed: " + speed + " " + unit +
                ", degrees: " + degrees;
        if (gust != null) {
            output += ", Gust: " + gust + " " + unit;
        }
        return output;
    }
}

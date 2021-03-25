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
 * Represents some location by its latitude and longitude.
 */
public class Coordinate {
    private double latitude;
    private double longitude;

    private Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Method for {@link Coordinate} creation with correctness check.
     * @param latitude latitude
     * @param longitude longitude
     * @return coordinate object.
     */
    public static Coordinate withValues(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude value must be in the next range: [-90.0; 90.0].");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude value must be in the next range: [-180.0; 180.0].");
        }
        return new Coordinate(latitude, longitude);
    }

    /**
     * Sets latitude with checks.
     * @param latitude latitude value
     */
    public void setLatitude(double latitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude value must be in the next range: [-90.0; 90.0].");
        }
        this.latitude = latitude;
    }

    /**
     * Sets longitude with checks.
     * @param longitude longitude value
     */
    public void setLongitude(double longitude) {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude value must be in the next range: [-180.0; 180.0].");
        }
        this.longitude = longitude;
    }

    /**
     * Returns latitude.
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns longitude.
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return formatAsDegree(latitude) +
                ", " + formatAsDegree(longitude);
    }

    private String formatAsDegree(double value) {
        int degrees = (int) value;
        double secondsDouble = value % 1 * 60;
        int minutes = (int) secondsDouble;
        int seconds = (int) (secondsDouble % 1 * 60);

        return String.format("%s° %s′ %s″", degrees, minutes, seconds);
    }
}

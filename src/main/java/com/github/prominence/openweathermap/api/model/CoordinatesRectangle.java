/*
 * Copyright (c) 2021-present Alexey Zinchenko
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
 * Represents coordinate rectangle by its bottom-left and top-right coordinates.
 */
public class CoordinatesRectangle {
    private final double longitudeLeft;
    private final double latitudeBottom;
    private final double longitudeRight;
    private final double latitudeTop;

    private CoordinatesRectangle(double longitudeLeft, double latitudeBottom, double longitudeRight, double latitudeTop) {
        this.longitudeLeft = longitudeLeft;
        this.latitudeBottom = latitudeBottom;
        this.longitudeRight = longitudeRight;
        this.latitudeTop = latitudeTop;
    }

    /**
     * Method for {@link CoordinatesRectangle} creation with correctness check.
     * @param longitudeLeft left longitude
     * @param latitudeBottom bottom latitude
     * @param longitudeRight right longitude
     * @param latitudeTop tip latitude
     * @return coordinate rectangle object.
     */
    public static CoordinatesRectangle withValues(double longitudeLeft, double latitudeBottom, double longitudeRight, double latitudeTop) {
        if (latitudeBottom < -90 || latitudeTop < -90 || latitudeBottom > 90 || latitudeTop > 90) {
            throw new IllegalArgumentException("Latitude value must be in the next range: [-90.0; 90.0].");
        }
        if (longitudeLeft < -180 || longitudeRight < -180 || longitudeLeft > 180 || longitudeRight > 180) {
            throw new IllegalArgumentException("Longitude value must be in the next range: [-180.0; 180.0].");
        }
        return new CoordinatesRectangle(longitudeLeft, latitudeBottom, longitudeRight, latitudeTop);
    }

    /**
     * Returns left longitude value.
     * @return left longitude
     */
    public double getLongitudeLeft() {
        return longitudeLeft;
    }

    /**
     * Returns bottom latitude value.
     * @return bottom latitude
     */
    public double getLatitudeBottom() {
        return latitudeBottom;
    }

    /**
     * Returns right longitude value.
     * @return right longitude
     */
    public double getLongitudeRight() {
        return longitudeRight;
    }

    /**
     * Returns top latitude value.
     * @return top latitude
     */
    public double getLatitudeTop() {
        return latitudeTop;
    }

    /**
     * Formatted coordinate rectangle string.
     * @return formatted string
     */
    public String getFormattedRequestString() {
        return longitudeLeft + "," + latitudeBottom + "," + longitudeRight + "," + latitudeTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoordinatesRectangle)) return false;
        CoordinatesRectangle rectangle = (CoordinatesRectangle) o;
        return Double.compare(rectangle.longitudeLeft, longitudeLeft) == 0 &&
                Double.compare(rectangle.latitudeBottom, latitudeBottom) == 0 &&
                Double.compare(rectangle.longitudeRight, longitudeRight) == 0 &&
                Double.compare(rectangle.latitudeTop, latitudeTop) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitudeLeft, latitudeBottom, longitudeRight, latitudeTop);
    }

    @Override
    public String toString() {
        return "Rectangle: " + getFormattedRequestString();
    }

    /**
     * Builder for CoordinateRectangle class.
     */
    public static class Builder {
        private Double longitudeLeft;
        private Double latitudeBottom;
        private Double longitudeRight;
        private Double latitudeTop;

        /**
         * Creates Builder object.
         */
        public Builder() {
        }

        /**
         * Sets left longitude with correctness check.
         * @param longitudeLeft left longitude
         * @return builder object
         */
        public Builder setLongitudeLeft(double longitudeLeft) {
            if (longitudeLeft < -180 || longitudeLeft > 180) {
                throw new IllegalArgumentException("Longitude value must be in the next range: [-180.0; 180.0].");
            }
            this.longitudeLeft = longitudeLeft;
            return this;
        }

        /**
         * Sets bottom latitude with correctness check.
         * @param latitudeBottom bottom latitude
         * @return builder object
         */
        public Builder setLatitudeBottom(double latitudeBottom) {
            if (latitudeBottom < -90 || latitudeBottom > 90) {
                throw new IllegalArgumentException("Latitude value must be in the next range: [-90.0; 90.0].");
            }
            this.latitudeBottom = latitudeBottom;
            return this;
        }

        /**
         * Sets right longitude with correctness check.
         * @param longitudeRight right longitude
         * @return builder object
         */
        public Builder setLongitudeRight(double longitudeRight) {
            if (longitudeRight < -180 || longitudeRight > 180) {
                throw new IllegalArgumentException("Longitude value must be in the next range: [-180.0; 180.0].");
            }
            this.longitudeRight = longitudeRight;
            return this;
        }

        /**
         * Sets top latitude with correctness check.
         * @param latitudeTop top latitude
         * @return builder object
         */
        public Builder setLatitudeTop(double latitudeTop) {
            if (latitudeTop < -90 || latitudeTop > 90) {
                throw new IllegalArgumentException("Latitude value must be in the next range: [-90.0; 90.0].");
            }
            this.latitudeTop = latitudeTop;
            return this;
        }

        /**
         * Builds {@link CoordinatesRectangle} object with correctness check.
         * @return {@link CoordinatesRectangle} built object.
         */
        public CoordinatesRectangle build() {
            if (longitudeLeft == null || latitudeBottom == null || longitudeRight == null || latitudeTop == null) {
                throw new IllegalStateException("Not all fields were set.");
            }
            return new CoordinatesRectangle(longitudeLeft, latitudeBottom, longitudeRight, latitudeTop);
        }
    }
}

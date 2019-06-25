/*
 * Copyright (c) 2019 Alexey Zinchenko
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

public class CoordinateRectangle {

    private double longitudeLeft;
    private double latitudeBottom;
    private double longitudeRight;
    private double latitudeTop;

    public CoordinateRectangle(double longitudeLeft, double latitudeBottom, double longitudeRight, double latitudeTop) {
        if (latitudeBottom < -90 || latitudeTop < -90 || latitudeBottom > 90 || latitudeTop > 90) {
            throw new IllegalArgumentException("Latitude value must be in the next range: [-90.0; 90.0].");
        }
        if (longitudeLeft < -180 || longitudeRight < -180 || longitudeLeft > 180 || longitudeRight > 180) {
            throw new IllegalArgumentException("Longitude value must be in the next range: [-180.0; 180.0].");
        }
        this.longitudeLeft = longitudeLeft;
        this.latitudeBottom = latitudeBottom;
        this.longitudeRight = longitudeRight;
        this.latitudeTop = latitudeTop;
    }

    public double getLongitudeLeft() {
        return longitudeLeft;
    }

    public double getLatitudeBottom() {
        return latitudeBottom;
    }

    public double getLongitudeRight() {
        return longitudeRight;
    }

    public double getLatitudeTop() {
        return latitudeTop;
    }

    public String getFormattedString() {
        return longitudeLeft + "," + latitudeBottom + "," + longitudeRight + "," + latitudeTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoordinateRectangle)) return false;
        CoordinateRectangle rectangle = (CoordinateRectangle) o;
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
        return "Rectangle: " + getFormattedString();
    }
}

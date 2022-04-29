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

package com.github.prominence.openweathermap.api.model.geocoding;

import com.github.prominence.openweathermap.api.model.Coordinates;

import java.util.Map;
import java.util.Objects;

public final class GeocodingRecord {
    private final String name;
    private final Map<String, String> localNames;
    private final Coordinates coordinates;
    private final String countryCode;

    public GeocodingRecord(String name, Map<String, String> localNames, Coordinates coordinates, String countryCode) {
        this.name = name;
        this.localNames = localNames;
        this.coordinates = coordinates;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getLocalNames() {
        return localNames;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GeocodingRecord) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.localNames, that.localNames) &&
                Objects.equals(this.coordinates, that.coordinates) &&
                Objects.equals(this.countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, localNames, coordinates, countryCode);
    }

    @Override
    public String toString() {
        return "GeocodingRecord[" +
                "name=" + name + ", " +
                "localNames=" + localNames + ", " +
                "coordinates=" + coordinates + ", " +
                "countryCode=" + countryCode + ']';
    }

}

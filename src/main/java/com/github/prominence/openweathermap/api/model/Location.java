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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class Location {

    private String countryCode;
    private String name;
    private Integer id;

    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private ZoneOffset zoneOffset;

    private Coordinate coordinate;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }

    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }

    public void setZoneOffset(ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(countryCode, location.countryCode) &&
                Objects.equals(name, location.name) &&
                Objects.equals(id, location.id) &&
                Objects.equals(sunrise, location.sunrise) &&
                Objects.equals(sunset, location.sunset) &&
                Objects.equals(zoneOffset, location.zoneOffset) &&
                Objects.equals(coordinate, location.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, name, id, sunrise, sunset, zoneOffset, coordinate);
    }

    @Override
    public String toString() {
        String result = coordinate != null ? (coordinate.toString() + ", ") : "";
        return result +
                "Country code: '" + countryCode + '\'' +
                ", Name: '" + name + '\'' +
                ", ID: " + id;
    }
}

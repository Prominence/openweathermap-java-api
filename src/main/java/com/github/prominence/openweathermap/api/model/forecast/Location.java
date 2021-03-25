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

package com.github.prominence.openweathermap.api.model.forecast;

import com.github.prominence.openweathermap.api.model.Coordinate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * Represents location information.
 */
public class Location {
    private int id;
    private String name;
    private String countryCode;

    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private ZoneOffset zoneOffset;

    private Coordinate coordinate;

    private Long population;

    private Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Creates {@link Location} object with correctness check.
     * @param id location id
     * @param name location name
     * @return location object
     */
    public static Location withValues(int id, String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must be set.");
        }
        return new Location(id, name);
    }

    /**
     * Returns ID.
     * @return location ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets location ID.
     * @param id location id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns location name.
     * @return location name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets location name.
     * @param name location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns country code.
     * @return location country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets location country code.
     * @param countryCode location country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Returns location sunrise time.
     * @return sunrise time
     */
    public LocalDateTime getSunrise() {
        return sunrise;
    }

    /**
     * Sets location sunrise time.
     * @param sunrise sunrise time
     */
    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Returns location sunset time.
     * @return sunset time
     */
    public LocalDateTime getSunset() {
        return sunset;
    }

    /**
     * Sets location sunset time.
     * @param sunset sunset time
     */
    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }

    /**
     * Returns location timezone offset.
     * @return timezone offset
     */
    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }

    /**
     * Sets location timezone offset.
     * @param zoneOffset timezone offset
     */
    public void setZoneOffset(ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
    }

    /**
     * Returns location coordinates.
     * @return location coordinates.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Sets location coordinates.
     * @param coordinate location coordinates
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Sets location population.
     * @return location population
     */
    public Long getPopulation() {
        return population;
    }

    /**
     * Sets location population.
     * @param population location population
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(name, location.name) &&
                Objects.equals(countryCode, location.countryCode) &&
                Objects.equals(sunrise, location.sunrise) &&
                Objects.equals(sunset, location.sunset) &&
                Objects.equals(zoneOffset, location.zoneOffset) &&
                Objects.equals(coordinate, location.coordinate) &&
                Objects.equals(population, location.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryCode, sunrise, sunset, zoneOffset, coordinate, population);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        if (coordinate != null) {
            stringBuilder.append(coordinate.toString());
            stringBuilder.append(". ");
        }
        stringBuilder.append("ID: ");
        stringBuilder.append(id);
        stringBuilder.append(", Name: ");
        stringBuilder.append(name);
        if (countryCode != null) {
            stringBuilder.append('(');
            stringBuilder.append(countryCode);
            stringBuilder.append(')');
        }
        if (population != null) {
            stringBuilder.append(", Population: ");
            stringBuilder.append(population);
        }
        return stringBuilder.toString();
    }
}

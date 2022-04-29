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

package com.github.prominence.openweathermap.api.model.forecast.free;

import com.github.prominence.openweathermap.api.model.Coordinates;

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

    private LocalDateTime sunriseTime;
    private LocalDateTime sunsetTime;
    private ZoneOffset zoneOffset;

    private Coordinates coordinates;

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
    public LocalDateTime getSunriseTime() {
        return sunriseTime;
    }

    /**
     * Sets location sunrise time.
     * @param sunriseTime sunrise time
     */
    public void setSunriseTime(LocalDateTime sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    /**
     * Returns location sunset time.
     * @return sunset time
     */
    public LocalDateTime getSunsetTime() {
        return sunsetTime;
    }

    /**
     * Sets location sunset time.
     * @param sunsetTime sunset time
     */
    public void setSunsetTime(LocalDateTime sunsetTime) {
        this.sunsetTime = sunsetTime;
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
    public Coordinates getCoordinate() {
        return coordinates;
    }

    /**
     * Sets location coordinates.
     * @param coordinates location coordinates
     */
    public void setCoordinate(Coordinates coordinates) {
        this.coordinates = coordinates;
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
                Objects.equals(sunriseTime, location.sunriseTime) &&
                Objects.equals(sunsetTime, location.sunsetTime) &&
                Objects.equals(zoneOffset, location.zoneOffset) &&
                Objects.equals(coordinates, location.coordinates) &&
                Objects.equals(population, location.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryCode, sunriseTime, sunsetTime, zoneOffset, coordinates, population);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        if (coordinates != null) {
            stringBuilder.append(coordinates.toString());
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

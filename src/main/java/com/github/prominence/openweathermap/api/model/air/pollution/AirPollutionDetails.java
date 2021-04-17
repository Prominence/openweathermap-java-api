/*
 *
 *  * Copyright (c) 2021 Alexey Zinchenko
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package com.github.prominence.openweathermap.api.model.air.pollution;

import com.github.prominence.openweathermap.api.model.Coordinate;

import java.util.List;
import java.util.Objects;

/**
 * The type Air pollution.
 */
public class AirPollutionDetails {
    private Coordinate coordinate;
    private List<AirPollutionRecord> airPollutionRecords;

    /**
     * Gets coordinate.
     *
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Sets coordinate.
     *
     * @param coordinate the coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Gets air pollution details.
     *
     * @return the air pollution details
     */
    public List<AirPollutionRecord> getAirPollutionRecords() {
        return airPollutionRecords;
    }

    /**
     * Sets air pollution details.
     *
     * @param airPollutionRecords the air pollution details
     */
    public void setAirPollutionRecords(List<AirPollutionRecord> airPollutionRecords) {
        this.airPollutionRecords = airPollutionRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirPollutionDetails that = (AirPollutionDetails) o;
        return Objects.equals(coordinate, that.coordinate) && Objects.equals(airPollutionRecords, that.airPollutionRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, airPollutionRecords);
    }
}

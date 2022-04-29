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

import com.github.prominence.openweathermap.api.model.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AirPollutionDetailsUnitTest {
    @Test
    public void getCoordinate() {
        final AirPollutionDetails airPollutionDetails = new AirPollutionDetails();
        final Coordinates coordinates = Coordinates.of(22.3, 44.2);
        airPollutionDetails.setCoordinate(coordinates);

        assertEquals(coordinates, airPollutionDetails.getCoordinate());
    }

    @Test
    public void getAirPollutionSamples() {
        final AirPollutionDetails airPollutionDetails = new AirPollutionDetails();
        final List<AirPollutionRecord> airPollutionRecords = new ArrayList<>();
        airPollutionDetails.setAirPollutionRecords(airPollutionRecords);

        assertEquals(airPollutionRecords, airPollutionDetails.getAirPollutionRecords());
    }

    @Test
    public void testEquals() {
        final AirPollutionDetails first = new AirPollutionDetails();
        final AirPollutionDetails second = new AirPollutionDetails();
        final Coordinates coordinates = Coordinates.of(22.3, 44.2);
        final List<AirPollutionRecord> airPollutionRecords = new ArrayList<>();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertEquals(first, second);
        assertNotEquals(first, new Object());

        assertEquals(first, second);

        first.setCoordinate(coordinates);

        assertNotEquals(first, second);

        second.setCoordinate(coordinates);

        assertEquals(first, second);

        first.setAirPollutionRecords(airPollutionRecords);

        assertNotEquals(first, second);

        second.setAirPollutionRecords(airPollutionRecords);

        assertEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final AirPollutionDetails first = new AirPollutionDetails();
        final AirPollutionDetails second = new AirPollutionDetails();
        final Coordinates coordinates = Coordinates.of(22.3, 44.2);

        assertEquals(first.hashCode(), second.hashCode());

        first.setCoordinate(coordinates);

        assertNotEquals(first.hashCode(), second.hashCode());
    }
}
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

import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinatesUnitTest {

    @Test
    public void whenCreateCoordinateWithValidValues_thenObjectCreated() {
        new Coordinates(44, 53);
    }

    @Test
    public void whenCreateCoordinateWithInvalidLatitudeBelowMinus90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-333, 44));
    }

    @Test
    public void whenCreateCoordinateWithInvalidLatitudeAbove90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(223, 44));
    }

    @Test
    public void whenCreateCoordinateWithInvalidLongitudeBelowMinus180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(33, -999));
    }

    @Test
    public void whenCreateCoordinateWithInvalidLongitudeAbove180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(33, 999));
    }

    @Test
    public void whenSetValidCoordinates_thenAllIsFine() {
        Coordinates coordinates = new Coordinates(-90, -180);
        assertEquals(-90, coordinates.getLatitude(), 0.00001);
        assertEquals(-180, coordinates.getLongitude(), 0.00001);

        coordinates = new Coordinates(90, 180);
        assertEquals(90, coordinates.getLatitude(), 0.00001);
        assertEquals(180, coordinates.getLongitude(), 0.00001);

        coordinates = new Coordinates(44, 130);
        assertEquals(44, coordinates.getLatitude(), 0.00001);
        assertEquals(130, coordinates.getLongitude(), 0.00001);
    }

    @Test
    public void whenSetInvalidLatitudeBelowMinus90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-91, 0));
    }

    @Test
    public void whenSetInvalidLatitudeAbove90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(92, 0));
    }

    @Test
    public void whenSetInvalidLongitudeBelowMinus180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(0, -194));
    }

    @Test
    public void whenSetInvalidLongitudeAbove180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(0, 444));
    }

    @Test
    public void whenGetLatitude_thenAllIsFine() {
        final Coordinates coordinates = new Coordinates(44, 0);
        assertEquals(44, coordinates.getLatitude(), 0.00001);
    }

    @Test
    public void whenGetLongitude_thenAllIsFine() {
        final Coordinates coordinates = new Coordinates(0, 33);
        assertEquals(33, coordinates.getLongitude(), 0.00001);
    }
}

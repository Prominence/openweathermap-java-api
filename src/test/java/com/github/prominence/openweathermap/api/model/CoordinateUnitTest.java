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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateUnitTest {

    @Test
    public void whenCreateCoordinateWithValidValues_thenObjectCreated() {
        Coordinate.of(44, 53);
    }

    @Test
    public void whenCreateCoordinateWithInvalidLatitudeBelowMinus90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.of(-333, 44));
    }

    @Test
    public void whenCreateCoordinateWithInvalidLatitudeAbove90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.of(223, 44));
    }

    @Test
    public void whenCreateCoordinateWithInvalidLongitudeBelowMinus180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.of(33, -999));
    }

    @Test
    public void whenCreateCoordinateWithInvalidLongitudeAbove180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.of(33, 999));
    }

    @Test
    public void whenSetValidCoordinates_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.of(0, 0);

        coordinate.setLatitude(-90);
        assertEquals(-90, coordinate.getLatitude(), 0.00001);
        coordinate.setLatitude(90);
        assertEquals(90, coordinate.getLatitude(), 0.00001);
        coordinate.setLatitude(44);
        assertEquals(44, coordinate.getLatitude(), 0.00001);

        coordinate.setLongitude(-180);
        assertEquals(-180, coordinate.getLongitude(), 0.00001);
        coordinate.setLongitude(180);
        assertEquals(180, coordinate.getLongitude(), 0.00001);
        coordinate.setLongitude(130);
        assertEquals(130, coordinate.getLongitude(), 0.00001);
    }

    @Test
    public void whenSetInvalidLatitudeBelowMinus90_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.of(0, 0);
        assertThrows(IllegalArgumentException.class, () -> coordinate.setLatitude(-91));
    }

    @Test
    public void whenSetInvalidLatitudeAbove90_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.of(0, 0);
        assertThrows(IllegalArgumentException.class, () -> coordinate.setLatitude(92));
    }

    @Test
    public void whenSetInvalidLongitudeBelowMinus180_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.of(0, 0);
        assertThrows(IllegalArgumentException.class, () -> coordinate.setLongitude(-194));
    }

    @Test
    public void whenSetInvalidLongitudeAbove180_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.of(0, 0);
        assertThrows(IllegalArgumentException.class, () -> coordinate.setLongitude(444));
    }

    @Test
    public void whenGetLatitude_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.of(0, 0);
        assertEquals(0, coordinate.getLatitude(), 0.00001);

        coordinate.setLatitude(45);

        assertEquals(45, coordinate.getLatitude(), 0.00001);
    }

    @Test
    public void whenGetLongitude_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.of(0, 0);
        assertEquals(0, coordinate.getLongitude(), 0.00001);

        coordinate.setLongitude(33);

        assertEquals(33, coordinate.getLongitude(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.of(0, 0);
        assertNotNull(coordinate.toString());
        assertNotEquals("", coordinate.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Coordinate first = Coordinate.of(22, 66);
        final Coordinate second = Coordinate.of(22, 44);

        assertNotEquals(first.hashCode(), second.hashCode());

        second.setLongitude(66);

        assertEquals(first.hashCode(), second.hashCode());

        second.setLatitude(89);

        assertNotEquals(first.hashCode(), second.hashCode());

        first.setLatitude(89);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Coordinate first = Coordinate.of(11, 99);
        final Coordinate second = Coordinate.of(11, 99);

        assertEquals(first, second);
        assertEquals(first, first);
        assertNotEquals(first, new Object());

        first.setLatitude(34);

        assertNotEquals(first, second);

        second.setLatitude(34);

        assertEquals(first, second);

        second.setLongitude(74);

        assertNotEquals(first, second);

        first.setLongitude(74);

        assertEquals(first, second);
    }
}

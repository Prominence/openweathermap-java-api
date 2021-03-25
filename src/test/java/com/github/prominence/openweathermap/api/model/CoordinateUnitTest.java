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

import org.junit.Assert;
import org.junit.Test;

public class CoordinateUnitTest {
    @Test
    public void whenCreateCoordinateWithValidValues_thenObjectCreated() {
        Coordinate.withValues(44, 53);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLatitudeBelowMinus90_thenThrowAnException() {
        Coordinate.withValues(-333, 44);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLatitudeAbove90_thenThrowAnException() {
        Coordinate.withValues(223, 44);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLongitudeBelowMinus180_thenThrowAnException() {
        Coordinate.withValues(33, -999);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLongitudeAbove180_thenThrowAnException() {
        Coordinate.withValues(33, 999);
    }

    @Test
    public void whenSetValidCoordinates_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);

        coordinate.setLatitude(-90);
        Assert.assertEquals(-90, coordinate.getLatitude(), 0.00001);
        coordinate.setLatitude(90);
        Assert.assertEquals(90, coordinate.getLatitude(), 0.00001);
        coordinate.setLatitude(44);
        Assert.assertEquals(44, coordinate.getLatitude(), 0.00001);

        coordinate.setLongitude(-180);
        Assert.assertEquals(-180, coordinate.getLongitude(), 0.00001);
        coordinate.setLongitude(180);
        Assert.assertEquals(180, coordinate.getLongitude(), 0.00001);
        coordinate.setLongitude(130);
        Assert.assertEquals(130, coordinate.getLongitude(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLatitudeBelowMinus90_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);
        coordinate.setLatitude(-91);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLatitudeAbove90_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);
        coordinate.setLatitude(92);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLongitudeBelowMinus180_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);
        coordinate.setLongitude(-194);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLongitudeAbove180_thenThrowAnException() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);
        coordinate.setLongitude(444);
    }

    @Test
    public void whenGetLatitude_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);
        Assert.assertEquals(0, coordinate.getLatitude(), 0.00001);

        coordinate.setLatitude(45);

        Assert.assertEquals(45, coordinate.getLatitude(), 0.00001);
    }

    @Test
    public void whenGetLongitude_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);
        Assert.assertEquals(0, coordinate.getLongitude(), 0.00001);

        coordinate.setLongitude(33);

        Assert.assertEquals(33, coordinate.getLongitude(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Coordinate coordinate = Coordinate.withValues(0, 0);
        Assert.assertNotNull(coordinate.toString());
        Assert.assertNotEquals("", coordinate.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Coordinate first = Coordinate.withValues(22, 66);
        final Coordinate second = Coordinate.withValues(22, 44);

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        second.setLongitude(66);

        Assert.assertEquals(first.hashCode(), second.hashCode());

        second.setLatitude(89);

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        first.setLatitude(89);

        Assert.assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Coordinate first = Coordinate.withValues(11, 99);
        final Coordinate second = Coordinate.withValues(11, 99);

        Assert.assertTrue(first.equals(second));
        Assert.assertTrue(first.equals(first));
        Assert.assertFalse(first.equals(new Object()));

        first.setLatitude(34);

        Assert.assertFalse(first.equals(second));

        second.setLatitude(34);

        Assert.assertTrue(first.equals(second));

        second.setLongitude(74);

        Assert.assertFalse(first.equals(second));

        first.setLongitude(74);

        Assert.assertTrue(first.equals(second));
    }
}

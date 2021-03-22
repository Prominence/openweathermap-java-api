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

package com.github.prominence.openweathermap.api.model.weather;

import com.github.prominence.openweathermap.api.model.CoordinateRectangle;
import org.junit.Assert;
import org.junit.Test;

public class CoordinateRectangleUnitTest {

    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        CoordinateRectangle.forValues(44.5, 22.4, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeBottomBelowMinus90_thenThrowAnException() {
        CoordinateRectangle.forValues(44.5, -91.2, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeBottomAbove90_thenThrowAnException() {
        CoordinateRectangle.forValues(44.5, 91.2, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeTopBelowMinus90_thenThrowAnException() {
        CoordinateRectangle.forValues(44.5, 22.4, 54.4, -92.3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeTopAbove90_thenThrowAnException() {
        CoordinateRectangle.forValues(44.5, 22.5, 54.4, 94.887);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeLeftBelowMinus180_thenThrowAnException() {
        CoordinateRectangle.forValues(-944.5, 22.4, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeLeftAbove180_thenThrowAnException() {
        CoordinateRectangle.forValues(544.5, 22.4, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeRightBelowMinus180_thenThrowAnException() {
        CoordinateRectangle.forValues(44.5, 22.4, -254.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeRightAbove180_thenThrowAnException() {
        CoordinateRectangle.forValues(44.5, 22.4, 354.4, 22.2);
    }

    @Test
    public void whenGetAllParameters_thenAllIsFine() {
        final CoordinateRectangle rectangle = CoordinateRectangle.forValues(44.5, 22.4, 54.4, 22.2);
        Assert.assertEquals(44.5, rectangle.getLongitudeLeft(), 0.00001);
        Assert.assertEquals(22.4, rectangle.getLatitudeBottom(), 0.00001);
        Assert.assertEquals(54.4, rectangle.getLongitudeRight(), 0.00001);
        Assert.assertEquals(22.2, rectangle.getLatitudeTop(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final CoordinateRectangle rectangle = CoordinateRectangle.forValues(44.5, 22.4, 54.4, 22.2);

        Assert.assertNotNull(rectangle.toString());
        Assert.assertNotEquals("", rectangle.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final CoordinateRectangle first = CoordinateRectangle.forValues(44.5, 22.4, 54.4, 22.2);
        final CoordinateRectangle second = CoordinateRectangle.forValues(44.5, 22.4, 54.4, 22.2);

        Assert.assertEquals(first.hashCode(), second.hashCode());

        final CoordinateRectangle third = CoordinateRectangle.forValues(44.5, 22.4, 54.4, 23.566);

        Assert.assertNotEquals(first.hashCode(), third.hashCode());
        Assert.assertNotEquals(second.hashCode(), third.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        CoordinateRectangle first = CoordinateRectangle.forValues(44.5, 22.4, 54.4, 22.2);
        CoordinateRectangle second = CoordinateRectangle.forValues(44.5, 22.4, 54.4, 22.2);

        Assert.assertTrue(first.equals(second));
        Assert.assertTrue(first.equals(first));
        Assert.assertFalse(first.equals(new Object()));

        first = CoordinateRectangle.forValues(49.5, 22.4, 54.4, 22.2);

        Assert.assertFalse(first.equals(second));

        first = CoordinateRectangle.forValues(44.5, 29.4, 54.4, 22.2);

        Assert.assertFalse(first.equals(second));

        first = CoordinateRectangle.forValues(44.5, 22.4, 24.4, 22.2);

        Assert.assertFalse(first.equals(second));

        first = CoordinateRectangle.forValues(44.5, 22.4, 54.4, -2.2);

        Assert.assertFalse(first.equals(second));
    }
}

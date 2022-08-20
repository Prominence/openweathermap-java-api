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

import com.github.prominence.openweathermap.api.model.generic.location.CoordinatesRectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinatesRectangleUnitTest {
    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 22.2);
    }

    @Test
    public void whenCreateObjectWithLatitudeBottomBelowMinus90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(44.5, -91.2, 54.4, 22.2));
    }

    @Test
    public void whenCreateObjectWithLatitudeBottomAbove90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(44.5, 91.2, 54.4, 22.2));
    }

    @Test
    public void whenCreateObjectWithLatitudeTopBelowMinus90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(44.5, 22.4, 54.4, -92.3));
    }

    @Test
    public void whenCreateObjectWithLatitudeTopAbove90_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(44.5, 22.5, 54.4, 94.887));
    }

    @Test
    public void whenCreateObjectWithLongitudeLeftBelowMinus180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(-944.5, 22.4, 54.4, 22.2));
    }

    @Test
    public void whenCreateObjectWithLongitudeLeftAbove180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(544.5, 22.4, 54.4, 22.2));
    }

    @Test
    public void whenCreateObjectWithLongitudeRightBelowMinus180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(44.5, 22.4, -254.4, 22.2));
    }

    @Test
    public void whenCreateObjectWithLongitudeRightAbove180_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> CoordinatesRectangle.withValues(44.5, 22.4, 354.4, 22.2));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLatitudeBottom_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLatitudeBottom(-1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLatitudeBottom2_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLatitudeBottom(1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLatitudeTop_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLatitudeTop(-1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLatitudeTop2_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLatitudeTop(1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLongitudeLeft_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeLeft(-1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLongitudeLeft2_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeLeft(1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLongitudeRight_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeRight(-1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithInvalidLongitudeRight2_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeRight(1000));
    }

    @Test
    public void whenCreateObjectUsingBuilderWithoutAllPropertiesSet_thenFail() {
        assertThrows(IllegalStateException.class, () -> new CoordinatesRectangle.Builder()
                .build());
    }

    @Test
    public void whenCreateObjectUsingBuilderWithoutAllPropertiesSet1_thenFail() {
        assertThrows(IllegalStateException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeLeft(10)
                .build());
    }

    @Test
    public void whenCreateObjectUsingBuilderWithoutAllPropertiesSet2_thenFail() {
        assertThrows(IllegalStateException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeRight(10)
                .setLatitudeBottom(10)
                .build());
    }

    @Test
    public void whenCreateObjectUsingBuilderWithoutAllPropertiesSet3_thenFail() {
        assertThrows(IllegalStateException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeLeft(10)
                .setLatitudeBottom(10)
                .setLongitudeRight(10)
                .build());
    }

    @Test
    public void whenCreateObjectUsingBuilderWithoutAllPropertiesSet4_thenFail() {
        assertThrows(IllegalStateException.class, () -> new CoordinatesRectangle.Builder()
                .setLongitudeLeft(10)
                .setLatitudeTop(10)
                .setLatitudeBottom(10)
                .build());
    }

    @Test
    public void whenCreateObjectUsingBuilderWithCorrectUsage_thenOk() {
        final CoordinatesRectangle rectangle = new CoordinatesRectangle.Builder()
                .setLongitudeRight(10)
                .setLongitudeLeft(10)
                .setLatitudeTop(10)
                .setLatitudeBottom(10)
                .build();

        assertNotNull(rectangle);
    }

    @Test
    public void whenGetAllParameters_thenAllIsFine() {
        final CoordinatesRectangle rectangle = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 22.2);
        assertEquals(44.5, rectangle.getLongitudeLeft(), 0.00001);
        assertEquals(22.4, rectangle.getLatitudeBottom(), 0.00001);
        assertEquals(54.4, rectangle.getLongitudeRight(), 0.00001);
        assertEquals(22.2, rectangle.getLatitudeTop(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final CoordinatesRectangle rectangle = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 22.2);

        assertNotNull(rectangle.toString());
        assertNotEquals("", rectangle.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final CoordinatesRectangle first = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 22.2);
        final CoordinatesRectangle second = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 22.2);

        assertEquals(first.hashCode(), second.hashCode());

        final CoordinatesRectangle third = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 23.566);

        assertNotEquals(first.hashCode(), third.hashCode());
        assertNotEquals(second.hashCode(), third.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        CoordinatesRectangle first = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 22.2);
        CoordinatesRectangle second = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, 22.2);

        assertEquals(first, second);
        assertEquals(first, first);
        assertNotEquals(first, new Object());

        first = CoordinatesRectangle.withValues(49.5, 22.4, 54.4, 22.2);

        assertNotEquals(first, second);

        first = CoordinatesRectangle.withValues(44.5, 29.4, 54.4, 22.2);

        assertNotEquals(first, second);

        first = CoordinatesRectangle.withValues(44.5, 22.4, 24.4, 22.2);

        assertNotEquals(first, second);

        first = CoordinatesRectangle.withValues(44.5, 22.4, 54.4, -2.2);

        assertNotEquals(first, second);
    }
}

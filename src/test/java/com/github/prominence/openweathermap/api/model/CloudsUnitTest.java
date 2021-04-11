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

public class CloudsUnitTest {
    @Test
    public void whenCreateCloudsWithValidArgs_thenValueIsSet() {
        Clouds clouds = Clouds.withValue((byte) 100);
        assertEquals(100, clouds.getValue());

        assertEquals(0, Clouds.withValue((byte) 0).getValue());
        assertEquals(100, Clouds.withValue((byte) 100).getValue());
        assertEquals(55, Clouds.withValue((byte) 55).getValue());
    }

    @Test
    public void whenCreateCloudsByConstructorWithInvalidDataAboveHundred_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Clouds.withValue((byte) 110));
    }

    @Test
    public void whenCreateCloudsByConstructorWithInvalidDataNegative_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Clouds.withValue((byte) -33));
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Clouds clouds = Clouds.withValue((byte) 14);
        clouds.setValue((byte) 0);
        assertEquals(0, clouds.getValue());
        clouds.setValue((byte) 15);
        assertEquals(15, clouds.getValue());
        clouds.setValue((byte) 100);
        assertEquals(100, clouds.getValue());
    }

    @Test
    public void whenCreateCloudsAndSetInvalidDataAboveHundred_thenThrowAnException() {
        Clouds clouds = Clouds.withValue((byte) 12);

        assertThrows(IllegalArgumentException.class, () -> clouds.setValue((byte) 112));
    }

    @Test
    public void whenCreateCloudsAndSetInvalidDataNegative_thenThrowAnException() {
        Clouds clouds = Clouds.withValue((byte) 88);

        assertThrows(IllegalArgumentException.class, () -> clouds.setValue((byte) -89));
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Clouds one = Clouds.withValue((byte) 22);
        Clouds two = Clouds.withValue((byte) 22);

        assertEquals(one, two);
        assertEquals(one, one);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Clouds one = Clouds.withValue((byte) 5);
        Clouds two = Clouds.withValue((byte) 88);

        assertNotEquals(one, two);
        assertNotEquals(two, one);
        assertNotEquals(one, new Object());
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String cloudsString = Clouds.withValue((byte) 44).toString();
        assertNotNull(cloudsString);
        assertNotEquals("", cloudsString);
    }
}

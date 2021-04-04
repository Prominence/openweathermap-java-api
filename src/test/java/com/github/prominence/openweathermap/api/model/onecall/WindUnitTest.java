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

package com.github.prominence.openweathermap.api.model.onecall;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindUnitTest {
    @Test(expected = IllegalArgumentException.class)
    public void withInvalidValue_noUnit() {
        Wind.withValue(20, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withInvalidValue_negative() {
        Wind.withValue(-20, "m/s");
    }

    @Test
    public void getSpeed() {
        final Wind wind = Wind.withValue(20, "m/s");

        assertEquals(20.0, wind.getSpeed(), 0.00001);
    }

    @Test
    public void getGust() {
        final Wind wind = Wind.withValue(20, "m/s");
        wind.setGust(40);

        assertEquals(40.0, wind.getGust(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setGust_negative() {
        final Wind wind = Wind.withValue(20, "m/s");
        wind.setGust(-20);
    }

    @Test
    public void getDegrees() {
        final Wind wind = Wind.withValue(20, "m/s");
        wind.setDegrees(180);

        assertEquals(180.0, wind.getDegrees(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDegrees_lessThanZero() {
        final Wind wind = Wind.withValue(20, "m/s");
        wind.setDegrees(-20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDegrees_moreThan360() {
        final Wind wind = Wind.withValue(20, "m/s");
        wind.setDegrees(420);
    }

    @Test
    public void getUnit() {
        final Wind wind = Wind.withValue(20, "m/s");

        assertEquals("m/s", wind.getUnit());
    }

    @Test
    public void testEquals() {
        final Wind first = Wind.withValue(40, "m/s");
        final Wind second = Wind.withValue(40, "m/s");

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        assertEquals(first, second);

        first.setSpeed(20);

        assertNotEquals(first, second);

        second.setSpeed(20);

        assertEquals(first, second);

        first.setDegrees(10);

        assertNotEquals(first, second);

        second.setDegrees(10);

        assertEquals(first, second);

        first.setGust(30);

        assertNotEquals(first, second);

        second.setGust(30);

        assertEquals(first, second);

        second.setUnit("km/h");

        assertNotEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final Wind first = Wind.withValue(40, "m/s");
        final Wind second = Wind.withValue(40, "m/s");

        assertEquals(first.hashCode(), second.hashCode());

        first.setGust(30);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final Wind wind = Wind.withValue(40, "m/s");

        assertNotNull(wind.toString());
        assertNotEquals("", wind.toString());

        wind.setGust(20);

        assertNotNull(wind.toString());
        assertNotEquals("", wind.toString());
    }
}
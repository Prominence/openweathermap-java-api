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

public class RainUnitTest {
    @Test(expected = IllegalArgumentException.class)
    public void withInvalidValue_negative() {
        Rain.withOneHourLevelValue(-20.0);
    }

    @Test
    public void getOneHourRainLevel() {
        final Rain rain = Rain.withOneHourLevelValue(220.0);

        assertEquals(220.0, rain.getOneHourLevel(), 0.00001);
    }

    @Test
    public void getUnit() {
        final Rain rain = Rain.withOneHourLevelValue(220.0);

        assertNotNull(rain.getUnit());
        assertNotEquals("", rain.getUnit());
    }

    @Test
    public void testEquals() {
        final Rain first = Rain.withOneHourLevelValue(600);
        final Rain second = Rain.withOneHourLevelValue(600);

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        assertEquals(first, second);

        first.setOneHourLevel(200);

        assertNotEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final Rain first = Rain.withOneHourLevelValue(600);
        final Rain second = Rain.withOneHourLevelValue(600);

        assertEquals(first.hashCode(), second.hashCode());

        first.setOneHourLevel(200);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final Rain rain = Rain.withOneHourLevelValue(22.2);

        assertNotNull(rain.toString());
        assertNotEquals("", rain.toString());
    }
}
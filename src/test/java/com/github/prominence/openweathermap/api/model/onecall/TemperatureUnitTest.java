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

public class TemperatureUnitTest {
    @Test(expected = IllegalArgumentException.class)
    public void withInvalidValue_noUnit() {
        Temperature.withValue(-20, null);
    }

    @Test
    public void getValue() {
        final Temperature temperature = Temperature.withValue(20, "K");

        assertEquals(20, temperature.getValue(), 0.00001);
    }

    @Test
    public void getFeelsLike() {
        final Temperature temperature = Temperature.withValue(20, "K");
        temperature.setFeelsLike(18.0);

        assertEquals(18.0, temperature.getFeelsLike(), 0.00001);
    }

    @Test
    public void getDewPoint() {
        final Temperature temperature = Temperature.withValue(20, "K");
        temperature.setDewPoint(5.0);

        assertEquals(5.0, temperature.getDewPoint(), 0.00001);
    }

    @Test
    public void getUnit() {
        final Temperature temperature = Temperature.withValue(20, "K");

        assertEquals("K", temperature.getUnit());
    }

    @Test
    public void testEquals() {
        final Temperature first = Temperature.withValue(30, "L");
        final Temperature second = Temperature.withValue(30, "K");

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        assertNotEquals(first, second);

        first.setUnit("K");

        assertEquals(first, second);

        first.setFeelsLike(25.0);

        assertNotEquals(first, second);

        second.setFeelsLike(25.0);

        assertEquals(first, second);

        first.setDewPoint(10.0);

        assertNotEquals(first, second);

        second.setDewPoint(10.0);

        assertEquals(first, second);

        first.setValue(27.0);

        assertNotEquals(first, second);

        second.setValue(27.0);

        assertEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final Temperature first = Temperature.withValue(600, "K");
        final Temperature second = Temperature.withValue(600, "K");

        assertEquals(first.hashCode(), second.hashCode());

        first.setFeelsLike(599d);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final Temperature temperature = Temperature.withValue(20, "K");

        assertNotNull(temperature.toString());
        assertNotEquals("", temperature.toString());

        temperature.setFeelsLike(22.4);

        assertNotNull(temperature.toString());
        assertNotEquals("", temperature.toString());
    }
}
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

package com.github.prominence.openweathermap.api.model.onecall.current;

import org.junit.Test;

import static org.junit.Assert.*;

public class DailySnowUnitTest {
    @Test
    public void getValue() {
        final DailySnow dailySnow = DailySnow.withValue(20);

        assertEquals(20, dailySnow.getValue(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInvalidValue_negative() {
        DailySnow.withValue(-20);
    }

    @Test
    public void getUnit() {
        final DailySnow dailySnow = DailySnow.withValue(20);

        assertNotNull(dailySnow.getUnit());
        assertNotEquals("", dailySnow.getUnit());
    }

    @Test
    public void getEquals() {
        final DailySnow first = DailySnow.withValue(0);
        final DailySnow second = DailySnow.withValue(0);

        assertEquals(first, second);

        first.setValue(20);

        assertNotEquals(first, second);
    }

    @Test
    public void getHashCode() {
        final DailySnow first = DailySnow.withValue(0);
        final DailySnow second = DailySnow.withValue(0);

        assertEquals(first.hashCode(), second.hashCode());

        first.setValue(20);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void getToString() {
        final DailySnow dailySnow = DailySnow.withValue(20);

        assertNotNull(dailySnow.toString());
        assertNotEquals("", dailySnow.toString());
    }
}
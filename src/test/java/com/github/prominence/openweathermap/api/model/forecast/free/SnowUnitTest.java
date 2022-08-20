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

package com.github.prominence.openweathermap.api.model.forecast.free;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SnowUnitTest {
    @Test
    public void whenCreateSnowWithValidArgs_ObjectIsCreated() {
        Snow.withThreeHourLevelValue(2222.3);
        Snow.withThreeHourLevelValue(0);
    }

    @Test
    public void whenCreateWithInvalidData_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> Snow.withThreeHourLevelValue(-20));
    }

    @Test
    public void whenSetValues_thenTheyAreSet() {
        final Snow snow = Snow.withThreeHourLevelValue(0);

        assertEquals(0, snow.getThreeHourLevel(), 0.00001);

        snow.setThreeHourLevel(55.5);
        assertEquals(55.5, snow.getThreeHourLevel(), 0.00001);
    }

    @Test
    public void whenSetInvalidValue_thenFail() {
        final Snow snow = Snow.withThreeHourLevelValue(0);

        assertThrows(IllegalArgumentException.class, () -> snow.setThreeHourLevel(-20));
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Snow snow = Snow.withThreeHourLevelValue(33.5);

        assertNotNull(snow.toString());
        assertNotEquals("", snow.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Snow first = Snow.withThreeHourLevelValue(0);
        final Snow second = Snow.withThreeHourLevelValue(0);

        assertEquals(first.hashCode(), second.hashCode());

        second.setThreeHourLevel(11.0);

        assertNotEquals(first.hashCode(), second.hashCode());

        first.setThreeHourLevel(11.0);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Snow first = Snow.withThreeHourLevelValue(0);
        final Snow second = Snow.withThreeHourLevelValue(0);

        assertEquals(first, second);
        assertEquals(first, first);
        assertNotEquals(first, new Object());

        second.setThreeHourLevel(66.7);

        assertNotEquals(first, second);

        first.setThreeHourLevel(66.7);

        assertEquals(first, second);
    }
}

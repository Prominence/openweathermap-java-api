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

package com.github.prominence.openweathermap.api.model.onecall;

import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtmosphericPressureUnitTest {
    @Test
    public void withInvalidValue_negative() {
        assertThrows(IllegalArgumentException.class, () -> AtmosphericPressure.withValue(-20.0));
    }

    @Test
    public void getSeaLevelValue() {
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.2);

        assertEquals(22.2, atmosphericPressure.getSeaLevelValue(), 0.00001);
    }

    @Test
    public void getUnit() {
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.2);

        assertNotNull(atmosphericPressure.getUnit());
        assertNotEquals("", atmosphericPressure.getUnit());
    }

    @Test
    public void testEquals() {
        final AtmosphericPressure first = AtmosphericPressure.withValue(600);
        final AtmosphericPressure second = AtmosphericPressure.withValue(600);

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        assertEquals(first, second);

        first.setSeaLevelValue(200);

        assertNotEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final AtmosphericPressure first = AtmosphericPressure.withValue(600);
        final AtmosphericPressure second = AtmosphericPressure.withValue(600);

        assertEquals(first.hashCode(), second.hashCode());

        first.setSeaLevelValue(200);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(22.2);

        assertNotNull(atmosphericPressure.toString());
        assertNotEquals("", atmosphericPressure.toString());
    }
}

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

package com.github.prominence.openweathermap.api.model.onecall.current;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinutelyUnitTest {
    @Test
    public void withValue() {
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final Minutely minutely = Minutely.withValue(now, 10.0);

        assertEquals(now, minutely.getForecastTime());
        assertEquals(10.0, minutely.getPrecipitationVolume(), 0.00001);
    }

    @Test
    public void withInvalidValueValue_negative() {
        assertThrows(IllegalArgumentException.class, () -> Minutely.withValue(LocalDateTime.now(), -10.0));

    }

    @Test
    public void testEquals() {
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final Minutely first = Minutely.withValue(now, 10.0);
        final Minutely firstCopy = Minutely.withValue(now, 10.0);
        final Minutely second = Minutely.withValue(now, 11.0);
        final Minutely secondCopy = Minutely.withValue(now.plusMinutes(11), 11.0);
        final Minutely third = Minutely.withValue(now.plusMinutes(22), 44.9);

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        assertEquals(first, firstCopy);
        assertNotEquals(first, second);
        assertNotEquals(second, secondCopy);
        assertNotEquals(second, third);
    }

    @Test
    public void testHashCode() {
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final Minutely first = Minutely.withValue(now, 10.0);
        final Minutely firstCopy = Minutely.withValue(now, 10.0);
        final Minutely second = Minutely.withValue(now.plusMinutes(2), 11.0);

        assertEquals(first.hashCode(), first.hashCode());
        assertEquals(first.hashCode(), firstCopy.hashCode());

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final Minutely minutely = Minutely.withValue(LocalDateTime.now(), 10.0);

        assertNotNull(minutely.toString());
        assertNotEquals("", minutely.toString());
    }
}

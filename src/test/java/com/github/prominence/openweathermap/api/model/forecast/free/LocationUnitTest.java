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

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocationUnitTest {
    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        Location.withValues(33, "test");
    }

    @Test
    public void whenCreateObjectWithoutName_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Location.withValues(33, null));
    }

    @Test
    public void whenSetId_thenValueIsSet() {
        final Location location = Location.withValues(33, "test");
        location.setId(55);

        assertEquals(55, location.getId());
    }

    @Test
    public void whenSetName_thenValueIsSet() {
        final Location location = Location.withValues(33, "test");
        location.setName("city");

        assertEquals("city", location.getName());
    }

    @Test
    public void whenSetCountryCode_thenValueIsSet() {
        final Location location = Location.withValues(33, "test");
        location.setCountryCode("by");

        assertEquals("by", location.getCountryCode());
    }

    @Test
    public void whenSetSunrise_thenValueIsSet() {
        final Location location = Location.withValues(33, "test");
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        location.setSunriseTime(now);

        assertEquals(now, location.getSunriseTime());
    }

    @Test
    public void whenSetSunset_thenValueIsSet() {
        final Location location = Location.withValues(33, "test");
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        location.setSunsetTime(now);

        assertEquals(now, location.getSunsetTime());
    }

    @Test
    public void whenSetZoneOffset_thenValueIsSet() {
        final Location location = Location.withValues(33, "test");
        final ZoneOffset offset = ZoneOffset.UTC;
        location.setZoneOffset(offset);

        assertEquals(offset, location.getZoneOffset());
    }

}

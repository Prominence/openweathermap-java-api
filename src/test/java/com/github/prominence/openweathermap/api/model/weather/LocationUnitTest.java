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

package com.github.prominence.openweathermap.api.model.weather;

import com.github.prominence.openweathermap.api.model.Coordinates;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocationUnitTest {
    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        City.withValues(33, "test");
    }

    @Test
    public void whenCreateObjectWithoutName_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> City.withValues(33, null));
    }

    @Test
    public void whenSetId_thenValueIsSet() {
        final City location = City.withValues(33, "test");
        location.setId(55);

        assertEquals(55, location.getId());
    }

    @Test
    public void whenSetName_thenValueIsSet() {
        final City location = City.withValues(33, "test");
        location.setName("city");

        assertEquals("city", location.getName());
    }

    @Test
    public void whenSetCountryCode_thenValueIsSet() {
        final City location = City.withValues(33, "test");
        location.setCountryCode("by");

        assertEquals("by", location.getCountryCode());
    }

    @Test
    public void whenSetSunrise_thenValueIsSet() {
        final City location = City.withValues(33, "test");
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        location.setSunriseTime(now);

        assertEquals(now, location.getSunriseTime());
    }

    @Test
    public void whenSetSunset_thenValueIsSet() {
        final City location = City.withValues(33, "test");
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        location.setSunsetTime(now);

        assertEquals(now, location.getSunsetTime());
    }

    @Test
    public void whenSetZoneOffset_thenValueIsSet() {
        final City location = City.withValues(33, "test");
        final ZoneOffset offset = ZoneOffset.UTC;
        location.setZoneOffset(offset);

        assertEquals(offset, location.getZoneOffset());
    }

    @Test
    public void whenSetCoordinate_thenValueIsSet() {
        final City location = City.withValues(33, "test");
        final Coordinates coordinates = new Coordinates(33.2, 64.2);
        location.setCoordinates(coordinates);

        assertEquals(coordinates, location.getCoordinates());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final City location = City.withValues(44, "test");

        assertNotEquals("", location.toString());

        location.setCoordinates(new Coordinates(33.2, 56.3));

        assertNotEquals("", location.toString());

        location.setCountryCode("TN");

        assertNotEquals("", location.toString());
        assertNotNull(location.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final City one = City.withValues(44, "test");
        final City two = City.withValues(44, "test");

        assertEquals(one.hashCode(), two.hashCode());

        two.setName("112");

        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final City one = City.withValues(44, "test");
        final City two = City.withValues(44, "test");

        assertEquals(one, one);
        assertNotEquals(one, new Object());

        assertEquals(one, two);

        two.setId(23);

        assertNotEquals(one, two);

        one.setId(23);

        assertEquals(one, two);

        one.setName("23");

        assertNotEquals(one, two);

        two.setName("23");

        assertEquals(one, two);

        one.setCountryCode("11");

        assertNotEquals(one, two);

        two.setCountryCode("11");

        assertEquals(one, two);

        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        one.setSunriseTime(now);

        assertNotEquals(one, two);

        two.setSunriseTime(now);

        assertEquals(one, two);

        one.setSunsetTime(now);

        assertNotEquals(one, two);

        two.setSunsetTime(now);

        assertEquals(one, two);

        one.setZoneOffset(ZoneOffset.UTC);

        assertNotEquals(one, two);

        two.setZoneOffset(ZoneOffset.UTC);

        assertEquals(one, two);

        final Coordinates coordinates = new Coordinates(33.5, -22.4);

        one.setCoordinates(coordinates);

        assertNotEquals(one, two);

        two.setCoordinates(coordinates);

        assertEquals(one, two);
    }
}

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

package com.github.prominence.openweathermap.api.model.forecast;

import com.github.prominence.openweathermap.api.model.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocationUnitTest {
    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        Location.forValue(33, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithoutName_thenThrowAnException() {
        Location.forValue(33, null);
    }

    @Test
    public void whenSetId_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        location.setId(55);

        Assert.assertEquals(55, location.getId());
    }

    @Test
    public void whenSetName_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        location.setName("city");

        Assert.assertEquals("city", location.getName());
    }

    @Test
    public void whenSetCountryCode_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        location.setCountryCode("by");

        Assert.assertEquals("by", location.getCountryCode());
    }

    @Test
    public void whenSetSunrise_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        final LocalDateTime now = LocalDateTime.now();
        location.setSunrise(now);

        Assert.assertEquals(now, location.getSunrise());
    }

    @Test
    public void whenSetSunset_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        final LocalDateTime now = LocalDateTime.now();
        location.setSunset(now);

        Assert.assertEquals(now, location.getSunset());
    }

    @Test
    public void whenSetZoneOffset_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        final ZoneOffset offset = ZoneOffset.UTC;
        location.setZoneOffset(offset);

        Assert.assertEquals(offset, location.getZoneOffset());
    }

    @Test
    public void whenSetCoordinate_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        final Coordinate coordinate = Coordinate.forValues(33.2, 64.2);
        location.setCoordinate(coordinate);

        Assert.assertEquals(coordinate, location.getCoordinate());
    }

    @Test
    public void whenSetPopulation_thenValueIsSet() {
        final Location location = Location.forValue(33, "test");
        location.setPopulation(3444L);

        Assert.assertEquals(3444L, location.getPopulation().longValue());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Location location = Location.forValue(44, "test");

        Assert.assertNotEquals("", location.toString());

        location.setCoordinate(Coordinate.forValues(33.2, 56.3));

        Assert.assertNotEquals("", location.toString());

        location.setCountryCode("TN");

        Assert.assertNotEquals("", location.toString());
        Assert.assertNotNull(location.toString());

        location.setPopulation(34343L);
        Assert.assertNotEquals("", location.toString());
        Assert.assertNotNull(location.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Location one = Location.forValue(44, "test");
        final Location two = Location.forValue(44, "test");

        Assert.assertEquals(one.hashCode(), two.hashCode());

        two.setName("112");

        Assert.assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Location one = Location.forValue(44, "test");
        final Location two = Location.forValue(44, "test");

        Assert.assertTrue(one.equals(one));
        Assert.assertFalse(one.equals(new Object()));

        Assert.assertTrue(one.equals(two));

        two.setId(23);

        Assert.assertFalse(one.equals(two));

        one.setId(23);

        Assert.assertTrue(one.equals(two));

        one.setName("23");

        Assert.assertFalse(one.equals(two));

        two.setName("23");

        Assert.assertTrue(one.equals(two));

        one.setCountryCode("11");

        Assert.assertFalse(one.equals(two));

        two.setCountryCode("11");

        Assert.assertTrue(one.equals(two));

        final LocalDateTime now = LocalDateTime.now();

        one.setSunrise(now);

        Assert.assertFalse(one.equals(two));

        two.setSunrise(now);

        Assert.assertTrue(one.equals(two));

        one.setSunset(now);

        Assert.assertFalse(one.equals(two));

        two.setSunset(now);

        Assert.assertTrue(one.equals(two));

        one.setZoneOffset(ZoneOffset.UTC);

        Assert.assertFalse(one.equals(two));

        two.setZoneOffset(ZoneOffset.UTC);

        Assert.assertTrue(one.equals(two));

        final Coordinate coordinate = Coordinate.forValues(33.5, -22.4);

        one.setCoordinate(coordinate);

        Assert.assertFalse(one.equals(two));

        two.setCoordinate(coordinate);

        Assert.assertTrue(one.equals(two));

        one.setPopulation(20000L);

        Assert.assertFalse(one.equals(two));

        two.setPopulation(20000L);

        Assert.assertTrue(one.equals(two));
    }
}

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

package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AtmosphericPressureUnitTest {
    @Test
    public void whenCreatePressureWithArgs_thenValueIsSet() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(100);
        assertEquals(100, atmosphericPressure.getValue(), 0.00001);

        assertEquals(0, AtmosphericPressure.withValue(0).getValue(), 0.00001);
        assertEquals(100, AtmosphericPressure.withValue(100).getValue(), 0.00001);
        assertEquals(55, AtmosphericPressure.withValue(55).getValue(), 0.00001);
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        AtmosphericPressure one = AtmosphericPressure.withValue(22);
        AtmosphericPressure two = AtmosphericPressure.withValue(22);

        assertEquals(one, two);
        assertEquals(one, one);
        assertEquals(one.hashCode(), two.hashCode());

        one.setSeaLevelValue(333);
        one.setGroundLevelValue(555);

        two.setSeaLevelValue(333);
        two.setGroundLevelValue(555);

        assertEquals(one, two);
        assertEquals(two, one);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        AtmosphericPressure one = AtmosphericPressure.withValue(5);
        AtmosphericPressure two = AtmosphericPressure.withValue(88);

        assertNotEquals(one, two);
        assertNotEquals(two, one);
        assertNotEquals(one, new Object());
        assertNotEquals(one.hashCode(), two.hashCode());

        one = AtmosphericPressure.withValue(44);
        one.setSeaLevelValue(44);
        two = AtmosphericPressure.withValue(44);
        two.setGroundLevelValue(22);

        assertNotEquals(one, two);
        assertNotEquals(two, one);

        two.setSeaLevelValue(44);

        assertNotEquals(one, two);
        assertNotEquals(two, one);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(14);
        atmosphericPressure.setValue(0);
        atmosphericPressure.setValue(15);
        atmosphericPressure.setValue(100);

        atmosphericPressure.setGroundLevelValue(222);
        assertEquals(222, atmosphericPressure.getGroundLevelValue(), 0.00001);

        atmosphericPressure.setSeaLevelValue(4232);
        assertEquals(4232, atmosphericPressure.getSeaLevelValue(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String pressureString = AtmosphericPressure.withValue(44).toString();
        assertNotNull(pressureString);
        assertNotEquals("", pressureString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureByConstructorWithInvalidDataNegative_thenThrowAnException() {
        AtmosphericPressure.withValue(-33);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureAndSetInvalidDataNegative_thenThrowAnException() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(88);
        atmosphericPressure.setValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidSeaLevelPressure_thenThrowAnException() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(88);
        atmosphericPressure.setSeaLevelValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidGroundLevelPressure_thenThrowAnException() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(88);
        atmosphericPressure.setGroundLevelValue(-223);
    }
}

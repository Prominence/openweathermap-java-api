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

package com.github.prominence.openweathermap.api.model.weather;

import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import org.junit.Assert;
import org.junit.Test;

public class AtmosphericPressureUnitTest {

    @Test
    public void whenCreatePressureWithArgs_thenValueIsSet() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.forValue(100);
        Assert.assertEquals(100, atmosphericPressure.getValue(), 0.00001);

        Assert.assertEquals(0, AtmosphericPressure.forValue(0).getValue(), 0.00001);
        Assert.assertEquals(100, AtmosphericPressure.forValue(100).getValue(), 0.00001);
        Assert.assertEquals(55, AtmosphericPressure.forValue(55).getValue(), 0.00001);
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        AtmosphericPressure one = AtmosphericPressure.forValue(22);
        AtmosphericPressure two = AtmosphericPressure.forValue(22);

        Assert.assertTrue(one.equals(two));
        Assert.assertTrue(one.equals(one));
        Assert.assertEquals(one.hashCode(), two.hashCode());

        one.setSeaLevelValue(333);
        one.setGroundLevelValue(555);

        two.setSeaLevelValue(333);
        two.setGroundLevelValue(555);

        Assert.assertTrue(one.equals(two));
        Assert.assertTrue(two.equals(one));
        Assert.assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        AtmosphericPressure one = AtmosphericPressure.forValue(5);
        AtmosphericPressure two = AtmosphericPressure.forValue(88);

        Assert.assertFalse(one.equals(two));
        Assert.assertFalse(two.equals(one));
        Assert.assertFalse(one.equals(new Object()));
        Assert.assertNotEquals(one.hashCode(), two.hashCode());

        one = AtmosphericPressure.forValue(44);
        one.setSeaLevelValue(44);
        two = AtmosphericPressure.forValue(44);
        two.setGroundLevelValue(22);

        Assert.assertFalse(one.equals(two));
        Assert.assertFalse(two.equals(one));

        two.setSeaLevelValue(44);

        Assert.assertFalse(one.equals(two));
        Assert.assertFalse(two.equals(one));
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.forValue(14);
        atmosphericPressure.setValue(0);
        atmosphericPressure.setValue(15);
        atmosphericPressure.setValue(100);

        atmosphericPressure.setGroundLevelValue(222);
        Assert.assertEquals(222, atmosphericPressure.getGroundLevelValue(), 0.00001);

        atmosphericPressure.setSeaLevelValue(4232);
        Assert.assertEquals(4232, atmosphericPressure.getSeaLevelValue(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String pressureString = AtmosphericPressure.forValue(44).toString();
        Assert.assertNotNull(pressureString);
        Assert.assertNotEquals("", pressureString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureByConstructorWithInvalidDataNegative_thenThrowAnException() {
        AtmosphericPressure.forValue(-33);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureAndSetInvalidDataNegative_thenThrowAnException() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.forValue(88);
        atmosphericPressure.setValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidSeaLevelPressure_thenThrowAnException() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.forValue(88);
        atmosphericPressure.setSeaLevelValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidGroundLevelPressure_thenThrowAnException() {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.forValue(88);
        atmosphericPressure.setGroundLevelValue(-223);
    }
}

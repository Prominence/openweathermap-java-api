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

import org.junit.Assert;
import org.junit.Test;

public class HumidityUnitTest {
    @Test
    public void whenCreateHumidityWithArgs_thenValueIsSet() {
        Humidity humidity = Humidity.withValue((byte) 100);
        Assert.assertEquals(100, humidity.getValue());

        Assert.assertEquals(0, Humidity.withValue((byte) 0).getValue());
        Assert.assertEquals(55, Humidity.withValue((byte) 55).getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityByConstructorWithInvalidDataAboveHundred_thenThrowAnException() {
        Humidity.withValue((byte) 112);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityByConstructorWithInvalidDataNegative_thenThrowAnException() {
        Humidity.withValue((byte) -33);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Humidity humidity = Humidity.withValue((byte) 14);
        Assert.assertEquals(14, humidity.getValue());
        humidity.setValue((byte) 0);
        Assert.assertEquals(0, humidity.getValue());
        humidity.setValue((byte) 15);
        Assert.assertEquals(15, humidity.getValue());
        humidity.setValue((byte) 100);
        Assert.assertEquals(100, humidity.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityAndSetInvalidDataAboveHundred_thenThrowAnException() {
        Humidity humidity = Humidity.withValue((byte) 12);
        humidity.setValue((byte) 112);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityAndSetInvalidDataNegative_thenThrowAnException() {
        Humidity humidity = Humidity.withValue((byte) 88);
        humidity.setValue((byte) -89);
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Humidity one = Humidity.withValue((byte) 22);
        Humidity two = Humidity.withValue((byte) 22);

        Assert.assertTrue(one.equals(two));
        Assert.assertTrue(one.equals(one));
        Assert.assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Humidity one = Humidity.withValue((byte) 5);
        Humidity two = Humidity.withValue((byte) 88);

        Assert.assertFalse(one.equals(two));
        Assert.assertFalse(two.equals(one));
        Assert.assertFalse(one.equals(new Object()));
        Assert.assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String humidityString = Humidity.withValue((byte) 44).toString();
        Assert.assertNotNull(humidityString);
        Assert.assertNotEquals("", humidityString);
    }
}

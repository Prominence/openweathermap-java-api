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

public class CloudsUnitTest {
    @Test
    public void whenCreateCloudsWithValidArgs_thenValueIsSet() {
        Clouds clouds = Clouds.forValue((byte) 100);
        Assert.assertEquals(100, clouds.getValue());

        Assert.assertEquals(0, Clouds.forValue((byte) 0).getValue());
        Assert.assertEquals(100, Clouds.forValue((byte) 100).getValue());
        Assert.assertEquals(55, Clouds.forValue((byte) 55).getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsByConstructorWithInvalidDataAboveHundred_thenThrowAnException() {
        Clouds.forValue((byte) 110);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsByConstructorWithInvalidDataNegative_thenThrowAnException() {
        Clouds.forValue((byte) -33);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Clouds clouds = Clouds.forValue((byte) 14);
        clouds.setValue((byte) 0);
        Assert.assertEquals(0, clouds.getValue());
        clouds.setValue((byte) 15);
        Assert.assertEquals(15, clouds.getValue());
        clouds.setValue((byte) 100);
        Assert.assertEquals(100, clouds.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsAndSetInvalidDataAboveHundred_thenThrowAnException() {
        Clouds clouds = Clouds.forValue((byte) 12);
        clouds.setValue((byte) 112);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsAndSetInvalidDataNegative_thenThrowAnException() {
        Clouds clouds = Clouds.forValue((byte) 88);
        clouds.setValue((byte) -89);
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Clouds one = Clouds.forValue((byte) 22);
        Clouds two = Clouds.forValue((byte) 22);

        Assert.assertTrue(one.equals(two));
        Assert.assertTrue(one.equals(one));
        Assert.assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Clouds one = Clouds.forValue((byte) 5);
        Clouds two = Clouds.forValue((byte) 88);

        Assert.assertFalse(one.equals(two));
        Assert.assertFalse(two.equals(one));
        Assert.assertFalse(one.equals(new Object()));
        Assert.assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String cloudsString = Clouds.forValue((byte) 44).toString();
        Assert.assertNotNull(cloudsString);
        Assert.assertNotEquals("", cloudsString);
    }
}

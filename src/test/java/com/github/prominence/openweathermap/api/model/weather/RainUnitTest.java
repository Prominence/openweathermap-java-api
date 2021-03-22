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

import org.junit.Assert;
import org.junit.Test;

public class RainUnitTest {

    @Test
    public void whenCreateRainWithValidArgs_thenObjectIsCreated() {
        new Rain(2222.3, 324234.3);
        new Rain(null, -213123.4);
        new Rain(-123123.123, null);
        new Rain(null, null);
    }

    @Test
    public void whenSetValues_thenTheyAreSet() {
        final Rain rain = new Rain(null, null);

        Assert.assertNull(rain.getOneHourRainLevel());
        Assert.assertNull(rain.getThreeHourRainLevel());

        rain.setOneHourRainLevel(33.3);
        Assert.assertEquals(33.3, rain.getOneHourRainLevel(), 0.00001);

        rain.setThreeHourRainLevel(55.5);
        Assert.assertEquals(55.5, rain.getThreeHourRainLevel(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Rain rain = new Rain();

        Assert.assertNotNull(rain.toString());
        Assert.assertEquals("unknown", rain.toString());

        rain.setThreeHourRainLevel(33.5);

        Assert.assertNotNull(rain.toString());
        Assert.assertEquals("unknown", rain.toString());

        rain.setOneHourRainLevel(22.2);

        Assert.assertNotNull(rain.toString());
        Assert.assertEquals("unknown", rain.toString());

        rain.setThreeHourRainLevel(null);

        Assert.assertNotNull(rain.toString());
        Assert.assertEquals("unknown", rain.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Rain first = new Rain();
        final Rain second = new Rain();

        Assert.assertEquals(first.hashCode(), second.hashCode());

        second.setThreeHourRainLevel(11.0);

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        first.setThreeHourRainLevel(11.0);

        Assert.assertEquals(first.hashCode(), second.hashCode());

        first.setOneHourRainLevel(333.2);

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        second.setOneHourRainLevel(333.2);

        Assert.assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Rain first = new Rain();
        final Rain second = new Rain();

        Assert.assertTrue(first.equals(second));
        Assert.assertTrue(first.equals(first));
        Assert.assertFalse(first.equals(new Object()));

        first.setOneHourRainLevel(0.34);

        Assert.assertFalse(first.equals(second));

        second.setOneHourRainLevel(0.34);

        Assert.assertTrue(first.equals(second));

        second.setThreeHourRainLevel(66.7);

        Assert.assertFalse(first.equals(second));

        first.setThreeHourRainLevel(66.7);

        Assert.assertTrue(first.equals(second));
    }
}

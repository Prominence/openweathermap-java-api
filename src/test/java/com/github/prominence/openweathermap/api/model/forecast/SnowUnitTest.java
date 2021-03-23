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

import org.junit.Assert;
import org.junit.Test;

public class SnowUnitTest {
    @Test
    public void whenCreateSnowWithValidArgs_ObjectIsCreated() {
        new Snow(2222.3);
        new Snow(null);
    }

    @Test
    public void whenSetValues_thenTheyAreSet() {
        final Snow snow = new Snow(null);

        Assert.assertNull(snow.getThreeHourSnowLevel());

        snow.setThreeHourSnowLevel(55.5);
        Assert.assertEquals(55.5, snow.getThreeHourSnowLevel(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Snow snow = new Snow();

        Assert.assertNotNull(snow.toString());
        Assert.assertEquals("unknown", snow.toString());

        snow.setThreeHourSnowLevel(33.5);

        Assert.assertNotNull(snow.toString());
        Assert.assertNotEquals("unknown", snow.toString());

        snow.setThreeHourSnowLevel(null);

        Assert.assertNotNull(snow.toString());
        Assert.assertEquals("unknown", snow.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Snow first = new Snow();
        final Snow second = new Snow();

        Assert.assertEquals(first.hashCode(), second.hashCode());

        second.setThreeHourSnowLevel(11.0);

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        first.setThreeHourSnowLevel(11.0);

        Assert.assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Snow first = new Snow();
        final Snow second = new Snow();

        Assert.assertTrue(first.equals(second));
        Assert.assertTrue(first.equals(first));
        Assert.assertFalse(first.equals(new Object()));

        second.setThreeHourSnowLevel(66.7);

        Assert.assertFalse(first.equals(second));

        first.setThreeHourSnowLevel(66.7);

        Assert.assertTrue(first.equals(second));
    }
}

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

import com.github.prominence.openweathermap.api.model.Clouds;
import org.junit.Test;

public class CloudsUnitTest {

    @Test
    public void whenCreateCloudsWithValidArgs_thenValueIsSet() {
        Clouds clouds = new Clouds((byte) 100);
        assert clouds.getValue() == 100;

        assert new Clouds((byte) 0).getValue() == 0;
        assert new Clouds((byte) 100).getValue() == 100;
        assert new Clouds((byte) 55).getValue() == 55;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsByConstructorWithInvalidDataAboveHundred_thenThrowAnException() {
        new Clouds((byte) 110);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsByConstructorWithInvalidDataNegative_thenThrowAnException() {
        new Clouds((byte) -33);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Clouds clouds = new Clouds((byte) 14);
        clouds.setValue((byte) 0);
        clouds.setValue((byte) 15);
        clouds.setValue((byte) 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsAndSetInvalidDataAboveHundred_thenThrowAnException() {
        Clouds clouds = new Clouds((byte) 12);
        clouds.setValue((byte) 112);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsAndSetInvalidDataNegative_thenThrowAnException() {
        Clouds clouds = new Clouds((byte) 88);
        clouds.setValue((byte) -89);
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Clouds one = new Clouds((byte) 22);
        Clouds two = new Clouds((byte) 22);

        assert one.equals(two);
        assert one.equals(one);
        assert one.hashCode() == two.hashCode();
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Clouds one = new Clouds((byte) 5);
        Clouds two = new Clouds((byte) 88);

        assert !one.equals(two);
        assert !two.equals(one);
        assert !one.equals(new Object());
        assert one.hashCode() != two.hashCode();
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String cloudsString = new Clouds((byte) 44).toString();
        assert cloudsString != null;
        assert !"".equals(cloudsString);
    }
}

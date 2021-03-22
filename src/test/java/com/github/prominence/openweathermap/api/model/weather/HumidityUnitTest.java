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

import com.github.prominence.openweathermap.api.model.Humidity;
import org.junit.Test;

public class HumidityUnitTest {

    @Test
    public void whenCreateHumidityWithArgs_thenValueIsSet() {
        Humidity humidity = new Humidity((byte) 100);
        assert humidity.getValue() == 100;

        assert new Humidity((byte) 0).getValue() == 0;
        assert new Humidity((byte) 100).getValue() == 100;
        assert new Humidity((byte) 55).getValue() == 55;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityByConstructorWithInvalidDataAboveHundred_thenThrowAnException() {
        new Humidity((byte) 112);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityByConstructorWithInvalidDataNegative_thenThrowAnException() {
        new Humidity((byte) -33);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Humidity humidity = new Humidity((byte) 14);
        humidity.setValue((byte) 0);
        humidity.setValue((byte) 15);
        humidity.setValue((byte) 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityAndSetInvalidDataAboveHundred_thenThrowAnException() {
        Humidity humidity = new Humidity((byte) 12);
        humidity.setValue((byte) 112);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateHumidityAndSetInvalidDataNegative_thenThrowAnException() {
        Humidity humidity = new Humidity((byte) 88);
        humidity.setValue((byte) -89);
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Humidity one = new Humidity((byte) 22);
        Humidity two = new Humidity((byte) 22);

        assert one.equals(two);
        assert one.equals(one);
        assert one.hashCode() == two.hashCode();
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Humidity one = new Humidity((byte) 5);
        Humidity two = new Humidity((byte) 88);

        assert !one.equals(two);
        assert !two.equals(one);
        assert !one.equals(new Object());
        assert one.hashCode() != two.hashCode();
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String humidityString = new Humidity((byte) 44).toString();
        assert humidityString != null;
        assert !"".equals(humidityString);
    }
}

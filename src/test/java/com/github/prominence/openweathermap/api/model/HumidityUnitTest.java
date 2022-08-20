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

package com.github.prominence.openweathermap.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HumidityUnitTest {
    @Test
    public void whenCreateHumidityWithArgs_thenValueIsSet() {
        Humidity humidity = Humidity.withValue((byte) 100);
        assertEquals(100, humidity.getValue());

        assertEquals(0, Humidity.withValue((byte) 0).getValue());
        assertEquals(55, Humidity.withValue((byte) 55).getValue());
    }

    @Test
    public void whenCreateHumidityByConstructorWithInvalidDataAboveHundred_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Humidity.withValue((byte) 112));
    }

    @Test
    public void whenCreateHumidityByConstructorWithInvalidDataNegative_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> Humidity.withValue((byte) -33));
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Humidity humidity = Humidity.withValue((byte) 14);
        assertEquals(14, humidity.getValue());
        humidity.setValue((byte) 0);
        assertEquals(0, humidity.getValue());
        humidity.setValue((byte) 15);
        assertEquals(15, humidity.getValue());
        humidity.setValue((byte) 100);
        assertEquals(100, humidity.getValue());
    }

    @Test
    public void whenCreateHumidityAndSetInvalidDataAboveHundred_thenThrowAnException() {
        Humidity humidity = Humidity.withValue((byte) 12);
        assertThrows(IllegalArgumentException.class, () -> humidity.setValue((byte) 112));
    }

    @Test
    public void whenCreateHumidityAndSetInvalidDataNegative_thenThrowAnException() {
        Humidity humidity = Humidity.withValue((byte) 88);
        assertThrows(IllegalArgumentException.class, () -> humidity.setValue((byte) -89));
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Humidity one = Humidity.withValue((byte) 22);
        Humidity two = Humidity.withValue((byte) 22);

        assertEquals(one, two);
        assertEquals(one, one);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Humidity one = Humidity.withValue((byte) 5);
        Humidity two = Humidity.withValue((byte) 88);

        assertNotEquals(one, two);
        assertNotEquals(two, one);
        assertNotEquals(one, new Object());
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String humidityString = Humidity.withValue((byte) 44).toString();
        assertNotNull(humidityString);
        assertNotEquals("", humidityString);
    }
}

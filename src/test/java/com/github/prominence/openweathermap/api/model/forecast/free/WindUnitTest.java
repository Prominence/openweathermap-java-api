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

package com.github.prominence.openweathermap.api.model.forecast.free;

import com.github.prominence.openweathermap.api.model.WindModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WindUnitTest {
    @Test
    public void whenCreateWindWithValidArgs_thenValueIsSet() {
        assertEquals(44.0, WindModel.withValue(44, "ms").getSpeed(), 0.00001);
    }

    @Test
    public void whenCreateWindWithInvalidSpeedArg_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> WindModel.withValue(-21, "a"));
    }

    @Test
    public void whenCreateWindWithInvalidUnitArg_thenThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> WindModel.withValue(342, null));
    }

    @Test
    public void whenSetValidSpeed_thenValueIsSet() {
        final WindModel wind = WindModel.withValue(33, "as");

        assertEquals(33, wind.getSpeed(), 0.00001);

        wind.setSpeed(0);

        assertEquals(0, wind.getSpeed(), 0.00001);

        wind.setSpeed(3656);

        assertEquals(3656, wind.getSpeed(), 0.00001);
    }

    @Test
    public void whenSetInvalidSpeedBelow0_thenThrowAnException() {
        final WindModel wind = WindModel.withValue(33, "as");

        assertEquals(33, wind.getSpeed(), 0.00001);

        assertThrows(IllegalArgumentException.class, () -> wind.setSpeed(-22));
    }

    @Test
    public void whenSetValidDegrees_thenValueIsSet() {
        final WindModel wind = WindModel.withValue(33, "as");

        assertNull(wind.getDegrees());

        wind.setDegrees(22);

        assertEquals(22, wind.getDegrees(), 0.00001);

        wind.setDegrees(0);

        assertEquals(0, wind.getDegrees(), 0.00001);

        wind.setDegrees(360);

        assertEquals(360, wind.getDegrees(), 0.00001);
    }

    @Test
    public void whenSetInvalidDegreesBelow0_thenThrowAnException() {
        final WindModel wind = WindModel.withValue(33, "as");
        assertThrows(IllegalArgumentException.class, () -> wind.setDegrees(-32));
    }

    @Test
    public void whenSetInvalidDegreesAbove360_thenThrowAnException() {
        final WindModel wind = WindModel.withValue(33, "as");
        assertThrows(IllegalArgumentException.class, () -> wind.setDegrees(378));
    }

    @Test
    public void whenSetNonNullUnit_thenValueIsSet() {
        final WindModel wind = WindModel.withValue(33, "as");

        assertEquals(wind.getUnit(), "as");

        wind.setUnit("myUnit");

        assertEquals(wind.getUnit(), "myUnit");
    }

    @Test
    public void whenSetNullUnit_thenThrowAnException() {
        final WindModel wind = WindModel.withValue(33, "as");

        assertThrows(IllegalArgumentException.class, () -> wind.setUnit(null));
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final WindModel wind = WindModel.withValue(302, "a");

        assertNotNull(wind.toString());

        wind.setDegrees(22);

        assertNotNull(wind.toString());
        assertNotEquals("", wind.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final WindModel first = WindModel.withValue(22, "a");
        final WindModel second = WindModel.withValue(22, "b");

        assertNotEquals(first.hashCode(), second.hashCode());

        second.setUnit("a");

        assertEquals(first.hashCode(), second.hashCode());

        second.setSpeed(33);

        assertNotEquals(first.hashCode(), second.hashCode());

        first.setSpeed(333);

        assertNotEquals(first.hashCode(), second.hashCode());

        first.setSpeed(33);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final WindModel first = WindModel.withValue(11, "a");
        final WindModel second = WindModel.withValue(12, "a");

        assertNotEquals(first, second);
        first.setSpeed(12);

        assertEquals(first, second);
        assertEquals(first, first);
        assertNotEquals(first, new Object());

        first.setDegrees(34);

        assertNotEquals(first, second);

        second.setDegrees(34);

        assertEquals(first, second);

        second.setUnit("v");

        assertNotEquals(first, second);

        first.setUnit("v");

        assertEquals(first, second);

        first.setSpeed(second.getSpeed() + 4);

        assertNotEquals(first, second);
    }
}

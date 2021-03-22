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

import com.github.prominence.openweathermap.api.model.Pressure;
import org.junit.Test;

public class PressureUnitTest {

    @Test
    public void whenCreatePressureWithArgs_thenValueIsSet() {
        Pressure pressure = new Pressure(100);
        assert pressure.getValue() == 100;

        assert new Pressure(0).getValue() == 0;
        assert new Pressure(100).getValue() == 100;
        assert new Pressure(55).getValue() == 55;
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Pressure one = new Pressure(22);
        Pressure two = new Pressure(22);

        assert one.equals(two);
        assert one.equals(one);
        assert one.hashCode() == two.hashCode();

        one.setSeaLevelValue(333);
        one.setGroundLevelValue(555);

        two.setSeaLevelValue(333);
        two.setGroundLevelValue(555);

        assert one.equals(two);
        assert two.equals(one);
        assert one.hashCode() == two.hashCode();
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Pressure one = new Pressure(5);
        Pressure two = new Pressure(88);

        assert !one.equals(two);
        assert !two.equals(one);
        assert !one.equals(new Object());
        assert one.hashCode() != two.hashCode();

        one = new Pressure(44);
        one.setSeaLevelValue(44);
        two = new Pressure(44);
        two.setGroundLevelValue(22);

        assert !one.equals(two);
        assert !two.equals(one);

        two.setSeaLevelValue(44);

        assert !one.equals(two);
        assert !two.equals(one);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Pressure pressure = new Pressure(14);
        pressure.setValue(0);
        pressure.setValue(15);
        pressure.setValue(100);

        pressure.setGroundLevelValue(222);
        assert pressure.getGroundLevelValue() == 222;

        pressure.setSeaLevelValue(4232);
        assert pressure.getSeaLevelValue() == 4232;
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String pressureString = new Pressure(44).toString();
        assert pressureString != null;
        assert !"".equals(pressureString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureByConstructorWithInvalidDataNegative_thenThrowAnException() {
        new Pressure(-33);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureAndSetInvalidDataNegative_thenThrowAnException() {
        Pressure pressure = new Pressure(88);
        pressure.setValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidSeaLevelPressure_thenThrowAnException() {
        Pressure pressure = new Pressure(88);
        pressure.setSeaLevelValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidGroundLevelPressure_thenThrowAnException() {
        Pressure pressure = new Pressure(88);
        pressure.setGroundLevelValue(-223);
    }
}

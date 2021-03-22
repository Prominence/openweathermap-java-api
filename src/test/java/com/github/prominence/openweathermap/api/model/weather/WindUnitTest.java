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

import com.github.prominence.openweathermap.api.model.forecast.Wind;
import org.junit.Test;

public class WindUnitTest {
    @Test
    public void whenCreateWindWithValidArgs_thenValueIsSet() {
        assert new Wind(44, "ms").getSpeed() == 44.0;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateWindWithInvalidSpeedArg_thenThrowAnException() {
        new Wind(-21, "a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateWindWithInvalidUnitArg_thenThrowAnException() {
        new Wind(342, null);
    }

    @Test
    public void whenSetValidSpeed_thenValueIsSet() {
        final Wind wind = new Wind(33, "as");

        assert wind.getSpeed() == 33;

        wind.setSpeed(0);

        assert wind.getSpeed() == 0;

        wind.setSpeed(3656);

        assert wind.getSpeed() == 3656;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidSpeedBelow0_thenThrowAnException() {
        final Wind wind = new Wind(33, "as");

        assert wind.getSpeed() == 33;

        wind.setSpeed(-22);

        assert false;
    }

    @Test
    public void whenSetValidDegrees_thenValueIsSet() {
        final Wind wind = new Wind(33, "as");

        assert wind.getDegrees() == null;

        wind.setDegrees(22);

        assert wind.getDegrees() == 22;

        wind.setDegrees(0);

        assert wind.getDegrees() == 0;

        wind.setDegrees(360);

        assert wind.getDegrees() == 360;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidDegreesBelow0_thenThrowAnException() {
        final Wind wind = new Wind(33, "as");
        wind.setDegrees(-32);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidDegreesAbove360_thenThrowAnException() {
        final Wind wind = new Wind(33, "as");
        wind.setDegrees(378);
    }

    @Test
    public void whenSetNonNullUnit_thenValueIsSet() {
        final Wind wind = new Wind(33, "as");

        assert "as".equals(wind.getUnit());

        wind.setUnit("myUnit");

        assert "myUnit".equals(wind.getUnit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetNullUnit_thenThrowAnException() {
        final Wind wind = new Wind(33, "as");

        wind.setUnit(null);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Wind wind = new Wind(302, "a");

        assert wind.toString() != null;

        wind.setDegrees(22);

        assert wind.toString() != null && !"".equals(wind.toString());
    }

    @Test
    public void RainwhenCallHashCode_thenAllIsFine() {
        final Wind first = new Wind(22, "a");
        final Wind second = new Wind(22, "b");

        assert first.hashCode() != second.hashCode();

        second.setUnit("a");

        assert first.hashCode() == second.hashCode();

        second.setSpeed(33);

        assert first.hashCode() != second.hashCode();

        first.setSpeed(333);

        assert first.hashCode() != second.hashCode();

        first.setSpeed(33);

        assert first.hashCode() == second.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Wind first = new Wind(11, "a");
        final Wind second = new Wind(11, "a");

        assert first.equals(second);
        assert first.equals(first);
        assert !first.equals(new Object());

        first.setDegrees(34);

        assert !first.equals(second);

        second.setDegrees(34);

        assert first.equals(second);

        second.setUnit("v");

        assert !first.equals(second);

        first.setUnit("v");
        first.setSpeed(second.getSpeed() + 4);

        assert !first.equals(second);
    }
}

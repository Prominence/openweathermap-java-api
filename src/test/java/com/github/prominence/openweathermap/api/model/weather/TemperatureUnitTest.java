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

import org.junit.Test;

public class TemperatureUnitTest {

    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        new Temperature(22.2, "K");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithEmptyUnit_thenThrowAnException() {
        new Temperature(22.2, null);
    }

    @Test
    public void whenSetValue_thenAllIsFine() {
        final Temperature temperature = new Temperature(22.2, "K");
        temperature.setValue(55.44);

        assert temperature.getValue() == 55.44;
    }

    @Test
    public void whenSetMaximumTemperature_thenAllIsOk() {
        final Temperature temperature = new Temperature(22.2, "K");
        temperature.setMaxTemperature(44.4);

        assert temperature.getMaxTemperature() == 44.4;

        temperature.setMaxTemperature(null);

        assert temperature.getMaxTemperature() == null;
    }

    @Test
    public void whenSetMinimumTemperature_thenAllIsOk() {
        final Temperature temperature = new Temperature(22.2, "K");
        temperature.setMinTemperature(33.2);

        assert temperature.getMinTemperature() == 33.2;

        temperature.setMinTemperature(null);

        assert temperature.getMinTemperature() == null;
    }

    @Test
    public void whenSetNonNullUnit_thenAllIsOk() {
        final Temperature temperature = new Temperature(22.2, "K");
        temperature.setUnit("test");

        assert "test".equals(temperature.getUnit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetNullUnit_thenThrowAnException() {
        final Temperature temperature = new Temperature(22.2, "K");

        temperature.setUnit(null);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Temperature temperature = new Temperature(22.2, "K");

        assert !"".equals(temperature.toString());

        temperature.setMinTemperature(11.2);

        assert !"".equals(temperature.toString());

        temperature.setMaxTemperature(44.3);

        assert !"".equals(temperature.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Temperature one = new Temperature(22.2, "K");
        final Temperature two = new Temperature(22.2, "K");

        assert one.hashCode() == two.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Temperature one = new Temperature(22.2, "K");
        final Temperature two = new Temperature(21.2, "K");

        assert one.equals(one);
        assert !one.equals(new Object());

        assert !one.equals(two);

        one.setValue(21.2);

        assert one.equals(two);

        one.setMaxTemperature(33.56);

        assert !one.equals(two);

        two.setMaxTemperature(33.56);

        assert one.equals(two);

        one.setMinTemperature(11.54);

        assert !one.equals(two);

        two.setMinTemperature(11.54);

        assert one.equals(two);

        two.setUnit("U");

        assert !one.equals(two);
    }
}

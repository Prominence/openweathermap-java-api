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

package com.github.prominence.openweathermap.api.model.onecall.current;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DailyTemperatureUnitTest {

    @Test
    public void getMorning() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setMorning(value);

        assertEquals(value, temperature.getMorning(), 0.00001);
    }

    @Test
    public void getMorningFeelsLike() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setMorningFeelsLike(value);

        assertEquals(value, temperature.getMorningFeelsLike(), 0.00001);
    }

    @Test
    public void getDay() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setDay(value);

        assertEquals(value, temperature.getDay(), 0.00001);
    }

    @Test
    public void getDayFeelsLike() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setDayFeelsLike(value);

        assertEquals(value, temperature.getDayFeelsLike(), 0.00001);
    }

    @Test
    public void getEve() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setEve(value);

        assertEquals(value, temperature.getEve(), 0.00001);
    }

    @Test
    public void getEveFeelsLike() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setEveFeelsLike(value);

        assertEquals(value, temperature.getEveFeelsLike(), 0.00001);
    }

    @Test
    public void getNight() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setNight(value);

        assertEquals(value, temperature.getNight(), 0.00001);
    }

    @Test
    public void getNightFeelsLike() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setNightFeelsLike(value);

        assertEquals(value, temperature.getNightFeelsLike(), 0.00001);
    }

    @Test
    public void getMin() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setMin(value);

        assertEquals(value, temperature.getMin(), 0.00001);
    }

    @Test
    public void getMax() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setMax(value);

        assertEquals(value, temperature.getMax(), 0.00001);
    }

    @Test
    public void getDewPoint() {
        final DailyTemperature temperature = new DailyTemperature();
        final double value = 20.0;
        temperature.setDewPoint(value);

        assertEquals(value, temperature.getDewPoint(), 0.00001);
    }

    @Test
    public void getUnit() {
        final DailyTemperature temperature = new DailyTemperature();
        temperature.setUnit("T");

        assertEquals("T", temperature.getUnit());
    }

    @Test
    public void getEquals() {
        final DailyTemperature first = new DailyTemperature();
        final DailyTemperature second = new DailyTemperature();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        assertEquals(first, second);

        first.setMorning(1.0);

        assertNotEquals(first, second);

        second.setMorning(1.0);

        assertEquals(first, second);

        first.setMorningFeelsLike(2.0);

        assertNotEquals(first, second);

        second.setMorningFeelsLike(2.0);

        assertEquals(first, second);

        first.setDay(3.0);

        assertNotEquals(first, second);

        second.setDay(3.0);

        assertEquals(first, second);

        first.setDayFeelsLike(4.0);

        assertNotEquals(first, second);

        second.setDayFeelsLike(4.0);

        assertEquals(first, second);

        first.setEve(5.0);

        assertNotEquals(first, second);

        second.setEve(5.0);

        assertEquals(first, second);

        first.setEveFeelsLike(6.0);

        assertNotEquals(first, second);

        second.setEveFeelsLike(6.0);

        assertEquals(first, second);

        first.setNight(7.0);

        assertNotEquals(first, second);

        second.setNight(7.0);

        assertEquals(first, second);

        first.setNightFeelsLike(8.0);

        assertNotEquals(first, second);

        second.setNightFeelsLike(8.0);

        assertEquals(first, second);

        first.setDewPoint(9.0);

        assertNotEquals(first, second);

        second.setDewPoint(9.0);

        assertEquals(first, second);

        first.setMin(10.0);

        assertNotEquals(first, second);

        second.setMin(10.0);

        assertEquals(first, second);

        first.setMax(11.0);

        assertNotEquals(first, second);

        second.setMax(11.0);

        assertEquals(first, second);

        first.setUnit("K");

        assertNotEquals(first, second);

        second.setUnit("K");

        assertEquals(first, second);
    }

    @Test
    public void getHashCode() {
        final DailyTemperature first = new DailyTemperature();
        final DailyTemperature second = new DailyTemperature();

        assertEquals(first.hashCode(), second.hashCode());

        first.setUnit("K");

        assertNotEquals(first.hashCode(), second.hashCode());
    }
}
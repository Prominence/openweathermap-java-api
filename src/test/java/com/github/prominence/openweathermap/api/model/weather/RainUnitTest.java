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

import com.github.prominence.openweathermap.api.model.weather.Rain;
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
        final Rain snow = new Rain(null, null);

        assert snow.getOneHourRainLevel() == null;
        assert snow.getThreeHourRainLevel() == null;

        snow.setOneHourRainLevel(33.3);
        assert snow.getOneHourRainLevel() == 33.3;

        snow.setThreeHourRainLevel(55.5);
        assert snow.getThreeHourRainLevel() == 55.5;
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Rain snow = new Rain();

        assert snow.toString() != null;
        assert "unknown".equals(snow.toString());

        snow.setThreeHourRainLevel(33.5);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());

        snow.setOneHourRainLevel(22.2);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());

        snow.setThreeHourRainLevel(null);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Rain first = new Rain();
        final Rain second = new Rain();

        assert first.hashCode() == second.hashCode();

        second.setThreeHourRainLevel(11.0);

        assert first.hashCode() != second.hashCode();

        first.setThreeHourRainLevel(11.0);

        assert first.hashCode() == second.hashCode();

        first.setOneHourRainLevel(333.2);

        assert first.hashCode() != second.hashCode();

        second.setOneHourRainLevel(333.2);

        assert first.hashCode() == second.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Rain first = new Rain();
        final Rain second = new Rain();

        assert first.equals(second);
        assert first.equals(first);
        assert !first.equals(new Object());

        first.setOneHourRainLevel(0.34);

        assert !first.equals(second);

        second.setOneHourRainLevel(0.34);

        assert first.equals(second);

        second.setThreeHourRainLevel(66.7);

        assert !first.equals(second);

        first.setThreeHourRainLevel(66.7);

        assert first.equals(second);
    }
}

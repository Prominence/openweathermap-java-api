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

package com.github.prominence.openweathermap.api.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherConditionUnitTest {
    @Test
    public void getId() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotEquals(0, weatherCondition.getId());
    }

    @Test
    public void getName() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(weatherCondition.getName());
        assertNotEquals("", weatherCondition.getName());
    }

    @Test
    public void getDescription() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(weatherCondition.getDescription());
        assertNotEquals("", weatherCondition.getDescription());
    }

    @Test
    public void getDayIconId() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(weatherCondition.getDayIconId());
        assertNotEquals("", weatherCondition.getDayIconId());
    }

    @Test
    public void getNightIconId() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(weatherCondition.getNightIconId());
        assertNotEquals("", weatherCondition.getNightIconId());
    }

    @Test
    public void getDayIconUrl() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(weatherCondition.getDayIconUrl());
        assertNotEquals("", weatherCondition.getDayIconUrl());
    }

    @Test
    public void getNightIconUrl() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(weatherCondition.getNightIconUrl());
        assertNotEquals("", weatherCondition.getNightIconUrl());
    }

    @Test
    public void getIconUrl() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(WeatherCondition.getIconUrl(weatherCondition.getNightIconId()));
        assertNotEquals("", WeatherCondition.getIconUrl(weatherCondition.getNightIconId()));
    }

    @Test
    public void getById() {
        assertEquals(WeatherCondition.ASH, WeatherCondition.getById(WeatherCondition.ASH.getId()));
    }

    @Test
    public void testToString() {
        final WeatherCondition weatherCondition = WeatherCondition.ASH;

        assertNotNull(weatherCondition.toString());
        assertNotEquals("", weatherCondition.toString());
    }
}

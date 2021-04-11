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

package com.github.prominence.openweathermap.api.model;

import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherStateUnitTest {
    @Test
    public void getId() {
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        assertEquals(800, weatherState.getId());
    }

    @Test
    public void getName() {
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        assertEquals("Clear", weatherState.getName());
    }

    @Test
    public void getDescription() {
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        assertEquals("clear sky", weatherState.getDescription());
    }

    @Test
    public void getIconId() {
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");
        weatherState.setIconId("04d");

        assertEquals("04d", weatherState.getIconId());
    }

    @Test
    public void getWeatherConditionEnum() {
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        assertEquals(WeatherCondition.CLEAR, weatherState.getWeatherConditionEnum());
    }

    @Test
    public void getWeatherIconUrl() {
        WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        String weatherIconUrl = weatherState.getWeatherIconUrl();

        assertNotNull(weatherIconUrl);
        assertNotEquals("", weatherIconUrl);

        weatherState.setIconId("04n");
        weatherIconUrl = weatherState.getWeatherIconUrl();

        assertNotNull(weatherIconUrl);
        assertNotEquals("", weatherIconUrl);

        weatherState = new WeatherState(0, "Unknown", "unknown");
        weatherIconUrl = weatherState.getWeatherIconUrl();

        assertNull(weatherIconUrl);
    }

    @Test
    public void testEquals() {
        final WeatherState first = new WeatherState(800, "Clear", "clear sky");
        final WeatherState second = new WeatherState(800, "Clear", "clear sky");

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        assertEquals(first, second);

        assertNotEquals(new WeatherState(800, "Clear", "clear sky"), new WeatherState(801, "Clear", "clear sky"));
        assertNotEquals(new WeatherState(800, "Clear", "clear sky"), new WeatherState(800, "Clear1", "clear sky"));
        assertNotEquals(new WeatherState(800, "Clear", "clear sky"), new WeatherState(800, "Clear", "clear sky1"));

        first.setIconId("50d");

        assertNotEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final WeatherState first = new WeatherState(800, "Clear", "clear sky");
        final WeatherState second = new WeatherState(800, "Clear", "clear sky");
        final WeatherState third = new WeatherState(0, "Unknown", "unknown");

        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first.hashCode(), third.hashCode());
        assertNotEquals(second.hashCode(), third.hashCode());
    }

    @Test
    public void testToString() {
        final WeatherState weatherState = new WeatherState(800, "Clear", "clear sky");

        assertNotNull(weatherState.toString());
        assertNotEquals("", weatherState.toString());
    }
}
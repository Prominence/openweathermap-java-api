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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherConditionUnitTest {

    private static final int EXPECTED_ID = 762;
    private static final String EXPECTED_NAME = "Ash";
    private static final String EXPECTED_DESCRIPTION = "volcanic ash";
    private static final String EXPECTED_ICON = "50";

    @Test
    public void testGetId_ShouldReturnPredefinedValue_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final int actual = underTest.getId();

        //then
        assertEquals(EXPECTED_ID, actual);
    }

    @Test
    public void testGetName_ShouldReturnPredefinedValue_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final String actual = underTest.getName();

        //then
        assertEquals(EXPECTED_NAME, actual);
    }

    @Test
    public void testGetDescription_ShouldReturnPredefinedValue_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final String actual = underTest.getDescription();

        //then
        assertEquals(EXPECTED_DESCRIPTION, actual);
    }

    @Test
    public void testGetDayIconId_ShouldReturnDayVariantOfPredefinedIcon_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final String actual = underTest.getDayIconId();

        //then
        assertEquals(EXPECTED_ICON + "d", actual);
    }

    @Test
    public void testGetNightIconId_ShouldReturnNightVariantOfPredefinedIcon_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final String actual = underTest.getNightIconId();

        //then
        assertEquals(EXPECTED_ICON + "n", actual);
    }

    @Test
    public void testGetDayIconUrl_ShouldReturnUrlEndingWithDayVariantOfPredefinedIcon_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final String actual = underTest.getDayIconUrl();

        //then
        assertTrue(actual.endsWith(EXPECTED_ICON + "d.png"));
    }

    @Test
    public void testGetNightIconUrl_ShouldReturnUrlEndingWithNightVariantOfPredefinedIcon_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final String actual = underTest.getNightIconUrl();

        //then
        assertTrue(actual.endsWith(EXPECTED_ICON + "n.png"));
    }

    @Test
    public void testGetById_ShouldReturnMatchingEnum_WhenCalledWithKnownId() {
        //given

        //when
        final WeatherCondition actual = WeatherCondition.getById(EXPECTED_ID);

        //then
        assertEquals(WeatherCondition.ASH, actual);
    }

    @Test
    public void testGetById_ShouldReturnNull_WhenCalledWithUnknownId() {
        //given

        //when
        final WeatherCondition actual = WeatherCondition.getById(-EXPECTED_ID);

        //then
        assertNull(actual);
    }

    @Test
    public void testGetById_ShouldReturnStringContainingIdNameDescription_WhenCalled() {
        //given
        final WeatherCondition underTest = WeatherCondition.ASH;

        //when
        final String actual = underTest.toString();

        //then
        assertTrue(actual.contains(String.valueOf(EXPECTED_ID)));
        assertTrue(actual.contains(EXPECTED_NAME));
        assertTrue(actual.contains(EXPECTED_DESCRIPTION));
    }
}

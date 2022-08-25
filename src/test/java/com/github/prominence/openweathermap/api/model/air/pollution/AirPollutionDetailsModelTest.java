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

package com.github.prominence.openweathermap.api.model.air.pollution;

import com.github.prominence.openweathermap.api.model.Coordinates;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class AirPollutionDetailsModelTest {

    @Test
    void testGetAirPollutionConcentration_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionDetailsModel underTest = new AirPollutionDetailsModel();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        components.setCoarseParticulateMatter(new Concentration(new BigDecimal("0.54")));
        final AirPollutionRecord expected = new AirPollutionRecord();
        expected.setComponents(components);
        underTest.setAirPollutionRecords(Collections.singletonList(expected));

        //when
        final List<AirPollutionConcentration> actual = underTest.getAirPollutionConcentration();

        //then
        assertIterableEquals(Collections.singletonList(expected), actual);
    }

    @Test
    void testGetCoordinates_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionDetailsModel underTest = new AirPollutionDetailsModel();
        final Coordinates expected = new Coordinates(12, 34);
        underTest.setCoordinates(expected);

        //when
        final Coordinates actual = underTest.getCoordinates();

        //then
        assertEquals(expected, actual);
    }
}

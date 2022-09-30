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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirQualityIndexTest {

    public static Stream<Arguments> valueProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(AirQualityIndex.GOOD.getValue(), AirQualityIndex.GOOD))
                .add(Arguments.of(AirQualityIndex.FAIR.getValue(), AirQualityIndex.FAIR))
                .add(Arguments.of(AirQualityIndex.MODERATE.getValue(), AirQualityIndex.MODERATE))
                .add(Arguments.of(AirQualityIndex.POOR.getValue(), AirQualityIndex.POOR))
                .add(Arguments.of(AirQualityIndex.VERY_POOR.getValue(), AirQualityIndex.VERY_POOR))
                .add(Arguments.of(6, null))
                .build();
    }

    @ParameterizedTest
    @MethodSource("valueProvider")
    void testGetByIndex_ShouldReturnNull_WhenValueIsNotFound(final int index, final AirQualityIndex expected) {
        //given

        //when
        final AirQualityIndex actual = AirQualityIndex.getByIndex(index);

        //then
        assertEquals(expected, actual);
    }
}

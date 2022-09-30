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

package com.github.prominence.openweathermap.api.model.generic.precipitation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrecipitationIntensityTest {

    public static Stream<Arguments> millimetersPerHourProvider() {
        return Stream.of(1d, 2.0, 3.1415, 42d)
                .map(BigDecimal::valueOf)
                .map(v -> Arguments.of(v, v.setScale(1, RoundingMode.HALF_EVEN)));
    }

    public static Stream<Arguments> millimetersPerMinuteProvider() {
        return Stream.of(1d, 2.0, 3.1415, 42d)
                .map(BigDecimal::valueOf)
                .map(v -> Arguments.of(v, v.divide(BigDecimal.valueOf(60), RoundingMode.HALF_EVEN).setScale(1, RoundingMode.HALF_EVEN)));
    }

    @ParameterizedTest
    @MethodSource("millimetersPerHourProvider")
    void testAsMillimetersPerHour_ShouldReturnRoundedValue_WhenCalled(final BigDecimal value, final BigDecimal expected) {
        //given
        final PrecipitationIntensity underTest = new PrecipitationIntensity(value);

        //when
        final BigDecimal actual = underTest.asMillimetersPerHour();

        //then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("millimetersPerMinuteProvider")
    void testAsMillimetersPerMinute_ShouldReturnRoundedAndConvertedValue_WhenCalled(final BigDecimal value, final BigDecimal expected) {
        //given
        final PrecipitationIntensity underTest = new PrecipitationIntensity(value);

        //when
        final BigDecimal actual = underTest.asMillimetersPerMinute();

        //then
        assertEquals(expected, actual);
    }
}

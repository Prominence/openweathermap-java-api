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

package com.github.prominence.openweathermap.api.model.generic.visibility;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisibilityTest {

    public static Stream<Arguments> visibilityInputProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO))
                .add(Arguments.of(BigDecimal.ONE, BigDecimal.valueOf(0.00), BigDecimal.valueOf(0.00)))
                .add(Arguments.of(BigDecimal.TEN, BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.01)))
                .add(Arguments.of(BigDecimal.valueOf(42.0), BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.03)))
                .add(Arguments.of(BigDecimal.valueOf(42000.0), BigDecimal.valueOf(42.00), BigDecimal.valueOf(26.10)))
                .build();
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("visibilityInputProvider")
    void testAsMeters_ShouldReturnRoundedValue_WhenCalled(
            final BigDecimal meters, final BigDecimal kilometers, final BigDecimal miles) {
        //given
        final Visibility underTest = new Visibility(meters);

        //when
        final BigDecimal actual = underTest.asMeters();

        //then
        assertEquals(normalize(meters), actual);
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("visibilityInputProvider")
    void testAsMiles_ShouldReturnRoundedValue_WhenCalled(
            final BigDecimal meters, final BigDecimal kilometers, final BigDecimal miles) {
        //given
        final Visibility underTest = new Visibility(meters);

        //when
        final BigDecimal actual = underTest.asMiles();

        //then
        assertEquals(normalize(miles), actual);
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("visibilityInputProvider")
    void testAsKilometers_ShouldReturnRoundedValue_WhenCalled(
            final BigDecimal meters, final BigDecimal kilometers, final BigDecimal miles) {
        //given
        final Visibility underTest = new Visibility(meters);

        //when
        final BigDecimal actual = underTest.asKilometers();

        //then
        assertEquals(normalize(kilometers), actual);
    }

    private static BigDecimal normalize(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}

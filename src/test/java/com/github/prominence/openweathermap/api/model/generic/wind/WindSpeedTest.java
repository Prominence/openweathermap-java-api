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

package com.github.prominence.openweathermap.api.model.generic.wind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

class WindSpeedTest {

    public static Stream<Arguments> speedInputProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO))
                .add(Arguments.of(BigDecimal.ONE, BigDecimal.valueOf(0.44704), BigDecimal.valueOf(1.60934)))
                .add(Arguments.of(BigDecimal.TEN, BigDecimal.valueOf(4.47040), BigDecimal.valueOf(16.0934)))
                .add(Arguments.of(BigDecimal.valueOf(42.0), BigDecimal.valueOf(18.77568), BigDecimal.valueOf(67.59228)))
                .build();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testConstructor_ShouldThrowException_WhenCalledWithNull() {
        //given

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> new WindSpeed(null));

        //then + exception
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("speedInputProvider")
    void testAsMetersPerSecond_ShouldReturnMeterSPerSecondValueRoundedTo2Decimals_WhenCalled(
            final BigDecimal milesPerHOur, final BigDecimal metersPerSecond, final BigDecimal kilometersPerHour) {
        //given
        final WindSpeed underTest = new WindSpeed(metersPerSecond);

        //when
        final BigDecimal actual = underTest.asMetersPerSecond();

        //then
        Assertions.assertEquals(normalize(metersPerSecond), actual);
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("speedInputProvider")
    void testAsMilesPerHour_ShouldReturnMeterSPerSecondValueConvertedAndRoundedTo2Decimals_WhenCalled(
            final BigDecimal milesPerHOur, final BigDecimal metersPerSecond, final BigDecimal kilometersPerHour) {
        //given
        final WindSpeed underTest = new WindSpeed(metersPerSecond);

        //when
        final BigDecimal actual = underTest.asMilesPerHour();

        //then
        Assertions.assertEquals(normalize(milesPerHOur), actual);
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("speedInputProvider")
    void testAsKilometersPerHour_ShouldReturnMeterSPerSecondValueConvertedAndRoundedTo2Decimals_WhenCalled(
            final BigDecimal milesPerHOur, final BigDecimal metersPerSecond, final BigDecimal kilometersPerHour) {
        //given
        final WindSpeed underTest = new WindSpeed(metersPerSecond);

        //when
        final BigDecimal actual = underTest.asKilometersPerHour();

        //then
        Assertions.assertEquals(normalize(kilometersPerHour), actual);
    }

    private static BigDecimal normalize(BigDecimal metersPerSecond) {
        return metersPerSecond.setScale(2, RoundingMode.HALF_EVEN);
    }
}

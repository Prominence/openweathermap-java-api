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

package com.github.prominence.openweathermap.api.model.generic.temperature;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureValueTest {


    public static Stream<Arguments> validValueProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(BigDecimal.ZERO, BigDecimal.valueOf(-459.67), BigDecimal.valueOf(-273.15)))
                .add(Arguments.of(BigDecimal.valueOf(273.15), BigDecimal.valueOf(32), BigDecimal.ZERO))
                .add(Arguments.of(BigDecimal.valueOf(313.15), BigDecimal.valueOf(104), BigDecimal.valueOf(40)))
                .build();
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("validValueProvider")
    void testAsKelvin_ShouldRoundDecimalPlaces_WhenCalled(
            final BigDecimal kelvin, final BigDecimal fahrenheit, final BigDecimal celsius) {
        //given
        final TemperatureValue underTest = new TemperatureValue(kelvin);

        //when
        final BigDecimal actual = underTest.asKelvin();

        //then
        assertEquals(normalize(kelvin), actual);
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("validValueProvider")
    void testAsCelsius_ShouldConvertAndRoundDecimalPlaces_WhenCalled(
            final BigDecimal kelvin, final BigDecimal fahrenheit, final BigDecimal celsius) {
        //given
        final TemperatureValue underTest = new TemperatureValue(kelvin);

        //when
        final BigDecimal actual = underTest.asCelsius();

        //then
        assertEquals(normalize(celsius), actual);
    }

    @SuppressWarnings("unused")
    @ParameterizedTest
    @MethodSource("validValueProvider")
    void testAsFahrenheit_ShouldConvertAndRoundDecimalPlaces_WhenCalled(
            final BigDecimal kelvin, final BigDecimal fahrenheit, final BigDecimal celsius) {
        //given
        final TemperatureValue underTest = new TemperatureValue(kelvin);

        //when
        final BigDecimal actual = underTest.asFahrenheit();

        //then
        assertEquals(normalize(fahrenheit), actual);
    }

    private static BigDecimal normalize(BigDecimal value) {
        return value.setScale(0, RoundingMode.HALF_EVEN);
    }
}

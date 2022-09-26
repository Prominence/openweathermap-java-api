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

class MoonTypeTest {

    public static Stream<Arguments> inputProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(0, MoonType.NEW_MOON))
                .add(Arguments.of(1, MoonType.NEW_MOON))
                .add(Arguments.of(0.25, MoonType.FIRST_QUARTER_MOON))
                .add(Arguments.of(0.5, MoonType.FULL_MOON))
                .add(Arguments.of(0.75, MoonType.LAST_QUARTER_MOON))
                .add(Arguments.of(0.01, MoonType.WAXING_CRESCENT))
                .add(Arguments.of(0.24, MoonType.WAXING_CRESCENT))
                .add(Arguments.of(0.26, MoonType.WAXING_GIBBOUS))
                .add(Arguments.of(0.49, MoonType.WAXING_GIBBOUS))
                .add(Arguments.of(0.51, MoonType.WANING_GIBBOUS))
                .add(Arguments.of(0.74, MoonType.WANING_GIBBOUS))
                .add(Arguments.of(0.76, MoonType.WANING_CRESCENT))
                .add(Arguments.of(0.99, MoonType.WANING_CRESCENT))
                .add(Arguments.of(1.01, MoonType.INVALID))
                .build();
    }

    @ParameterizedTest
    @MethodSource("inputProvider")
    void testValueOf_ShouldReturnTheAppropriateValue_WhenCalled(final double value, final MoonType expected) {
        //given

        //when
        final MoonType actual = MoonType.valueOf(value);

        //then
        assertEquals(expected, actual);
    }
}

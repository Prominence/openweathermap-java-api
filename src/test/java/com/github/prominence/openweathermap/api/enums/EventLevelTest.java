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

class EventLevelTest {

    public static Stream<Arguments> valueProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(EventLevel.UNKNOWN.getValue(), EventLevel.UNKNOWN))
                .add(Arguments.of(EventLevel.GREEN.getValue(), EventLevel.GREEN))
                .add(Arguments.of(EventLevel.YELLOW.getValue(), EventLevel.YELLOW))
                .add(Arguments.of(EventLevel.ORANGE.getValue(), EventLevel.ORANGE))
                .add(Arguments.of(EventLevel.RED.getValue(), EventLevel.RED))
                .add(Arguments.of(6, null))
                .build();
    }

    @ParameterizedTest
    @MethodSource("valueProvider")
    void testFindByValue_ShouldReturnNull_WhenValueIsNotFound(final int index, final EventLevel expected) {
        //given

        //when
        final EventLevel actual = EventLevel.findByValue(index);

        //then
        assertEquals(expected, actual);
    }
}

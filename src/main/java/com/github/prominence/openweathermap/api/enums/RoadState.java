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

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum RoadState {
    NO_REPORT(0),
    DRY(1),
    MOIST(2),
    MOIST_AND_CHEMICALLY_TREATED(3),
    WET(4),
    WET_AND_CHEMICALLY_TREATED(5),
    ICE(6),
    FROST(7),
    SNOW(8),
    SNOW_OR_ICE_WATCH(9),
    SNOW_OR_ICE_WARNING(10),
    WET_ABOVE_FREEZING(11),
    WET_BELOW_FREEZING(12),
    ABSORPTION(13),
    ABSORPTION_AT_DEWPOINT(14),
    DEW(15),
    BLACK_ICE_WARNING(16),
    OTHER(17),
    SLUSH(18);

    private final int value;

    RoadState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Finds the appropriate road state based on the numerical code.
     *
     * @param value the numerical code.
     * @return road state
     */
    @JsonCreator
    public static RoadState findByValue(int value) {
        return Arrays.stream(values()).filter(roadState -> roadState.getValue() == value).findFirst().orElse(null);
    }
}

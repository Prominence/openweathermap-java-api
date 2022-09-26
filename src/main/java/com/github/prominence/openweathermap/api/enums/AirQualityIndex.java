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
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * The enum Air quality index.
 */
public enum AirQualityIndex {
    /**
     * Good air quality index.
     */
    GOOD(1),
    /**
     * Fair air quality index.
     */
    FAIR(2),
    /**
     * Moderate air quality index.
     */
    MODERATE(3),
    /**
     * Poor air quality index.
     */
    POOR(4),
    /**
     * Very poor air quality index.
     */
    VERY_POOR(5);

    private final int value;

    AirQualityIndex(int index) {
        this.value = index;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets by index.
     *
     * @param index the index
     * @return the by index
     */
    @JsonCreator
    public static AirQualityIndex getByIndex(@JsonProperty("aqi") int index) {
        return Arrays.stream(values())
                .filter(airQualityIndex -> airQualityIndex.getValue() == index)
                .findFirst()
                .orElse(null);
    }
}

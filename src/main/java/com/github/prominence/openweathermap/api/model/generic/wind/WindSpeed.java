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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents wind speed data using multiple supported measurement units.
 */
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class WindSpeed {

    private static final BigDecimal METER_PER_SECOND_TO_MILES_PER_HOUR_SCALE = BigDecimal.valueOf(2.236936d);
    private static final BigDecimal METER_PER_SECOND_TO_KILOMETERS_PER_HOUR_SCALE = BigDecimal.valueOf(3.6);
    private static final int DECIMAL_PLACES = 2;
    @NonNull
    private final BigDecimal value;

    /**
     * Returns the value converted to m/s.
     *
     * @return speed m/s
     */
    @JsonIgnore
    public BigDecimal asMetersPerSecond() {
        return value.setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    /**
     * Returns the value converted to mph.
     *
     * @return speed mph
     */
    @JsonIgnore
    public BigDecimal asMilesPerHour() {
        return value.multiply(METER_PER_SECOND_TO_MILES_PER_HOUR_SCALE)
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    /**
     * Returns the value converted to kph.
     *
     * @return speed kph
     */
    @JsonIgnore
    public BigDecimal asKilometersPerHour() {
        return value.multiply(METER_PER_SECOND_TO_KILOMETERS_PER_HOUR_SCALE)
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

}

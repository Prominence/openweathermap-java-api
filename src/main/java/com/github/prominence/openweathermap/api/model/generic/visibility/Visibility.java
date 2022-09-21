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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Visibility {

    private static final BigDecimal METER_TO_MILES_SCALE = BigDecimal.ONE.setScale(8, RoundingMode.HALF_EVEN)
            .divide(BigDecimal.valueOf(1609.34d), RoundingMode.HALF_EVEN);
    private static final BigDecimal METER_TO_KILOMETER_SCALE = BigDecimal.valueOf(0.001);
    private static final int DECIMAL_PLACES = 2;
    @NonNull
    private final BigDecimal value;

    @JsonIgnore
    public BigDecimal asMeters() {
        return value.setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    @JsonIgnore
    public BigDecimal asMiles() {
        return value.multiply(METER_TO_MILES_SCALE)
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    @JsonIgnore
    public BigDecimal asKilometers() {
        return value.multiply(METER_TO_KILOMETER_SCALE)
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }
}

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

package com.github.prominence.openweathermap.api.model;

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
public class PrecipitationIntensity {

    private static final BigDecimal MILLIMETER_PER_HOUR_TO_MILLIMETER_PER_MINUTE_SCALE = BigDecimal.valueOf(60.0);
    private static final int DECIMAL_PLACES = 1;
    @NonNull
    private final BigDecimal value;

    @JsonIgnore
    public BigDecimal asMillimetersPerHour() {
        return value.setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    @JsonIgnore
    public BigDecimal asMillimetersPerMinute() {
        return value.multiply(MILLIMETER_PER_HOUR_TO_MILLIMETER_PER_MINUTE_SCALE)
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

}

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
public class TemperatureValue {

    private static final BigDecimal KELVIN_TO_CELSIUS_OFFSET = new BigDecimal("273.15");
    private static final BigDecimal CELSIUS_TO_FAHRENHEIT_OFFSET = new BigDecimal("32");
    private static final BigDecimal CELSIUS_TO_FAHRENHEIT_SCALE = new BigDecimal("1.8");
    private static final int DECIMAL_PLACES = 0;
    @NonNull
    private final BigDecimal value;

    @JsonIgnore
    public BigDecimal asKelvin() {
        return value.setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    @JsonIgnore
    public BigDecimal asCelsius() {
        return value.subtract(KELVIN_TO_CELSIUS_OFFSET)
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    @JsonIgnore
    public BigDecimal asFahrenheit() {
        return asCelsius().multiply(CELSIUS_TO_FAHRENHEIT_SCALE).add(CELSIUS_TO_FAHRENHEIT_OFFSET)
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }
}

/*
 * Copyright (c) 2021 Alexey Zinchenko
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

import java.util.Objects;

/**
 * The Clouds type represents cloudiness value percentage.
 * Its value can only be an integer in [0, 100] range.
 */
public class Clouds {
    private static final String DEFAULT_UNIT = "%";

    private byte value;

    /**
     * Instantiates a new Clouds.
     *
     * @param value the value representing cloudiness percentage.
     * @throws IllegalArgumentException in case if provided value isn't in allowed range.
     */
    private Clouds(byte value) {
        this.value = value;
    }

    /**
     * Static method for {@link Clouds} creation with value checking.
     *
     * @param value clouds percentage value.
     * @return instantiated {@link Clouds} object.
     */
    public static Clouds withValue(byte value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Cloudiness value must be in [0, 100] range.");
        }
        return new Clouds(value);
    }

    /**
     * Returns cloudiness percentage value.
     *
     * @return cloudiness percentage.
     */
    public byte getValue() {
        return value;
    }

    /**
     * Sets cloudiness percentage value.
     *
     * @param value new cloudiness value.
     * @throws IllegalArgumentException in case if provided value isn't in allowed range.
     */
    public void setValue(byte value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Cloudiness value must be in [0, 100] range.");
        }
        this.value = value;
    }

    /**
     * Returns cloudiness unitSystem. Constantly equals to '%'.
     *
     * @return the cloudiness unitSystem.
     */
    public String getUnit() {
        return DEFAULT_UNIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clouds)) return false;
        Clouds clouds = (Clouds) o;
        return value == clouds.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Clouds: " + value + getUnit();
    }
}

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

public class Snow {

    private static final String DEFAULT_UNIT = "mm";

    private Double oneHourSnowLevel;
    private Double threeHourSnowLevel;

    public Snow() {
    }

    public Snow(Double oneHourSnowLevel, Double threeHourSnowLevel) {
        this.oneHourSnowLevel = oneHourSnowLevel;
        this.threeHourSnowLevel = threeHourSnowLevel;
    }

    public Double getOneHourSnowLevel() {
        return oneHourSnowLevel;
    }

    public void setOneHourSnowLevel(Double oneHourSnowLevel) {
        this.oneHourSnowLevel = oneHourSnowLevel;
    }

    public Double getThreeHourSnowLevel() {
        return threeHourSnowLevel;
    }

    public void setThreeHourSnowLevel(Double threeHourSnowLevel) {
        this.threeHourSnowLevel = threeHourSnowLevel;
    }

    public String getUnit() {
        return DEFAULT_UNIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snow)) return false;
        Snow snow = (Snow) o;
        return Objects.equals(oneHourSnowLevel, snow.oneHourSnowLevel) &&
                Objects.equals(threeHourSnowLevel, snow.threeHourSnowLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneHourSnowLevel, threeHourSnowLevel);
    }

    @Override
    public String toString() {
        StringBuilder snowString = new StringBuilder();
        if (oneHourSnowLevel == null && threeHourSnowLevel == null) {
            snowString.append("unknown");
        } else {
            if (oneHourSnowLevel != null) {
                snowString.append("1 last hour snow level: ");
                snowString.append(oneHourSnowLevel);
                snowString.append(getUnit());
            }
            if (threeHourSnowLevel != null) {
                if (oneHourSnowLevel != null) {
                    snowString.append(", ");
                }
                snowString.append("3 last hours snow level: ");
                snowString.append(threeHourSnowLevel);
                snowString.append(getUnit());
            }
        }
        return snowString.toString();
    }
}

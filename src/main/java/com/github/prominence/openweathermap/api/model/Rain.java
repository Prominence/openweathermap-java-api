/*
 * Copyright (c) 2019 Alexey Zinchenko
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

public class Rain {

    private double oneHourRainLevel;
    private double threeHourRainLevel;

    public Rain() {
    }

    public Rain(double oneHourRainLevel, double threeHourRainLevel) {
        this.oneHourRainLevel = oneHourRainLevel;
        this.threeHourRainLevel = threeHourRainLevel;
    }

    public double getOneHourRainLevel() {
        return oneHourRainLevel;
    }

    public void setOneHourRainLevel(double oneHourRainLevel) {
        this.oneHourRainLevel = oneHourRainLevel;
    }

    public double getThreeHourRainLevel() {
        return threeHourRainLevel;
    }

    public void setThreeHourRainLevel(double threeHourRainLevel) {
        this.threeHourRainLevel = threeHourRainLevel;
    }

    public String getUnit() {
        return "mm";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rain)) return false;
        Rain rain = (Rain) o;
        return Double.compare(rain.oneHourRainLevel, oneHourRainLevel) == 0 &&
                Double.compare(rain.threeHourRainLevel, threeHourRainLevel) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneHourRainLevel, threeHourRainLevel);
    }

    @Override
    public String toString() {
        return "1 last hour rain level: " + oneHourRainLevel + + ' ' + getUnit() +
                ", 3 last hours rain level: " + threeHourRainLevel + ' ' + getUnit();
    }
}

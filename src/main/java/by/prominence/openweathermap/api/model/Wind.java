/*
 * Copyright (c) 2018 Alexey Zinchenko
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

package by.prominence.openweathermap.api.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

public class Wind {

    @JSONField(name = "speed")
    // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
    private float speed;

    private String unit;

    @JSONField(name = "deg")
    // Wind direction, degrees (meteorological)
    private short degrees;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public short getDegrees() {
        return degrees;
    }

    public void setDegrees(short degrees) {
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return "Wind: " + speed + ' ' + unit + ", " + degrees + " degrees";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wind wind = (Wind) o;
        return Float.compare(wind.speed, speed) == 0 &&
                degrees == wind.degrees;
    }

    @Override
    public int hashCode() {

        return Objects.hash(speed, degrees);
    }
}

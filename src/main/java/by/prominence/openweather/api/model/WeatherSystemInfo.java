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

package by.prominence.openweather.api.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.Objects;

public class WeatherSystemInfo {

    @JSONField(name = "type")
    // Internal parameter
    private short type;

    @JSONField(name = "id")
    // Internal parameter
    private long id;

    @JSONField(name = "message")
    // Internal parameter
    private double message;

    @JSONField(name = "country")
    // Country code (GB, JP etc.)
    private String country;

    @JSONField(name = "sunrise")
    // Sunrise time, unix, UTC
    private long sunrise;

    @JSONField(name = "sunset")
    // Sunset time, unix, UTC
    private long sunset;

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "WeatherSystemInfo{" +
                "type=" + type +
                ", id=" + id +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + new Date(sunrise * 1000) +
                ", sunset=" + new Date(sunset * 1000) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherSystemInfo that = (WeatherSystemInfo) o;
        return type == that.type &&
                id == that.id &&
                Double.compare(that.message, message) == 0 &&
                Objects.equals(country, that.country) &&
                Objects.equals(sunrise, that.sunrise) &&
                Objects.equals(sunset, that.sunset);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, id, message, country, sunrise, sunset);
    }
}

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

import java.util.Objects;

public class WeatherInfo {

    @JSONField(name = "temp")
    // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private float temperature;

    @JSONField(name = "pressure")
    // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    private short pressure;

    @JSONField(name = "humidity")
    // Humidity, %
    private byte humidity;

    @JSONField(name = "temp_min")
    // Minimum temperature at the moment. This is deviation from current temp that is possible for large cities
    // and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private float minimumTemperature;

    @JSONField(name = "temp_max")
    // Maximum temperature at the moment. This is deviation from current temp that is possible for large cities
    // and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private float maximumTemperature;

    @JSONField(name = "sea_level")
    // Atmospheric pressure on the sea level, hPa
    private short seaLevelPressure;

    @JSONField(name = "grnd_level")
    // Atmospheric pressure on the ground level, hPa
    private short groundLevelPressure;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public short getPressure() {
        return pressure;
    }

    public void setPressure(short pressure) {
        this.pressure = pressure;
    }

    public byte getHumidity() {
        return humidity;
    }

    public void setHumidity(byte humidity) {
        this.humidity = humidity;
    }

    public float getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(float minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public float getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(float maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public short getSeaLevelPressure() {
        return seaLevelPressure;
    }

    public void setSeaLevelPressure(short seaLevelPressure) {
        this.seaLevelPressure = seaLevelPressure;
    }

    public short getGroundLevelPressure() {
        return groundLevelPressure;
    }

    public void setGroundLevelPressure(short groundLevelPressure) {
        this.groundLevelPressure = groundLevelPressure;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity + "%" +
                ", minimumTemperature=" + minimumTemperature +
                ", maximumTemperature=" + maximumTemperature +
                ", seaLevelPressure=" + seaLevelPressure +
                ", groundLevelPressure=" + groundLevelPressure +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfo that = (WeatherInfo) o;
        return Float.compare(that.temperature, temperature) == 0 &&
                pressure == that.pressure &&
                humidity == that.humidity &&
                Float.compare(that.minimumTemperature, minimumTemperature) == 0 &&
                Float.compare(that.maximumTemperature, maximumTemperature) == 0 &&
                seaLevelPressure == that.seaLevelPressure &&
                groundLevelPressure == that.groundLevelPressure;
    }

    @Override
    public int hashCode() {

        return Objects.hash(temperature, pressure, humidity, minimumTemperature, maximumTemperature, seaLevelPressure, groundLevelPressure);
    }
}

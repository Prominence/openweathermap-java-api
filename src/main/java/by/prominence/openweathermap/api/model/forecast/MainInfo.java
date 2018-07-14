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

package by.prominence.openweathermap.api.model.forecast;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

public class MainInfo {

    @JSONField(name = "temp")
    // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private float temperature;

    @JSONField(name = "temp_min")
    // Minimum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and
    // megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private float minimumTemperature;

    @JSONField(name = "temp_max")
    // Maximum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and
    // megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private float maximumTemperature;

    // Atmospheric pressure on the sea level by default, hPa
    private float pressure;

    @JSONField(name = "sea_level")
    // Atmospheric pressure on the sea level, hPa
    private float seaLevelPressure;

    @JSONField(name = "grnd_level")
    // Atmospheric pressure on the ground level, hPa
    private float groundLevelPressure;

    // Humidity, %
    private byte humidity;

    @JSONField(name = "temp_kf")
    // Internal parameter
    private float temperatureCoefficient;

    private char temperatureUnit;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
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

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getSeaLevelPressure() {
        return seaLevelPressure;
    }

    public void setSeaLevelPressure(float seaLevelPressure) {
        this.seaLevelPressure = seaLevelPressure;
    }

    public float getGroundLevelPressure() {
        return groundLevelPressure;
    }

    public void setGroundLevelPressure(float groundLevelPressure) {
        this.groundLevelPressure = groundLevelPressure;
    }

    public byte getHumidity() {
        return humidity;
    }

    public void setHumidity(byte humidity) {
        this.humidity = humidity;
    }

    public float getTemperatureCoefficient() {
        return temperatureCoefficient;
    }

    public void setTemperatureCoefficient(float temperatureCoefficient) {
        this.temperatureCoefficient = temperatureCoefficient;
    }

    public char getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(char temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getPressureUnit() {
        return "hPa";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Temperature: ");
        stringBuilder.append(temperature);
        stringBuilder.append(' ');
        stringBuilder.append(temperatureUnit);
        stringBuilder.append(". Minimum temperature: ");
        stringBuilder.append(minimumTemperature);
        stringBuilder.append(' ');
        stringBuilder.append(temperatureUnit);
        stringBuilder.append(". Maximum temperature: ");
        stringBuilder.append(maximumTemperature);
        stringBuilder.append(' ');
        stringBuilder.append(temperatureUnit);
        stringBuilder.append(". Pressure: ");
        stringBuilder.append(pressure);
        stringBuilder.append(' ');
        stringBuilder.append(getPressureUnit());
        if (seaLevelPressure > 0) {
            stringBuilder.append(". Sea-level pressure: ");
            stringBuilder.append(seaLevelPressure);
            stringBuilder.append(' ');
            stringBuilder.append(getPressureUnit());
        }
        if (groundLevelPressure > 0) {
            stringBuilder.append(". Ground-level pressure: ");
            stringBuilder.append(groundLevelPressure);
            stringBuilder.append(' ');
            stringBuilder.append(getPressureUnit());
        }
        stringBuilder.append(". Humidity: ");
        stringBuilder.append(humidity);
        stringBuilder.append('%');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainInfo mainInfo = (MainInfo) o;
        return Float.compare(mainInfo.temperature, temperature) == 0 &&
                Float.compare(mainInfo.minimumTemperature, minimumTemperature) == 0 &&
                Float.compare(mainInfo.maximumTemperature, maximumTemperature) == 0 &&
                Float.compare(mainInfo.pressure, pressure) == 0 &&
                Float.compare(mainInfo.seaLevelPressure, seaLevelPressure) == 0 &&
                Float.compare(mainInfo.groundLevelPressure, groundLevelPressure) == 0 &&
                humidity == mainInfo.humidity &&
                Float.compare(mainInfo.temperatureCoefficient, temperatureCoefficient) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(temperature, minimumTemperature, maximumTemperature, pressure, seaLevelPressure, groundLevelPressure, humidity, temperatureCoefficient);
    }
}

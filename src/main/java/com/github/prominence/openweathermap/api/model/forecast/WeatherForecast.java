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

package com.github.prominence.openweathermap.api.model.forecast;

import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Pressure;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.DayTime;

import java.time.LocalDateTime;
import java.util.Objects;

public class WeatherForecast {
    private LocalDateTime forecastTime;
    private Temperature temperature;
    private Pressure pressure;
    private Humidity humidity;

    private String state;
    private String description;
    private String weatherIconUrl;

    private Clouds clouds;
    private Wind wind;
    private Snow snow;
    private Rain rain;

    private String forecastTimeISO;
    private DayTime dayTime;

    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public String getForecastTimeISO() {
        return forecastTimeISO;
    }

    public void setForecastTimeISO(String forecastTimeISO) {
        this.forecastTimeISO = forecastTimeISO;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public void setDayTime(DayTime dayTime) {
        this.dayTime = dayTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecast that = (WeatherForecast) o;
        return Objects.equals(forecastTime, that.forecastTime) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(state, that.state) &&
                Objects.equals(description, that.description) &&
                Objects.equals(weatherIconUrl, that.weatherIconUrl) &&
                Objects.equals(clouds, that.clouds) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(snow, that.snow) &&
                Objects.equals(rain, that.rain) &&
                Objects.equals(forecastTimeISO, that.forecastTimeISO) &&
                dayTime == that.dayTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, temperature, pressure, humidity, state, description, weatherIconUrl, clouds, wind, snow, rain, forecastTimeISO, dayTime);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timestamp: ");
        stringBuilder.append(forecastTimeISO);
        stringBuilder.append(", Weather: ");
        stringBuilder.append(description);
        if (temperature != null) {
            stringBuilder.append(", ");
            stringBuilder.append(temperature.getValue());
            stringBuilder.append(' ');
            stringBuilder.append(temperature.getUnit());
        }
        if (pressure != null) {
            stringBuilder.append(", ");
            stringBuilder.append(pressure.getValue());
            stringBuilder.append(' ');
            stringBuilder.append(pressure.getUnit());
        }
        if (clouds != null) {
            stringBuilder.append(", ");
            stringBuilder.append(clouds.toString());
        }
        if (rain != null && rain.getThreeHourRainLevel() != null) {
            stringBuilder.append(", Rain: ");
            stringBuilder.append(rain.getThreeHourRainLevel());
            stringBuilder.append(' ');
            stringBuilder.append(rain.getUnit());
        }
        if (snow != null && snow.getThreeHourSnowLevel() != null) {
            stringBuilder.append(", Snow: ");
            stringBuilder.append(snow.getThreeHourSnowLevel());
            stringBuilder.append(' ');
            stringBuilder.append(snow.getUnit());
        }
        return stringBuilder.toString();
    }
}

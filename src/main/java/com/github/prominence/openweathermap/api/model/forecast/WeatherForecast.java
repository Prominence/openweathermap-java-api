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

import com.github.prominence.openweathermap.api.model.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class WeatherForecast {
    private LocalDateTime forecastTime;
    private Temperature temperature;
    private AtmosphericPressure atmosphericPressure;
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

    private WeatherForecast(String state, String description) {
        this.state = state;
        this.description = description;
    }

    public static WeatherForecast forValue(String state, String description) {
        if (state == null) {
            throw new IllegalArgumentException("State must be set.");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description must be set.");
        }
        return new WeatherForecast(state, description);
    }

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

    public AtmosphericPressure getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public void setAtmosphericPressure(AtmosphericPressure atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
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
        if (state == null) {
            throw new IllegalArgumentException("State must be not null.");
        }
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description must be not null.");
        }
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
                Objects.equals(atmosphericPressure, that.atmosphericPressure) &&
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
        return Objects.hash(forecastTime, temperature, atmosphericPressure, humidity, state, description, weatherIconUrl, clouds, wind, snow, rain, forecastTimeISO, dayTime);
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
        if (atmosphericPressure != null) {
            stringBuilder.append(", ");
            stringBuilder.append(atmosphericPressure.getValue());
            stringBuilder.append(' ');
            stringBuilder.append(atmosphericPressure.getUnit());
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

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

package com.github.prominence.openweathermap.api.model.onecall.current;

import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.onecall.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.onecall.Wind;

import java.time.LocalDateTime;

public class Daily {
    private LocalDateTime forecastTime;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;

    private Integer weatherId;
    private String state;
    private String description;
    private String iconId;

    private DailyTemperature temperature;
    private AtmosphericPressure atmosphericPressure;
    private Humidity humidity;
    private Wind wind;
    private Clouds clouds;
    private Double uvIndex;
    private Double probabilityOfPrecipitation;
    private DailyRain rain;
    private DailySnow snow;

    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
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

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public DailyTemperature getTemperature() {
        return temperature;
    }

    public void setTemperature(DailyTemperature temperature) {
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

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Double getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public void setProbabilityOfPrecipitation(Double probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    public DailyRain getRain() {
        return rain;
    }

    public void setRain(DailyRain rain) {
        this.rain = rain;
    }

    public DailySnow getSnow() {
        return snow;
    }

    public void setSnow(DailySnow snow) {
        this.snow = snow;
    }
}

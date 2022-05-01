/*
 * Copyright (c) 2022 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model.forecast.free;

import com.github.prominence.openweathermap.api.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents weather forecast information for a particular timestamp.
 */
public class WeatherForecast {
    private LocalDateTime forecastTime;

    private List<WeatherState> weatherStates;
    private Temperature temperature;
    private AtmosphericPressure atmosphericPressure;
    private Humidity humidity;

    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Clouds clouds;

    private String forecastTimeISO;
    private DayTime dayTime;

    private Double visibilityInMetres;
    private Double probabilityOfPrecipitation;

    /**
     * Gets forecast time.
     *
     * @return the forecast time
     */
    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    /**
     * Sets forecast time.
     *
     * @param forecastTime the forecast time
     */
    public void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    /**
     * Gets weather state.
     *
     * @return the weather state
     */
    public List<WeatherState> getWeatherStates() {
        return weatherStates;
    }

    /**
     * Sets weather state.
     *
     * @param weatherStates the weather state
     */
    public void setWeatherStates(List<WeatherState> weatherStates) {
        this.weatherStates = weatherStates;
    }

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     * Sets temperature.
     *
     * @param temperature the temperature
     */
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets atmospheric pressure.
     *
     * @return the atmospheric pressure
     */
    public AtmosphericPressure getAtmosphericPressure() {
        return atmosphericPressure;
    }

    /**
     * Sets atmospheric pressure.
     *
     * @param atmosphericPressure the atmospheric pressure
     */
    public void setAtmosphericPressure(AtmosphericPressure atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    /**
     * Gets humidity.
     *
     * @return the humidity
     */
    public Humidity getHumidity() {
        return humidity;
    }

    /**
     * Sets humidity.
     *
     * @param humidity the humidity
     */
    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets wind.
     *
     * @return the wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets wind.
     *
     * @param wind the wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * Gets rain.
     *
     * @return the rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * Sets rain.
     *
     * @param rain the rain
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * Gets snow.
     *
     * @return the snow
     */
    public Snow getSnow() {
        return snow;
    }

    /**
     * Sets snow.
     *
     * @param snow the snow
     */
    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    /**
     * Gets clouds.
     *
     * @return the clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * Sets clouds.
     *
     * @param clouds the clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * Gets forecast time iso.
     *
     * @return the forecast time iso
     */
    public String getForecastTimeISO() {
        return forecastTimeISO;
    }

    /**
     * Sets forecast time iso.
     *
     * @param forecastTimeISO the forecast time iso
     */
    public void setForecastTimeISO(String forecastTimeISO) {
        this.forecastTimeISO = forecastTimeISO;
    }

    /**
     * Gets day time.
     *
     * @return the day time
     */
    public DayTime getDayTime() {
        return dayTime;
    }

    /**
     * Sets day time.
     *
     * @param dayTime the day time
     */
    public void setDayTime(DayTime dayTime) {
        this.dayTime = dayTime;
    }

    public Double getVisibilityInMetres() {
        return visibilityInMetres;
    }

    public void setVisibilityInMetres(Double visibilityInMetres) {
        this.visibilityInMetres = visibilityInMetres;
    }

    public Double getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public void setProbabilityOfPrecipitation(Double probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecast that = (WeatherForecast) o;
        return Objects.equals(forecastTime, that.forecastTime) &&
                Objects.equals(weatherStates, that.weatherStates) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(atmosphericPressure, that.atmosphericPressure) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(rain, that.rain) &&
                Objects.equals(snow, that.snow) &&
                Objects.equals(clouds, that.clouds) &&
                Objects.equals(forecastTimeISO, that.forecastTimeISO) &&
                Objects.equals(visibilityInMetres, that.visibilityInMetres) &&
                Objects.equals(probabilityOfPrecipitation, that.probabilityOfPrecipitation) &&
                dayTime == that.dayTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, weatherStates, temperature, atmosphericPressure, humidity, wind, rain, snow, clouds, forecastTimeISO, visibilityInMetres, probabilityOfPrecipitation, dayTime);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timestamp: ");
        stringBuilder.append(forecastTimeISO);
        if (weatherStates != null && weatherStates.size() > 0) {
            stringBuilder.append(", Weather: ");
            stringBuilder.append(weatherStates.get(0).getDescription());
        }
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
        if (rain != null) {
            stringBuilder.append(", Rain: ");
            stringBuilder.append(rain.getThreeHourLevel());
            stringBuilder.append(' ');
            stringBuilder.append(rain.getUnit());
        }
        if (snow != null) {
            stringBuilder.append(", Snow: ");
            stringBuilder.append(snow.getThreeHourLevel());
            stringBuilder.append(' ');
            stringBuilder.append(snow.getUnit());
        }
        return stringBuilder.toString();
    }
}

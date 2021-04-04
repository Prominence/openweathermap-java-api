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

package com.github.prominence.openweathermap.api.model.onecall;

import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.WeatherState;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Current.
 */
public class Current {
    protected LocalDateTime forecastTime;
    protected LocalDateTime sunriseTime;
    protected LocalDateTime sunsetTime;

    protected WeatherState weatherState;
    protected Temperature temperature;
    protected AtmosphericPressure atmosphericPressure;
    protected Humidity humidity;
    protected Clouds clouds;
    protected Double uvIndex;
    protected Double visibilityInMetres;
    protected Wind wind;
    protected Rain rain;
    protected Snow snow;

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
     * Gets sunrise time.
     *
     * @return the sunrise time
     */
    public LocalDateTime getSunriseTime() {
        return sunriseTime;
    }

    /**
     * Sets sunrise time.
     *
     * @param sunriseTime the sunrise time
     */
    public void setSunriseTime(LocalDateTime sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    /**
     * Gets sunset time.
     *
     * @return the sunset time
     */
    public LocalDateTime getSunsetTime() {
        return sunsetTime;
    }

    /**
     * Sets sunset time.
     *
     * @param sunsetTime the sunset time
     */
    public void setSunsetTime(LocalDateTime sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    /**
     * Gets weather state.
     *
     * @return the weather state
     */
    public WeatherState getWeatherState() {
        return weatherState;
    }

    /**
     * Sets weather state.
     *
     * @param weatherState the weather state
     */
    public void setWeatherState(WeatherState weatherState) {
        this.weatherState = weatherState;
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
     * Gets uv index.
     *
     * @return the uv index
     */
    public Double getUvIndex() {
        return uvIndex;
    }

    /**
     * Sets uv index.
     *
     * @param uvIndex the uv index
     */
    public void setUvIndex(Double uvIndex) {
        if (uvIndex != null && uvIndex < 0) {
            throw new IllegalArgumentException("UV index must not be negative.");
        }
        this.uvIndex = uvIndex;
    }

    /**
     * Gets visibility in metres.
     *
     * @return the visibility in metres
     */
    public Double getVisibilityInMetres() {
        return visibilityInMetres;
    }

    /**
     * Sets visibility in metres.
     *
     * @param visibilityInMetres the visibility in metres
     */
    public void setVisibilityInMetres(Double visibilityInMetres) {
        if (visibilityInMetres != null && visibilityInMetres < 0) {
            throw new IllegalArgumentException("Visibility must not be negative.");
        }
        this.visibilityInMetres = visibilityInMetres;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Current current = (Current) o;
        return Objects.equals(forecastTime, current.forecastTime) &&
                Objects.equals(sunriseTime, current.sunriseTime) &&
                Objects.equals(sunsetTime, current.sunsetTime) &&
                Objects.equals(weatherState, current.weatherState) &&
                Objects.equals(temperature, current.temperature) &&
                Objects.equals(atmosphericPressure, current.atmosphericPressure) &&
                Objects.equals(humidity, current.humidity) &&
                Objects.equals(clouds, current.clouds) &&
                Objects.equals(uvIndex, current.uvIndex) &&
                Objects.equals(visibilityInMetres, current.visibilityInMetres) &&
                Objects.equals(wind, current.wind) &&
                Objects.equals(rain, current.rain) &&
                Objects.equals(snow, current.snow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, sunriseTime, sunsetTime, weatherState, temperature, atmosphericPressure, humidity, clouds, uvIndex, visibilityInMetres, wind, rain, snow);
    }

    @Override
    public String toString() {
        return "Current weather information forecasted for " + forecastTime + ".";
    }
}

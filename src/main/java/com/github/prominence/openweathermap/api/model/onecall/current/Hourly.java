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
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.onecall.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.onecall.Rain;
import com.github.prominence.openweathermap.api.model.onecall.Snow;
import com.github.prominence.openweathermap.api.model.onecall.Temperature;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * The type Hourly.
 */
public class Hourly {
    private LocalDateTime forecastTime;

    private List<WeatherState> weatherStates;
    private Temperature temperature;
    private AtmosphericPressure atmosphericPressure;
    private Humidity humidity;
    private Double uvIndex;
    private Clouds clouds;
    private Double visibilityInMetres;
    private Wind wind;
    private Double probabilityOfPrecipitation;
    private Rain rain;
    private Snow snow;

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
        this.uvIndex = uvIndex;
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
     * Gets probability of precipitation.
     *
     * @return the probability of precipitation
     */
    public Double getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    /**
     * Sets probability of precipitation.
     *
     * @param probabilityOfPrecipitation the probability of precipitation
     */
    public void setProbabilityOfPrecipitation(Double probabilityOfPrecipitation) {
        if (probabilityOfPrecipitation != null && (probabilityOfPrecipitation < 0 || probabilityOfPrecipitation > 100))  {
            throw new IllegalArgumentException("Probability of precipitation value must be in [0, 100] range.");
        }
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    /**
     * Gets probability of precipitation percentage.
     *
     * @return the probability of precipitation percentage
     */
    public Byte getProbabilityOfPrecipitationPercentage() {
        if (probabilityOfPrecipitation != null) {
            return (byte)(probabilityOfPrecipitation * 100);
        }

        return null;
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
        Hourly hourly = (Hourly) o;
        return Objects.equals(forecastTime, hourly.forecastTime) &&
                Objects.equals(weatherStates, hourly.weatherStates) &&
                Objects.equals(temperature, hourly.temperature) &&
                Objects.equals(atmosphericPressure, hourly.atmosphericPressure) &&
                Objects.equals(humidity, hourly.humidity) &&
                Objects.equals(uvIndex, hourly.uvIndex) &&
                Objects.equals(clouds, hourly.clouds) &&
                Objects.equals(visibilityInMetres, hourly.visibilityInMetres) &&
                Objects.equals(wind, hourly.wind) &&
                Objects.equals(probabilityOfPrecipitation, hourly.probabilityOfPrecipitation) &&
                Objects.equals(rain, hourly.rain) &&
                Objects.equals(snow, hourly.snow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, weatherStates, temperature, atmosphericPressure, humidity, uvIndex, clouds, visibilityInMetres, wind, probabilityOfPrecipitation, rain, snow);
    }

    @Override
    public String toString() {
        return "Hourly weather information forecasted for " + forecastTime + ".";
    }
}

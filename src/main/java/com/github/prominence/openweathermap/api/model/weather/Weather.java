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

package com.github.prominence.openweathermap.api.model.weather;

import com.github.prominence.openweathermap.api.model.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents weather information.
 */
public class Weather {
    private LocalDateTime calculationTime;

    private WeatherState weatherState;
    private Temperature temperature;
    private AtmosphericPressure atmosphericPressure;
    private Humidity humidity;

    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Clouds clouds;

    private Location location;

    /**
     * Gets calculated on.
     *
     * @return the calculated on
     */
    public LocalDateTime getCalculationTime() {
        return calculationTime;
    }

    /**
     * Sets calculated on.
     *
     * @param calculationTime the calculated on
     */
    public void setCalculationTime(LocalDateTime calculationTime) {
        this.calculationTime = calculationTime;
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
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weather)) return false;
        Weather weather = (Weather) o;
        return Objects.equals(calculationTime, weather.calculationTime) &&
                Objects.equals(weatherState, weather.weatherState) &&
                Objects.equals(temperature, weather.temperature) &&
                Objects.equals(atmosphericPressure, weather.atmosphericPressure) &&
                Objects.equals(humidity, weather.humidity) &&
                Objects.equals(wind, weather.wind) &&
                Objects.equals(rain, weather.rain) &&
                Objects.equals(snow, weather.snow) &&
                Objects.equals(clouds, weather.clouds) &&
                Objects.equals(location, weather.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculationTime, weatherState, temperature, atmosphericPressure, humidity, wind, rain, snow, clouds, location);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        if (location != null) {
            stringBuilder.append("Location: ");
            stringBuilder.append(location.getName());

            final String countryCode = location.getCountryCode();
            if (countryCode != null) {
                stringBuilder.append('(');
                stringBuilder.append(countryCode);
                stringBuilder.append(')');
            }
        }
        if (weatherState != null) {
            stringBuilder.append(", Weather: ");
            stringBuilder.append(weatherState.getDescription());
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
        if (rain != null && rain.getOneHourRainLevel() != null) {
            stringBuilder.append(", Rain: ");
            stringBuilder.append(rain.getOneHourRainLevel());
            stringBuilder.append(' ');
            stringBuilder.append(rain.getUnit());
        }
        if (snow != null && snow.getOneHourSnowLevel() != null) {
            stringBuilder.append(", Snow: ");
            stringBuilder.append(snow.getOneHourSnowLevel());
            stringBuilder.append(' ');
            stringBuilder.append(snow.getUnit());
        }
        return stringBuilder.toString();
    }
}

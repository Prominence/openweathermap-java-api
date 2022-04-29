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

package com.github.prominence.openweathermap.api.model.forecast.daily;


import java.util.List;
import java.util.Objects;

/**
 * Represents information about forecast for different timestamps.
 */
public class Forecast {
    private Location location;
    private List<WeatherForecast> weatherForecasts;

    /**
     * Returns location information.
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets forecast location.
     * @param location forecast location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns list of weather forecasts for different timestamps.
     * @return list of forecast-per-timestamp information.
     */
    public List<WeatherForecast> getWeatherForecasts() {
        return weatherForecasts;
    }

    /**
     * Sets list of weather forecasts for different timestamps.
     * @param weatherForecasts list of forecast information
     */
    public void setWeatherForecasts(List<WeatherForecast> weatherForecasts) {
        this.weatherForecasts = weatherForecasts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(location, forecast.location) && Objects.equals(weatherForecasts, forecast.weatherForecasts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, weatherForecasts);
    }

    @Override
    public String toString() {
        return "A forecast for " + location.getName() + " with " + weatherForecasts.size() + " timestamps.";
    }
}

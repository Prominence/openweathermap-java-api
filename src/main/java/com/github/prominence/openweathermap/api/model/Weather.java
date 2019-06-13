/*
 * Copyright (c) 2019 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Weather {

    private String weatherState;
    private String weatherDescription;
    private String weatherIconUrl;
    private LocalDateTime requestedOn;

    private Temperature temperature;
    private Pressure pressure;
    private Humidity humidity;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Clouds clouds;

    private Location location;

    public String getWeatherState() {
        return weatherState;
    }

    public void setWeatherState(String weatherState) {
        this.weatherState = weatherState;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public LocalDateTime getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(LocalDateTime requestedOn) {
        this.requestedOn = requestedOn;
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

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weather)) return false;
        Weather weather = (Weather) o;
        return Objects.equals(weatherState, weather.weatherState) &&
                Objects.equals(weatherDescription, weather.weatherDescription) &&
                Objects.equals(weatherIconUrl, weather.weatherIconUrl) &&
                Objects.equals(requestedOn, weather.requestedOn) &&
                Objects.equals(temperature, weather.temperature) &&
                Objects.equals(pressure, weather.pressure) &&
                Objects.equals(humidity, weather.humidity) &&
                Objects.equals(wind, weather.wind) &&
                Objects.equals(rain, weather.rain) &&
                Objects.equals(snow, weather.snow) &&
                Objects.equals(clouds, weather.clouds) &&
                Objects.equals(location, weather.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherState, weatherDescription, weatherIconUrl, requestedOn, temperature, pressure, humidity, wind, rain, snow, clouds, location);
    }

    @Override
    public String toString() {
        final String countryCode = location.getCountryCode();
        String resultString = "Location: " +
                location.getName() + (countryCode != null ? ('(' + countryCode + ")") : "") +
                ", Weather: " + weatherDescription +
                ", " + temperature.getValue() + ' ' + temperature.getUnit() +
                ", " + pressure.getValue() + ' ' + pressure.getUnit() +
                ", " + clouds.toString();
        if (rain != null) {
            resultString += (", Rain: " + rain.getOneHourRainLevel() + ' ' + rain.getUnit());
        }
        if (snow != null) {
            resultString += (", Snow: " + snow.getOneHourSnowLevel() + ' ' + snow.getUnit());
        }
        return resultString;

    }
}

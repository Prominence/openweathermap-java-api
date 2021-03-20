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

package com.github.prominence.openweathermap.api.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Weather {

    private String state;
    private String description;
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

    public Weather(String state, String description) {
        if (state == null) {
            throw new IllegalArgumentException("State must be set.");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description must be set.");
        }
        this.state = state;
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state == null) {
            throw new IllegalArgumentException("State must be set.");
        }
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description must be set.");
        }
        this.description = description;
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
        return Objects.equals(state, weather.state) &&
                Objects.equals(description, weather.description) &&
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
        return Objects.hash(state, description, weatherIconUrl, requestedOn, temperature, pressure, humidity, wind, rain, snow, clouds, location);
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

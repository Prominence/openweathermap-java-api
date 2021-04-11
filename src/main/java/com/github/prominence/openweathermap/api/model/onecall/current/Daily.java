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
import com.github.prominence.openweathermap.api.model.onecall.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.onecall.Wind;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Daily.
 */
public class Daily {
    private LocalDateTime forecastTime;
    private LocalDateTime sunriseTime;
    private LocalDateTime sunsetTime;

    private WeatherState weatherState;
    private DailyTemperature temperature;
    private AtmosphericPressure atmosphericPressure;
    private Humidity humidity;
    private Wind wind;
    private Clouds clouds;
    private Double uvIndex;
    private Double probabilityOfPrecipitation;
    private DailyRain rain;
    private DailySnow snow;

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
    public DailyTemperature getTemperature() {
        return temperature;
    }

    /**
     * Sets temperature.
     *
     * @param temperature the temperature
     */
    public void setTemperature(DailyTemperature temperature) {
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
     * Gets rain.
     *
     * @return the rain
     */
    public DailyRain getRain() {
        return rain;
    }

    /**
     * Sets rain.
     *
     * @param rain the rain
     */
    public void setRain(DailyRain rain) {
        this.rain = rain;
    }

    /**
     * Gets snow.
     *
     * @return the snow
     */
    public DailySnow getSnow() {
        return snow;
    }

    /**
     * Sets snow.
     *
     * @param snow the snow
     */
    public void setSnow(DailySnow snow) {
        this.snow = snow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Daily daily = (Daily) o;
        return Objects.equals(forecastTime, daily.forecastTime) &&
                Objects.equals(sunriseTime, daily.sunriseTime) &&
                Objects.equals(sunsetTime, daily.sunsetTime) &&
                Objects.equals(weatherState, daily.weatherState) &&
                Objects.equals(temperature, daily.temperature) &&
                Objects.equals(atmosphericPressure, daily.atmosphericPressure) &&
                Objects.equals(humidity, daily.humidity) &&
                Objects.equals(wind, daily.wind) &&
                Objects.equals(clouds, daily.clouds) &&
                Objects.equals(uvIndex, daily.uvIndex) &&
                Objects.equals(probabilityOfPrecipitation, daily.probabilityOfPrecipitation) &&
                Objects.equals(rain, daily.rain) &&
                Objects.equals(snow, daily.snow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, sunriseTime, sunsetTime, weatherState, temperature, atmosphericPressure, humidity, wind, clouds, uvIndex, probabilityOfPrecipitation, rain, snow);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Weather on ");
        stringBuilder.append(forecastTime);
        stringBuilder.append(".");
        if (weatherState != null) {
            stringBuilder.append(" Weather: ");
            stringBuilder.append(weatherState.getDescription());
            stringBuilder.append('.');
        }
        if (temperature != null) {
            stringBuilder.append(" Temperature(day): ");
            stringBuilder.append(temperature.getDay());
            stringBuilder.append(' ');
            stringBuilder.append(temperature.getUnit());
            stringBuilder.append('.');
        }
        if (atmosphericPressure != null) {
            stringBuilder.append(" Atmospheric pressure: ");
            stringBuilder.append(atmosphericPressure.getSeaLevelValue());
            stringBuilder.append(' ');
            stringBuilder.append(atmosphericPressure.getUnit());
            stringBuilder.append('.');
        }
        if (clouds != null) {
            stringBuilder.append(" Clouds: ");
            stringBuilder.append(clouds.toString());
            stringBuilder.append('.');
        }
        if (rain != null) {
            stringBuilder.append(" Rain: ");
            stringBuilder.append(rain.getValue());
            stringBuilder.append(' ');
            stringBuilder.append(rain.getUnit());
            stringBuilder.append('.');
        }
        if (snow != null) {
            stringBuilder.append(". Snow: ");
            stringBuilder.append(snow.getValue());
            stringBuilder.append(' ');
            stringBuilder.append(snow.getUnit());
            stringBuilder.append('.');
        }
        return stringBuilder.toString();
    }
}

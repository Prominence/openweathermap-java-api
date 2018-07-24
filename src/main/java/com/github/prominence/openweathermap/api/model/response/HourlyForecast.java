/*
 * Copyright (c) 2018 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.prominence.openweathermap.api.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode
public class HourlyForecast implements OpenWeatherResponse {

    @JSONField(name = "cod")
    @Getter
    @Setter
    private short responseCode;

    @Getter
    @Setter
    private double message;

    // Number of lines returned by this API call
    @Getter
    @Setter
    private short cnt;

    @JSONField(name = "list")
    @Getter
    @Setter
    private List<Forecast> forecasts;

    @JSONField(name = "city")
    @Getter
    @Setter
    private CityInfo cityInfo;

    public String getCityName() {
        return cityInfo.getName();
    }

    public long getCityId() {
        return cityInfo.getId();
    }

    public String getCountry() {
        return cityInfo.getCountry();
    }

    public Coordinates getCoordinates() {
        return cityInfo.getCoordinates();
    }

    public float getAverageTemperature() {
        return (float)forecasts.stream().mapToDouble(forecast -> forecast.weatherInfo.temperature).average().orElse(0f);
    }

    public float getMinimumTemperature() {
        return (float)forecasts.stream().mapToDouble(forecast -> forecast.weatherInfo.temperature).min().orElse(0f);
    }

    public float getMaximumTemperature() {
        return (float)forecasts.stream().mapToDouble(forecast -> forecast.weatherInfo.temperature).max().orElse(0f);
    }

    public Forecast getByMinimumTemperature() {
        return forecasts.stream().min(Comparator.comparing(forecast -> forecast.weatherInfo.minimumTemperature)).orElse(null);
    }

    public Forecast getByMaximumTemperature() {
        return forecasts.stream().max(Comparator.comparing(forecast -> forecast.weatherInfo.maximumTemperature)).orElse(null);
    }

    public float getAveragePressure() {
        return (float)forecasts.stream().mapToDouble(forecast -> forecast.weatherInfo.pressure).average().orElse(0f);
    }

    public float getMinimumPressure() {
        return (float)forecasts.stream().mapToDouble(forecast -> forecast.weatherInfo.pressure).min().orElse(0f);
    }

    public float getMaximumPressure() {
        return (float)forecasts.stream().mapToDouble(forecast -> forecast.weatherInfo.pressure).max().orElse(0f);
    }

    public Forecast getByMinimumPressure() {
        return forecasts.stream().min(Comparator.comparing(forecast -> forecast.weatherInfo.pressure)).orElse(null);
    }

    public Forecast getByMaximumPressure() {
        return forecasts.stream().max(Comparator.comparing(forecast -> forecast.weatherInfo.pressure)).orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(cityInfo);
        builder.append("\nForecasts: ");
        forecasts.forEach(forecast -> {
            builder.append("\n\t");
            builder.append(forecast);
        });
        return builder.toString();
    }

    @EqualsAndHashCode
    public static class Forecast {

        @JSONField(name = "dt")
        // Time of data calculation, unix, UTC
        @Getter
        @Setter
        private long dataCalculationTime;

        @JSONField(name = "main")
        @Getter
        @Setter
        private WeatherInfo weatherInfo;

        @JSONField(name = "weather")
        @Getter
        @Setter
        private List<WeatherState> weatherStates;

        @Getter
        @Setter
        private Clouds clouds;

        @Getter
        @Setter
        private Wind wind;

        @Getter
        @Setter
        private Snow snow;

        @Getter
        @Setter
        private Rain rain;

        @JSONField(name = "sys")
        @Getter
        @Setter
        private ForecastSystemInfo systemInfo;

        // Data/time of calculation, UTC
        @Getter
        @Setter
        private String dt_txt;

        public Date getDataCalculationDate() {
            return new Date(dataCalculationTime * 1000);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Time: ");
            builder.append(new Date(dataCalculationTime * 1000));
            builder.append("; ");
            if (weatherStates.size() == 1) {
                builder.append(weatherStates.get(0));
            } else {
                builder.append(weatherStates);
            }
            builder.append("; ");
            builder.append(weatherInfo);
            if (clouds != null) {
                builder.append("; ");
                builder.append(clouds);
            }
            if (wind != null) {
                builder.append("; ");
                builder.append(wind);
            }
            if (snow != null) {
                builder.append("; ");
                builder.append(snow);
            }
            if (rain != null) {
                builder.append("; ");
                builder.append(rain);
            }

            return builder.toString();
        }

        @Data
        public static class ForecastSystemInfo {

            private String pod;
        }

        @EqualsAndHashCode
        public static class WeatherInfo {

            @JSONField(name = "temp")
            // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            @Getter
            @Setter
            private float temperature;

            @JSONField(name = "temp_min")
            // Minimum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and
            // megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            @Getter
            @Setter
            private float minimumTemperature;

            @JSONField(name = "temp_max")
            // Maximum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and
            // megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            @Getter
            @Setter
            private float maximumTemperature;

            // Atmospheric pressure on the sea level by default, hPa
            @Getter
            @Setter
            private float pressure;

            @JSONField(name = "sea_level")
            // Atmospheric pressure on the sea level, hPa
            @Getter
            @Setter
            private float seaLevelPressure;

            @JSONField(name = "grnd_level")
            // Atmospheric pressure on the ground level, hPa
            @Getter
            @Setter
            private float groundLevelPressure;

            // Humidity, %
            @Getter
            @Setter
            private byte humidity;

            @JSONField(name = "temp_kf")
            // Internal parameter
            @Getter
            @Setter
            private float temperatureCoefficient;

            @Getter
            @Setter
            private char temperatureUnit;

            public String getPressureUnit() {
                return "hPa";
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append("Temperature: ");
                builder.append(temperature);
                builder.append(' ');
                builder.append(temperatureUnit);
                builder.append("; Minimum temperature: ");
                builder.append(minimumTemperature);
                builder.append(' ');
                builder.append(temperatureUnit);
                builder.append("; Maximum temperature: ");
                builder.append(maximumTemperature);
                builder.append(' ');
                builder.append(temperatureUnit);
                builder.append("; Pressure: ");
                builder.append(pressure);
                builder.append(' ');
                builder.append(getPressureUnit());
                if (seaLevelPressure > 0) {
                    builder.append("; Sea-level pressure: ");
                    builder.append(seaLevelPressure);
                    builder.append(' ');
                    builder.append(getPressureUnit());
                }
                if (groundLevelPressure > 0) {
                    builder.append("; Ground-level pressure: ");
                    builder.append(groundLevelPressure);
                    builder.append(' ');
                    builder.append(getPressureUnit());
                }
                builder.append("; Humidity: ");
                builder.append(humidity);
                builder.append('%');

                return builder.toString();
            }
        }
    }

}

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
import com.github.prominence.openweathermap.api.model.CityInfo;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.OpenWeatherResponse;
import com.github.prominence.openweathermap.api.model.WeatherState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode
public class DailyForecast implements OpenWeatherResponse {

    @JSONField(name = "city")
    @Getter
    @Setter
    private CityInfo cityInfo;

    // Internal parameter
    @Getter
    @Setter
    private String cod;

    // Internal parameter
    @Getter
    @Setter
    private double message;

    // Number of lines returned by this API call
    @Getter
    @Setter
    private byte cnt;

    @JSONField(name = "list")
    @Getter
    @Setter
    private List<Forecast> forecasts;

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

    public short getResponseCode() {
        return Short.parseShort(cod);
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

        @JSONField(name = "temp")
        @Getter
        @Setter
        private Temperature temperature;

        @Getter
        @Setter
        private float pressure;

        @Getter
        @Setter
        private byte humidity;

        @JSONField(name = "weather")
        @Getter
        @Setter
        private List<WeatherState> weatherStates;

        @JSONField(name = "speed")
        @Getter
        @Setter
        private float windSpeed;

        @JSONField(name = "deg")
        // Wind direction, degrees (meteorological)
        @Getter
        @Setter
        private short windDegrees;

        @Getter
        @Setter
        private String windUnit;

        @JSONField(name = "clouds")
        @Getter
        @Setter
        private byte cloudiness;

        public String getPressureUnit() {
            return "hPa";
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(temperature);
            builder.append(", Pressure: ");
            builder.append(pressure);
            builder.append(' ');
            builder.append(getPressureUnit());
            builder.append("; Humidity: ");
            builder.append(humidity);
            builder.append("%; Weather: ");
            if (weatherStates.size() == 1) {
                builder.append(weatherStates.get(0));
            } else {
                builder.append(weatherStates);
            }
            builder.append("; Wind: ");
            builder.append(windSpeed);
            builder.append(' ');
            builder.append(windUnit);
            builder.append(", ");
            builder.append(windDegrees);
            builder.append(" degrees; Cloudiness: ");
            builder.append(cloudiness);
            builder.append('%');

            return builder.toString();
        }

        @EqualsAndHashCode
        public static class Temperature {

            @JSONField(name = "day")
            @Getter
            @Setter
            private float dayTemperature;

            @JSONField(name = "min")
            @Getter
            @Setter
            private float minTemperature;

            @JSONField(name = "max")
            @Getter
            @Setter
            private float maxTemperature;

            @JSONField(name = "night")
            @Getter
            @Setter
            private float nightTemperature;

            @JSONField(name = "eve")
            @Getter
            @Setter
            private float eveningTemperature;

            @JSONField(name = "morn")
            @Getter
            @Setter
            private float morningTemperature;

            @Getter
            @Setter
            private char temperatureUnit;

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();

                builder.append("Temperature: [");
                builder.append("Day temperature: ");
                builder.append(dayTemperature);
                builder.append(' ');
                builder.append(getTemperatureUnit());
                builder.append("; Night temperature: ");
                builder.append(nightTemperature);
                builder.append(' ');
                builder.append(getTemperatureUnit());
                builder.append("; Morning temperature: ");
                builder.append(morningTemperature);
                builder.append(' ');
                builder.append(getTemperatureUnit());
                builder.append("; Evening temperature: ");
                builder.append(eveningTemperature);
                builder.append(' ');
                builder.append(getTemperatureUnit());
                builder.append("; Minimum temperature: ");
                builder.append(minTemperature);
                builder.append(' ');
                builder.append(getTemperatureUnit());
                builder.append("; Maximum temperature: ");
                builder.append(maxTemperature);
                builder.append(' ');
                builder.append(getTemperatureUnit());
                builder.append("]");

                return builder.toString();
            }
        }
    }
}

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
public class Weather implements OpenWeatherResponse {

    @JSONField(name = "id")
    @Getter
    @Setter
    private long cityId;

    @JSONField(name = "name")
    @Getter
    @Setter
    private String cityName;

    @JSONField(name = "coord")
    @Getter
    @Setter
    private Coordinates coordinates;

    @JSONField(name = "weather")
    @Getter
    @Setter
    private List<WeatherState> weatherStates;

    @Getter
    @Setter
    private String base;

    @JSONField(name = "main")
    @Getter
    @Setter
    private WeatherInfo weatherInfo;

    @Getter
    @Setter
    private Wind wind;

    @Getter
    @Setter
    private Clouds clouds;

    @Getter
    @Setter
    private Rain rain;

    @Getter
    @Setter
    private Snow snow;

    @JSONField(name = "dt")
    @Getter
    @Setter
    private long dataCalculationTime;

    @JSONField(name = "sys")
    @Getter
    @Setter
    private WeatherSystemInfo weatherSystemInfo;

    @JSONField(name = "cod")
    @Getter
    @Setter
    private short responseCode;

    public String getCountry() {
        return weatherSystemInfo.country;
    }

    public String getWeatherDescription() {
        if (weatherStates != null && weatherStates.size() > 0) {
            return weatherStates.get(0).getDescription();
        }
        return null;
    }

    public Date getDataCalculationDate() {
        return new Date(dataCalculationTime * 1000);
    }

    public float getTemperature() {
        return weatherInfo.temperature;
    }

    public char getTemperatureUnit() {
        return weatherInfo.temperatureUnit;
    }

    public short getPressure() {
        return weatherInfo.pressure;
    }

    public String getPressureUnit() {
        return weatherInfo.getPressureUnit();
    }

    public byte getHumidityPercentage() {
        return weatherInfo.humidity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("City: ");
        stringBuilder.append(cityName);
        stringBuilder.append('(');
        stringBuilder.append(cityId);
        stringBuilder.append("); Coordinates: ");
        stringBuilder.append(coordinates);
        stringBuilder.append('\n');
        stringBuilder.append(weatherSystemInfo);
        stringBuilder.append('\n');
        if (weatherStates.size() == 1) {
            stringBuilder.append(weatherStates.get(0));
        } else {
            stringBuilder.append(weatherStates);
        }
        stringBuilder.append('\n');
        stringBuilder.append(weatherInfo);
        stringBuilder.append('\n');
        stringBuilder.append(wind);
        stringBuilder.append('\n');
        stringBuilder.append(clouds);
        stringBuilder.append('\n');
        if (rain != null) {
            stringBuilder.append(rain);
            stringBuilder.append('\n');
        }
        if (snow != null) {
            stringBuilder.append(snow);
            stringBuilder.append('\n');
        }
        stringBuilder.append("Data calculation time: ");
        stringBuilder.append(getDataCalculationDate());

        return stringBuilder.toString();
    }

    @EqualsAndHashCode
    public static class WeatherInfo {
        @JSONField(name = "temp")
        // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        @Getter
        @Setter
        private float temperature;

        @JSONField(name = "pressure")
        // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
        @Getter
        @Setter
        private short pressure;

        @JSONField(name = "humidity")
        // Humidity, %
        @Getter
        @Setter
        private byte humidity;

        @JSONField(name = "temp_min")
        // Minimum temperature at the moment. This is deviation from current temp that is possible for large cities
        // and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        @Getter
        @Setter
        private float minimumTemperature;

        @JSONField(name = "temp_max")
        // Maximum temperature at the moment. This is deviation from current temp that is possible for large cities
        // and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        @Getter
        @Setter
        private float maximumTemperature;

        @JSONField(name = "sea_level")
        // Atmospheric pressure on the sea level, hPa
        @Getter
        @Setter
        private short seaLevelPressure;

        @JSONField(name = "grnd_level")
        // Atmospheric pressure on the ground level, hPa
        @Getter
        @Setter
        private short groundLevelPressure;

        @Getter
        @Setter
        private char temperatureUnit;

        public String getPressureUnit() {
            return "hPa";
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Temperature: ");
            stringBuilder.append(temperature);
            stringBuilder.append(' ');
            stringBuilder.append(temperatureUnit);
            stringBuilder.append("; Minimum temparature: ");
            stringBuilder.append(minimumTemperature);
            stringBuilder.append(' ');
            stringBuilder.append(temperatureUnit);
            stringBuilder.append("; Maximum temperature: ");
            stringBuilder.append(maximumTemperature);
            stringBuilder.append(' ');
            stringBuilder.append(temperatureUnit);
            stringBuilder.append('\n');
            stringBuilder.append("Humidity: ");
            stringBuilder.append(humidity);
            stringBuilder.append("%");
            stringBuilder.append('\n');
            stringBuilder.append("Pressure: ");
            stringBuilder.append(pressure);
            stringBuilder.append(' ');
            stringBuilder.append(getPressureUnit());
            if (seaLevelPressure > 0) {
                stringBuilder.append("; Sea-level pressure: ");
                stringBuilder.append(seaLevelPressure);
                stringBuilder.append(' ');
                stringBuilder.append(getPressureUnit());
            }
            if (groundLevelPressure > 0) {
                stringBuilder.append("; Ground-level pressure: ");
                stringBuilder.append(groundLevelPressure);
                stringBuilder.append(' ');
                stringBuilder.append(getPressureUnit());
            }

            return stringBuilder.toString();
        }
    }

    public static class WeatherSystemInfo {
        @JSONField(name = "type")
        // Internal parameter
        @Getter
        @Setter
        private short type;

        @JSONField(name = "id")
        // Internal parameter
        @Getter
        @Setter
        private long id;

        @JSONField(name = "message")
        // Internal parameter
        @Getter
        @Setter
        private double message;

        @JSONField(name = "country")
        // Country code (GB, JP etc.)
        @Getter
        @Setter
        private String country;

        @JSONField(name = "sunrise")
        // Sunrise time, unix, UTC
        @Getter
        @Setter
        private long sunriseTimestamp;

        @JSONField(name = "sunset")
        // Sunset time, unix, UTC
        @Getter
        @Setter
        private long sunsetTimestamp;

        public Date getSunriseDate() {
            return new Date(sunriseTimestamp * 1000);
        }

        public Date getSunsetDate() {
            return new Date(sunsetTimestamp * 1000);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            if (country != null) {
                stringBuilder.append("Country: ");
                stringBuilder.append(country);
                stringBuilder.append('\n');
            }
            if (sunriseTimestamp > 0) {
                stringBuilder.append("Sunrise: ");
                stringBuilder.append(getSunriseDate());
                stringBuilder.append('\n');
            }
            if (sunsetTimestamp > 0) {
                stringBuilder.append("Sunset: ");
                stringBuilder.append(getSunsetDate());
            }

            return stringBuilder.toString();
        }
    }
}

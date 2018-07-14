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

package by.prominence.openweathermap.api.model.response;

import by.prominence.openweathermap.api.model.*;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class WeatherResponse implements OpenWeatherResponse {

    @JSONField(name = "id")
    private long cityId;

    @JSONField(name = "name")
    private String cityName;

    @JSONField(name = "coord")
    private Coordinates coordinates;

    @JSONField(name = "weather")
    private List<Weather> weather;

    @JSONField(name = "base")
    private String base;

    @JSONField(name = "main")
    private WeatherInfo weatherInfo;

    @JSONField(name = "wind")
    private Wind wind;

    @JSONField(name = "clouds")
    private Clouds clouds;

    @JSONField(name = "rain")
    private Rain rain;

    @JSONField(name = "snow")
    private Snow snow;

    @JSONField(name = "dt")
    private long dataCalculationTime;

    @JSONField(name = "sys")
    private WeatherSystemInfo weatherSystemInfo;

    @JSONField(name = "cod")
    private short responseCode;

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
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

    public long getDataCalculationTime() {
        return dataCalculationTime;
    }

    public void setDataCalculationTime(long dataCalculationTime) {
        this.dataCalculationTime = dataCalculationTime;
    }

    public WeatherSystemInfo getWeatherSystemInfo() {
        return weatherSystemInfo;
    }

    public void setWeatherSystemInfo(WeatherSystemInfo weatherSystemInfo) {
        this.weatherSystemInfo = weatherSystemInfo;
    }

    public short getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(short responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("City: ");
        stringBuilder.append(cityName);
        stringBuilder.append('(');
        stringBuilder.append(cityId);
        stringBuilder.append("). ");
        stringBuilder.append("Coordinates: ");
        stringBuilder.append(coordinates);
        stringBuilder.append('\n');
        stringBuilder.append(weatherSystemInfo);
        stringBuilder.append('\n');
        if (weather.size() == 1) {
            stringBuilder.append(weather.get(0));
        } else {
            stringBuilder.append(weather);
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
        stringBuilder.append(new Date(dataCalculationTime * 1000));

        return stringBuilder.toString();
    }

    public static class WeatherInfo {
        @JSONField(name = "temp")
        // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        private float temperature;

        @JSONField(name = "pressure")
        // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
        private short pressure;

        @JSONField(name = "humidity")
        // Humidity, %
        private byte humidity;

        @JSONField(name = "temp_min")
        // Minimum temperature at the moment. This is deviation from current temp that is possible for large cities
        // and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        private float minimumTemperature;

        @JSONField(name = "temp_max")
        // Maximum temperature at the moment. This is deviation from current temp that is possible for large cities
        // and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        private float maximumTemperature;

        @JSONField(name = "sea_level")
        // Atmospheric pressure on the sea level, hPa
        private short seaLevelPressure;

        @JSONField(name = "grnd_level")
        // Atmospheric pressure on the ground level, hPa
        private short groundLevelPressure;

        private char temperatureUnit;

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public short getPressure() {
            return pressure;
        }

        public void setPressure(short pressure) {
            this.pressure = pressure;
        }

        public byte getHumidity() {
            return humidity;
        }

        public void setHumidity(byte humidity) {
            this.humidity = humidity;
        }

        public float getMinimumTemperature() {
            return minimumTemperature;
        }

        public void setMinimumTemperature(float minimumTemperature) {
            this.minimumTemperature = minimumTemperature;
        }

        public float getMaximumTemperature() {
            return maximumTemperature;
        }

        public void setMaximumTemperature(float maximumTemperature) {
            this.maximumTemperature = maximumTemperature;
        }

        public short getSeaLevelPressure() {
            return seaLevelPressure;
        }

        public void setSeaLevelPressure(short seaLevelPressure) {
            this.seaLevelPressure = seaLevelPressure;
        }

        public short getGroundLevelPressure() {
            return groundLevelPressure;
        }

        public void setGroundLevelPressure(short groundLevelPressure) {
            this.groundLevelPressure = groundLevelPressure;
        }

        public char getTemperatureUnit() {
            return temperatureUnit;
        }

        public void setTemperatureUnit(char temperatureUnit) {
            this.temperatureUnit = temperatureUnit;
        }

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
            stringBuilder.append(". Minimum temparature: ");
            stringBuilder.append(minimumTemperature);
            stringBuilder.append(' ');
            stringBuilder.append(temperatureUnit);
            stringBuilder.append(". Maximum temperature: ");
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
                stringBuilder.append(". Sea-level pressure: ");
                stringBuilder.append(seaLevelPressure);
                stringBuilder.append(' ');
                stringBuilder.append(getPressureUnit());
            }
            if (groundLevelPressure > 0) {
                stringBuilder.append(". Ground-level pressure: ");
                stringBuilder.append(groundLevelPressure);
                stringBuilder.append(' ');
                stringBuilder.append(getPressureUnit());
            }

            return stringBuilder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeatherInfo that = (WeatherInfo) o;
            return Float.compare(that.temperature, temperature) == 0 &&
                    pressure == that.pressure &&
                    humidity == that.humidity &&
                    Float.compare(that.minimumTemperature, minimumTemperature) == 0 &&
                    Float.compare(that.maximumTemperature, maximumTemperature) == 0 &&
                    seaLevelPressure == that.seaLevelPressure &&
                    groundLevelPressure == that.groundLevelPressure;
        }

        @Override
        public int hashCode() {

            return Objects.hash(temperature, pressure, humidity, minimumTemperature, maximumTemperature, seaLevelPressure, groundLevelPressure);
        }
    }

    public static class WeatherSystemInfo {
        @JSONField(name = "type")
        // Internal parameter
        private short type;

        @JSONField(name = "id")
        // Internal parameter
        private long id;

        @JSONField(name = "message")
        // Internal parameter
        private double message;

        @JSONField(name = "country")
        // Country code (GB, JP etc.)
        private String country;

        @JSONField(name = "sunrise")
        // Sunrise time, unix, UTC
        private long sunrise;

        @JSONField(name = "sunset")
        // Sunset time, unix, UTC
        private long sunset;

        public short getType() {
            return type;
        }

        public void setType(short type) {
            this.type = type;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public double getMessage() {
            return message;
        }

        public void setMessage(double message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            if (country != null) {
                stringBuilder.append("Country: ");
                stringBuilder.append(country);
                stringBuilder.append('\n');
            }
            if (sunrise > 0) {
                stringBuilder.append("Sunrise: ");
                stringBuilder.append(new Date(sunrise * 1000));
                stringBuilder.append('\n');
            }
            if (sunset > 0) {
                stringBuilder.append("Sunset: ");
                stringBuilder.append(new Date(sunset * 1000));
            }

            return stringBuilder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeatherSystemInfo that = (WeatherSystemInfo) o;
            return type == that.type &&
                    id == that.id &&
                    Double.compare(that.message, message) == 0 &&
                    Objects.equals(country, that.country) &&
                    Objects.equals(sunrise, that.sunrise) &&
                    Objects.equals(sunset, that.sunset);
        }

        @Override
        public int hashCode() {

            return Objects.hash(type, id, message, country, sunrise, sunset);
        }
    }
}

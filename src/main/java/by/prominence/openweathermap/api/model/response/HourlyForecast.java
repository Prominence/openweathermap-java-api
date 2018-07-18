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

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HourlyForecast implements OpenWeatherResponse {

    @JSONField(name = "cod")
    private short responseCode;

    private double message;

    // Number of lines returned by this API call
    private short cnt;

    @JSONField(name = "list")
    private List<Forecast> forecasts;

    @JSONField(name = "city")
    private CityInfo cityInfo;

    public short getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(short responseCode) {
        this.responseCode = responseCode;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public short getCnt() {
        return cnt;
    }

    public void setCnt(short cnt) {
        this.cnt = cnt;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getCityName() {
        return cityInfo.name;
    }

    public long getCityId() {
        return cityInfo.id;
    }

    public String getCountry() {
        return cityInfo.country;
    }

    public Coordinates getCoordinates() {
        return cityInfo.coordinates;
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cityInfo);
        stringBuilder.append("\nForecasts: ");
        forecasts.forEach(forecast -> {
            stringBuilder.append("\n\t");
            stringBuilder.append(forecast);
        });
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HourlyForecast that = (HourlyForecast) o;
        return responseCode == that.responseCode &&
                Double.compare(that.message, message) == 0 &&
                cnt == that.cnt &&
                Objects.equals(forecasts, that.forecasts) &&
                Objects.equals(cityInfo, that.cityInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(responseCode, message, cnt, forecasts, cityInfo);
    }

    public static class CityInfo {

        // City ID
        private long id;

        // City name
        private String name;

        @JSONField(name = "coord")
        private Coordinates coordinates;

        // Country code (GB, JP etc.)
        private String country;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "City: " + name + "(" + id + "). Coordinates: " + coordinates + '\n' + "Country: " + country;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CityInfo cityInfo = (CityInfo) o;
            return id == cityInfo.id &&
                    Objects.equals(name, cityInfo.name) &&
                    Objects.equals(coordinates, cityInfo.coordinates) &&
                    Objects.equals(country, cityInfo.country);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, name, coordinates, country);
        }

    }

    public static class Forecast {

        @JSONField(name = "dt")
        // Time of data calculation, unix, UTC
        private long dataCalculationTime;

        @JSONField(name = "main")
        private WeatherInfo weatherInfo;

        @JSONField(name = "weather")
        private List<WeatherState> weatherStates;

        private Clouds clouds;

        private Wind wind;

        private Snow snow;

        private Rain rain;

        @JSONField(name = "sys")
        private ForecastSystemInfo systemInfo;

        // Data/time of calculation, UTC
        private String dt_txt;

        public long getDataCalculationTime() {
            return dataCalculationTime;
        }

        public void setDataCalculationTime(long dataCalculationTime) {
            this.dataCalculationTime = dataCalculationTime;
        }

        public Date getDataCalculationDate() {
            return new Date(dataCalculationTime * 1000);
        }

        public WeatherInfo getWeatherInfo() {
            return weatherInfo;
        }

        public void setWeatherInfo(WeatherInfo weatherInfo) {
            this.weatherInfo = weatherInfo;
        }

        public List<WeatherState> getWeatherStates() {
            return weatherStates;
        }

        public void setWeatherStates(List<WeatherState> weatherStates) {
            this.weatherStates = weatherStates;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Snow getSnow() {
            return snow;
        }

        public void setSnow(Snow snow) {
            this.snow = snow;
        }

        public Rain getRain() {
            return rain;
        }

        public void setRain(Rain rain) {
            this.rain = rain;
        }

        public ForecastSystemInfo getSystemInfo() {
            return systemInfo;
        }

        public void setSystemInfo(ForecastSystemInfo systemInfo) {
            this.systemInfo = systemInfo;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Time: ");
            stringBuilder.append(new Date(dataCalculationTime * 1000));
            stringBuilder.append(". ");
            if (weatherStates.size() == 1) {
                stringBuilder.append(weatherStates.get(0));
            } else {
                stringBuilder.append(weatherStates);
            }
            stringBuilder.append(". ");
            stringBuilder.append(weatherInfo);
            if (clouds != null) {
                stringBuilder.append(". ");
                stringBuilder.append(clouds);
            }
            if (wind != null) {
                stringBuilder.append(". ");
                stringBuilder.append(wind);
            }
            if (snow != null) {
                stringBuilder.append(". ");
                stringBuilder.append(snow);
            }
            if (rain != null) {
                stringBuilder.append(". ");
                stringBuilder.append(rain);
            }

            return stringBuilder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Forecast that = (Forecast) o;
            return dataCalculationTime == that.dataCalculationTime &&
                    Objects.equals(weatherInfo, that.weatherInfo) &&
                    Objects.equals(weatherStates, that.weatherStates) &&
                    Objects.equals(clouds, that.clouds) &&
                    Objects.equals(wind, that.wind) &&
                    Objects.equals(snow, that.snow) &&
                    Objects.equals(rain, that.rain) &&
                    Objects.equals(systemInfo, that.systemInfo) &&
                    Objects.equals(dt_txt, that.dt_txt);
        }

        @Override
        public int hashCode() {

            return Objects.hash(dataCalculationTime, weatherInfo, weatherStates, clouds, wind, snow, rain, systemInfo, dt_txt);
        }

        public static class ForecastSystemInfo {

            private String pod;

            public String getPod() {
                return pod;
            }

            public void setPod(String pod) {
                this.pod = pod;
            }

            @Override
            public String toString() {
                return "ForecastSystemInfo{" +
                        "pod='" + pod + '\'' +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ForecastSystemInfo that = (ForecastSystemInfo) o;
                return Objects.equals(pod, that.pod);
            }

            @Override
            public int hashCode() {

                return Objects.hash(pod);
            }
        }

        public static class WeatherInfo {

            @JSONField(name = "temp")
            // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            private float temperature;

            @JSONField(name = "temp_min")
            // Minimum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and
            // megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            private float minimumTemperature;

            @JSONField(name = "temp_max")
            // Maximum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and
            // megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            private float maximumTemperature;

            // Atmospheric pressure on the sea level by default, hPa
            private float pressure;

            @JSONField(name = "sea_level")
            // Atmospheric pressure on the sea level, hPa
            private float seaLevelPressure;

            @JSONField(name = "grnd_level")
            // Atmospheric pressure on the ground level, hPa
            private float groundLevelPressure;

            // Humidity, %
            private byte humidity;

            @JSONField(name = "temp_kf")
            // Internal parameter
            private float temperatureCoefficient;

            private char temperatureUnit;

            public float getTemperature() {
                return temperature;
            }

            public void setTemperature(float temperature) {
                this.temperature = temperature;
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

            public float getPressure() {
                return pressure;
            }

            public void setPressure(float pressure) {
                this.pressure = pressure;
            }

            public float getSeaLevelPressure() {
                return seaLevelPressure;
            }

            public void setSeaLevelPressure(float seaLevelPressure) {
                this.seaLevelPressure = seaLevelPressure;
            }

            public float getGroundLevelPressure() {
                return groundLevelPressure;
            }

            public void setGroundLevelPressure(float groundLevelPressure) {
                this.groundLevelPressure = groundLevelPressure;
            }

            public byte getHumidity() {
                return humidity;
            }

            public void setHumidity(byte humidity) {
                this.humidity = humidity;
            }

            public float getTemperatureCoefficient() {
                return temperatureCoefficient;
            }

            public void setTemperatureCoefficient(float temperatureCoefficient) {
                this.temperatureCoefficient = temperatureCoefficient;
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
                stringBuilder.append(". Minimum temperature: ");
                stringBuilder.append(minimumTemperature);
                stringBuilder.append(' ');
                stringBuilder.append(temperatureUnit);
                stringBuilder.append(". Maximum temperature: ");
                stringBuilder.append(maximumTemperature);
                stringBuilder.append(' ');
                stringBuilder.append(temperatureUnit);
                stringBuilder.append(". Pressure: ");
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
                stringBuilder.append(". Humidity: ");
                stringBuilder.append(humidity);
                stringBuilder.append('%');

                return stringBuilder.toString();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                WeatherInfo weatherInfo = (WeatherInfo) o;
                return Float.compare(weatherInfo.temperature, temperature) == 0 &&
                        Float.compare(weatherInfo.minimumTemperature, minimumTemperature) == 0 &&
                        Float.compare(weatherInfo.maximumTemperature, maximumTemperature) == 0 &&
                        Float.compare(weatherInfo.pressure, pressure) == 0 &&
                        Float.compare(weatherInfo.seaLevelPressure, seaLevelPressure) == 0 &&
                        Float.compare(weatherInfo.groundLevelPressure, groundLevelPressure) == 0 &&
                        humidity == weatherInfo.humidity &&
                        Float.compare(weatherInfo.temperatureCoefficient, temperatureCoefficient) == 0;
            }

            @Override
            public int hashCode() {

                return Objects.hash(temperature, minimumTemperature, maximumTemperature, pressure, seaLevelPressure, groundLevelPressure, humidity, temperatureCoefficient);
            }
        }
    }

}

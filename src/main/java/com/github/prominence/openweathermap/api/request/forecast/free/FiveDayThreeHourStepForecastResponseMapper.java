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

package com.github.prominence.openweathermap.api.request.forecast.free;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.*;
import com.github.prominence.openweathermap.api.model.forecast.Location;
import com.github.prominence.openweathermap.api.model.forecast.Rain;
import com.github.prominence.openweathermap.api.model.forecast.Snow;
import com.github.prominence.openweathermap.api.model.Temperature;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Official API response documentation.
 * Parameters(but the real response can differ):
 * --- cod Internal parameter
 * --- message Internal parameter
 * --- cnt A number of timestamps returned in the API response
 * --- list
 *      |- list.dt Time of data forecasted, unix, UTC
 *      |- list.main
 *          |- list.main.temp Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.feels_like This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.temp_min Minimum temperature at the moment of calculation. This is minimal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.temp_max Maximum temperature at the moment of calculation. This is maximal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *          |- list.main.pressure Atmospheric pressure on the sea level by default, hPa
 *          |- list.main.sea_level Atmospheric pressure on the sea level, hPa
 *          |- list.main.grnd_level Atmospheric pressure on the ground level, hPa
 *          |- list.main.humidity Humidity, %
 *          |- list.main.temp_kf Internal par
 *      |- list.weather
 *          |- list.weather.id Weather condition id
 *          |- list.weather.main Group of weather parameters (Rain, Snow, Extreme etc.)
 *          |- list.weather.description Weather condition within the group. You can get the output in your language.
 *          |- list.weather.icon Weather icon id
 *      |- list.clouds
 *          |- list.clouds.all Cloudiness, %
 *      |- list.wind
 *          |- list.wind.speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 *          |- list.wind.deg Wind direction, degrees (meteorological)
 *      |- list.visibility Average visibility, metres
 *      |- list.pop Probability of precipitation
 *      |- list.rain
 *          |- list.rain.3h Rain volume for last 3 hours, mm
 *      |- list.snow
 *          |- list.snow.3h Snow volume for last 3 hours
 *      |- list.sys
 *          |- list.sys.pod Part of the day (n - night, d - day)
 *      |- list.dt_txt Time of data forecasted, ISO, UTC
 * --- city
 *      |- city.id City ID
 *      |- city.name City name
 *      |- city.coord
 *          |- city.coord.lat City geo location, latitude
 *          |- city.coord.lon City geo location, longitude
 *      |- city.country Country code (GB, JP etc.)
 *      |- city.timezone Shift in seconds from UTC
 */
public class FiveDayThreeHourStepForecastResponseMapper {

    private final UnitSystem unitSystem;

    /**
     * Instantiates a new forecast response mapper.
     *
     * @param unitSystem the unit system
     */
    public FiveDayThreeHourStepForecastResponseMapper(UnitSystem unitSystem) {
        this.unitSystem = unitSystem;
    }

    /**
     * Maps forecast response into java object.
     *
     * @param json the json string
     * @return the forecast
     */
    public Forecast mapToForecast(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Forecast forecast;
        try {
            JsonNode root = objectMapper.readTree(json);
            forecast = mapToForecast(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Forecast response");
        }

        return forecast;
    }

    private Forecast mapToForecast(JsonNode root) {
        Forecast forecast = new Forecast();
        forecast.setLocation(parseLocation(root.get("city")));

        List<WeatherForecast> forecasts = new ArrayList<>(root.get("cnt").asInt());

        JsonNode forecastListNode = root.get("list");
        forecastListNode.forEach(forecastNode -> forecasts.add(parseWeatherForecast(forecastNode)));

        forecast.setWeatherForecasts(forecasts);

        return forecast;
    }

    private WeatherForecast parseWeatherForecast(JsonNode rootNode) {
        JsonNode weatherNode = rootNode.get("weather").get(0);
        WeatherForecast weatherForecast = WeatherForecast.forValue(
                weatherNode.get("main").asText(),
                weatherNode.get("description").asText()
        );
        weatherForecast.setWeatherIconId(weatherNode.get("icon").asText());

        JsonNode mainNode = rootNode.get("main");
        weatherForecast.setTemperature(parseTemperature(mainNode));
        weatherForecast.setAtmosphericPressure(parsePressure(mainNode));
        weatherForecast.setHumidity(parseHumidity(mainNode));
        weatherForecast.setClouds(parseClouds(rootNode));
        weatherForecast.setWind(parseWind(rootNode));
        weatherForecast.setRain(parseRain(rootNode));
        weatherForecast.setSnow(parseSnow(rootNode));

        JsonNode sysNode = rootNode.get("sys");
        if (sysNode != null) {
            weatherForecast.setDayTime("d".equals(sysNode.get("pod").asText()) ? DayTime.DAY : DayTime.NIGHT);
        }

        weatherForecast.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(rootNode.get("dt").asLong()), TimeZone.getDefault().toZoneId()));
        weatherForecast.setForecastTimeISO(rootNode.get("dt_txt").asText());

        return weatherForecast;
    }

    private Temperature parseTemperature(JsonNode rootNode) {
        final double tempValue = rootNode.get("temp").asDouble();
        Temperature temperature = Temperature.withValue(tempValue, unitSystem.getTemperatureUnit());

        final JsonNode tempMaxNode = rootNode.get("temp_max");
        if (tempMaxNode != null) {
            temperature.setMaxTemperature(tempMaxNode.asDouble());
        }
        final JsonNode tempMinNode = rootNode.get("temp_min");
        if (tempMinNode != null) {
            temperature.setMinTemperature(tempMinNode.asDouble());
        }
        final JsonNode tempFeelsLike = rootNode.get("fells_like");
        if (tempFeelsLike != null) {
            temperature.setFeelsLike(tempFeelsLike.asDouble());
        }

        return temperature;
    }

    private AtmosphericPressure parsePressure(JsonNode rootNode) {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(rootNode.get("pressure").asDouble());

        final JsonNode seaLevelNode = rootNode.get("sea_level");
        final JsonNode groundLevelNode = rootNode.get("grnd_level");
        if (seaLevelNode != null) {
            atmosphericPressure.setSeaLevelValue(seaLevelNode.asDouble());
        }
        if (groundLevelNode != null) {
            atmosphericPressure.setGroundLevelValue(groundLevelNode.asDouble());
        }

        return atmosphericPressure;
    }

    private Humidity parseHumidity(JsonNode rootNode) {
        return Humidity.withValue((byte) (rootNode.get("humidity").asInt()));
    }

    private Wind parseWind(JsonNode root) {
        final JsonNode windNode = root.get("wind");
        double speed = windNode.get("speed").asDouble();

        Wind wind = Wind.withValue(speed, unitSystem.getWindUnit());
        final JsonNode degNode = windNode.get("deg");
        if (degNode != null) {
            wind.setDegrees(degNode.asDouble());
        }

        return wind;
    }

    private Rain parseRain(JsonNode root) {
        final JsonNode rainNode = root.get("rain");
        if (rainNode != null) {
            final JsonNode threeHourNode = rainNode.get("3h");
            if (threeHourNode != null) {
                return Rain.withThreeHourLevelValue(threeHourNode.asDouble());
            }
        }
        return null;
    }

    private Snow parseSnow(JsonNode root) {
        final JsonNode snowNode = root.get("snow");
        if (snowNode != null) {
            final JsonNode threeHourNode = snowNode.get("3h");
            if (threeHourNode != null) {
                Rain.withThreeHourLevelValue(threeHourNode.asDouble());
            }
        }
        return null;
    }

    private Clouds parseClouds(JsonNode rootNode) {
        Clouds clouds = null;

        final JsonNode cloudsNode = rootNode.get("clouds");
        final JsonNode allValueNode = cloudsNode.get("all");
        if (allValueNode != null) {
            clouds = Clouds.withValue((byte) allValueNode.asInt());
        }

        return clouds;
    }

    private Location parseLocation(JsonNode rootNode) {
        Location location = Location.withValues(rootNode.get("id").asInt(), rootNode.get("name").asText());

        final JsonNode timezoneNode = rootNode.get("timezone");
        if (timezoneNode != null) {
            location.setZoneOffset(ZoneOffset.ofTotalSeconds(timezoneNode.asInt()));
        }

        final JsonNode countryNode = rootNode.get("country");
        if (countryNode != null) {
            location.setCountryCode(countryNode.asText());
        }

        final JsonNode sunriseNode = rootNode.get("sunrise");
        final JsonNode sunsetNode = rootNode.get("sunset");
        if (sunriseNode != null) {
            location.setSunrise(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseNode.asLong()), TimeZone.getDefault().toZoneId()));
        }
        if (sunsetNode != null) {
            location.setSunset(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetNode.asLong()), TimeZone.getDefault().toZoneId()));
        }

        final JsonNode coordNode = rootNode.get("coord");
        if (coordNode != null) {
            location.setCoordinate(parseCoordinate(coordNode));
        }

        final JsonNode populationNode = rootNode.get("population");
        if (populationNode != null) {
            location.setPopulation(populationNode.asLong());
        }

        return location;
    }

    private Coordinate parseCoordinate(JsonNode rootNode) {
        JsonNode latitudeNode = rootNode.get("lat");
        JsonNode longitudeNode = rootNode.get("lon");
        if (latitudeNode != null && longitudeNode != null) {
            return Coordinate.withValues(latitudeNode.asDouble(), longitudeNode.asDouble());
        }
        return null;
    }
}

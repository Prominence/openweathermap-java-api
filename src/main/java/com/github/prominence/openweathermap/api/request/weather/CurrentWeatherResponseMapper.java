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

package com.github.prominence.openweathermap.api.request.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.model.weather.*;
import com.github.prominence.openweathermap.api.request.ResponseMapper;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Official API response documentation:
 * Parameters:
 * --- coord
 *      |- coord.lon City geo location, longitude
 *      |- coord.lat City geo location, latitude
 * --- weather (more info Weather condition codes)
 *      |- weather.id Weather condition id
 *      |- weather.main Group of weather parameters (Rain, Snow, Extreme etc.)
 *      |- weather.description Weather condition within the group
 *      |- weather.icon Weather icon id
 * --- base Internal parameter
 * --- main
 *      |- main.temp Temperature. UnitSystem Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *      |- main.feels_like Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *      |- main.pressure Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
 *      |- main.humidity Humidity, %
 *      |- main.temp_min Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). UnitSystem Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *      |- main.temp_max Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). UnitSystem Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *      |- main.sea_level Atmospheric pressure on the sea level, hPa
 *      |- main.grnd_level Atmospheric pressure on the ground level, hPa
 * --- wind
 *      |- wind.speed Wind speed. UnitSystem Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 *      |- wind.deg Wind direction, degrees (meteorological)
 *      |- wind.gust Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
 * --- clouds
 *      |- clouds.all Cloudiness, %
 * --- rain
 *      |- rain.1h Rain volume for the last 1 hour, mm
 *      |- rain.3h Rain volume for the last 3 hours, mm
 * --- snow
 *      |- snow.1h Snow volume for the last 1 hour, mm
 *      |- snow.3h Snow volume for the last 3 hours, mm
 * --- dt Time of data calculation, unix, UTC
 * --- sys
 *      |- sys.type Internal parameter
 *      |- sys.id Internal parameter
 *      |- sys.message Internal parameter
 *      |- sys.country Country code (GB, JP etc.)
 *      |- sys.sunrise Sunrise time, unix, UTC
 *      |- sys.sunset Sunset time, unix, UTC
 * --- timezone Shift in seconds from UTC
 * --- id City ID
 * --- name City name
 * --- cod Internal parameter
 */
public class CurrentWeatherResponseMapper implements ResponseMapper<Weather> {

    private final UnitSystem unitSystem;

    public CurrentWeatherResponseMapper(UnitSystem unitSystem) {
        this.unitSystem = unitSystem != null ? unitSystem : UnitSystem.STANDARD;
    }

    @Override
    public Weather getSingle(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather;
        try {
            JsonNode root = objectMapper.readTree(json);
            weather = getSingle(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Weather response");
        }

        return weather;
    }

    private Weather getSingle(JsonNode rootNode) {
        JsonNode weatherState = rootNode.get("weather").get(0);
        Weather weather = Weather.forValue(weatherState.get("main").asText(), weatherState.get("description").asText());
        weather.setWeatherIconUrl("http://openweathermap.org/img/w/" + weatherState.get("icon").asText() + ".png");

        weather.setTemperature(parseTemperature(rootNode));
        weather.setAtmosphericPressure(parsePressure(rootNode));
        weather.setHumidity(parseHumidity(rootNode));
        weather.setWind(parseWind(rootNode));
        weather.setRain(parseRain(rootNode));
        weather.setSnow(parseSnow(rootNode));
        weather.setClouds(parseClouds(rootNode));
        weather.setLocation(parseLocation(rootNode));

        final JsonNode dtNode = rootNode.get("dt");
        if (dtNode != null) {
            weather.setRequestedOn(LocalDateTime.ofInstant(Instant.ofEpochSecond(dtNode.asInt()), TimeZone.getDefault().toZoneId()));
        }

        return weather;
    }

    @Override
    public List<Weather> getList(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Weather> weatherList = new ArrayList<>();
        try {
            final JsonNode root = objectMapper.readTree(json);
            final JsonNode listNode = root.get("list");
            listNode.forEach(jsonNode -> weatherList.add(getSingle(jsonNode)));
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Weather response");
        }

        return weatherList;
    }

    private Temperature parseTemperature(JsonNode rootNode) {
        Temperature temperature;
        final JsonNode mainNode = rootNode.get("main");

        final double tempValue = mainNode.get("temp").asDouble();
        temperature = Temperature.forValue(tempValue, unitSystem.getTemperatureUnit());

        final JsonNode feelsLikeNode = mainNode.get("feels_like");
        if (feelsLikeNode != null) {
            temperature.setFeelsLike(feelsLikeNode.asDouble());
        }
        final JsonNode tempMaxNode = mainNode.get("temp_max");
        if (tempMaxNode != null) {
            temperature.setMaxTemperature(tempMaxNode.asDouble());
        }
        final JsonNode tempMinNode = mainNode.get("temp_min");
        if (tempMinNode != null) {
            temperature.setMinTemperature(tempMinNode.asDouble());
        }

        return temperature;
    }

    private AtmosphericPressure parsePressure(JsonNode rootNode) {
        final JsonNode mainNode = rootNode.get("main");
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.forValue(mainNode.get("pressure").asDouble());

        final JsonNode seaLevelNode = mainNode.get("sea_level");
        final JsonNode groundLevelNode = mainNode.get("grnd_level");
        if (seaLevelNode != null) {
            atmosphericPressure.setSeaLevelValue(seaLevelNode.asDouble());
        }
        if (groundLevelNode != null) {
            atmosphericPressure.setGroundLevelValue(groundLevelNode.asDouble());
        }

        return atmosphericPressure;
    }

    private Humidity parseHumidity(JsonNode rootNode) {
        final JsonNode mainNode = rootNode.get("main");

        return Humidity.forValue((byte) (mainNode.get("humidity").asInt()));
    }

    private Wind parseWind(JsonNode rootNode) {
        final JsonNode windNode = rootNode.get("wind");
        double speed = windNode.get("speed").asDouble();

        Wind wind = Wind.forValue(speed, unitSystem.getWindUnit());

        final JsonNode degNode = windNode.get("deg");
        if (degNode != null) {
            wind.setDegrees(degNode.asDouble());
        }
        final JsonNode gustNode = windNode.get("gust");
        if (gustNode != null) {
            wind.setGust(gustNode.asDouble());
        }

        return wind;
    }

    private Rain parseRain(JsonNode rootNode) {
        Rain rain = null;

        final JsonNode rainNode = rootNode.get("rain");
        if (rainNode != null) {
            rain = new Rain();
            final JsonNode oneHourNode = rainNode.get("1h");
            final JsonNode threeHourNode = rainNode.get("3h");
            if (oneHourNode != null) {
                rain.setOneHourRainLevel(oneHourNode.asDouble());
            }
            if (threeHourNode != null) {
                rain.setThreeHourRainLevel(threeHourNode.asDouble());
            }
        }

        return rain;
    }

    private Snow parseSnow(JsonNode rootNode) {
        Snow snow = null;

        final JsonNode snowNode = rootNode.get("snow");

        if (snowNode != null) {
            snow = new Snow();
            final JsonNode oneHourNode = snowNode.get("1h");
            final JsonNode threeHourNode = snowNode.get("3h");
            if (oneHourNode != null) {
                snow.setOneHourSnowLevel(oneHourNode.asDouble());
            }
            if (threeHourNode != null) {
                snow.setThreeHourSnowLevel(threeHourNode.asDouble());
            }
        }

        return snow;
    }

    private Clouds parseClouds(JsonNode rootNode) {
        Clouds clouds = null;

        final JsonNode cloudsNode = rootNode.get("clouds");
        final JsonNode allValueNode = cloudsNode.get("all");
        if (allValueNode != null) {
            clouds = Clouds.forValue((byte) allValueNode.asInt());
        }

        return clouds;
    }

    private Location parseLocation(JsonNode rootNode) {
        Location location = Location.forValue(rootNode.get("id").asInt(), rootNode.get("name").asText());

        final JsonNode timezoneNode = rootNode.get("timezone");
        if (timezoneNode != null) {
            location.setZoneOffset(ZoneOffset.ofTotalSeconds(timezoneNode.asInt()));
        }

        final JsonNode sysNode = rootNode.get("sys");
        if (sysNode != null) {
            final JsonNode countryNode = sysNode.get("country");
            if (countryNode != null) {
                location.setCountryCode(countryNode.asText());
            }

            final JsonNode sunriseNode = sysNode.get("sunrise");
            final JsonNode sunsetNode = sysNode.get("sunset");
            if (sunriseNode != null) {
                location.setSunrise(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseNode.asInt()), TimeZone.getDefault().toZoneId()));
            }
            if (sunsetNode != null) {
                location.setSunset(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetNode.asInt()), TimeZone.getDefault().toZoneId()));
            }
        }

        final JsonNode coordNode = rootNode.get("coord");
        if (coordNode != null) {
            location.setCoordinate(parseCoordinate(coordNode));
        }

        return location;
    }

    private Coordinate parseCoordinate(JsonNode rootNode) {
        JsonNode latitudeNode = rootNode.get("lat");
        JsonNode longitudeNode = rootNode.get("lon");
        if (latitudeNode != null && longitudeNode != null) {
            return Coordinate.forValues(latitudeNode.asDouble(), longitudeNode.asDouble());
        }
        return null;
    }
}

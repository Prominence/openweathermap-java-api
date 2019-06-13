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

package com.github.prominence.openweathermap.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.ResponseMapper;
import com.github.prominence.openweathermap.api.enums.Unit;
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
 *      |- main.temp Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *      |- main.pressure Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
 *      |- main.humidity Humidity, %
 *      |- main.temp_min Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *      |- main.temp_max Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 *      |- main.sea_level Atmospheric pressure on the sea level, hPa
 *      |- main.grnd_level Atmospheric pressure on the ground level, hPa
 * --- wind
 *      |- wind.speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 *      |- wind.deg Wind direction, degrees (meteorological)
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
 * --- id City ID
 * --- name City name
 * --- cod Internal parameter
 */
public class CurrentWeatherResponseMapper implements ResponseMapper<Weather> {

    private Unit unit;

    CurrentWeatherResponseMapper(Unit unit) {
        this.unit = unit != null ? unit : Unit.STANDARD_SYSTEM;
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

    private Weather getSingle(JsonNode root) {
        final Weather weather = new Weather();

        JsonNode weatherState = root.get("weather").get(0);
        weather.setWeatherState(weatherState.get("main").asText());
        weather.setWeatherDescription(weatherState.get("description").asText());
        weather.setWeatherIconUrl("http://openweathermap.org/img/w/" + weatherState.get("icon").asText() + ".png");

        weather.setTemperature(parseTemperature(root));
        weather.setPressure(parsePressure(root));
        weather.setHumidity(parseHumidity(root));
        weather.setWind(parseWind(root));
        weather.setRain(parseRain(root));
        weather.setSnow(parseSnow(root));
        weather.setClouds(parseClouds(root));
        weather.setLocation(parseLocation(root));

        final JsonNode dtNode = root.get("dt");
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

    private Temperature parseTemperature(JsonNode root) {
        Temperature temperature = new Temperature();
        final JsonNode mainNode = root.get("main");

        temperature.setValue(mainNode.get("temp").asDouble());
        final JsonNode tempMaxNode = mainNode.get("temp_max");
        final JsonNode tempMinNode = mainNode.get("temp_min");
        if (tempMaxNode != null) {
            temperature.setMaxTemperature(tempMaxNode.asDouble());
        }
        if (tempMinNode != null) {
            temperature.setMinTemperature(tempMinNode.asDouble());
        }
        temperature.setUnit(Unit.getTemperatureUnit(unit));

        return temperature;
    }

    private Pressure parsePressure(JsonNode root) {
        Pressure pressure = new Pressure();

        final JsonNode mainNode = root.get("main");
        pressure.setValue(mainNode.get("pressure").asDouble());

        final JsonNode seaLevelNode = mainNode.get("sea_level");
        final JsonNode grndLevelNode = mainNode.get("grnd_level");
        if (seaLevelNode != null) {
            pressure.setSeaLevelValue(seaLevelNode.asDouble());
        }
        if (grndLevelNode != null) {
            pressure.setGroundLevelValue(grndLevelNode.asDouble());
        }

        return pressure;
    }

    private Humidity parseHumidity(JsonNode root) {
        Humidity humidity = new Humidity();

        final JsonNode mainNode = root.get("main");
        humidity.setValue(mainNode.get("humidity").asInt());

        return humidity;
    }

    private Wind parseWind(JsonNode root) {
        Wind wind = new Wind();
        final JsonNode windNode = root.get("wind");

        wind.setSpeed(windNode.get("speed").asDouble());
        final JsonNode degNode = windNode.get("deg");
        if (degNode != null) {
            wind.setDegrees(degNode.asDouble());
        }
        wind.setUnit(Unit.getWindUnit(unit));

        return wind;
    }

    private Rain parseRain(JsonNode root) {
        Rain rain = null;

        final JsonNode rainNode = root.get("rain");
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

    private Snow parseSnow(JsonNode root) {
        Snow snow = null;

        final JsonNode snowNode = root.get("snow");

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

    private Clouds parseClouds(JsonNode root) {
        Clouds clouds = new Clouds();

        final JsonNode cloudsNode = root.get("clouds");
        final JsonNode allValueNode = cloudsNode.get("all");
        if (allValueNode != null) {
            clouds.setValue((byte) allValueNode.asInt());
        }

        return clouds;
    }

    private Location parseLocation(JsonNode root) {
        Location location = new Location();

        location.setName(root.get("name").asText());
        location.setId(root.get("id").asInt());

        final JsonNode timezoneNode = root.get("timezone");
        if (timezoneNode != null) {
            location.setZoneOffset(ZoneOffset.ofTotalSeconds(timezoneNode.asInt()));
        }

        final JsonNode sysNode = root.get("sys");
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

        final JsonNode coordNode = root.get("coord");
        if (coordNode != null) {
            JsonNode latitudeNode = coordNode.get("lat");
            if (latitudeNode == null) {
                latitudeNode = coordNode.get("Lat"); // in multiple request
            }
            JsonNode longitudeNode = coordNode.get("lon");
            if (longitudeNode == null) {
                longitudeNode = coordNode.get("Lon"); // in multiple request
            }
            if (latitudeNode != null && longitudeNode != null) {
                location.setCoordinate(new Coordinate(latitudeNode.asDouble(), longitudeNode.asDouble()));
            }
        }

        return location;
    }
}

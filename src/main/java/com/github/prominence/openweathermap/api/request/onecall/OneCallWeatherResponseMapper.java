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

package com.github.prominence.openweathermap.api.request.onecall;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.onecall.*;
import com.github.prominence.openweathermap.api.model.onecall.current.*;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeather;
import com.github.prominence.openweathermap.api.model.onecall.historical.HourlyHistorical;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeatherData;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Object mapper for OneCall API response.
 */
public class OneCallWeatherResponseMapper {
    private final UnitSystem unitSystem;

    /**
     * Instantiates a new forecast response mapper.
     *
     * @param unitSystem the unit system
     */
    public OneCallWeatherResponseMapper(UnitSystem unitSystem) {
        this.unitSystem = unitSystem;
    }

    /**
     * Maps current weather data response into java object.
     *
     * @param json the json string
     * @return the current data object
     */
    public CurrentWeatherData mapToCurrent(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        CurrentWeatherData currentData;
        try {
            JsonNode root = objectMapper.readTree(json);
            currentData = mapToCurrent(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Forecast response");
        }

        return currentData;
    }

    /**
     * Maps current weather data response into java object.
     *
     * @param json the json string
     * @return the current data object
     */
    public HistoricalWeatherData mapToHistorical(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        HistoricalWeatherData historicalData;
        try {
            JsonNode root = objectMapper.readTree(json);
            historicalData = mapToHistorical(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse Forecast response");
        }

        return historicalData;
    }

    private CurrentWeatherData mapToCurrent(JsonNode rootNode) {
        CurrentWeatherData currentData = new CurrentWeatherData();
        currentData.setCoordinate(Coordinate.of(rootNode.get("lat").asDouble(), rootNode.get("lon").asDouble()));
        currentData.setTimezone(ZoneId.of(rootNode.get("timezone").asText()));
        currentData.setTimezoneOffset(ZoneOffset.ofTotalSeconds(rootNode.get("timezone_offset").asInt()));
        currentData.setCurrent(parseCurrent(rootNode.get("current")));
        currentData.setMinutelyList(parseMinutelyList(rootNode.get("minutely")));
        currentData.setHourlyList(parseHourlyList(rootNode.get("hourly")));
        currentData.setDailyList(parseDailyList(rootNode.get("daily")));
        currentData.setAlerts(parseAlerts(rootNode.get("alerts")));

        return currentData;
    }

    private Current parseCurrent(JsonNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        Current current = new Current();
        current.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(currentNode.get("dt").asInt()), TimeZone.getDefault().toZoneId()));
        current.setSunrise(LocalDateTime.ofInstant(Instant.ofEpochSecond(currentNode.get("sunrise").asInt()), TimeZone.getDefault().toZoneId()));
        current.setSunset(LocalDateTime.ofInstant(Instant.ofEpochSecond(currentNode.get("sunset").asInt()), TimeZone.getDefault().toZoneId()));
        current.setTemperature(parseTemperature(currentNode));
        current.setAtmosphericPressure(parsePressure(currentNode));
        current.setHumidity(parseHumidity(currentNode));
        current.setClouds(parseClouds(currentNode));
        current.setUvIndex(currentNode.get("uvi").asDouble());
        final JsonNode visibilityMode = currentNode.get("visibility");
        if (visibilityMode != null) {
            current.setVisibilityInMetres(visibilityMode.asDouble());
        }
        current.setWind(parseWind(currentNode));
        current.setRain(parseRain(currentNode));
        current.setSnow(parseSnow(currentNode));

        JsonNode weatherNode = currentNode.get("weather").get(0);
        current.setWeatherId(weatherNode.get("id").asInt());
        current.setState(weatherNode.get("main").asText());
        current.setDescription(weatherNode.get("description").asText());
        current.setIconId(weatherNode.get("icon").asText());

        return current;
    }

    private List<Minutely> parseMinutelyList(JsonNode minutelyListNode) {
        if (minutelyListNode == null) {
            return null;
        }
        List<Minutely> minutelyList = new ArrayList<>();
        for (final JsonNode minutelyNode : minutelyListNode) {
            minutelyList.add(Minutely.withValue(
                    LocalDateTime.ofInstant(Instant.ofEpochSecond(minutelyNode.get("dt").asInt()), TimeZone.getDefault().toZoneId()),
                    minutelyNode.get("precipitation").asDouble()
            ));
        }

        return minutelyList;
    }

    private List<Hourly> parseHourlyList(JsonNode hourlyListNode) {
        if (hourlyListNode == null) {
            return null;
        }
        List<Hourly> hourlyList = new ArrayList<>();
        for (final JsonNode hourlyNode : hourlyListNode) {
            final Hourly hourly = new Hourly();
            hourly.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(hourlyNode.get("dt").asInt()), TimeZone.getDefault().toZoneId()));
            hourly.setTemperature(parseTemperature(hourlyNode));
            hourly.setAtmosphericPressure(parsePressure(hourlyNode));
            hourly.setHumidity(parseHumidity(hourlyNode));
            hourly.setClouds(parseClouds(hourlyNode));

            final JsonNode uviNode = hourlyNode.get("uvi");
            if (uviNode != null) {
                hourly.setUvIndex(uviNode.asDouble());
            }

            final JsonNode visibilityNode = hourlyNode.get("visibility");
            if (visibilityNode != null) {
                hourly.setVisibilityInMetres(visibilityNode.asDouble());
            }
            hourly.setWind(parseWind(hourlyNode));
            final JsonNode popNode = hourlyNode.get("pop");
            if (popNode != null) {
                hourly.setProbabilityOfPrecipitation(popNode.asDouble());
            }
            hourly.setRain(parseRain(hourlyNode));
            hourly.setSnow(parseSnow(hourlyNode));

            JsonNode weatherNode = hourlyNode.get("weather").get(0);
            hourly.setWeatherId(weatherNode.get("id").asInt());
            hourly.setState(weatherNode.get("main").asText());
            hourly.setDescription(weatherNode.get("description").asText());
            hourly.setIconId(weatherNode.get("icon").asText());

            hourlyList.add(hourly);
        }

        return hourlyList;
    }

    private List<Daily> parseDailyList(JsonNode dailyListNode) {
        if (dailyListNode == null) {
            return null;
        }
        List<Daily> dailyList = new ArrayList<>();
        for (final JsonNode dailyNode : dailyListNode) {
            final Daily daily = new Daily();
            daily.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(dailyNode.get("dt").asInt()), TimeZone.getDefault().toZoneId()));
            daily.setSunrise(LocalDateTime.ofInstant(Instant.ofEpochSecond(dailyNode.get("sunrise").asInt()), TimeZone.getDefault().toZoneId()));
            daily.setSunset(LocalDateTime.ofInstant(Instant.ofEpochSecond(dailyNode.get("sunset").asInt()), TimeZone.getDefault().toZoneId()));

            daily.setTemperature(parseDailyTemperature(dailyNode));
            daily.setAtmosphericPressure(parsePressure(dailyNode));
            daily.setHumidity(parseHumidity(dailyNode));
            daily.setWind(parseWind(dailyNode));
            daily.setClouds(parseClouds(dailyNode));
            daily.setUvIndex(dailyNode.get("uvi").asDouble());
            daily.setProbabilityOfPrecipitation(dailyNode.get("pop").asDouble());
            daily.setRain(parseDailyRain(dailyNode));
            daily.setSnow(parseDailySnow(dailyNode));

            JsonNode weatherNode = dailyNode.get("weather").get(0);
            daily.setWeatherId(weatherNode.get("id").asInt());
            daily.setState(weatherNode.get("main").asText());
            daily.setDescription(weatherNode.get("description").asText());
            daily.setIconId(weatherNode.get("icon").asText());


            dailyList.add(daily);
        }

        return dailyList;
    }

    private List<Alert> parseAlerts(JsonNode alertsNode) {
        if (alertsNode == null) {
            return null;
        }
        List<Alert> alerts = new ArrayList<>();
        for (final JsonNode alertNode : alertsNode) {
            Alert alert = new Alert();
            alert.setSenderName(alertNode.get("sender_name").asText());
            alert.setEventName(alertNode.get("event").asText());
            alert.setStartDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(alertNode.get("start").asInt()), TimeZone.getDefault().toZoneId()));
            alert.setEndDateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(alertNode.get("end").asInt()), TimeZone.getDefault().toZoneId()));
            alert.setDescription(alertNode.get("description").asText());
            alerts.add(alert);
        }
        return alerts;
    }

    private HistoricalWeatherData mapToHistorical(JsonNode rootNode) {
        HistoricalWeatherData historicalData = new HistoricalWeatherData();
        historicalData.setCoordinate(Coordinate.of(rootNode.get("lat").asDouble(), rootNode.get("lon").asDouble()));
        historicalData.setTimezone(ZoneId.of(rootNode.get("timezone").asText()));
        historicalData.setTimezoneOffset(ZoneOffset.ofTotalSeconds(rootNode.get("timezone_offset").asInt()));
        historicalData.setHistoricalWeather(parseHistoricalWeather(rootNode.get("current")));
        historicalData.setHourlyList(parseHourlyHistoricalList(rootNode.get("hourly")));

        return historicalData;
    }

    private HistoricalWeather parseHistoricalWeather(JsonNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        HistoricalWeather historicalWeather = new HistoricalWeather();
        historicalWeather.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(currentNode.get("dt").asInt()), TimeZone.getDefault().toZoneId()));
        historicalWeather.setSunrise(LocalDateTime.ofInstant(Instant.ofEpochSecond(currentNode.get("sunrise").asInt()), TimeZone.getDefault().toZoneId()));
        historicalWeather.setSunset(LocalDateTime.ofInstant(Instant.ofEpochSecond(currentNode.get("sunset").asInt()), TimeZone.getDefault().toZoneId()));
        historicalWeather.setTemperature(parseTemperature(currentNode));
        historicalWeather.setAtmosphericPressure(parsePressure(currentNode));
        historicalWeather.setHumidity(parseHumidity(currentNode));
        historicalWeather.setClouds(parseClouds(currentNode));
        historicalWeather.setUvIndex(currentNode.get("uvi").asDouble());
        final JsonNode visibilityMode = currentNode.get("visibility");
        if (visibilityMode != null) {
            historicalWeather.setVisibilityInMetres(visibilityMode.asDouble());
        }
        historicalWeather.setWind(parseWind(currentNode));
        historicalWeather.setRain(parseRain(currentNode));
        historicalWeather.setSnow(parseSnow(currentNode));

        JsonNode weatherNode = currentNode.get("weather").get(0);
        historicalWeather.setWeatherId(weatherNode.get("id").asInt());
        historicalWeather.setState(weatherNode.get("main").asText());
        historicalWeather.setDescription(weatherNode.get("description").asText());
        historicalWeather.setIconId(weatherNode.get("icon").asText());

        return historicalWeather;
    }

    private List<HourlyHistorical> parseHourlyHistoricalList(JsonNode hourlyListNode) {
        if (hourlyListNode == null) {
            return null;
        }
        List<HourlyHistorical> hourlyList = new ArrayList<>();
        for (final JsonNode hourlyNode : hourlyListNode) {
            final HourlyHistorical hourly = new HourlyHistorical();
            hourly.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(hourlyNode.get("dt").asInt()), TimeZone.getDefault().toZoneId()));
            hourly.setTemperature(parseTemperature(hourlyNode));
            hourly.setAtmosphericPressure(parsePressure(hourlyNode));
            hourly.setHumidity(parseHumidity(hourlyNode));
            hourly.setClouds(parseClouds(hourlyNode));

            final JsonNode visibilityNode = hourlyNode.get("visibility");
            if (visibilityNode != null) {
                hourly.setVisibilityInMetres(visibilityNode.asDouble());
            }
            hourly.setWind(parseWind(hourlyNode));
            hourly.setRain(parseRain(hourlyNode));
            hourly.setSnow(parseSnow(hourlyNode));

            JsonNode weatherNode = hourlyNode.get("weather").get(0);
            hourly.setWeatherId(weatherNode.get("id").asInt());
            hourly.setState(weatherNode.get("main").asText());
            hourly.setDescription(weatherNode.get("description").asText());
            hourly.setIconId(weatherNode.get("icon").asText());

            hourlyList.add(hourly);
        }

        return hourlyList;
    }

    private Temperature parseTemperature(JsonNode rootNode) {
        final double tempValue = rootNode.get("temp").asDouble();
        final Temperature temperature = Temperature.withValue(tempValue, unitSystem.getTemperatureUnit());

        final JsonNode tempFeelsLike = rootNode.get("fells_like");
        if (tempFeelsLike != null) {
            temperature.setFeelsLike(tempFeelsLike.asDouble());
        }
        final JsonNode dewPoint = rootNode.get("dew_point");
        if (dewPoint != null) {
            temperature.setDewPoint(dewPoint.asDouble());
        }

        return temperature;
    }

    private DailyTemperature parseDailyTemperature(JsonNode dailyNode) {
        if (dailyNode == null) {
            return null;
        }
        final DailyTemperature temperature = new DailyTemperature();
        final JsonNode tempNode = dailyNode.get("temp");
        temperature.setMorning(tempNode.get("morn").asDouble());
        temperature.setDay(tempNode.get("day").asDouble());
        temperature.setEve(tempNode.get("eve").asDouble());
        temperature.setNight(tempNode.get("night").asDouble());
        temperature.setMin(tempNode.get("min").asDouble());
        temperature.setMax(tempNode.get("max").asDouble());

        final JsonNode feelsLikeNode = dailyNode.get("feels_like");
        temperature.setMorningFeelsLike(feelsLikeNode.get("morn").asDouble());
        temperature.setDayFeelsLike(feelsLikeNode.get("day").asDouble());
        temperature.setEveFeelsLike(feelsLikeNode.get("eve").asDouble());
        temperature.setNightFeelsLike(feelsLikeNode.get("night").asDouble());

        return temperature;
    }

    private AtmosphericPressure parsePressure(JsonNode rootNode) {
        AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(rootNode.get("pressure").asDouble());

        final JsonNode seaLevelNode = rootNode.get("pressure");
        if (seaLevelNode != null) {
            atmosphericPressure.setSeaLevelValue(seaLevelNode.asDouble());
        }

        return atmosphericPressure;
    }

    private Humidity parseHumidity(JsonNode rootNode) {
        return Humidity.withValue((byte) (rootNode.get("humidity").asInt()));
    }

    private Wind parseWind(JsonNode rootNode) {
        final JsonNode windSpeedNode = rootNode.get("wind_speed");
        if (windSpeedNode == null) {
            return null;
        }

        Wind wind = Wind.withValue(windSpeedNode.asDouble(), unitSystem.getWindUnit());

        final JsonNode degNode = rootNode.get("wind_deg");
        if (degNode != null) {
            wind.setDegrees(degNode.asDouble());
        }
        final JsonNode gustNode = rootNode.get("wind_gust");
        if (gustNode != null) {
            wind.setGust(gustNode.asDouble());
        }

        return wind;
    }

    private Rain parseRain(JsonNode root) {
        final JsonNode rainNode = root.get("rain");
        if (rainNode != null) {
            final JsonNode threeHourNode = rainNode.get("1h");
            if (threeHourNode != null) {
                return Rain.withOneHourLevelValue(threeHourNode.asDouble());
            }
        }
        return null;
    }

    private DailyRain parseDailyRain(JsonNode dailyNode) {
        final JsonNode valueNode = dailyNode.get("rain");
        if (valueNode != null) {
            return DailyRain.withValue(valueNode.asDouble());
        }
        return null;
    }

    private Snow parseSnow(JsonNode root) {
        final JsonNode snowNode = root.get("snow");
        if (snowNode != null) {
            final JsonNode threeHourNode = snowNode.get("1h");
            if (threeHourNode != null) {
                Rain.withOneHourLevelValue(threeHourNode.asDouble());
            }
        }
        return null;
    }

    private DailySnow parseDailySnow(JsonNode dailyNode) {
        final JsonNode valueNode = dailyNode.get("snow");
        if (valueNode != null) {
            return DailySnow.withValue(valueNode.asDouble());
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
}

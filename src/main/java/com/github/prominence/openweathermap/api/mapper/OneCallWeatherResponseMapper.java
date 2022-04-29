/*
 * Copyright (c) 2022 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.mapper;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.*;
import com.github.prominence.openweathermap.api.deserializer.onecall.*;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.onecall.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.onecall.Temperature;
import com.github.prominence.openweathermap.api.model.onecall.*;
import com.github.prominence.openweathermap.api.model.onecall.current.*;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeather;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeatherData;
import com.github.prominence.openweathermap.api.model.onecall.historical.HourlyHistorical;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Official API response documentation:
 * <ul>
 *  <li></li><a href="https://openweathermap.org/api/one-call-api#parameter">https://openweathermap.org/api/one-call-api#parameter</a></li>
 *  <li></li><a href="https://openweathermap.org/api/one-call-api#hist_parameter">https://openweathermap.org/api/one-call-api#hist_parameter</a></li>
 * </ul>
 */
public class OneCallWeatherResponseMapper extends AbstractMapper {
    /**
     * Instantiates a new forecast response mapper.
     *
     * @param unitSystem the unit system
     */
    public OneCallWeatherResponseMapper(UnitSystem unitSystem) {
        objectMapper.setInjectableValues(new InjectableValues.Std().addValue("unitSystem", unitSystem != null ? unitSystem : UnitSystem.STANDARD));
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Coordinates.class, new CoordinatesDeserializer());
        module.addDeserializer(AtmosphericPressure.class, new OneCallAtmosphericPressureDeserializer());
        module.addDeserializer(Temperature.class, new OneCallTemperatureDeserializer());
        module.addDeserializer(WeatherState.class, new WeatherStateDeserializer());
        module.addDeserializer(Humidity.class, new HumidityDeserializer());
        module.addDeserializer(Wind.class, new WindDeserializer());
        module.addDeserializer(Clouds.class, new CloudsDeserializer());
        module.addDeserializer(Rain.class, new OneCallRainDeserializer());
        module.addDeserializer(Snow.class, new OneCallSnowDeserializer());
        module.addDeserializer(DailyTemperature.class, new OneCallDailyTemperatureDeserializer());
        module.addDeserializer(DailyRain.class, new OneCallDailyRainDeserializer());
        module.addDeserializer(DailySnow.class, new OneCallDailySnowDeserializer());
        module.addDeserializer(Alert.class, new AlertDeserializer());
        objectMapper.registerModule(module);
    }

    /**
     * Maps current weather data response into java object.
     *
     * @param json the json string
     * @return the current data object
     */
    public CurrentWeatherData mapToCurrent(String json) {
        CurrentWeatherData currentData;
        try {
            final JsonNode root = objectMapper.readTree(json);
            currentData = mapToCurrent(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse OneCall response", e);
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
        HistoricalWeatherData historicalData;
        try {
            final JsonNode root = objectMapper.readTree(json);
            historicalData = mapToHistorical(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse OneCall response");
        }

        return historicalData;
    }

    private CurrentWeatherData mapToCurrent(JsonNode rootNode) throws IOException {
        final CurrentWeatherData currentData = new CurrentWeatherData();
        currentData.setCoordinate(objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Coordinates.class));
        currentData.setTimezone(parseZoneId(rootNode.get("timezone")));
        currentData.setTimezoneOffset(parseZoneOffset(rootNode.get("timezone_offset")));

        currentData.setCurrent(parseCurrent(rootNode.get("current")));
        currentData.setMinutelyList(parseMinutelyList(rootNode.get("minutely")));
        currentData.setHourlyList(parseHourlyList(rootNode.get("hourly")));
        currentData.setDailyList(parseDailyList(rootNode.get("daily")));
        currentData.setAlerts(parseAlerts(rootNode.get("alerts")));

        return currentData;
    }

    private Current parseCurrent(JsonNode currentNode) throws IOException {
        if (currentNode == null) {
            return null;
        }
        final Current current = new Current();
        current.setForecastTime(parseDateTime(currentNode.get("dt")));
        current.setSunriseTime(parseDateTime(currentNode.get("sunrise")));
        current.setSunsetTime(parseDateTime(currentNode.get("sunset")));

        current.setWeatherStates(parseWeatherStates(currentNode.get("weather")));

        current.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Temperature.class));
        current.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), AtmosphericPressure.class));
        current.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Humidity.class));
        current.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Clouds.class));

        current.setUvIndex(currentNode.get("uvi").asDouble());
        final JsonNode visibilityNode = currentNode.get("visibility");
        if (visibilityNode != null) {
            current.setVisibilityInMetres(visibilityNode.asDouble());
        }

        current.setWind(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Wind.class));
        current.setRain(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Rain.class));
        current.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Snow.class));

        return current;
    }

    private List<Minutely> parseMinutelyList(JsonNode minutelyListNode) {
        if (minutelyListNode == null) {
            return null;
        }
        final List<Minutely> minutelyList = new ArrayList<>();
        for (final JsonNode minutelyNode : minutelyListNode) {
            minutelyList.add(Minutely.withValue(
                    parseDateTime(minutelyNode.get("dt")),
                    minutelyNode.get("precipitation").asDouble()
            ));
        }

        return minutelyList;
    }

    private List<Hourly> parseHourlyList(JsonNode hourlyListNode) throws IOException {
        if (hourlyListNode == null) {
            return null;
        }
        final List<Hourly> hourlyList = new ArrayList<>();
        for (final JsonNode hourlyNode : hourlyListNode) {
            final Hourly hourly = new Hourly();
            hourly.setForecastTime(parseDateTime(hourlyNode.get("dt")));

            hourly.setWeatherStates(parseWeatherStates(hourlyNode.get("weather")));

            hourly.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Temperature.class));
            hourly.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), AtmosphericPressure.class));
            hourly.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Humidity.class));
            hourly.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Clouds.class));

            final JsonNode uviNode = hourlyNode.get("uvi");
            if (uviNode != null) {
                hourly.setUvIndex(uviNode.asDouble());
            }

            final JsonNode visibilityNode = hourlyNode.get("visibility");
            if (visibilityNode != null) {
                hourly.setVisibilityInMetres(visibilityNode.asDouble());
            }
            hourly.setWind(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Wind.class));
            final JsonNode popNode = hourlyNode.get("pop");
            if (popNode != null) {
                hourly.setProbabilityOfPrecipitation(popNode.asDouble());
            }
            hourly.setRain(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Rain.class));
            hourly.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Snow.class));

            hourlyList.add(hourly);
        }

        return hourlyList;
    }

    private List<Daily> parseDailyList(JsonNode dailyListNode) throws IOException {
        if (dailyListNode == null) {
            return null;
        }
        final List<Daily> dailyList = new ArrayList<>();
        for (final JsonNode dailyNode : dailyListNode) {
            final Daily daily = new Daily();
            daily.setForecastTime(parseDateTime(dailyNode.get("dt")));
            daily.setSunriseTime(parseDateTime(dailyNode.get("sunrise")));
            daily.setSunsetTime(parseDateTime(dailyNode.get("sunset")));
            final JsonNode moonriseTimeNode = dailyNode.get("moonrise");
            if (moonriseTimeNode != null) {
                daily.setMoonriseTime(parseDateTime(moonriseTimeNode));
            }
            final JsonNode moonsetTimeNode = dailyNode.get("moonset");
            if (moonsetTimeNode != null) {
                daily.setMoonsetTime(parseDateTime(moonsetTimeNode));
            }
            final JsonNode moonPhaseNode = dailyNode.get("moon_phase");
            if (moonPhaseNode != null) {
                daily.setMoonPhase(new MoonPhase(moonPhaseNode.asDouble()));
            }

            daily.setWeatherStates(parseWeatherStates(dailyNode.get("weather")));

            daily.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(dailyNode), DailyTemperature.class));
            daily.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(dailyNode), AtmosphericPressure.class));
            daily.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(dailyNode), Humidity.class));
            daily.setWind(objectMapper.readValue(objectMapper.treeAsTokens(dailyNode), Wind.class));
            daily.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(dailyNode), Clouds.class));
            daily.setUvIndex(dailyNode.get("uvi").asDouble());
            daily.setProbabilityOfPrecipitation(dailyNode.get("pop").asDouble());
            daily.setRain(objectMapper.readValue(objectMapper.treeAsTokens(dailyNode), DailyRain.class));
            daily.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(dailyNode), DailySnow.class));

            dailyList.add(daily);
        }

        return dailyList;
    }

    private List<Alert> parseAlerts(JsonNode alertsNode) throws IOException {
        if (alertsNode == null || !alertsNode.isArray()) {
            return null;
        }
        final List<Alert> alerts = new ArrayList<>();
        for (final JsonNode alertNode : alertsNode) {
            alerts.add(objectMapper.readValue(objectMapper.treeAsTokens(alertNode), Alert.class));
        }
        return alerts;
    }

    private HistoricalWeatherData mapToHistorical(JsonNode rootNode) throws IOException {
        final HistoricalWeatherData historicalData = new HistoricalWeatherData();
        historicalData.setCoordinate(Coordinates.of(rootNode.get("lat").asDouble(), rootNode.get("lon").asDouble()));
        historicalData.setTimezone(parseZoneId(rootNode.get("timezone")));
        historicalData.setTimezoneOffset(parseZoneOffset(rootNode.get("timezone_offset")));
        historicalData.setHistoricalWeather(parseHistoricalWeather(rootNode.get("current")));
        historicalData.setHourlyList(parseHourlyHistoricalList(rootNode.get("hourly")));

        return historicalData;
    }

    private HistoricalWeather parseHistoricalWeather(JsonNode currentNode) throws IOException {
        if (currentNode == null) {
            return null;
        }
        final HistoricalWeather historicalWeather = new HistoricalWeather();
        historicalWeather.setForecastTime(parseDateTime(currentNode.get("dt")));
        historicalWeather.setSunriseTime(parseDateTime(currentNode.get("sunrise")));
        historicalWeather.setSunsetTime(parseDateTime(currentNode.get("sunset")));

        historicalWeather.setWeatherStates(parseWeatherStates(currentNode.get("weather")));

        historicalWeather.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Temperature.class));
        historicalWeather.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), AtmosphericPressure.class));
        historicalWeather.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Humidity.class));
        historicalWeather.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Clouds.class));

        final JsonNode uviNode = currentNode.get("uvi");
        if (uviNode != null) {
            historicalWeather.setUvIndex(uviNode.asDouble());
        }

        final JsonNode visibilityNode = currentNode.get("visibility");
        if (visibilityNode != null) {
            historicalWeather.setVisibilityInMetres(visibilityNode.asDouble());
        }
        historicalWeather.setWind(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Wind.class));
        historicalWeather.setRain(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Rain.class));
        historicalWeather.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(currentNode), Snow.class));

        return historicalWeather;
    }

    private List<HourlyHistorical> parseHourlyHistoricalList(JsonNode hourlyListNode) throws IOException {
        if (hourlyListNode == null) {
            return null;
        }
        final List<HourlyHistorical> hourlyList = new ArrayList<>();
        for (final JsonNode hourlyNode : hourlyListNode) {
            final HourlyHistorical hourly = new HourlyHistorical();
            hourly.setForecastTime(parseDateTime(hourlyNode.get("dt")));

            hourly.setWeatherStates(parseWeatherStates(hourlyNode.get("weather")));

            hourly.setTemperature(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Temperature.class));
            hourly.setAtmosphericPressure(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), AtmosphericPressure.class));
            hourly.setHumidity(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Humidity.class));
            hourly.setClouds(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Clouds.class));

            final JsonNode visibilityNode = hourlyNode.get("visibility");
            if (visibilityNode != null) {
                hourly.setVisibilityInMetres(visibilityNode.asDouble());
            }
            hourly.setWind(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Wind.class));
            hourly.setRain(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Rain.class));
            hourly.setSnow(objectMapper.readValue(objectMapper.treeAsTokens(hourlyNode), Snow.class));

            hourlyList.add(hourly);
        }

        return hourlyList;
    }
}

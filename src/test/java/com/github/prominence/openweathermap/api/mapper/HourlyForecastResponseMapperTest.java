/*
 * Copyright (c) 2021-present Alexey Zinchenko
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.enums.DayTime;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.BasePrecipitation;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Location;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.forecast.hourly.HourlyForecast;
import com.github.prominence.openweathermap.api.model.forecast.hourly.HourlyForecastModel;
import com.github.prominence.openweathermap.api.model.forecast.hourly.Weather;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HourlyForecastResponseMapperTest {

    @Test
    void forecastMappingTestWithOfficialExample() throws JsonProcessingException {
        final String jsonResponse = """
                {
                  "cod": "200",
                  "message": 0.0179,
                  "cnt": 96,
                  "list": [
                    {
                      "dt": 1596632400,
                      "main": {
                        "temp": 289.16,
                        "feels_like": 288.41,
                        "temp_min": 289.16,
                        "temp_max": 289.16,
                        "pressure": 1013,
                        "sea_level": 1013,
                        "grnd_level": 1010,
                        "humidity": 78,
                        "temp_kf": 0
                      },
                      "weather": [
                        {
                          "id": 804,
                          "main": "Clouds",
                          "description": "overcast clouds",
                          "icon": "04n"
                        }
                      ],
                      "clouds": {
                        "all": 100
                      },
                      "wind": {
                        "speed": 2.03,
                        "deg": 252,
                        "gust": 5.46
                      },
                      "rain": {
                        "1h": 23.3
                      },
                      "snow": {
                        "1h": 27.945
                      },
                      "visibility": 10000,
                      "pop": 0.04,
                      "sys": {
                        "pod": "n"
                      },
                      "dt_txt": "2020-08-05 13:00:00"
                    }
                  ],
                  "city": {
                    "id": 2643743,
                    "name": "London",
                    "coord": {
                      "lat": 51.5085,
                      "lon": -0.1258
                    },
                    "country": "GB",
                    "timezone": 0,
                    "sunrise": 1568958164,
                    "sunset": 1569002733
                  }
                }
                """;

        final HourlyForecast hourlyForecast = new ObjectMapper().readValue(jsonResponse, HourlyForecastModel.class);
        assertNotNull(hourlyForecast);

        final Location location = hourlyForecast.getLocation();
        assertEquals(2643743, location.getCityId());
        assertEquals("London", location.getCityName());
        assertEquals(new Coordinates(51.5085, -0.1258), location.getCoordinates());
        assertEquals("GB", location.getCountryCode());
        assertEquals(ZoneOffset.ofTotalSeconds(0), location.getTimeZone());
        assertEquals(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1568958164), ZoneOffset.UTC), location.getSunriseTime());
        assertEquals(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1569002733), ZoneOffset.UTC), location.getSunsetTime());

        final Weather weatherForecast = hourlyForecast.getWeatherForecasts().get(0);
        assertEquals(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1596632400), ZoneOffset.UTC), weatherForecast.getForecastTime());
        assertEquals(DayTime.NIGHT, weatherForecast.getPartOfDay());
        assertEquals(new BigDecimal("10000.00"), weatherForecast.getVisibility().asMeters());
        assertEquals(new BigDecimal("0.04"), weatherForecast.getProbabilityOfPrecipitation());

        final Temperature temperature = weatherForecast.getTemperature();
        assertEquals(new BigDecimal("289"), temperature.getTemperature().asKelvin());
        assertEquals(new BigDecimal("288"), temperature.getFeelsLike().asKelvin());
        assertEquals(new BigDecimal("289"), temperature.getMin().asKelvin());
        assertEquals(new BigDecimal("289"), temperature.getMax().asKelvin());

        final AtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(new BigDecimal("1013"), pressure.getPressure());
        assertEquals(new BigDecimal("1013"), pressure.getSeaLevel());
        assertEquals(new BigDecimal("1010"), pressure.getGroundLevel());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(78, humidity.getHumidityPercentage());

        final WeatherCondition weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(804, weatherState.getId());
        assertEquals("Clouds", weatherState.getName());
        assertEquals("overcast clouds: 85-100%", weatherState.getDescription());
        assertEquals("04n", weatherState.getIconId(DayTime.NIGHT));

        final Clouds clouds = weatherForecast.getClouds();
        assertEquals(100, clouds.getValuePercentage());

        final Wind wind = weatherForecast.getWind();
        assertEquals(new BigDecimal("2.03"), wind.getSpeed().asMetersPerSecond());
        assertEquals(252, wind.getDirectionDegrees());
        assertEquals(new BigDecimal("5.46"), wind.getGust().asMetersPerSecond());

        final BasePrecipitation rain = weatherForecast.getRain();
        assertEquals(new BigDecimal("23.3"), rain.getOneHourLevel());

        final BasePrecipitation snow = weatherForecast.getSnow();
        assertEquals(new BigDecimal("27.945"), snow.getOneHourLevel());
    }
}

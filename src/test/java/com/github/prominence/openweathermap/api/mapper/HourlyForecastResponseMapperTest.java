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

import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.hourly.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HourlyForecastResponseMapperTest {

    @Test
    void forecastMappingTestWithOfficialExample() {
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

        final HourlyForecast hourlyForecast = new HourlyForecastResponseMapper(UnitSystem.METRIC).mapToForecast(jsonResponse);
        assertNotNull(hourlyForecast);

        final Location location = hourlyForecast.getLocation();
        assertEquals(2643743, location.getId());
        assertEquals("London", location.getName());
        assertEquals(Coordinates.of(51.5085, -0.1258), location.getCoordinate());
        assertEquals("GB", location.getCountryCode());
        assertEquals(ZoneOffset.ofTotalSeconds(0), location.getZoneOffset());
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochSecond(1568958164), TimeZone.getDefault().toZoneId()), location.getSunriseTime());
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochSecond(1569002733), TimeZone.getDefault().toZoneId()), location.getSunsetTime());

        final WeatherForecast weatherForecast = hourlyForecast.getWeatherForecasts().get(0);
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochSecond(1596632400), TimeZone.getDefault().toZoneId()), weatherForecast.getForecastTime());
        assertEquals("2020-08-05 13:00:00", weatherForecast.getForecastTimeISO());
        assertEquals(DayTime.NIGHT, weatherForecast.getDayTime());
        assertEquals(10000, weatherForecast.getAverageVisibilityInMetres());
        assertEquals(0.04, weatherForecast.getProbabilityOfPrecipitation());

        final Temperature temperature = weatherForecast.getTemperature();
        assertEquals(289.16, temperature.getValue());
        assertEquals(288.41, temperature.getFeelsLike());
        assertEquals(289.16, temperature.getMinTemperature());
        assertEquals(289.16, temperature.getMaxTemperature());

        final AtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(1013, pressure.getValue());
        assertEquals(1013, pressure.getSeaLevelValue());
        assertEquals(1010, pressure.getGroundLevelValue());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(78, humidity.getValue());

        final WeatherState weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(804, weatherState.getId());
        assertEquals("Clouds", weatherState.getName());
        assertEquals("overcast clouds", weatherState.getDescription());
        assertEquals("04n", weatherState.getIconId());

        final Clouds clouds = weatherForecast.getClouds();
        assertEquals(100, clouds.getValue());

        final Wind wind = weatherForecast.getWind();
        assertEquals(2.03, wind.getSpeed());
        assertEquals(252, wind.getDegrees());
        assertEquals(5.46, wind.getGust());

        final Rain rain = weatherForecast.getRain();
        assertEquals(23.3, rain.getOneHourLevel());

        final Snow snow = weatherForecast.getSnow();
        assertEquals(27.945, snow.getOneHourLevel());
    }
}
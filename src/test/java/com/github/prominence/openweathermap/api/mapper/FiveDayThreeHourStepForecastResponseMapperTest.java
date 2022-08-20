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
import com.github.prominence.openweathermap.api.context.TestMappingUtils;
import com.github.prominence.openweathermap.api.enums.DayTime;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.LocationExtended;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.forecast.free.FiveDaysThreeHoursForecast;
import com.github.prominence.openweathermap.api.model.forecast.free.FiveDaysThreeHoursForecastModel;
import com.github.prominence.openweathermap.api.model.forecast.free.Precipitation;
import com.github.prominence.openweathermap.api.model.forecast.free.Weather;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FiveDayThreeHourStepForecastResponseMapperTest {

    @Test
    void mapToForecast() throws JsonProcessingException {
        final String jsonResponse = """
                {
                  "cod": "200",
                  "message": 0,
                  "cnt": 40,
                  "list": [
                    {
                      "dt": 1647345600,
                      "main": {
                        "temp": 286.88,
                        "feels_like": 285.93,
                        "temp_min": 286.74,
                        "temp_max": 286.88,
                        "pressure": 1021,
                        "sea_level": 1021,
                        "grnd_level": 1018,
                        "humidity": 62,
                        "temp_kf": 0.14
                      },
                      "weather": [
                        {
                          "id": 804,
                          "main": "Clouds",
                          "description": "overcast clouds",
                          "icon": "04d"
                        }
                      ],
                      "clouds": {
                        "all": 85
                      },
                      "wind": {
                        "speed": 3.25,
                        "deg": 134,
                        "gust": 4.45
                      },
                      "visibility": 10000,
                      "pop": 0,
                      "sys": {
                        "pod": "d"
                      },
                      "rain": {
                        "3h": 22.1
                      },
                      "snow": {
                        "3h": 13.6
                      },
                      "dt_txt": "2022-03-15 12:00:00"
                    },
                    {
                      "dt": 1647356400,
                      "main": {
                        "temp": 286.71,
                        "feels_like": 285.77,
                        "temp_min": 286.38,
                        "temp_max": 286.71,
                        "pressure": 1021,
                        "sea_level": 1021,
                        "grnd_level": 1017,
                        "humidity": 63,
                        "temp_kf": 0.33
                      },
                      "weather": [
                        {
                          "id": 804,
                          "main": "Clouds",
                          "description": "overcast clouds",
                          "icon": "04d"
                        }
                      ],
                      "clouds": {
                        "all": 90
                      },
                      "wind": {
                        "speed": 3.34,
                        "deg": 172,
                        "gust": 4.03
                      },
                      "visibility": 10000,
                      "pop": 0,
                      "sys": {
                        "pod": "d"
                      },
                      "dt_txt": "2022-03-15 15:00:00"
                    }
                  ],
                  "city": {
                    "id": 2643743,
                    "name": "London",
                    "coord": {
                      "lat": 51.5073,
                      "lon": -0.1277
                    },
                    "country": "GB",
                    "population": 1000000,
                    "timezone": 0,
                    "sunrise": 1647324903,
                    "sunset": 1647367441
                  }
                }
                """;

        final FiveDaysThreeHoursForecast forecast = new ObjectMapper().readValue(jsonResponse, FiveDaysThreeHoursForecastModel.class);
        assertNotNull(forecast);

        final LocationExtended location = forecast.getLocation();
        assertNotNull(location);
        assertEquals(new Coordinates(51.5073, -0.1277), location.getCoordinates());
        assertEquals(2643743, location.getCityId());
        assertEquals("London", location.getCityName());
        assertEquals("GB", location.getCountryCode());
        assertEquals(1000000, location.getPopulation());
        assertEquals(TestMappingUtils.parseDateTime(1647324903), location.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1647367441), location.getSunsetTime());
        assertEquals(ZoneOffset.ofTotalSeconds(0), location.getTimeZone());


        final List<Weather> weatherForecastList = forecast.getWeatherForecasts();
        assertEquals(2, weatherForecastList.size());

        final Weather weatherForecast = weatherForecastList.get(0);
        assertNotNull(weatherForecast);

        assertEquals(TestMappingUtils.parseDateTime(1647345600), weatherForecast.getForecastTime());
        assertEquals(new BigDecimal("10000.00"), weatherForecast.getVisibility().asMeters());
        assertEquals(new BigDecimal(0), weatherForecast.getProbabilityOfPrecipitation());
        assertEquals(OffsetDateTime.of(2022, 3, 15, 12, 0, 0, 0, ZoneOffset.UTC),
                weatherForecast.getForecastTime());
        assertEquals(DayTime.DAY, weatherForecast.getPartOfDay());

        final Temperature temperature = weatherForecast.getTemperature();
        assertNotNull(temperature);
        assertEquals(new BigDecimal("286.88"), temperature.getTemperature().asKelvin());
        assertEquals(new BigDecimal("285.93"), temperature.getFeelsLike().asKelvin());
        assertEquals(new BigDecimal("286.74"), temperature.getMin().asKelvin());
        assertEquals(new BigDecimal("286.88"), temperature.getMax().asKelvin());

        final AtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(new BigDecimal("1021"), pressure.getPressure());
        assertEquals(new BigDecimal("1021"), pressure.getSeaLevel());
        assertEquals(new BigDecimal("1018"), pressure.getGroundLevel());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(62, humidity.getHumidityPercentage());

        final Clouds clouds = weatherForecast.getClouds();
        assertEquals(85, clouds.getValuePercentage());

        final Wind wind = weatherForecast.getWind();
        assertEquals(new BigDecimal("3.25"), wind.getSpeed().asMetersPerSecond());
        assertEquals(134, wind.getDirectionDegrees());
        assertEquals(new BigDecimal("4.45"), wind.getGust().asMetersPerSecond());

        assertEquals(1, weatherForecast.getWeatherStates().size());

        final WeatherCondition weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(804, weatherState.getId());
        assertEquals("Clouds", weatherState.getName());
        assertEquals("overcast clouds", weatherState.getDescription());
        assertEquals("04d", weatherState.getIconId(weatherForecast.getPartOfDay()));

        final Precipitation rain = weatherForecast.getRain();
        assertEquals(new BigDecimal("22.1"), rain.getThreeHourLevel());

        final Precipitation snow = weatherForecast.getSnow();
        assertEquals(new BigDecimal("13.6"), snow.getThreeHourLevel());
    }
}

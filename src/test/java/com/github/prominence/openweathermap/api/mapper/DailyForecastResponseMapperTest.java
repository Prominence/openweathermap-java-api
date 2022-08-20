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

import com.github.prominence.openweathermap.api.context.TestMappingUtils;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.LocationExtended;
import com.github.prominence.openweathermap.api.model.WindModel;
import com.github.prominence.openweathermap.api.model.forecast.daily.Temperature;
import com.github.prominence.openweathermap.api.model.forecast.daily.WeatherForecast;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DailyForecastResponseMapperTest {

    @Test
    public void mapToForecast() {
        final String jsonResponse = """
                {
                  "city": {
                    "id": 2643743,
                    "name": "London",
                    "coord": {
                      "lon": -0.1258,
                      "lat": 51.5085
                    },
                    "country": "GB",
                    "population": 0,
                    "timezone": 3600
                  },
                  "cod": "200",
                  "message": 0.7809187,
                  "cnt": 1,
                  "list": [
                    {
                      "dt": 1568977200,
                      "sunrise": 1568958164,
                      "sunset": 1569002733,
                      "temp": {
                        "day": 293.79,
                        "min": 288.85,
                        "max": 294.47,
                        "night": 288.85,
                        "eve": 290.44,
                        "morn": 293.79
                      },
                      "feels_like": {
                        "day": 278.87,
                        "night": 282.73,
                        "eve": 281.92,
                        "morn": 278.87
                      },
                      "pressure": 1025.04,
                      "humidity": 42,
                      "weather": [
                        {
                          "id": 800,
                          "main": "Clear",
                          "description": "sky is clear",
                          "icon": "01d"
                        }
                      ],
                      "speed": 4.66,
                      "deg": 102,
                      "gust": 5.3,
                      "clouds": 0,
                      "pop": 0.24,
                      "rain": 22.2,
                      "snow": 24.2
                    }
                  ]
                }
                """;

        final Forecast forecast = new DailyForecastResponseMapper(UnitSystem.METRIC).mapToForecast(jsonResponse);
        assertNotNull(forecast);

        final LocationExtended location = forecast.getLocation();
        assertNotNull(location);
        assertEquals(new Coordinates(51.5085, -0.1258), location.getCoordinates());
        assertEquals(2643743, location.getId());
        assertEquals("London", location.getName());
        assertEquals("GB", location.getCountryCode());
        assertEquals(0, location.getPopulation());
        assertEquals(ZoneOffset.ofTotalSeconds(3600), location.getZoneOffset());

        assertEquals(1, forecast.getWeatherForecasts().size());
        final WeatherForecast weatherForecast = forecast.getWeatherForecasts().get(0);
        assertEquals(TestMappingUtils.parseDateTime(1568977200), weatherForecast.getForecastTime());
        // TODO: Does the API provide the sunrise and sunset info??? It is not officially described in the API but present in the example.
        assertEquals(TestMappingUtils.parseDateTime(1568958164), weatherForecast.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1569002733), weatherForecast.getSunsetTime());

        final Temperature temperature = weatherForecast.getTemperature();
        assertEquals(293.79, temperature.getDay());
        assertEquals(288.85, temperature.getMin());
        assertEquals(294.47, temperature.getMax());
        assertEquals(288.85, temperature.getNight());
        assertEquals(290.44, temperature.getEve());
        assertEquals(293.79, temperature.getMorning());
        assertEquals(278.87, temperature.getDayFeelsLike());
        assertEquals(282.73, temperature.getNightFeelsLike());
        assertEquals(281.92, temperature.getEveFeelsLike());
        assertEquals(278.87, temperature.getMorningFeelsLike());

        final AtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(1025.04, pressure.getSeaLevelValue());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(42, humidity.getValue());

        final WindModel wind = weatherForecast.getWind();
        assertEquals(4.66, wind.getSpeed());
        assertEquals(102, wind.getDegrees());
        assertEquals(5.3, wind.getGust());

        final Clouds clouds = weatherForecast.getClouds();
        assertEquals(0, clouds.getValue());

        assertEquals(1, weatherForecast.getWeatherStates().size());
        final WeatherState weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(800, weatherState.getId());
        assertEquals("Clear", weatherState.getName());
        assertEquals("sky is clear", weatherState.getDescription());
        assertEquals("01d", weatherState.getIconId());

        final Rain rain = weatherForecast.getRain();
        assertEquals(22.2, rain.getLevel());

        final Snow snow = weatherForecast.getSnow();
        assertEquals(24.2, snow.getLevel());

        assertEquals(0.24, weatherForecast.getProbabilityOfPrecipitation());
    }
}

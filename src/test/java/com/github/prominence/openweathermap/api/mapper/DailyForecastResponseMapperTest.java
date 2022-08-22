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
import com.github.prominence.openweathermap.api.model.BaseAtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.forecast.daily.DailyPrecipitation;
import com.github.prominence.openweathermap.api.model.forecast.daily.Location;
import com.github.prominence.openweathermap.api.model.forecast.daily.SixteenDaysDailyForecast;
import com.github.prominence.openweathermap.api.model.forecast.daily.SixteenDaysDailyForecastModel;
import com.github.prominence.openweathermap.api.model.forecast.daily.Temperature;
import com.github.prominence.openweathermap.api.model.forecast.daily.Weather;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DailyForecastResponseMapperTest {

    @Test
    public void mapToForecast() throws JsonProcessingException {
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

        final SixteenDaysDailyForecast forecast = new ObjectMapper().readValue(jsonResponse, SixteenDaysDailyForecastModel.class);
        assertNotNull(forecast);

        final Location location = forecast.getLocation();
        assertNotNull(location);
        assertEquals(new Coordinates(51.5085, -0.1258), location.getCoordinates());
        assertEquals(2643743, location.getCityId());
        assertEquals("London", location.getCityName());
        assertEquals("GB", location.getCountryCode());
        assertEquals(0, location.getPopulation());
        assertEquals(ZoneOffset.ofTotalSeconds(3600), location.getTimeZone());

        assertEquals(1, forecast.getWeatherForecasts().size());
        final Weather weatherForecast = forecast.getWeatherForecasts().get(0);
        assertEquals(TestMappingUtils.parseDateTime(1568977200), weatherForecast.getForecastTime());
        // TODO: Does the API provide the sunrise and sunset info??? It is not officially described in the API but present in the example.
        assertEquals(TestMappingUtils.parseDateTime(1568958164), weatherForecast.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1569002733), weatherForecast.getSunsetTime());

        final Temperature temperature = weatherForecast.getTemperature();
        assertEquals(new BigDecimal("294"), temperature.getDay().asKelvin());
        assertEquals(new BigDecimal("289"), temperature.getMin().asKelvin());
        assertEquals(new BigDecimal("294"), temperature.getMax().asKelvin());
        assertEquals(new BigDecimal("289"), temperature.getNight().asKelvin());
        assertEquals(new BigDecimal("290"), temperature.getEve().asKelvin());
        assertEquals(new BigDecimal("294"), temperature.getMorning().asKelvin());
        assertEquals(new BigDecimal("279"), temperature.getDayFeelsLike().asKelvin());
        assertEquals(new BigDecimal("283"), temperature.getNightFeelsLike().asKelvin());
        assertEquals(new BigDecimal("282"), temperature.getEveFeelsLike().asKelvin());
        assertEquals(new BigDecimal("279"), temperature.getMorningFeelsLike().asKelvin());

        final BaseAtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(new BigDecimal("1025.04"), pressure.getPressure());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(42, humidity.getHumidityPercentage());

        final Wind wind = weatherForecast.getWind();
        assertEquals(new BigDecimal("4.66"), wind.getSpeed().asMetersPerSecond());
        assertEquals(102, wind.getDirectionDegrees());
        assertEquals(new BigDecimal("5.30"), wind.getGust().asMetersPerSecond());

        final Clouds clouds = weatherForecast.getCloudCoverage();
        assertEquals(0, clouds.getValuePercentage());

        assertEquals(1, weatherForecast.getWeatherStates().size());
        final WeatherCondition weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(800, weatherState.getId());
        assertEquals("Clear", weatherState.getName());
        assertEquals("clear sky", weatherState.getDescription());
        assertEquals("01d", weatherState.getIconId(DayTime.DAY));

        final DailyPrecipitation precipitation = weatherForecast.getPrecipitation();
        assertEquals(new BigDecimal("22.2"), precipitation.getRain());
        assertEquals(new BigDecimal("24.2"), precipitation.getSnow());
        assertEquals(new BigDecimal("0.24"), precipitation.getProbabilityOfPrecipitation());
    }
}

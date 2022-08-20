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
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.model.weather.WeatherModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrentWeatherResponseMapperTest {

    @Test
    public void mapToWeatherOfficialJsonExample() throws JsonProcessingException {
        final String jsonString = """
                {
                  "coord": {
                    "lon": -122.08,
                    "lat": 37.39
                  },
                  "weather": [
                    {
                      "id": 800,
                      "main": "Clear",
                      "description": "clear sky",
                      "icon": "01d"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 282.55,
                    "feels_like": 281.86,
                    "temp_min": 280.37,
                    "temp_max": 284.26,
                    "pressure": 1023,
                    "humidity": 100
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 1.5,
                    "deg": 350
                  },
                  "clouds": {
                    "all": 1
                  },
                  "dt": 1560350645,
                  "sys": {
                    "type": 1,
                    "id": 5122,
                    "message": 0.0139,
                    "country": "US",
                    "sunrise": 1560343627,
                    "sunset": 1560396563
                  },
                  "timezone": -25200,
                  "id": 420006353,
                  "name": "Mountain View",
                  "cod": 200
                }
                """;

        final Weather weather = deserialize(jsonString);

        assertNotNull(weather);
    }

    @Test
    public void mapToWeather() throws JsonProcessingException {
        final String jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;

        final WeatherModel weather = deserialize(jsonString);

        assertNotNull(weather);
    }

    @Test
    public void mapToWeather_withDamagedJSON() {
        final String jsonString = """
                {
                  "coord": "lon"
                  :
                  27.5667,
                  "lat": 53.9
                },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        assertThrows(RuntimeException.class, () -> deserialize(jsonString));
    }

    @Test
    public void mapToWeather_withoutDt() throws JsonProcessingException {
        final String jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;

        final Weather weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNull(weather.getForecastTime());
    }

    @Test
    @Disabled
    public void mapToWeather_withoutWeatherNode() throws JsonProcessingException {
        final String jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;

        final Weather weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNull(weather.getWeatherStates());
    }

    @Test
    public void mapToWeather_withTemperatureVariations() throws JsonProcessingException {
        String jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        Weather weather = deserialize(jsonString);

        assertNotNull(weather);
        assertEquals(new BigDecimal("1.8"), weather.getTemperature().getTemperature().asKelvin());
        assertEquals(new BigDecimal("-0.3"), weather.getTemperature().getFeelsLike().asKelvin());
        assertEquals(new BigDecimal("1.7"), weather.getTemperature().getMin().asKelvin());
        assertEquals(new BigDecimal("2.0"), weather.getTemperature().getMax().asKelvin());

        // without feels like node
        jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNull(weather.getTemperature().getFeelsLike());
        assertNotNull(weather.getTemperature().getMin());
        assertNotNull(weather.getTemperature().getMax());

        // without min temperature node
        jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getTemperature().getFeelsLike());
        assertNull(weather.getTemperature().getMin());
        assertNotNull(weather.getTemperature().getMax());

        // without max temperature node
        jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getTemperature().getFeelsLike());
        assertNotNull(weather.getTemperature().getMin());
        assertNull(weather.getTemperature().getMax());
    }

    @Test
    public void mapToWeather_withWindVariations() throws JsonProcessingException {
        String jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;

        Weather weather = deserialize(jsonString);

        assertNotNull(weather);
        assertEquals(new BigDecimal("2.00"), weather.getWind().getSpeed().asMetersPerSecond());
        assertEquals(250, weather.getWind().getDirectionDegrees());
        assertNull(weather.getWind().getGust());

        // without degrees
        jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getWind());
        assertNull(weather.getWind().getDirectionDegrees());

        // with gust
        jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250,
                    "gust": 2.44
                  },
                  "snow": {
                    "1h": 0.2
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = deserialize(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getWind());
        assertNotNull(weather.getWind().getDirectionDegrees());
        assertEquals(new BigDecimal("2.44"), weather.getWind().getGust().asMetersPerSecond());
    }

    @Test
    public void mapToWeather_withRainVariations() throws JsonProcessingException {
        final String jsonWith1Hr = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "rain": {
                    "1h": 0.1
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        final String jsonWith3Hr = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "rain": {
                    "3h": 0.3
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        final String jsonWithBoth = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "rain": {
                    "1h": 0.1,
                    "3h": 0.3
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;

        final Weather weather = deserialize(jsonWith1Hr);

        // with 1h level only
        assertNotNull(weather.getRain());
        assertEquals(0.1, weather.getRain().getOneHourLevel(), 0.00001);
        assertNull(weather.getRain().getThreeHourLevel());

        weather = mapper.mapToWeather(jsonWith3Hr);

        // with 3h level only
        assertNotNull(weather.getRain());
        assertNull(weather.getRain().getOneHourLevel());
        assertEquals(0.3, weather.getRain().getThreeHourLevel(), 0.00001);

        weather = mapper.mapToWeather(jsonWithBoth);

        // with both levels
        assertNotNull(weather.getRain());
        assertEquals(0.1, weather.getRain().getOneHourLevel(), 0.00001);
        assertEquals(0.3, weather.getRain().getThreeHourLevel(), 0.00001);
    }

    @Test
    public void mapToWeather_withSnowVariations() {
        final String jsonWith1Hr = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.1
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        final String jsonWith3Hr = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "3h": 0.3
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        final String jsonWithBoth = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.1,
                    "3h": 0.3
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;

        final CurrentWeatherResponseMapper mapper = new CurrentWeatherResponseMapper(UnitSystem.METRIC);
        WeatherModel weather = mapper.mapToWeather(jsonWith1Hr);

        // with 1h level only
        assertNotNull(weather.getSnow());
        assertEquals(0.1, weather.getSnow().getOneHourLevel(), 0.00001);
        assertNull(weather.getSnow().getThreeHourLevel());

        weather = mapper.mapToWeather(jsonWith3Hr);

        // with 3h level only
        assertNotNull(weather.getSnow());
        assertNull(weather.getSnow().getOneHourLevel());
        assertEquals(0.3, weather.getSnow().getThreeHourLevel(), 0.00001);

        weather = mapper.mapToWeather(jsonWithBoth);

        // with both levels
        assertNotNull(weather.getSnow());
        assertEquals(0.1, weather.getSnow().getOneHourLevel(), 0.00001);
        assertEquals(0.3, weather.getSnow().getThreeHourLevel(), 0.00001);
    }

    @Test
    public void mapToWeather_withLocationVariations() {
        String jsonString = """
                {
                  "coord": {
                    "lon": 27.5667,
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.1
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        final CurrentWeatherResponseMapper mapper = new CurrentWeatherResponseMapper(UnitSystem.METRIC);

        WeatherModel weather = mapper.mapToWeather(jsonString);

        assertNotNull(weather.getLocation().getCoordinates());
        assertNotNull(weather.getLocation().getCountryCode());

        // without coordinates and country code
        jsonString = """
                {
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.1
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = mapper.mapToWeather(jsonString);
        assertNull(weather.getLocation().getCoordinates());
        assertNull(weather.getLocation().getCountryCode());

        // coordinates without latitude
        jsonString = """
                {
                  "coord": {
                    "lon": 27.5667
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.1
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = mapper.mapToWeather(jsonString);
        assertNull(weather.getLocation().getCoordinates());
        assertNotNull(weather.getLocation().getCountryCode());

        // coordinates without longitude
        jsonString = """
                {
                  "coord": {
                    "lat": 53.9
                  },
                  "weather": [
                    {
                      "id": 600,
                      "main": "Snow",
                      "description": "небольшой снег",
                      "icon": "13n"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 1.84,
                    "feels_like": -0.31,
                    "temp_min": 1.67,
                    "temp_max": 2,
                    "pressure": 1001,
                    "humidity": 69
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2,
                    "deg": 250
                  },
                  "snow": {
                    "1h": 0.1
                  },
                  "clouds": {
                    "all": 75
                  },
                  "dt": 1617746826,
                  "sys": {
                    "type": 1,
                    "id": 8939,
                    "country": "BY",
                    "sunrise": 1617766068,
                    "sunset": 1617814530
                  },
                  "timezone": 10800,
                  "id": 0,
                  "name": "Minsk",
                  "cod": 200
                }
                """;
        weather = mapper.mapToWeather(jsonString);
        assertNull(weather.getLocation().getCoordinates());
        assertNotNull(weather.getLocation().getCountryCode());
    }

    private static Weather deserialize(final String jsonString) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, WeatherModel.class);
    }
}

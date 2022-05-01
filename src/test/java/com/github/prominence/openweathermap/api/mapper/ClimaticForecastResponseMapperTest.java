package com.github.prominence.openweathermap.api.mapper;

import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Wind;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.climatic.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.forecast.climatic.Temperature;
import com.github.prominence.openweathermap.api.model.forecast.climatic.*;
import com.github.prominence.openweathermap.api.utils.TestMappingUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClimaticForecastResponseMapperTest {

    @Test
    public void mapToForecast() {
        final String jsonResponse = """
                {
                  "cod": "200",
                  "city": {
                    "id": 2643743,
                    "name": "London",
                    "coord": {
                      "lon": -0.1277,
                      "lat": 51.5073
                    },
                    "country": "GB"
                  },
                  "message": 0.353472054,
                  "list": [
                    {
                      "dt": 1594382400,
                      "sunrise": 1594353335,
                      "sunset": 1594412149,
                      "temp": {
                        "day": 286.98,
                        "min": 285.22,
                        "max": 287.97,
                        "night": 285.22,
                        "eve": 287.97,
                        "morn": 287.29
                      },
                      "feels_like": {
                        "day": 282.61,
                        "night": 283.19,
                        "eve": 284.98,
                        "morn": 282.68
                      },
                      "pressure": 1016,
                      "humidity": 84,
                      "weather": [
                        {
                          "id": 500,
                          "main": "Rain",
                          "description": "light rain",
                          "icon": "10d"
                        }
                      ],
                      "speed": 6.78,
                      "deg": 320,
                      "clouds": 81,
                      "rain": 1.96,
                      "snow": 2.21
                    }
                  ]
                }
                """;

        final Forecast forecast = new ClimaticForecastResponseMapper(UnitSystem.METRIC).mapToForecast(jsonResponse);
        assertNotNull(forecast);

        final Location location = forecast.getLocation();
        assertNotNull(location);
        assertEquals(Coordinates.of(51.5073, -0.1277), location.getCoordinates());
        assertEquals(2643743, location.getId());
        assertEquals("London", location.getName());
        assertEquals("GB", location.getCountryCode());
        assertNull(location.getPopulation());
        assertNull(location.getZoneOffset());

        assertEquals(1, forecast.getWeatherForecasts().size());
        final WeatherForecast weatherForecast = forecast.getWeatherForecasts().get(0);
        assertEquals(TestMappingUtils.parseDateTime(1594382400), weatherForecast.getForecastTime());
        assertEquals(TestMappingUtils.parseDateTime(1594353335), weatherForecast.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1594412149), weatherForecast.getSunsetTime());

        final Temperature temperature = weatherForecast.getTemperature();
        assertEquals(286.98, temperature.getDay());
        assertEquals(285.22, temperature.getMin());
        assertEquals(287.97, temperature.getMax());
        assertEquals(285.22, temperature.getNight());
        assertEquals(287.97, temperature.getEve());
        assertEquals(287.29, temperature.getMorning());
        assertEquals(282.61, temperature.getDayFeelsLike());
        assertEquals(283.19, temperature.getNightFeelsLike());
        assertEquals(284.98, temperature.getEveFeelsLike());
        assertEquals(282.68, temperature.getMorningFeelsLike());

        final AtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(1016, pressure.getSeaLevelValue());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(84, humidity.getValue());

        final Wind wind = weatherForecast.getWind();
        assertEquals(6.78, wind.getSpeed());
        assertEquals(320, wind.getDegrees());

        final Clouds clouds = weatherForecast.getClouds();
        assertEquals(81, clouds.getValue());

        assertEquals(1, weatherForecast.getWeatherStates().size());
        final WeatherState weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(500, weatherState.getId());
        assertEquals("Rain", weatherState.getName());
        assertEquals("light rain", weatherState.getDescription());
        assertEquals("10d", weatherState.getIconId());

        final Rain rain = weatherForecast.getRain();
        assertEquals(1.96, rain.getLevel());

        final Snow snow = weatherForecast.getSnow();
        assertEquals(2.21, snow.getLevel());
    }
}
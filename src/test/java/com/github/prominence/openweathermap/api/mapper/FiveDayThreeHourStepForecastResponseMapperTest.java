package com.github.prominence.openweathermap.api.mapper;

import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.free.*;
import com.github.prominence.openweathermap.api.utils.TestMappingUtils;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FiveDayThreeHourStepForecastResponseMapperTest {

    @Test
    void mapToForecast() {
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

        final Forecast forecast = new FiveDayThreeHourStepForecastResponseMapper(UnitSystem.METRIC).mapToForecast(jsonResponse);
        assertNotNull(forecast);

        final Location location = forecast.getLocation();
        assertNotNull(location);
        assertEquals(Coordinates.of(51.5073, -0.1277), location.getCoordinates());
        assertEquals(2643743, location.getId());
        assertEquals("London", location.getName());
        assertEquals("GB", location.getCountryCode());
        assertEquals(1000000, location.getPopulation());
        assertEquals(TestMappingUtils.parseDateTime(1647324903), location.getSunriseTime());
        assertEquals(TestMappingUtils.parseDateTime(1647367441), location.getSunsetTime());
        assertEquals(ZoneOffset.ofTotalSeconds(0), location.getZoneOffset());


        final List<WeatherForecast> weatherForecastList = forecast.getWeatherForecasts();
        assertEquals(2, weatherForecastList.size());

        final WeatherForecast weatherForecast = weatherForecastList.get(0);
        assertNotNull(weatherForecast);

        assertEquals(TestMappingUtils.parseDateTime(1647345600), weatherForecast.getForecastTime());
        assertEquals(10000, weatherForecast.getVisibilityInMetres());
        assertEquals(0, weatherForecast.getProbabilityOfPrecipitation());
        assertEquals("2022-03-15 12:00:00", weatherForecast.getForecastTimeISO());
        assertEquals(DayTime.DAY, weatherForecast.getDayTime());

        final Temperature temperature = weatherForecast.getTemperature();
        assertNotNull(temperature);
        assertEquals(286.88, temperature.getValue());
        assertEquals(285.93, temperature.getFeelsLike());
        assertEquals(286.74, temperature.getMinTemperature());
        assertEquals(286.88, temperature.getMaxTemperature());

        final AtmosphericPressure pressure = weatherForecast.getAtmosphericPressure();
        assertEquals(1021, pressure.getValue());
        assertEquals(1021, pressure.getSeaLevelValue());
        assertEquals(1018, pressure.getGroundLevelValue());

        final Humidity humidity = weatherForecast.getHumidity();
        assertEquals(62, humidity.getValue());

        final Clouds clouds = weatherForecast.getClouds();
        assertEquals(85, clouds.getValue());

        final Wind wind = weatherForecast.getWind();
        assertEquals(3.25, wind.getSpeed());
        assertEquals(134, wind.getDegrees());
        assertEquals(4.45, wind.getGust());

        assertEquals(1, weatherForecast.getWeatherStates().size());

        final WeatherState weatherState = weatherForecast.getWeatherStates().get(0);
        assertEquals(804, weatherState.getId());
        assertEquals("Clouds", weatherState.getName());
        assertEquals("overcast clouds", weatherState.getDescription());
        assertEquals("04d", weatherState.getIconId());

        final Rain rain = weatherForecast.getRain();
        assertEquals(22.1, rain.getThreeHourLevel());

        final Snow snow = weatherForecast.getSnow();
        assertEquals(13.6, snow.getThreeHourLevel());
    }
}
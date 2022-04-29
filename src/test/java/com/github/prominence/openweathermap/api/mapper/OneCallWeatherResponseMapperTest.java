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
import com.github.prominence.openweathermap.api.model.onecall.current.CurrentWeatherData;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeatherData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneCallWeatherResponseMapperTest {

    @Test
    void mapToCurrent() {
        final String jsonResponse = """
                {
                   "lat": 33.44,
                   "lon": -94.04,
                   "timezone": "America/Chicago",
                   "timezone_offset": -21600,
                   "current": {
                     "dt": 1618317040,
                     "sunrise": 1618282134,
                     "sunset": 1618333901,
                     "temp": 284.07,
                     "feels_like": 282.84,
                     "pressure": 1019,
                     "humidity": 62,
                     "dew_point": 277.08,
                     "uvi": 0.89,
                     "clouds": 0,
                     "visibility": 10000,
                     "wind_speed": 6,
                     "wind_deg": 300,
                     "weather": [
                       {
                         "id": 500,
                         "main": "Rain",
                         "description": "light rain",
                         "icon": "10d"
                       }
                     ],
                     "rain": {
                       "1h": 0.21
                     }
                   },
                   "minutely": [
                     {
                       "dt": 1618317060,
                       "precipitation": 0.205
                     }
                   ],
                   "hourly": [
                     {
                       "dt": 1618315200,
                       "temp": 282.58,
                       "feels_like": 280.4,
                       "pressure": 1019,
                       "humidity": 68,
                       "dew_point": 276.98,
                       "uvi": 1.4,
                       "clouds": 19,
                       "visibility": 306,
                       "wind_speed": 4.12,
                       "wind_deg": 296,
                       "wind_gust": 7.33,
                       "weather": [
                         {
                           "id": 801,
                           "main": "Clouds",
                           "description": "few clouds",
                           "icon": "02d"
                         }
                       ],
                       "pop": 0
                     }
                   ],
                   "daily": [
                     {
                       "dt": 1618308000,
                       "sunrise": 1618282134,
                       "sunset": 1618333901,
                       "moonrise": 1618284960,
                       "moonset": 1618339740,
                       "moon_phase": 0.04,
                       "temp": {
                         "day": 279.79,
                         "min": 275.09,
                         "max": 284.07,
                         "night": 275.09,
                         "eve": 279.21,
                         "morn": 278.49
                       },
                       "feels_like": {
                         "day": 277.59,
                         "night": 276.27,
                         "eve": 276.49,
                         "morn": 276.27
                       },
                       "pressure": 1020,
                       "humidity": 81,
                       "dew_point": 276.77,
                       "wind_speed": 3.06,
                       "wind_deg": 294,
                       "weather": [
                         {
                           "id": 500,
                           "main": "Rain",
                           "description": "light rain",
                           "icon": "10d"
                         }
                       ],
                       "clouds": 56,
                       "pop": 0.2,
                       "rain": 0.62,
                       "uvi": 1.93
                     }
                   ],
                   "alerts": [
                     {
                       "sender_name": "NWS Tulsa",
                       "event": "Heat Advisory",
                       "start": 1597341600,
                       "end": 1597366800,
                       "description": "...HEAT ADVISORY REMAINS IN EFFECT FROM 1 PM THIS AFTERNOON TO\\n8 PM CDT THIS EVENING...\\n* WHAT...Heat index values of 105 to 109 degrees expected.\\n* WHERE...Creek, Okfuskee, Okmulgee, McIntosh, Pittsburg,\\nLatimer, Pushmataha, and Choctaw Counties.\\n* WHEN...From 1 PM to 8 PM CDT Thursday.\\n* IMPACTS...The combination of hot temperatures and high\\nhumidity will combine to create a dangerous situation in which\\nheat illnesses are possible.",
                       "tags": [
                         "Extreme temperature value"
                       ]
                     }
                   ]
                 }
                """;

        final CurrentWeatherData weatherData = new OneCallWeatherResponseMapper(UnitSystem.METRIC).mapToCurrent(jsonResponse);

        assertNotNull(weatherData);
        assertNotEquals(0, weatherData.getDailyList().size());
        assertEquals(1, weatherData.getAlerts().get(0).getTags().size());
    }

    @Test
    void mapToHistorical() {
        final String jsonResponse = """
                {
                  "lat": 60.99,
                  "lon": 30.9,
                  "timezone": "Europe/Moscow",
                  "timezone_offset": 10800,
                  "current": {
                    "dt": 1586468027,
                    "sunrise": 1586487424,
                    "sunset": 1586538297,
                    "temp": 274.31,
                    "feels_like": 269.79,
                    "pressure": 1006,
                    "humidity": 72,
                    "dew_point": 270.21,
                    "clouds": 0,
                    "visibility": 10000,
                    "wind_speed": 3,
                    "wind_deg": 260,
                    "weather": [
                      {
                        "id": 800,
                        "main": "Clear",
                        "description": "clear sky",
                        "icon": "01n"
                      }
                    ]
                  },
                  "hourly": [
                    {
                      "dt": 1586390400,
                      "temp": 278.41,
                      "feels_like": 269.43,
                      "pressure": 1006,
                      "humidity": 65,
                      "dew_point": 272.46,
                      "clouds": 0,
                      "wind_speed": 9.83,
                      "wind_deg": 60,
                      "wind_gust": 15.65,
                      "weather": [
                        {
                          "id": 800,
                          "main": "Clear",
                          "description": "clear sky",
                          "icon": "01n"
                        }
                      ]
                    }
                  ]
                }
                """;

        final HistoricalWeatherData weatherData = new OneCallWeatherResponseMapper(UnitSystem.METRIC).mapToHistorical(jsonResponse);

        assertNotNull(weatherData);
        assertNotNull(weatherData.getHistoricalWeather());
        assertNotEquals(0, weatherData.getHourlyList().size());
    }
}
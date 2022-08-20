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

import com.github.prominence.openweathermap.api.model.roadrisk.RoadRiskModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoadRiskResponseMapperTest {

    @Test
    void mapToObjects() {
        final String jsonResponse = """
                [
                  {
                    "dt": 1602702000,
                    "coord": [
                      7.27,
                      44.04
                    ],
                    "weather": {
                      "temp": 278.44,
                      "wind_speed": 2.27,
                      "wind_deg": 7,
                      "precipitation_intensity": 0.38,
                      "dew_point": 276.13
                    },
                    "road": {
                      "state": 2,
                      "temp": 293.85
                    },
                    "alerts": [
                      {
                        "sender_name": "METEO-FRANCE",
                        "event": "Moderate thunderstorm warning",
                        "event_level": 2
                      }
                    ]
                  },
                  {
                    "dt": 1602702400,
                    "coord": [
                      7.37,
                      45.04
                    ],
                    "weather": {
                      "temp": 282.44,
                      "wind_speed": 1.84,
                      "wind_deg": 316,
                      "dew_point": 275.99
                    },
                    "road": {
                      "state": 1,
                      "temp": 293.85
                    },
                    "alerts": [
                    ]
                  }
                ]
                """;

        final List<RoadRiskModel> roadRiskRecords = new RoadRiskResponseMapper().mapToObjects(jsonResponse);
        assertNotNull(roadRiskRecords);

    }
}

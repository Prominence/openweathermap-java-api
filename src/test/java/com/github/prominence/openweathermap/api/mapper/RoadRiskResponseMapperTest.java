package com.github.prominence.openweathermap.api.mapper;

import com.github.prominence.openweathermap.api.model.roadrisk.RoadRiskRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        final List<RoadRiskRecord> roadRiskRecords = new RoadRiskResponseMapper().mapToObjects(jsonResponse);
        assertNotNull(roadRiskRecords);

    }
}
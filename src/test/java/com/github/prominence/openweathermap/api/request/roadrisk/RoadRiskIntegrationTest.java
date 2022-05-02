package com.github.prominence.openweathermap.api.request.roadrisk;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.core.net.MockHttpClient;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.roadrisk.RoadRiskRecord;
import com.github.prominence.openweathermap.api.model.roadrisk.TrackPoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class RoadRiskIntegrationTest extends ApiTest {
    private static final MockHttpClient httpClient = new MockHttpClient();

    @BeforeAll
    public static void setup() {
        getClient().setHttpClient(httpClient);
    }

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsJava_thenReturnNotNull() {
        final TrackPoint trackPoint = new TrackPoint();
        trackPoint.setCoordinates(Coordinates.of(5, 5));
        trackPoint.setRequestedTime(LocalDateTime.now());

        final String responseOutput = """
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
        httpClient.setResponseOutput(responseOutput);

        final List<RoadRiskRecord> roadRiskRecords = getClient()
                .roadRisk()
                .byTrackPoints(List.of(trackPoint))
                .retrieve()
                .asJava();

        System.out.println(roadRiskRecords);
    }

}

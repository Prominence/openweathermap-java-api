package com.github.prominence.openweathermap.api.mapper;

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiation;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiationRecord;
import com.github.prominence.openweathermap.api.utils.TestMappingUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolarRadiationResponseMapperTest {

    @Test
    void mapToObject() {
        final String jsonResponse = """
                {
                  "coord": {
                    "lon": -114.6244,
                    "lat": 32.7243
                  },
                  "list": [
                    {
                      "radiation": {
                        "ghi": 206.68,
                        "dni": 2.27,
                        "dhi": 204.83,
                        "ghi_cs": 826.71,
                        "dni_cs": 885.47,
                        "dhi_cs": 114.93
                      },
                      "dt": 1618232400
                    }
                  ]
                }
                """;

        final SolarRadiation solarRadiation = new SolarRadiationResponseMapper().mapToObject(jsonResponse);
        assertNotNull(solarRadiation);

        assertEquals(Coordinates.of(32.7243, -114.6244), solarRadiation.getCoordinates());

        final List<SolarRadiationRecord> records = solarRadiation.getSolarRadiationRecords();
        assertEquals(1, records.size());

        final SolarRadiationRecord record = records.get(0);
        assertEquals(TestMappingUtils.parseDateTime(1618232400), record.getMeasurementTime());
        assertEquals(206.68, record.getCloudSkyGlobalHorizontalIrradiance());
        assertEquals(2.27, record.getCloudSkyDirectNormalIrradiance());
        assertEquals(204.83, record.getCloudSkyDiffuseHorizontalIrradiance());
        assertEquals(826.71, record.getClearSkyGlobalHorizontalIrradiance());
        assertEquals(885.47, record.getClearSkyDirectNormalIrradiance());
        assertEquals(114.93, record.getClearSkyDiffuseHorizontalIrradiance());
    }
}
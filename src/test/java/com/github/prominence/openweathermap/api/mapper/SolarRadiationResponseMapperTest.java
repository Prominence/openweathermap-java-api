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
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiation;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiationEntry;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiationModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolarRadiationResponseMapperTest {

    @Test
    void mapToObject() throws JsonProcessingException {
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

        final SolarRadiation solarRadiation = new ObjectMapper().readValue(jsonResponse, SolarRadiationModel.class);
        assertNotNull(solarRadiation);

        assertEquals(new Coordinates(32.7243, -114.6244), solarRadiation.getCoordinates());

        final List<SolarRadiationEntry> records = solarRadiation.getSolarRadiationRecords();
        assertEquals(1, records.size());

        final SolarRadiationEntry record = records.get(0);
        assertEquals(TestMappingUtils.parseDateTime(1618232400), record.getMeasurementTime());
        assertEquals(206.68, record.getSolarRadiationMeasurement().getGlobalHorizontalIrradiance());
        assertEquals(2.27, record.getSolarRadiationMeasurement().getDirectNormalIrradiance());
        assertEquals(204.83, record.getSolarRadiationMeasurement().getDiffuseHorizontalIrradiance());
        assertEquals(826.71, record.getSolarRadiationMeasurement().getGlobalHorizontalIrradianceClearSky());
        assertEquals(885.47, record.getSolarRadiationMeasurement().getDirectNormalIrradianceClearSky());
        assertEquals(114.93, record.getSolarRadiationMeasurement().getDiffuseHorizontalIrradianceClearSky());
    }
}

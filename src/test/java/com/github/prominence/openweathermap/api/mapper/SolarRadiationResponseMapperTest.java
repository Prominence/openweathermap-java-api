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

import com.github.prominence.openweathermap.api.context.TestMappingUtils;
import com.github.prominence.openweathermap.api.model.generic.location.Coordinates;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiation;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiationEntry;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiationModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolarRadiationResponseMapperTest {


    @Test
    void testDeserialize_ShouldSucceed_WhenCalledWithOfficialExample() throws IOException {
        //given
        final String resource = "/responses/valid/solar-radiation.json";

        //when
        final SolarRadiation actual = loadDeserializedResourceAs(resource, SolarRadiationModel.class);

        //then
        assertNotNull(actual);
        assertEquals(new Coordinates(32.7243, -114.6244), actual.getCoordinates());

        final List<SolarRadiationEntry> records = actual.getSolarRadiationRecords();
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

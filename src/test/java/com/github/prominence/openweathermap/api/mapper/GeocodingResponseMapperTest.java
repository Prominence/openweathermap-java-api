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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingModel;
import com.github.prominence.openweathermap.api.model.geocoding.ZipCodeGeocodingModel;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeocodingResponseMapperTest {

    @Test
    public void reverseGeocodingResponseMappingTest() throws IOException {
        String jsonResponse = IOUtils.resourceToString("/responses/valid/geocoding-reverse.json", StandardCharsets.UTF_8);

        List<GeocodingModel> geocodingRecords = new ObjectMapper().readerFor(new TypeReference<List<GeocodingModel>>() {
        }).readValue(jsonResponse);

        assertNotNull(geocodingRecords);
        assertEquals(5, geocodingRecords.size());
    }

    @Test
    public void zipGeocodingInfoResponseMappingTest() throws IOException {
        String jsonResponse = IOUtils.resourceToString("/responses/valid/geocoding-zipcode.json", StandardCharsets.UTF_8);

        ZipCodeGeocodingModel zipCodeGeocodingRecord = new ObjectMapper().readValue(jsonResponse, ZipCodeGeocodingModel.class);

        assertNotNull(zipCodeGeocodingRecord);
        assertEquals("90210", zipCodeGeocodingRecord.getZipCode());
        assertEquals("Beverly Hills", zipCodeGeocodingRecord.getName());
        assertEquals("US", zipCodeGeocodingRecord.getCountryCode());
        assertEquals(new Coordinates(34.0901, -118.4065), zipCodeGeocodingRecord.getCoordinates());
    }
}

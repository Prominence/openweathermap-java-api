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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.GeocodingRecordDeserializer;
import com.github.prominence.openweathermap.api.deserializer.ZipCodeGeocodingDeserializer;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingRecord;
import com.github.prominence.openweathermap.api.model.geocoding.ZipCodeGeocodingRecord;

import java.util.List;

/**
 * Official API response documentation: <a href="https://openweathermap.org/api/geocoding-api">https://openweathermap.org/api/geocoding-api</a>.
 */
public class GeocodingResponseMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public GeocodingResponseMapper() {
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(GeocodingRecord.class, new GeocodingRecordDeserializer());
        module.addDeserializer(ZipCodeGeocodingRecord.class, new ZipCodeGeocodingDeserializer());
        objectMapper.registerModule(module);
    }

    public List<GeocodingRecord> mapGeocodingResponse(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<GeocodingRecord>>() {});
        }  catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse ReverseGeocoding response.", e);
        }
    }

    public ZipCodeGeocodingRecord mapZipCodeGeocodingResponse(String json) {
        try {
            return objectMapper.readValue(json, ZipCodeGeocodingRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse GeocodingInfo response.", e);
        }
    }
}

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

package com.github.prominence.openweathermap.api.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingRecord;

import java.io.IOException;
import java.util.Map;

public class GeocodingRecordDeserializer extends JsonDeserializer<GeocodingRecord> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public GeocodingRecordDeserializer() {
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Coordinates.class, new CoordinatesDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public GeocodingRecord deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode rootNode = jp.getCodec().readTree(jp);
        String name = rootNode.get("name").asText();
        String country = rootNode.get("country").asText();
        Map<String, String> localNames = objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("local_names")), new TypeReference<Map<String, String>>() {});
        Coordinates coordinates = objectMapper.readValue(objectMapper.treeAsTokens(rootNode), Coordinates.class);
        return new GeocodingRecord(name, localNames, coordinates, country);
    }
}

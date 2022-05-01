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

package com.github.prominence.openweathermap.api.deserializer.forecast.free;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.CoordinatesDeserializer;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.forecast.free.Location;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class FreeForecastLocationDeserializer extends JsonDeserializer<Location> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FreeForecastLocationDeserializer() {
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Coordinates.class, new CoordinatesDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public Location deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode rootNode = p.getCodec().readTree(p);
        final Location location = Location.withValues(rootNode.get("id").asInt(), rootNode.get("name").asText());

        final JsonNode timezoneNode = rootNode.get("timezone");
        if (timezoneNode != null) {
            location.setZoneOffset(ZoneOffset.ofTotalSeconds(timezoneNode.asInt()));
        }

        final JsonNode countryNode = rootNode.get("country");
        if (countryNode != null) {
            location.setCountryCode(countryNode.asText());
        }

        final JsonNode sunriseNode = rootNode.get("sunrise");
        final JsonNode sunsetNode = rootNode.get("sunset");
        if (sunriseNode != null) {
            location.setSunriseTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseNode.asLong()), TimeZone.getDefault().toZoneId()));
        }
        if (sunsetNode != null) {
            location.setSunsetTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetNode.asLong()), TimeZone.getDefault().toZoneId()));
        }

        final JsonNode coordNode = rootNode.get("coord");
        if (coordNode != null) {
            location.setCoordinates(objectMapper.readValue(objectMapper.treeAsTokens(coordNode), Coordinates.class));
        }

        final JsonNode populationNode = rootNode.get("population");
        if (populationNode != null) {
            location.setPopulation(populationNode.asLong());
        }

        return location;
    }
}

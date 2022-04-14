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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.enums.AirQualityIndex;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionDetails;
import com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionRecord;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * The type Air pollution response mapper.
 */
public class AirPollutionResponseMapper {
    /**
     * Map to air pollution air pollution.
     *
     * @param json the json
     * @return the air pollution
     */
    public AirPollutionDetails mapToAirPollution(String json) {
        final ObjectMapper objectMapper = new ObjectMapper();
        AirPollutionDetails airPollutionDetails;
        try {
            final JsonNode root = objectMapper.readTree(json);
            airPollutionDetails = mapToAirPollution(root);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse Air Pollution response");
        }

        return airPollutionDetails;
    }

    private AirPollutionDetails mapToAirPollution(JsonNode rootNode) {
        final AirPollutionDetails airPollutionDetails = new AirPollutionDetails();
        airPollutionDetails.setCoordinate(parseCoordinate(rootNode.get("coord")));

        final List<AirPollutionRecord> sampleList = new ArrayList<>();
        final JsonNode sampleListNode = rootNode.get("list");
        sampleListNode.forEach(sampleNode -> {
            sampleList.add(parseAirPollutionSample(sampleNode));
        });
        airPollutionDetails.setAirPollutionRecords(sampleList);

        return airPollutionDetails;
    }

    private AirPollutionRecord parseAirPollutionSample(JsonNode sampleNode) {
        AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        airPollutionRecord.setForecastTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(sampleNode.get("dt").asInt()), TimeZone.getDefault().toZoneId()));
        airPollutionRecord.setAirQualityIndex(AirQualityIndex.getByIndex(sampleNode.get("main").get("aqi").asInt()));

        final JsonNode componentsNode = sampleNode.get("components");
        airPollutionRecord.setCO(componentsNode.get("co").asDouble());
        airPollutionRecord.setNO(componentsNode.get("no").asDouble());
        airPollutionRecord.setNO2(componentsNode.get("no2").asDouble());
        airPollutionRecord.setO3(componentsNode.get("o3").asDouble());
        airPollutionRecord.setSO2(componentsNode.get("so2").asDouble());
        airPollutionRecord.setPM2_5(componentsNode.get("pm2_5").asDouble());
        airPollutionRecord.setPM10(componentsNode.get("pm10").asDouble());
        airPollutionRecord.setNH3(componentsNode.get("nh3").asDouble());

        return airPollutionRecord;
    }

    private Coordinate parseCoordinate(JsonNode rootNode) {
        final JsonNode latitudeNode = rootNode.get("lat");
        final JsonNode longitudeNode = rootNode.get("lon");
        if (latitudeNode != null && longitudeNode != null) {
            return Coordinate.of(latitudeNode.asDouble(), longitudeNode.asDouble());
        }
        return null;
    }
}

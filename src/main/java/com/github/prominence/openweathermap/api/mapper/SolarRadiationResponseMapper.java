package com.github.prominence.openweathermap.api.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.CoordinatesDeserializer;
import com.github.prominence.openweathermap.api.deserializer.radiation.SolarRadiationRecordDeserializer;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiation;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiationRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolarRadiationResponseMapper extends AbstractMapper {

    public SolarRadiationResponseMapper() {
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(SolarRadiationRecord.class, new SolarRadiationRecordDeserializer());
        module.addDeserializer(Coordinates.class, new CoordinatesDeserializer());
        objectMapper.registerModule(module);
    }

    public SolarRadiation mapToObject(String jsonResponse) {
        SolarRadiation solarRadiation;
        try {
            final JsonNode root = objectMapper.readTree(jsonResponse);
            solarRadiation = mapToObject(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse SolarRadiation response", e);
        }

        return solarRadiation;
    }

    private SolarRadiation mapToObject(JsonNode rootNode) throws IOException {
        final SolarRadiation solarRadiation = new SolarRadiation();
        solarRadiation.setCoordinates(objectMapper.readValue(objectMapper.treeAsTokens(rootNode.get("coord")), Coordinates.class));

        final JsonNode listRecordsNode = rootNode.get("list");
        List<SolarRadiationRecord> radiationRecords = new ArrayList<>();
        for (JsonNode recordNode : listRecordsNode) {
            radiationRecords.add(objectMapper.readValue(objectMapper.treeAsTokens(recordNode), SolarRadiationRecord.class));
        }
        solarRadiation.setSolarRadiationRecords(radiationRecords);

        return solarRadiation;
    }
}

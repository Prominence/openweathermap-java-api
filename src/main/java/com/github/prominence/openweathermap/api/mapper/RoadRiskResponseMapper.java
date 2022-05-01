package com.github.prominence.openweathermap.api.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.roadrisk.RoadRiskAlertDeserializer;
import com.github.prominence.openweathermap.api.deserializer.roadrisk.RoadRiskRoadDetailsDeserializer;
import com.github.prominence.openweathermap.api.deserializer.roadrisk.RoadRiskWeatherDeserializer;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.roadrisk.Alert;
import com.github.prominence.openweathermap.api.model.roadrisk.RoadDetails;
import com.github.prominence.openweathermap.api.model.roadrisk.RoadRiskRecord;
import com.github.prominence.openweathermap.api.model.roadrisk.Weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoadRiskResponseMapper extends AbstractMapper {

    public RoadRiskResponseMapper() {
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Weather.class, new RoadRiskWeatherDeserializer());
        module.addDeserializer(RoadDetails.class, new RoadRiskRoadDetailsDeserializer());
        module.addDeserializer(Alert.class, new RoadRiskAlertDeserializer());
        objectMapper.registerModule(module);
    }

    public List<RoadRiskRecord> mapToObjects(String jsonResponse) {
        List<RoadRiskRecord> roadRiskRecords;
        try {
            final JsonNode root = objectMapper.readTree(jsonResponse);
            roadRiskRecords = mapToObjects(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse SolarRadiation response", e);
        }

        return roadRiskRecords;
    }

    private List<RoadRiskRecord> mapToObjects(JsonNode rootNode) throws IOException {
        List<RoadRiskRecord> roadRiskRecords = new ArrayList<>();

        if (rootNode.isArray()) {
            for (JsonNode recordNode : rootNode) {
                RoadRiskRecord roadRiskRecord = new RoadRiskRecord();
                roadRiskRecord.setForecastTime(parseDateTime(recordNode.get("dt")));

                final JsonNode coordNode = recordNode.get("coord");
                roadRiskRecord.setCoordinates(Coordinates.of(coordNode.get(0).asDouble(), coordNode.get(1).asDouble()));

                roadRiskRecord.setWeather(objectMapper.readValue(objectMapper.treeAsTokens(recordNode.get("weather")), Weather.class));
                if (recordNode.has("road")) {
                    roadRiskRecord.setRoadDetails(objectMapper.readValue(objectMapper.treeAsTokens(recordNode.get("road")), RoadDetails.class));
                }

                final JsonNode alertsNode = recordNode.get("alerts");
                if (alertsNode != null && alertsNode.isArray()) {
                    List<Alert> alerts = new ArrayList<>();
                    for (JsonNode alertNode : alertsNode) {
                        alerts.add(objectMapper.readValue(objectMapper.treeAsTokens(alertNode), Alert.class));
                    }

                    roadRiskRecord.setAlerts(alerts);
                }

                roadRiskRecords.add(roadRiskRecord);
            }
        }

        return roadRiskRecords;
    }
}

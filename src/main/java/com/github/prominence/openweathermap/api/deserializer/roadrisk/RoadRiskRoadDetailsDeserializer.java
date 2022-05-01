package com.github.prominence.openweathermap.api.deserializer.roadrisk;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.enums.RoadState;
import com.github.prominence.openweathermap.api.model.roadrisk.RoadDetails;

import java.io.IOException;

public class RoadRiskRoadDetailsDeserializer extends JsonDeserializer<RoadDetails> {
    @Override
    public RoadDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        final RoadDetails roadDetails = new RoadDetails();
        roadDetails.setSurfaceTemperature(rootNode.get("temp").asDouble());
        roadDetails.setRoadState(RoadState.findByValue(rootNode.get("state").asInt()));

        return roadDetails;
    }
}

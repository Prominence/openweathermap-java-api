package com.github.prominence.openweathermap.api.deserializer.roadrisk;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.enums.EventLevel;
import com.github.prominence.openweathermap.api.model.roadrisk.Alert;

import java.io.IOException;

public class RoadRiskAlertDeserializer extends JsonDeserializer<Alert> {
    @Override
    public Alert deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        final Alert alert = new Alert();
        alert.setEvent(rootNode.get("event").asText());
        alert.setSenderName(rootNode.get("sender_name").asText());
        alert.setEventLevel(EventLevel.findByValue(rootNode.get("event_level").asInt()));
        return alert;
    }
}

package com.github.prominence.openweathermap.api.deserializer.forecast.daily;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.forecast.daily.Snow;

import java.io.IOException;

public class DailyForecastSnowDeserializer extends JsonDeserializer<Snow> {
    @Override
    public Snow deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        final JsonNode snowNode = rootNode.get("snow");
        if (snowNode != null) {
            return (Snow) Snow.withValue(snowNode.asDouble());
        }
        return null;
    }
}

package com.github.prominence.openweathermap.api.deserializer.forecast.climatic;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.Clouds;

import java.io.IOException;

public class ClimaticForecastCloudsDeserializer extends JsonDeserializer<Clouds> {
    @Override
    public Clouds deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        final JsonNode cloudsNode = rootNode.get("clouds");
        if (cloudsNode != null) {
            return Clouds.withValue((byte) cloudsNode.asInt());
        }

        return null;
    }
}

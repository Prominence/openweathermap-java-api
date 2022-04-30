package com.github.prominence.openweathermap.api.deserializer.forecast.climatic;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.forecast.climatic.Rain;

import java.io.IOException;

public class ClimaticForecastRainDeserializer extends JsonDeserializer<Rain> {
    @Override
    public Rain deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        final JsonNode rainNode = rootNode.get("rain");
        if (rainNode != null) {
            return (Rain) Rain.withValue(rainNode.asDouble());
        }
        return null;
    }
}

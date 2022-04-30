package com.github.prominence.openweathermap.api.deserializer.forecast.daily;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.forecast.daily.AtmosphericPressure;

import java.io.IOException;

public class DailyForecastAtmosphericPressureDeserializer extends JsonDeserializer<AtmosphericPressure> {
    @Override
    public AtmosphericPressure deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return (AtmosphericPressure) AtmosphericPressure.withValue(jsonNode.get("pressure").asDouble());
    }
}

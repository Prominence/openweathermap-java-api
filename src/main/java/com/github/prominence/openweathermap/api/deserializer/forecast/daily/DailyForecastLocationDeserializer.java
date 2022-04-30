package com.github.prominence.openweathermap.api.deserializer.forecast.daily;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.prominence.openweathermap.api.deserializer.CoordinatesDeserializer;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.forecast.daily.Location;

import java.io.IOException;

import static com.github.prominence.openweathermap.api.utils.JsonDeserializationUtils.parseZoneOffset;

public class DailyForecastLocationDeserializer extends JsonDeserializer<Location> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DailyForecastLocationDeserializer() {
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Coordinates.class, new CoordinatesDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public Location deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        final Location location = (Location) Location.withValues(rootNode.get("id").asInt(), rootNode.get("name").asText());

        final JsonNode timezoneNode = rootNode.get("timezone");
        if (timezoneNode != null) {
            location.setZoneOffset(parseZoneOffset(timezoneNode));
        }

        final JsonNode countryNode = rootNode.get("country");
        if (countryNode != null) {
            location.setCountryCode(countryNode.asText());
        }

        final JsonNode coordNode = rootNode.get("coord");
        if (coordNode != null) {
            location.setCoordinate(objectMapper.readValue(objectMapper.treeAsTokens(coordNode), Coordinates.class));
        }

        final JsonNode populationNode = rootNode.get("population");
        if (populationNode != null) {
            location.setPopulation(populationNode.asLong());
        }

        return location;
    }
}

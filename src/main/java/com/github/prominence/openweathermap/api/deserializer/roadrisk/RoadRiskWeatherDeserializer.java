package com.github.prominence.openweathermap.api.deserializer.roadrisk;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.roadrisk.Weather;

import java.io.IOException;

public class RoadRiskWeatherDeserializer extends JsonDeserializer<Weather> {
    @Override
    public Weather deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        final Weather weather = new Weather();
        weather.setTemperature(rootNode.get("temp").asDouble());
        weather.setDewPoint(rootNode.get("dew_point").asDouble());
        weather.setWindSpeed(rootNode.get("wind_speed").asDouble());
        weather.setWindDegrees(rootNode.get("wind_deg").asDouble());
        if (rootNode.has("precipitation_intensity")) {
            weather.setPrecipitationIntensity(rootNode.get("precipitation_intensity").asDouble());
        }
        if (rootNode.has("visibility")) {
            weather.setVisibilityInMetres(rootNode.get("visibility").asDouble());
        }

        return weather;
    }
}

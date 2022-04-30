package com.github.prominence.openweathermap.api.deserializer.forecast.daily;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.forecast.daily.Temperature;

import java.io.IOException;

public class DailyForecastTemperatureDeserializer extends JsonDeserializer<Temperature> {
    @Override
    public Temperature deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        final Temperature temperature = new Temperature();
        final JsonNode tempNode = rootNode.get("temp");
        temperature.setMorning(tempNode.get("morn").asDouble());
        temperature.setDay(tempNode.get("day").asDouble());
        temperature.setEve(tempNode.get("eve").asDouble());
        temperature.setNight(tempNode.get("night").asDouble());
        temperature.setMin(tempNode.get("min").asDouble());
        temperature.setMax(tempNode.get("max").asDouble());

        final JsonNode feelsLikeNode = rootNode.get("feels_like");
        temperature.setMorningFeelsLike(feelsLikeNode.get("morn").asDouble());
        temperature.setDayFeelsLike(feelsLikeNode.get("day").asDouble());
        temperature.setEveFeelsLike(feelsLikeNode.get("eve").asDouble());
        temperature.setNightFeelsLike(feelsLikeNode.get("night").asDouble());

        return temperature;
    }
}

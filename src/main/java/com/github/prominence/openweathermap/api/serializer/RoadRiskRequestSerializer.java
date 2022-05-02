package com.github.prominence.openweathermap.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.roadrisk.TrackPoint;
import com.github.prominence.openweathermap.api.request.roadrisk.model.RoadRiskRequestPayload;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;

public class RoadRiskRequestSerializer extends JsonSerializer<RoadRiskRequestPayload> {
    @Override
    public void serialize(RoadRiskRequestPayload roadRiskRequestPayload, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("track");

        final List<TrackPoint> trackPoints = roadRiskRequestPayload.getPayload();
        for (TrackPoint point : trackPoints) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("dt", point.getRequestedTime().atZone(ZoneId.systemDefault()).toEpochSecond());

            final Coordinates coordinates = point.getCoordinates();
            jsonGenerator.writeNumberField("lat", coordinates.getLatitude());
            jsonGenerator.writeNumberField("lon", coordinates.getLongitude());

            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}

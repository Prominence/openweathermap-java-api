package com.github.prominence.openweathermap.api.deserializer.radiation;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.radiation.SolarRadiationRecord;
import com.github.prominence.openweathermap.api.utils.JsonDeserializationUtils;

import java.io.IOException;

public class SolarRadiationRecordDeserializer extends JsonDeserializer<SolarRadiationRecord> {
    @Override
    public SolarRadiationRecord deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        SolarRadiationRecord record = new SolarRadiationRecord();
        final JsonNode radiationNode = rootNode.get("radiation");
        record.setMeasurementTime(JsonDeserializationUtils.parseDateTime(rootNode.get("dt")));
        record.setCloudSkyGlobalHorizontalIrradiance(radiationNode.get("ghi").asDouble());
        record.setCloudSkyDirectNormalIrradiance(radiationNode.get("dni").asDouble());
        record.setCloudSkyDiffuseHorizontalIrradiance(radiationNode.get("dhi").asDouble());
        record.setClearSkyGlobalHorizontalIrradiance(radiationNode.get("ghi_cs").asDouble());
        record.setClearSkyDirectNormalIrradiance(radiationNode.get("dni_cs").asDouble());
        record.setClearSkyDiffuseHorizontalIrradiance(radiationNode.get("dhi_cs").asDouble());
        return record;
    }
}

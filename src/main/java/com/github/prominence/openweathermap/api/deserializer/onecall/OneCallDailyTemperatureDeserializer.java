/*
 * Copyright (c) 2022 Alexey Zinchenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.prominence.openweathermap.api.deserializer.onecall;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.prominence.openweathermap.api.model.onecall.current.DailyTemperature;

import java.io.IOException;

public class OneCallDailyTemperatureDeserializer extends JsonDeserializer<DailyTemperature> {
    @Override
    public DailyTemperature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode rootNode = p.getCodec().readTree(p);

        final DailyTemperature temperature = new DailyTemperature();
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

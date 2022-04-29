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

package com.github.prominence.openweathermap.api.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.utils.JsonDeserializationUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper {
    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected LocalDateTime parseDateTime(JsonNode dateTimeNode) {
        return JsonDeserializationUtils.parseDateTime(dateTimeNode);
    }

    protected ZoneId parseZoneId(JsonNode zoneIdNode) {
        return JsonDeserializationUtils.parseZoneId(zoneIdNode);
    }

    protected ZoneOffset parseZoneOffset(JsonNode zoneOffsetNode) {
        return JsonDeserializationUtils.parseZoneOffset(zoneOffsetNode);
    }

    protected List<WeatherState> parseWeatherStates(JsonNode weatherArrayNode) throws IOException {
        List<WeatherState> weatherStateList = new ArrayList<>();
        if (weatherArrayNode != null && weatherArrayNode.isArray()) {
            for (JsonNode weatherNode : weatherArrayNode) {
                weatherStateList.add(objectMapper.readValue(objectMapper.treeAsTokens(weatherNode), WeatherState.class));
            }
        }
        return weatherStateList;
    }
}

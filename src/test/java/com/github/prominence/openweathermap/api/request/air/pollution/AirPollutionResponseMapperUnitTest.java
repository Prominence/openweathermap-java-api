/*
 * Copyright (c) 2021-present Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.request.air.pollution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionDetailsModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AirPollutionResponseMapperUnitTest {
    @Test
    public void mapToAirPollution_withInvalidJSON() {
        final String jsonString = "{\"coord\":{lon\":27.34,\"lat\":53.54},\"list\":[{\"main\":{\"aqi\":1},\"components\":{\"co\":243.66,\"no\":0,\"no2\":4.07,\"o3\":62.23,\"so2\":1.77,\"pm2_5\":3.87,\"pm10\":4.58,\"nh3\":2.41},\"dt\":1618610400}]}";
        assertThrows(JsonMappingException.class, () -> new ObjectMapper().readValue(jsonString, AirPollutionDetailsModel.class));
    }

    @Test
    public void mapToAirPollution_withCoordinatesVariation() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"coord\":{},\"list\":[{\"main\":{\"aqi\":1},\"components\":{\"co\":243.66,\"no\":0,\"no2\":4.07,\"o3\":62.23,\"so2\":1.77,\"pm2_5\":3.87,\"pm10\":4.58,\"nh3\":2.41},\"dt\":1618610400}]}";
        AirPollutionDetailsModel airPollutionDetails = objectMapper.readValue(jsonString, AirPollutionDetailsModel.class);

        assertNotNull(airPollutionDetails);

        jsonString = "{\"coord\":{\"lon\":27.34},\"list\":[{\"main\":{\"aqi\":1},\"components\":{\"co\":243.66,\"no\":0,\"no2\":4.07,\"o3\":62.23,\"so2\":1.77,\"pm2_5\":3.87,\"pm10\":4.58,\"nh3\":2.41},\"dt\":1618610400}]}";
        airPollutionDetails = objectMapper.readValue(jsonString, AirPollutionDetailsModel.class);

        assertNotNull(airPollutionDetails);

        jsonString = "{\"coord\":{\"lat\":53.54},\"list\":[{\"main\":{\"aqi\":1},\"components\":{\"co\":243.66,\"no\":0,\"no2\":4.07,\"o3\":62.23,\"so2\":1.77,\"pm2_5\":3.87,\"pm10\":4.58,\"nh3\":2.41},\"dt\":1618610400}]}";
        airPollutionDetails = objectMapper.readValue(jsonString, AirPollutionDetailsModel.class);

        assertNotNull(airPollutionDetails);
    }
}

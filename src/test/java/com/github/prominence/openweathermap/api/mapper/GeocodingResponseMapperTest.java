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

package com.github.prominence.openweathermap.api.mapper;

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingModel;
import com.github.prominence.openweathermap.api.model.geocoding.ZipCodeGeocodingModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAs;
import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAsList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeocodingResponseMapperTest {

    @Test
    public void testDeserialize_ShouldSucceed_WhenCalledWithValidReverseGeocodingJson() throws IOException {
        //given
        final String resource = "/responses/valid/geocoding-reverse.json";

        //when
        List<GeocodingModel> actual = loadDeserializedResourceAsList(resource, GeocodingModel.class);

        //then
        assertNotNull(actual);
        assertEquals(5, actual.size());
    }

    @Test
    public void testDeserialize_ShouldSucceed_WhenCalledWithValidZipCodeGeocodingJson() throws IOException {
        //given
        final String resource = "/responses/valid/geocoding-zipcode.json";

        //when
        ZipCodeGeocodingModel actual = loadDeserializedResourceAs(resource, ZipCodeGeocodingModel.class);

        //then
        assertNotNull(actual);
        assertEquals("90210", actual.getZipCode());
        assertEquals("Beverly Hills", actual.getName());
        assertEquals("US", actual.getCountryCode());
        assertEquals(new Coordinates(34.0901, -118.4065), actual.getCoordinates());
    }
}

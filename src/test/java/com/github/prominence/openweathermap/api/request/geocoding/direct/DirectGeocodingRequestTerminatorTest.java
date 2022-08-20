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

package com.github.prominence.openweathermap.api.request.geocoding.direct;

import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.model.geocoding.Geocoding;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingModel;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectGeocodingRequestTerminatorTest {

    @Test
    void testAsXML_ShouldThrowException_WhenCalled() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        final DirectGeocodingRequestTerminator underTest =
                new DirectGeocodingRequestTerminator(requestSettings);

        //when
        Assertions.assertThrows(UnsupportedOperationException.class, underTest::asXML);

        //then + exception
    }

    @Test
    void testAsHTML_ShouldThrowException_WhenCalled() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        final DirectGeocodingRequestTerminator underTest =
                new DirectGeocodingRequestTerminator(requestSettings);

        //when
        Assertions.assertThrows(UnsupportedOperationException.class, underTest::asHTML);

        //then + exception
    }

    @Test
    void testGetValueType_ShouldReturnExpectedClass_WhenCalled() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        final DirectGeocodingRequestTerminator underTest =
                new DirectGeocodingRequestTerminator(requestSettings);

        //when
        final Class<Geocoding> actual = underTest.getValueType();

        //then
        assertEquals(Geocoding.class, actual);
    }

    @Test
    void testGetInnerType_ShouldReturnExpectedClass_WhenCalled() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        final DirectGeocodingRequestTerminator underTest =
                new DirectGeocodingRequestTerminator(requestSettings);

        //when
        final Class<GeocodingModel> actual = underTest.getInnerType();

        //then
        assertEquals(GeocodingModel.class, actual);
    }
}

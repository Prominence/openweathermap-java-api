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
import com.github.prominence.openweathermap.api.request.RequestSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class ZipCodeGeocodingAsyncRequestTerminatorTest {

    @Test
    void testAsXML_ShouldThrowException_WhenCalled() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        final ZipCodeGeocodingAsyncRequestTerminator underTest =
                new ZipCodeGeocodingAsyncRequestTerminator(requestSettings);

        //when
        Assertions.assertThrows(ExecutionException.class, () -> underTest.asXML().get());

        //then + exception
    }

    @Test
    void testAsHTML_ShouldThrowException_WhenCalled() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        final ZipCodeGeocodingAsyncRequestTerminator underTest =
                new ZipCodeGeocodingAsyncRequestTerminator(requestSettings);

        //when
        Assertions.assertThrows(ExecutionException.class, () -> underTest.asHTML().get());

        //then + exception
    }
}

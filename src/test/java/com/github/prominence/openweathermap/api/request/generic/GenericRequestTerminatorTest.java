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

package com.github.prominence.openweathermap.api.request.generic;

import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.enums.ResponseType;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.ApiPayloadParseException;
import com.github.prominence.openweathermap.api.model.generic.clouds.CloudCoverage;
import com.github.prominence.openweathermap.api.model.generic.clouds.Clouds;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;

class GenericRequestTerminatorTest {

    private static final String ALL_50 = "{\"all\":50}";

    @Test
    void testAsJava_ShouldMapRawResponseToJavObject_WhenRawResponseExists() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        GenericRequestTerminator<CloudCoverage, Clouds> underTest = new GenericRequestTerminator<CloudCoverage, Clouds>(requestSettings) {
            @Override
            protected Class<Clouds> getValueType() {
                return Clouds.class;
            }

            @Override
            protected String getRawResponse() {
                return ALL_50;
            }
        };

        //when
        final CloudCoverage actual = underTest.asJava();

        //then
        assertEquals(new Clouds(50), actual);
    }

    @Test
    void testAsJava_ShouldThrowApiPayloadParseException_WhenRawResponseCannotBeMapped() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        GenericRequestTerminator<CloudCoverage, Clouds> underTest = new GenericRequestTerminator<CloudCoverage, Clouds>(requestSettings) {
            @Override
            protected Class<Clouds> getValueType() {
                return Clouds.class;
            }

            @Override
            protected String getRawResponse() {
                return "{\"all\":\"-\"}";
            }
        };

        //when
        assertThrows(ApiPayloadParseException.class, underTest::asJava);

        //then + exception
    }

    @Test
    void testAsJava_ShouldSetUnitSystemToStandard_WhenTheRequestIsPrepared() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        requestSettings.setUnitSystem(UnitSystem.IMPERIAL);
        RequestSettings spyRequestSettings = spy(requestSettings);
        GenericRequestTerminator<CloudCoverage, Clouds> underTest =
                spy(new GenericRequestTerminator<CloudCoverage, Clouds>(spyRequestSettings) {
                    @Override
                    protected Class<Clouds> getValueType() {
                        return Clouds.class;
                    }

                    @Override
                    protected String getRawResponse() {
                        return ALL_50;
                    }
                });

        //when
        underTest.asJava();

        //then
        final InOrder inOrder = inOrder(spyRequestSettings, underTest);
        inOrder.verify(spyRequestSettings).setUnitSystem(eq(UnitSystem.STANDARD));
        inOrder.verify(underTest).getRawResponse();
    }

    @Test
    void testAsJson_ShouldSetTheUnitSystemAndTheResponseType_WhenTheRequestIsPrepared() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        requestSettings.setUnitSystem(UnitSystem.IMPERIAL);
        RequestSettings spyRequestSettings = spy(requestSettings);
        GenericRequestTerminator<CloudCoverage, Clouds> underTest =
                spy(new GenericRequestTerminator<CloudCoverage, Clouds>(spyRequestSettings) {
                    @Override
                    protected Class<Clouds> getValueType() {
                        return Clouds.class;
                    }

                    @Override
                    protected String getRawResponse() {
                        return ALL_50;
                    }
                });

        //when
        final String actual = underTest.asJSON(UnitSystem.METRIC);

        //then
        assertEquals(ALL_50, actual);
        final InOrder inOrder = inOrder(spyRequestSettings, underTest);
        inOrder.verify(spyRequestSettings).setUnitSystem(UnitSystem.METRIC);
        inOrder.verify(spyRequestSettings).setResponseType(ResponseType.JSON);
        inOrder.verify(underTest).getRawResponse();
    }

    @Test
    void testAsXml_ShouldSetTheUnitSystemAndTheResponseType_WhenTheRequestIsPrepared() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        requestSettings.setUnitSystem(UnitSystem.IMPERIAL);
        RequestSettings spyRequestSettings = spy(requestSettings);
        GenericRequestTerminator<CloudCoverage, Clouds> underTest =
                spy(new GenericRequestTerminator<CloudCoverage, Clouds>(spyRequestSettings) {
                    @Override
                    protected Class<Clouds> getValueType() {
                        return Clouds.class;
                    }

                    @Override
                    protected String getRawResponse() {
                        return ALL_50;
                    }
                });

        //when
        final String actual = underTest.asXML(UnitSystem.METRIC);

        //then
        assertEquals(ALL_50, actual);
        final InOrder inOrder = inOrder(spyRequestSettings, underTest);
        inOrder.verify(spyRequestSettings).setUnitSystem(UnitSystem.METRIC);
        inOrder.verify(spyRequestSettings).setResponseType(ResponseType.XML);
        inOrder.verify(underTest).getRawResponse();
    }

    @Test
    void testAsHtml_ShouldSetTheUnitSystemAndTheResponseType_WhenTheRequestIsPrepared() {
        //given
        final RequestSettings requestSettings = new RequestSettings(ApiConfiguration.builder().apiKey("-").build());
        requestSettings.setUnitSystem(UnitSystem.IMPERIAL);
        RequestSettings spyRequestSettings = spy(requestSettings);
        GenericRequestTerminator<CloudCoverage, Clouds> underTest =
                spy(new GenericRequestTerminator<CloudCoverage, Clouds>(spyRequestSettings) {
                    @Override
                    protected Class<Clouds> getValueType() {
                        return Clouds.class;
                    }

                    @Override
                    protected String getRawResponse() {
                        return ALL_50;
                    }
                });

        //when
        final String actual = underTest.asHTML(UnitSystem.METRIC);

        //then
        assertEquals(ALL_50, actual);
        final InOrder inOrder = inOrder(spyRequestSettings, underTest);
        inOrder.verify(spyRequestSettings).setUnitSystem(UnitSystem.METRIC);
        inOrder.verify(spyRequestSettings).setResponseType(ResponseType.HTML);
        inOrder.verify(underTest).getRawResponse();
    }
}

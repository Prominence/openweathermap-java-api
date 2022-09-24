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

package com.github.prominence.openweathermap.api.core.net;

import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HttpURLConnectionBasedHttpClientTest {

    public static Stream<Arguments> validGetRequestDataProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of("http://localhost", 200, "response line 1\nline 2\n\n4"))
                .add(Arguments.of("http://127.0.0.1", 200, "{}"))
                .build();
    }

    public static Stream<Arguments> invalidGetRequestDataProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of("http://localhost", 404, false, NoDataFoundException.class))
                .add(Arguments.of("http://127.0.0.1", 400, false, NoDataFoundException.class))
                .add(Arguments.of("http://127.0.0.1", 401, false, InvalidAuthTokenException.class))
                .add(Arguments.of("http://localhost", 301, true, NoDataFoundException.class))
                .build();
    }

    public static Stream<Arguments> validPostRequestDataProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of("http://localhost", "input", 200, "response line 1\nline 2\n\n4"))
                .add(Arguments.of("http://127.0.0.1", null, 200, "{}"))
                .build();
    }

    public static Stream<Arguments> cleanUpInputProvider() {
        return Stream.<Arguments>builder()
                .add(Arguments.of("http://localhost", 200, "response line 1\nline 2\n\n4"))
                .add(Arguments.of("http://localhost", 301, "response line 1\nline 2\n\n4"))
                .add(Arguments.of("http://127.0.0.1", 404, null))
                .add(Arguments.of("http://127.0.0.1", 401, null))
                .build();
    }

    @Test
    void testSetTimeoutSettings_ShouldSetTimeOutValues_WhenCalled() throws IOException {
        //given
        HttpURLConnectionBasedHttpClient underTest = spy(new HttpURLConnectionBasedHttpClient());
        final TimeoutSettings timeoutSettings = new TimeoutSettings(500, 1000);
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(connection.getResponseCode()).thenReturn(200);
        when(connection.getInputStream()).thenReturn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        doReturn(connection).when(underTest).getConnection(anyString());

        //when
        underTest.setTimeoutSettings(timeoutSettings);
        underTest.executeGetRequest("http://localhsot");

        //then
        verify(connection).setConnectTimeout(eq(timeoutSettings.getConnectionTimeout()));
        verify(connection).setReadTimeout(eq(timeoutSettings.getReadTimeout()));
    }

    @ParameterizedTest
    @MethodSource("validPostRequestDataProvider")
    void testExecutePostRequest_ShouldProcessResponse_WhenThereIsOne(
            final String url, final String body, final int responseCode, final String response) throws IOException {
        //given
        HttpURLConnectionBasedHttpClient underTest = spy(new HttpURLConnectionBasedHttpClient());
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(connection.getResponseCode()).thenReturn(responseCode);
        when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8)));
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        doReturn(connection).when(underTest).getConnection(urlCaptor.capture());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        doReturn(output).when(connection).getOutputStream();

        //when
        final String actual = underTest.executePostRequest(url, body);

        //then
        verify(underTest).getConnection(eq(url));
        verify(connection).getResponseCode();
        verify(connection).getInputStream();
        verify(connection).setRequestMethod("POST");
        assertEquals(Optional.ofNullable(body).orElse(""), output.toString(StandardCharsets.UTF_8.toString()));
        assertEquals(response, actual);
    }

    @ParameterizedTest
    @MethodSource("validGetRequestDataProvider")
    void testExecuteGetRequest_ShouldProcessResponse_WhenThereIsOne(
            final String url, final int responseCode, final String response) throws IOException {
        //given
        HttpURLConnectionBasedHttpClient underTest = spy(new HttpURLConnectionBasedHttpClient());
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(connection.getResponseCode()).thenReturn(responseCode);
        when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8)));
        doReturn(connection).when(underTest).getConnection(eq(url));

        //when
        final String actual = underTest.executeGetRequest(url);

        //then
        verify(underTest).getConnection(eq(url));
        verify(connection).getResponseCode();
        verify(connection).getInputStream();
        verify(connection).setRequestMethod("GET");
        assertEquals(response, actual);
    }

    @ParameterizedTest
    @MethodSource("invalidGetRequestDataProvider")
    void testExecuteGetRequest_ShouldThrowException_WhenTheRequestWasNotSuccessful(
            final String url,
            final int responseCode,
            final boolean shouldCallInputStream,
            final Class<? extends Exception> exception) throws IOException {
        //given
        HttpURLConnectionBasedHttpClient underTest = spy(new HttpURLConnectionBasedHttpClient());
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(connection.getResponseCode()).thenReturn(responseCode);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        doReturn(connection).when(underTest).getConnection(urlCaptor.capture());

        //when
        assertThrows(exception, () -> underTest.executeGetRequest(url));

        //then + exception
        verify(connection).setRequestMethod("GET");
        verify(connection).getResponseCode();
        if (!shouldCallInputStream) {
            verify(connection, never()).getInputStream();
        }
        assertEquals(url, urlCaptor.getValue());
    }

    @ParameterizedTest
    @MethodSource("cleanUpInputProvider")
    void testExecuteGetRequest_ShouldAttemptToCloseTheResponseInputStream_WhenCalled(
            final String url,
            final int responseCode,
            final String response) throws IOException {
        //given
        HttpURLConnectionBasedHttpClient underTest = spy(new HttpURLConnectionBasedHttpClient());
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(connection.getResponseCode()).thenReturn(responseCode);
        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        if (response == null) {
            when(connection.getInputStream()).thenReturn(null);
        } else {
            final ByteArrayInputStream stream = mock(ByteArrayInputStream.class);
            doThrow(new IOException("fail")).when(stream).close();
            when(connection.getInputStream()).thenReturn(stream);
        }
        doReturn(connection).when(underTest).getConnection(urlCaptor.capture());

        //when
        assertThrows(Exception.class, () -> underTest.executeGetRequest(url));

        //then + exception
        verify(connection).setRequestMethod("GET");
        verify(connection).getResponseCode();
        verify(connection, atMostOnce()).getInputStream();
        assertEquals(url, urlCaptor.getValue());
    }
}

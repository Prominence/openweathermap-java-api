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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpURLConnectionBasedHttpClient implements HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpURLConnectionBasedHttpClient.class);

    private TimeoutSettings timeoutSettings;

    @Override
    public void setTimeoutSettings(TimeoutSettings timeoutSettings) {
        this.timeoutSettings = timeoutSettings;
    }

    @Override
    public String executeGetRequest(String url) {
        return doExecute(url, RequestExecutor.Method.GET, null);
    }

    private String doExecute(String url, RequestExecutor.Method method, String body) {
        InputStream resultStream = null;
        try {
            HttpURLConnection connection = getConnection(url);
            configureTimeouts(connection);
            configureConnection(connection, method, body);

            resultStream = evaluateResponse(connection);
            logger.debug("Executing OpenWeatherMap API request: " + url);
            return convertInputStreamToString(resultStream);
        } catch (IllegalStateException | IOException ex) {
            closeQuietly(resultStream);
            logger.error("An error occurred during OpenWeatherMap API response parsing: ", ex);
            throw new NoDataFoundException(ex);
        }
    }

    HttpURLConnection getConnection(String url) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
    }

    private void closeQuietly(InputStream resultStream) {
        if (resultStream != null) {
            try {
                resultStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private InputStream evaluateResponse(HttpURLConnection connection) throws IOException {
        final int responseCode = connection.getResponseCode();
        switch (responseCode) {
            case HttpURLConnection.HTTP_OK:
                return connection.getInputStream();
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                throw new InvalidAuthTokenException();
            case HttpURLConnection.HTTP_NOT_FOUND:
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new NoDataFoundException();
            default:
                throw new IllegalStateException("Unexpected value: " + responseCode);
        }
    }

    private void configureTimeouts(HttpURLConnection connection) {
        Optional.ofNullable(timeoutSettings)
                .ifPresent(ts -> {
                    Optional.ofNullable(ts.getConnectionTimeout())
                            .ifPresent(connection::setConnectTimeout);
                    Optional.ofNullable(ts.getReadTimeout())
                            .ifPresent(connection::setReadTimeout);
                });
    }

    private void configureConnection(HttpURLConnection connection, RequestExecutor.Method method, String body) throws IOException {
        connection.setRequestMethod(method.name());
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        addOptionalBodyContent(connection, body);
    }

    @Override
    public String executePostRequest(String url, String body) {
        return doExecute(url, RequestExecutor.Method.POST, body);
    }

    private void addOptionalBodyContent(HttpURLConnection connection, String body) throws IOException {
        if (body != null) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }
    }

    /**
     * Reads the input stream line-by-line and returns its content in <code>String</code> representation.
     *
     * @param inputStream input stream to convert.
     * @return converted <code>InputStream</code> content.
     * @throws IllegalArgumentException in case if input stream is unable to be read.
     */
    private String convertInputStreamToString(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException ex) {
            logger.error("Error during response reading: ", ex);
            throw new IllegalArgumentException(ex);
        }
    }
}

/*
 * Copyright (c) 2021 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.utils;

import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Utility class for API calls execution.
 */
public final class RequestUtils {

    private static final String OWM_URL_BASE = "https://api.openweathermap.org/data/2.5/";

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    private RequestUtils() {
    }

    public static String getResponse(RequestSettings requestSettings) {
        StringBuilder requestUrlBuilder = new StringBuilder(OWM_URL_BASE);
        requestUrlBuilder.append(requestSettings.getUrlAppender());
        requestUrlBuilder.append('?');
        String parameters = requestSettings.getRequestParameters().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        requestUrlBuilder.append(parameters);

        return getResponse(requestUrlBuilder.toString(), requestSettings.getTimeoutSettings());
    }

    /**
     * Executes call to provided API url and retrieves response in <code>String</code> representation.
     *
     * @param url the url to make API request.
     * @return response from the request in <code>String</code> representation.
     * @throws IllegalArgumentException in case if provided parameter isn't a valid url for {@link URL} instance.
     */
    public static String getResponse(String url) {
        return getResponse(url, new TimeoutSettings());
    }

    /**
     * Executes call to provided API url and retrieves response in <code>String</code> representation.
     *
     * @param url the url to make API request.
     * @param timeoutSettings an object with timeout settings.
     * @return response from the request in <code>String</code> representation.
     * @throws IllegalArgumentException in case if provided parameter isn't a valid url for {@link URL} instance.
     */
    public static String getResponse(String url, TimeoutSettings timeoutSettings) {
        URL requestUrl;
        try {
            requestUrl = new URL(url);
        } catch (MalformedURLException ex) {
            logger.error("Invalid URL: ", ex);
            throw new IllegalArgumentException(ex);
        }
        logger.debug("Executing OpenWeatherMap API request: " + url);
        final InputStream requestInputStream = executeRequest(requestUrl, timeoutSettings);

        return convertInputStreamToString(requestInputStream);
    }

    /**
     * Executes call to provided API url and retrieves response as an <code>InputStream</code> instance.
     *
     * @param requestUrl url for API call execution.
     * @return <code>InputStream</code> instance containing http response body.
     * @throws InvalidAuthTokenException in case if authentication token wasn't set or requested functionality is not permitted for its subscription plan.
     * @throws NoDataFoundException in case if there is no any data for requested location(s) or request is invalid.
     */
    private static InputStream executeRequest(URL requestUrl, TimeoutSettings timeoutSettings) {
        InputStream resultStream;

        try {
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            if (timeoutSettings.getConnectionTimeout() != null) {
                connection.setConnectTimeout(timeoutSettings.getConnectionTimeout());
            }

            if (timeoutSettings.getReadTimeout() != null) {
                connection.setReadTimeout(timeoutSettings.getReadTimeout());
            }

            connection.setRequestMethod("GET");

            resultStream = switch (connection.getResponseCode()) {
                case HttpURLConnection.HTTP_OK -> connection.getInputStream();
                case HttpURLConnection.HTTP_UNAUTHORIZED -> throw new InvalidAuthTokenException();
                case HttpURLConnection.HTTP_NOT_FOUND, HttpURLConnection.HTTP_BAD_REQUEST ->
                        throw new NoDataFoundException();
                default -> throw new IllegalStateException("Unexpected value: " + connection.getResponseCode());
            };
        } catch (IllegalStateException | IOException ex) {
            logger.error("An error occurred during OpenWeatherMap API response parsing: ", ex);
            throw new NoDataFoundException(ex);
        }

        return resultStream;
    }

    /**
     * Reads the input stream line-by-line and returns its content in <code>String</code> representation.
     *
     * @param inputStream input stream to convert.
     * @return converted <code>InputStream</code> content.
     * @throws IllegalArgumentException in case if input stream is unable to be read.
     */
    private static String convertInputStreamToString(InputStream inputStream) {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException ex) {
            logger.error("Error during response reading: ", ex);
            throw new IllegalArgumentException(ex);
        }

        return result.toString();
    }
}

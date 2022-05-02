package com.github.prominence.openweathermap.api.core.net;

import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpURLConnectionBasedHttpClient implements HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpURLConnectionBasedHttpClient.class);

    private TimeoutSettings timeoutSettings;

    @Override
    public void setTimeoutSettings(TimeoutSettings timeoutSettings) {
        this.timeoutSettings = timeoutSettings;
    }

    @Override
    public String executeGetRequest(String url) {
        InputStream resultStream;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            if (timeoutSettings != null) {
                if (timeoutSettings.getConnectionTimeout() != null) {
                    connection.setConnectTimeout(timeoutSettings.getConnectionTimeout());
                }

                if (timeoutSettings.getReadTimeout() != null) {
                    connection.setReadTimeout(timeoutSettings.getReadTimeout());
                }
            }

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

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
        logger.debug("Executing OpenWeatherMap API request: " + url);

        return convertInputStreamToString(resultStream);
    }

    @Override
    public String executePostRequest(String url, String body) {
        InputStream resultStream;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            if (timeoutSettings != null) {
                if (timeoutSettings.getConnectionTimeout() != null) {
                    connection.setConnectTimeout(timeoutSettings.getConnectionTimeout());
                }

                if (timeoutSettings.getReadTimeout() != null) {
                    connection.setReadTimeout(timeoutSettings.getReadTimeout());
                }
            }

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

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
        logger.debug("Executing OpenWeatherMap API request: " + url);

        return convertInputStreamToString(resultStream);
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

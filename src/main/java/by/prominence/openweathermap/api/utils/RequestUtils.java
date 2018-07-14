/*
 * Copyright (c) 2018 Alexey Zinchenko
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

package by.prominence.openweathermap.api.utils;

import by.prominence.openweathermap.api.exception.DataNotFoundException;
import by.prominence.openweathermap.api.exception.InvalidAuthTokenException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestUtils {

    public static InputStream executeGetRequest(URL requestUrl) throws InvalidAuthTokenException, DataNotFoundException {
        InputStream resultStream = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            switch (connection.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                    resultStream = connection.getInputStream();
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    throw new InvalidAuthTokenException();
                case HttpURLConnection.HTTP_NOT_FOUND:
                    throw new DataNotFoundException();
            }
        } catch (IOException | ClassCastException ex) {
            ex.printStackTrace();
        }

        return resultStream;
    }

}

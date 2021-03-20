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

package com.github.prominence.openweathermap.api.request;

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestUrlBuilder {

    private static final String API_KEY_PARAM_NAME = "appid";

    private final StringBuilder builder = new StringBuilder("http://api.openweathermap.org/data/2.5/");
    private final Map<String, Object> requestParameters = new HashMap<>();

    public RequestUrlBuilder(String key) {
        requestParameters.put(API_KEY_PARAM_NAME, key);
    }

    public void append(String value) {
        builder.append(value);
    }

    public void addRequestParameter(String key, Object value) {
        requestParameters.put(key, value);
    }

    public void applyCustomization(Language language, UnitSystem unitSystem) {
        if (language != null) {
            addRequestParameter("lang", language.getValue());
        }
        if (unitSystem != null && unitSystem != UnitSystem.STANDARD) {
            addRequestParameter("units", unitSystem.getValue());
        }
    }

    public String buildUrl() {
        final String joinedParameters = requestParameters.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        builder.append('?');
        builder.append(joinedParameters);
        return builder.toString();
    }
}

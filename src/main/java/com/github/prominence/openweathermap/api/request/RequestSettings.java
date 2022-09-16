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

package com.github.prominence.openweathermap.api.request;

import com.github.prominence.openweathermap.api.conf.TimeoutSettings;
import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.ResponseType;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class RequestSettings {

    public static final String LANG_PARAM = "lang";
    public static final String UNITS_PARAM = "units";
    public static final String MODE_PARAM = "mode";
    public static final String API_KEY_PARAM_NAME = "appid";
    public static final String LATITUDE_PARAM = "lat";
    public static final String LONGITUDE_PARAM = "lon";
    public static final String START_PARAM = "start";
    public static final String END_PARAM = "end";
    public static final String DATE_TIME_PARAM = "dt";
    public static final String QUERY_VALUE_PARAM = "q";
    public static final String ZIP_PARAM = "zip";
    public static final String COUNT_PARAM = "cnt";

    private TimeoutSettings timeoutSettings;

    private final Map<String, String> requestParameters = new HashMap<>(8);

    private final StringBuilder urlAppenderBuilder = new StringBuilder();
    private final ApiConfiguration apiConfiguration;

    private Language language = Language.ENGLISH;
    private UnitSystem unitSystem = UnitSystem.STANDARD;
    private Object requestPayload;

    public RequestSettings(ApiConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;
        this.putRequestParameter(API_KEY_PARAM_NAME, apiConfiguration.getApiKey());
        // make a copy
        this.timeoutSettings = new TimeoutSettings(apiConfiguration.getDefaultTimeoutSettings());
    }

    public TimeoutSettings getTimeoutSettings() {
        return timeoutSettings;
    }

    public void setTimeoutSettings(@NonNull TimeoutSettings timeoutSettings) {
        this.timeoutSettings = timeoutSettings;
    }

    public UnitSystem getUnitSystem() {
        return unitSystem;
    }

    public void setUnitSystem(UnitSystem unitSystem) {
        this.putRequestParameter(UNITS_PARAM, unitSystem.getValue());
        this.unitSystem = unitSystem;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.putRequestParameter(LANG_PARAM, language.getValue());
        this.language = language;
    }

    public void setResponseType(ResponseType responseType) {
        this.putRequestParameter(MODE_PARAM, responseType.getValue());
    }

    public void putRequestParameter(String key, String value) {
        this.requestParameters.put(key, value);
    }

    public void removeRequestParameter(String key) {
        this.requestParameters.remove(key);
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public void appendToURL(String appendix) {
        urlAppenderBuilder.append(appendix);
    }

    public StringBuilder getUrlAppender() {
        return urlAppenderBuilder;
    }

    public ApiConfiguration getApiConfiguration() {
        return apiConfiguration;
    }

    public Object getPayloadObject() {
        return requestPayload;
    }

    public void setPayloadObject(Object payload) {
        this.requestPayload = payload;
    }
}

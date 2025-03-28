/*
 * Copyright (c) 2022 Alexey Zinchenko
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
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.ResponseType;
import com.github.prominence.openweathermap.api.enums.UnitSystem;

import java.util.HashMap;
import java.util.Map;

public class RequestSettings {

    private static final String LANG_PARAM = "lang";
    private static final String UNITS_PARAM = "units";
    private static final String MODE_PARAM = "mode";
    private static final String API_KEY_PARAM_NAME = "appid";

    private final TimeoutSettings timeoutSettings;

    private final Map<String, String> requestParameters = new HashMap<>(8);

    private final StringBuilder urlAppenderBuilder = new StringBuilder("");

    private Language language = Language.ENGLISH;
    private UnitSystem unitSystem = UnitSystem.STANDARD;
    private boolean useApi3 = false;

    public RequestSettings(String apiKey, TimeoutSettings timeoutSettings) {
        this.putRequestParameter(API_KEY_PARAM_NAME, apiKey);
        // make a copy
        this.timeoutSettings = new TimeoutSettings(timeoutSettings);
    }

    public TimeoutSettings getTimeoutSettings() {
        return timeoutSettings;
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

    public void setUseApi3() {
        this.useApi3 = true;
    }

    public boolean getUseApi3() {
        return this.useApi3;
    }

    public StringBuilder getUrlAppender() {
        return urlAppenderBuilder;
    }
}

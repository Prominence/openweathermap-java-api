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

package com.github.prominence.openweathermap.api.request.geocoding.direct;

import com.github.prominence.openweathermap.api.mapper.GeocodingResponseMapper;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingRecord;
import com.github.prominence.openweathermap.api.model.geocoding.ZipCodeGeocodingRecord;
import com.github.prominence.openweathermap.api.request.RequestSettings;

import java.util.List;

public class DirectGeocodingRequester {
    private final RequestSettings requestSettings;

    public DirectGeocodingRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public DirectGeocodingRequestCustomizer<List<GeocodingRecord>> byLocationName(String cityName, String stateCode, String countryCode) {
        requestSettings.appendToURL("direct");
        requestSettings.putRequestParameter("q", cityName + "," + stateCode + "," + countryCode);
        return new DirectGeocodingRequestCustomizer<>(requestSettings, (String json) -> new GeocodingResponseMapper().mapGeocodingResponse(json));
    }

    public DirectGeocodingRequestCustomizer<ZipCodeGeocodingRecord> byZipCode(String zipCode, String countryCode) {
        requestSettings.appendToURL("zip");
        requestSettings.putRequestParameter("zip", zipCode + "," + countryCode);
        return new DirectGeocodingRequestCustomizer<>(requestSettings, (String json) -> new GeocodingResponseMapper().mapZipCodeGeocodingResponse(json));
    }
}

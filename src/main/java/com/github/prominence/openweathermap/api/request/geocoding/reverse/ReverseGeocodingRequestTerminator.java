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

package com.github.prominence.openweathermap.api.request.geocoding.reverse;

import com.github.prominence.openweathermap.api.core.net.RequestExecutor;
import com.github.prominence.openweathermap.api.enums.ApiVariant;
import com.github.prominence.openweathermap.api.model.geocoding.Geocoding;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingModel;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.GenericListRequestTerminator;
import com.github.prominence.openweathermap.api.request.generic.JsonApiTerminator;

import java.util.List;

public class ReverseGeocodingRequestTerminator
        extends GenericListRequestTerminator<Geocoding, GeocodingModel>
        implements JsonApiTerminator<List<Geocoding>> {
    ReverseGeocodingRequestTerminator(RequestSettings requestSettings) {
        super(requestSettings);
    }

    protected String getRawResponse() {
        return new RequestExecutor(requestSettings).getResponse(ApiVariant.BASE);
    }

    @Override
    public String asXML() {
        //Method meant to be hidden as only JsonApiTerminator is exposed
        throw new UnsupportedOperationException("XML format not supported for this API.");
    }

    @Override
    public String asHTML() {
        //Method meant to be hidden as only JsonApiTerminator is exposed
        throw new UnsupportedOperationException("HTML format not supported for this API.");
    }

    @Override
    protected Class<Geocoding> getValueType() {
        return Geocoding.class;
    }

    @Override
    protected Class<GeocodingModel> getInnerType() {
        return GeocodingModel.class;
    }
}

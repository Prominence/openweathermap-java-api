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

package com.github.prominence.openweathermap.api.request.air.pollution;

import com.github.prominence.openweathermap.api.core.net.RequestExecutor;
import com.github.prominence.openweathermap.api.enums.ApiVariant;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionDetails;
import com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionDetailsModel;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import com.github.prominence.openweathermap.api.request.generic.GenericRequestTerminator;
import com.github.prominence.openweathermap.api.request.generic.JsonApiTerminator;

/**
 * The type Air pollution request terminator.
 */
public class AirPollutionRequestTerminator
        extends GenericRequestTerminator<AirPollutionDetails, AirPollutionDetailsModel>
        implements JsonApiTerminator<AirPollutionDetails> {

    /**
     * Instantiates a new Air pollution request terminator.
     *
     * @param requestSettings request settings object.
     */
    AirPollutionRequestTerminator(RequestSettings requestSettings) {
        super(requestSettings);
    }

    @Override
    public String asXML(final UnitSystem unitSystem) {
        //Method meant to be hidden as only JsonApiTerminator is exposed
        throw new UnsupportedOperationException("XML format not supported for this API.");
    }

    @Override
    public String asHTML(final UnitSystem unitSystem) {
        //Method meant to be hidden as only JsonApiTerminator is exposed
        throw new UnsupportedOperationException("HTML format not supported for this API.");
    }

    protected String getRawResponse() {
        return new RequestExecutor(requestSettings).getResponse(ApiVariant.BASE);
    }

    @Override
    protected Class<AirPollutionDetailsModel> getValueType() {
        return AirPollutionDetailsModel.class;
    }
}

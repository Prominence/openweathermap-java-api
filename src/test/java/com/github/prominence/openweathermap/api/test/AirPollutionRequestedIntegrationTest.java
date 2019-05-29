/*
 * Copyright (c) 2019 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.test;

import com.github.prominence.openweathermap.api.AirPollutionRequester;
import com.github.prominence.openweathermap.api.constants.TimeFrame;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class AirPollutionRequestedIntegrationTest extends ApiTest {

    private static AirPollutionRequester airPollutionRequester;

    @BeforeClass
    public static void setup() {
        airPollutionRequester = getManager().getAirPollutionRequester(0f, 10f, new Date(116, 11, 25), TimeFrame.DAY);
    }

    @Test
    public void whenRequestAirPollutionState_thenReturnNotNull() {
        assert airPollutionRequester.retrieve() != null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenRequestAirPollutionStateWithoutAnyParam_thenThrowAnException() {
        AirPollutionRequester requester = getManager().getAirPollutionRequester(0f, 10f, null, TimeFrame.DAY);
        requester.retrieve();
    }
}

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

import com.github.prominence.openweathermap.api.UltravioletIndexRequester;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class UltravioletIndexRequestedIntegrationTest extends ApiTest {

    private static UltravioletIndexRequester minskUltravioletIndexRequester;

    @BeforeClass
    public static void setup() {
        minskUltravioletIndexRequester = getManager().getUltravioletIndexRequester(53.9f, 27.56667f);
    }

    @Test
    public void whenRequestUVI_thenReturnNotNull() {
        assert minskUltravioletIndexRequester.getCurrentUVIndex() != null;
    }

    @Test
    public void whenRequestUVIForecastFor5Days_thenReturnNotNull() {
        assert minskUltravioletIndexRequester.getUVIndexForecast(5) != null;
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestUVIForecastForWrongAmountOfDays_thenThrowAnException() {
        minskUltravioletIndexRequester.getUVIndexForecast(-2);
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestUVIForecastForLargeAmountOfDays_thenThrowAnException() {
        assert minskUltravioletIndexRequester.getUVIndexForecast(10000) != null;
    }

    @Test // check it later
    public void whenRequestUVIForPeriod_thenReturnNotNull() {
        assert minskUltravioletIndexRequester.getUVIndexByPeriod(new Date(), new Date()) != null;
    }

    @Test(expected = NullPointerException.class) // not good, will be reimplemented in further versions
    public void whenRequestUVIForNullPeriod_thenThrowAnException() {
        minskUltravioletIndexRequester.getUVIndexByPeriod(null, null);
    }

    @Test(expected = NullPointerException.class) // not good, will be reimplemented in further versions
    public void whenRequestUVIForNullCoordinates_thenThrowAnException() {
        UltravioletIndexRequester requester = getManager().getUltravioletIndexRequester(null);
        requester.getCurrentUVIndex();
    }
}

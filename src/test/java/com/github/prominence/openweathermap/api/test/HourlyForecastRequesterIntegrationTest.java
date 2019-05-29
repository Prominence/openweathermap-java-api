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

import com.github.prominence.openweathermap.api.HourlyForecastRequester;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.model.Coordinates;
import org.junit.BeforeClass;
import org.junit.Test;

public class HourlyForecastRequesterIntegrationTest extends ApiTest {

    private static HourlyForecastRequester hourlyForecastRequester;

    @BeforeClass
    public static void setup() {
        hourlyForecastRequester = getManager().getHourlyForecastRequester();
    }

    @Test
    public void whenRequestForecastForMinskByCity() {
        assert hourlyForecastRequester.getByCityName("Minsk") != null;
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestForecastByWrongCity_thenThrowAnException() {
        hourlyForecastRequester.getByCityId("IncorrectCity");
    }

    @Test
    public void whenRequestForecastForMinskById_thenReturnNotNull() {
        assert hourlyForecastRequester.getByCityId("625144") != null;
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestForecastByWrongId_thenThrowAnException() {
        hourlyForecastRequester.getByCityId("IncorrectId");
    }

    @Test
    public void whenRequestForecastByCoordinates_thenReturnNotNull() {
        // given
        float latitude = 53.9f;
        float longitude = 27.56667f;
        Coordinates coordinates = new Coordinates(latitude, longitude);

        // expected
        assert hourlyForecastRequester.getByCoordinates(coordinates) != null;
        assert hourlyForecastRequester.getByCoordinates(latitude, longitude) != null;
    }

    @Test(expected = NullPointerException.class) // not good, will be reimplemented in further versions
    public void whenRequestByNullCoordinates_thenThrowAnException() {
        hourlyForecastRequester.getByCoordinates(null);
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestForecastByWrongCoordinates_thenThrowAnException() {
        hourlyForecastRequester.getByCoordinates(91f, 180f);
    }

    @Test
    public void whenRequestForecastByMaxCoordinates_thenReturnNotNull() {
        assert hourlyForecastRequester.getByCoordinates(90f, 180f) != null;
    }

    @Test
    public void whenRequestForecastByMinCoordinates_thenReturnNotNull() {
        assert hourlyForecastRequester.getByCoordinates(-90f, -180f) != null;
    }

    @Test
    public void whenRequestForecastByZipCode_thenReturnNotNull() {
        assert hourlyForecastRequester.getByZIPCode("220015", "BY") != null;
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestForecastByWrongZipCode_thenThrowAnException() {
        hourlyForecastRequester.getByZIPCode("wrongZipCode", "BY");
    }
}

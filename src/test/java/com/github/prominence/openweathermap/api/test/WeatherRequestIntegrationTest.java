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

import com.github.prominence.openweathermap.api.WeatherRequester;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.model.Coordinates;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherRequestIntegrationTest extends ApiTest {

    private static WeatherRequester weatherRequester;

    @BeforeClass
    public static void setup() {
        weatherRequester = getManager().getWeatherRequester();
    }

    @Test
    public void whenRequestWeatherForMinskByName_thenReturnNotNull() {
        assert weatherRequester.getByCityName("Minsk") != null;
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestWeatherByWrongCity_thenThrowAnException() {
        weatherRequester.getByCityId("IncorrectCity");
    }

    @Test
    public void whenRequestWeatherForMinskById_thenReturnNotNull() {
        assert weatherRequester.getByCityId("625144") != null;
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestWeatherByWrongId_thenThrowAnException() {
        weatherRequester.getByCityId("IncorrectId");
    }

    @Test
    public void whenRequestWeatherByCoordinates_thenReturnNotNull() {
        // given
        float latitude = 53.9f;
        float longitude = 27.56667f;
        Coordinates coordinates = new Coordinates(latitude, longitude);

        // expected
        assert weatherRequester.getByCoordinates(coordinates) != null;
        assert weatherRequester.getByCoordinates(latitude, longitude) != null;
    }

    @Test(expected = NullPointerException.class) // not good, will be reimplemented in further versions
    public void whenRequestByNullCoordinates_thenThrowAnException() {
        weatherRequester.getByCoordinates(null);
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestWeatherByWrongCoordinates_thenThrowAnException() {
        weatherRequester.getByCoordinates(91f, 180f);
    }

    @Test
    public void whenRequestWeatherByMaxCoordinates_thenReturnNotNull() {
        assert weatherRequester.getByCoordinates(90f, 180f) != null;
    }

    @Test
    public void whenRequestWeatherByMinCoordinates_thenReturnNotNull() {
        assert weatherRequester.getByCoordinates(-90f, -180f) != null;
    }

    @Test
    public void whenRequestWeatherByZipCode_thenReturnNotNull() {
        assert weatherRequester.getByZIPCode("220015", "BY") != null;
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRequestWeatherByWrongZipCode_thenThrowAnException() {
        weatherRequester.getByZIPCode("wrongZipCode", "BY");
    }
}

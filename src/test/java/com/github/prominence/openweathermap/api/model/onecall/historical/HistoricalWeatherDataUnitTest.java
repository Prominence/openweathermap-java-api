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

package com.github.prominence.openweathermap.api.model.onecall.historical;

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.onecall.Measurement;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class HistoricalWeatherDataUnitTest {
    @Test
    public void getCoordinates() {
        final HistoricalWeather historicalWeatherData = new HistoricalWeather();
        final Coordinates coordinates = new Coordinates(11.2, 43.2);
        historicalWeatherData.setLatitude(coordinates.getLatitude());
        historicalWeatherData.setLongitude(coordinates.getLongitude());

        assertEquals(coordinates, historicalWeatherData.getCoordinates());
    }

    @Test
    public void getTimezone() {
        final HistoricalWeather historicalWeatherData = new HistoricalWeather();
        final ZoneId timeZone = ZoneId.of("GMT");
        historicalWeatherData.setTimezone(timeZone);

        assertEquals(timeZone, historicalWeatherData.getTimezone());
    }

    @Test
    public void getTimezoneOffset() {
        final HistoricalWeather historicalWeatherData = new HistoricalWeather();
        final ZoneOffset offset = ZoneOffset.UTC;
        historicalWeatherData.setTimezoneOffset(offset);

        assertEquals(offset, historicalWeatherData.getTimezoneOffset());
    }

    @Test
    public void getHistoricalWeather() {
        final HistoricalWeather historicalWeatherData = new HistoricalWeather();
        final List<Measurement> historicalWeather = Collections.singletonList(new Measurement());
        historicalWeatherData.setData(historicalWeather);

        assertIterableEquals(historicalWeather, historicalWeatherData.getDataPoints());
    }
}

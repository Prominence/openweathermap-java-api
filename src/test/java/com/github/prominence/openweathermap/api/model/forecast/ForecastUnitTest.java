/*
 * Copyright (c) 2021 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model.forecast;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ForecastUnitTest {
    @Test
    public void whenLocationIsSet_thenAllIsOk() {
        final Forecast forecast = new Forecast();
        forecast.setLocation(Location.forValue(2, "asd"));

        Assert.assertNotNull(forecast.getLocation());
    }

    @Test
    public void whenWeatherForecastsAreSet_thenAllIsOk() {
        final Forecast forecast = new Forecast();
        forecast.setWeatherForecasts(new ArrayList<>());

        Assert.assertNotNull(forecast.getWeatherForecasts());
    }

    @Test
    public void whenCalculateHashCode_thenAllIsOk() {
        final Forecast one = new Forecast();
        final Forecast two = new Forecast();

        Assert.assertEquals(one.hashCode(), two.hashCode());

        one.setLocation(Location.forValue(22, "444"));

        Assert.assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsOk() {
        final Forecast one = new Forecast();
        final Forecast two = new Forecast();

        Assert.assertTrue(one.equals(one));
        Assert.assertFalse(one.equals(null));
        Assert.assertTrue(one.equals(two));
        Assert.assertFalse(one.equals(new Object()));

        one.setLocation(Location.forValue(22, "234"));

        Assert.assertFalse(one.equals(two));

        two.setLocation(one.getLocation());

        Assert.assertTrue(one.equals(two));

        one.setWeatherForecasts(new ArrayList<>());

        Assert.assertFalse(one.equals(two));
    }
}

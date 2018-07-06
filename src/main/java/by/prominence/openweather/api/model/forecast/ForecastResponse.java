/*
 * Copyright (c) 2018 Alexey Zinchenko
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

package by.prominence.openweather.api.model.forecast;

import by.prominence.openweather.api.model.OpenWeatherResponse;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Objects;

public class ForecastResponse implements OpenWeatherResponse {

    @JSONField(name = "cod")
    private short responseCode;

    private double message;

    // Number of lines returned by this API call
    private short cnt;

    @JSONField(name = "list")
    private List<ForecastInfo> forecasts;

    private City city;

    public short getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(short responseCode) {
        this.responseCode = responseCode;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public short getCnt() {
        return cnt;
    }

    public void setCnt(short cnt) {
        this.cnt = cnt;
    }

    public List<ForecastInfo> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastInfo> forecasts) {
        this.forecasts = forecasts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ForecastResponse{" +
                "responseCode=" + responseCode +
                ", message=" + message +
                ", cnt=" + cnt +
                ", forecasts=" + forecasts +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastResponse that = (ForecastResponse) o;
        return responseCode == that.responseCode &&
                Double.compare(that.message, message) == 0 &&
                cnt == that.cnt &&
                Objects.equals(forecasts, that.forecasts) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {

        return Objects.hash(responseCode, message, cnt, forecasts, city);
    }
}

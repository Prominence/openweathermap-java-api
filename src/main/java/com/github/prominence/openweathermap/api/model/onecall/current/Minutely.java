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

package com.github.prominence.openweathermap.api.model.onecall.current;

import java.time.LocalDateTime;
import java.util.Objects;

public class Minutely {
    private LocalDateTime forecastTime;
    private double precipitationVolume;

    private Minutely() {

    }

    public static Minutely withValue(LocalDateTime forecastTime, double precipitationVolume) {
        Objects.requireNonNull(forecastTime);
        if (precipitationVolume < 0) {
            throw new IllegalArgumentException("Precipitation volume cannot be negative.");
        }
        Minutely minutely = new Minutely();
        minutely.setForecastTime(forecastTime);
        minutely.setPrecipitationVolume(precipitationVolume);

        return minutely;
    }

    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    private void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    public double getPrecipitationVolume() {
        return precipitationVolume;
    }

    private void setPrecipitationVolume(double precipitationVolume) {
        this.precipitationVolume = precipitationVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minutely minutely = (Minutely) o;
        return Double.compare(minutely.precipitationVolume, precipitationVolume) == 0 && Objects.equals(forecastTime, minutely.forecastTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, precipitationVolume);
    }

    @Override
    public String toString() {
        return "Time: " + forecastTime + ", precipitation volume: " + precipitationVolume;
    }
}

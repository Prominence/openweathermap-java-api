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

import java.util.Objects;

/**
 * The type Daily temperature.
 */
public class DailyTemperature {
    private Double morning;
    private Double morningFeelsLike;
    private Double day;
    private Double dayFeelsLike;
    private Double eve;
    private Double eveFeelsLike;
    private Double night;
    private Double nightFeelsLike;
    private Double min;
    private Double max;
    private double dewPoint;
    private String unit;

    /**
     * Gets morning temperature.
     *
     * @return the morning
     */
    public Double getMorning() {
        return morning;
    }

    /**
     * Sets morning temperature.
     *
     * @param morning the morning
     */
    public void setMorning(Double morning) {
        this.morning = morning;
    }

    /**
     * Gets morning feels like temperature.
     *
     * @return the morning feels like temperature
     */
    public Double getMorningFeelsLike() {
        return morningFeelsLike;
    }

    /**
     * Sets morning feels like temperature.
     *
     * @param morningFeelsLike the morning feels like temperature
     */
    public void setMorningFeelsLike(Double morningFeelsLike) {
        this.morningFeelsLike = morningFeelsLike;
    }

    /**
     * Gets day temperature.
     *
     * @return the day temperature
     */
    public Double getDay() {
        return day;
    }

    /**
     * Sets day temperature.
     *
     * @param day the day temperature
     */
    public void setDay(Double day) {
        this.day = day;
    }

    /**
     * Gets day feels like temperature.
     *
     * @return the day feels like temperature
     */
    public Double getDayFeelsLike() {
        return dayFeelsLike;
    }

    /**
     * Sets day feels like temperature.
     *
     * @param dayFeelsLike the day feels like temperature
     */
    public void setDayFeelsLike(Double dayFeelsLike) {
        this.dayFeelsLike = dayFeelsLike;
    }

    /**
     * Gets eve temperature.
     *
     * @return the eve temperature
     */
    public Double getEve() {
        return eve;
    }

    /**
     * Sets eve temperature.
     *
     * @param eve the eve temperature
     */
    public void setEve(Double eve) {
        this.eve = eve;
    }

    /**
     * Gets eve feels like temperature.
     *
     * @return the eve feels like temperature
     */
    public Double getEveFeelsLike() {
        return eveFeelsLike;
    }

    /**
     * Sets eve feels like temperature.
     *
     * @param eveFeelsLike the eve feels like temperature
     */
    public void setEveFeelsLike(Double eveFeelsLike) {
        this.eveFeelsLike = eveFeelsLike;
    }

    /**
     * Gets night temperature.
     *
     * @return the night temperature
     */
    public Double getNight() {
        return night;
    }

    /**
     * Sets night temperature.
     *
     * @param night the night temperature
     */
    public void setNight(Double night) {
        this.night = night;
    }

    /**
     * Gets night feels like temperature.
     *
     * @return the night feels like temperature
     */
    public Double getNightFeelsLike() {
        return nightFeelsLike;
    }

    /**
     * Sets night feels like temperature.
     *
     * @param nightFeelsLike the night feels like temperature
     */
    public void setNightFeelsLike(Double nightFeelsLike) {
        this.nightFeelsLike = nightFeelsLike;
    }

    /**
     * Gets min temperature.
     *
     * @return the min temperature
     */
    public Double getMin() {
        return min;
    }

    /**
     * Sets min temperature.
     *
     * @param min the min temperature
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * Gets max temperature.
     *
     * @return the max temperature
     */
    public Double getMax() {
        return max;
    }

    /**
     * Sets max temperature.
     *
     * @param max the max temperature
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * Gets dew point temperature.
     *
     * @return the dew point temperature
     */
    public double getDewPoint() {
        return dewPoint;
    }

    /**
     * Sets dew point temperature.
     *
     * @param dewPoint the dew point temperature
     */
    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     * Gets unit temperature.
     *
     * @return the unit temperature
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unit temperature.
     *
     * @param unit the unit temperature
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTemperature that = (DailyTemperature) o;
        return Double.compare(that.dewPoint, dewPoint) == 0 &&
                Objects.equals(morning, that.morning) &&
                Objects.equals(morningFeelsLike, that.morningFeelsLike) &&
                Objects.equals(day, that.day) &&
                Objects.equals(dayFeelsLike, that.dayFeelsLike) &&
                Objects.equals(eve, that.eve) &&
                Objects.equals(eveFeelsLike, that.eveFeelsLike) &&
                Objects.equals(night, that.night) &&
                Objects.equals(nightFeelsLike, that.nightFeelsLike) &&
                Objects.equals(min, that.min) &&
                Objects.equals(max, that.max) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(morning, morningFeelsLike, day, dayFeelsLike, eve, eveFeelsLike, night, nightFeelsLike, min, max, dewPoint, unit);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Daily temperature: ");
        stringBuilder.append("dew point - ");
        stringBuilder.append(dewPoint);
        stringBuilder.append(unit);
        if (morning != null) {
            stringBuilder.append(", morning - ");
            stringBuilder.append(morning);
            stringBuilder.append(unit);
        }
        if (morningFeelsLike != null) {
            stringBuilder.append(", morning feels like - ");
            stringBuilder.append(morningFeelsLike);
            stringBuilder.append(unit);
        }
        if (day != null) {
            stringBuilder.append(", day - ");
            stringBuilder.append(day);
            stringBuilder.append(unit);
        }
        if (dayFeelsLike != null) {
            stringBuilder.append(", day feels like - ");
            stringBuilder.append(dayFeelsLike);
            stringBuilder.append(unit);
        }
        if (eve != null) {
            stringBuilder.append(", eve - ");
            stringBuilder.append(eve);
            stringBuilder.append(unit);
        }
        if (eveFeelsLike != null) {
            stringBuilder.append(", eve feels like - ");
            stringBuilder.append(eveFeelsLike);
            stringBuilder.append(unit);
        }
        if (night != null) {
            stringBuilder.append(", night - ");
            stringBuilder.append(night);
            stringBuilder.append(unit);
        }
        if (nightFeelsLike != null) {
            stringBuilder.append(", night feels like - ");
            stringBuilder.append(nightFeelsLike);
            stringBuilder.append(unit);
        }
        if (min != null) {
            stringBuilder.append(", min - ");
            stringBuilder.append(min);
            stringBuilder.append(unit);
        }
        if (max != null) {
            stringBuilder.append(", max - ");
            stringBuilder.append(max);
            stringBuilder.append(unit);
        }
        return stringBuilder.toString();
    }
}

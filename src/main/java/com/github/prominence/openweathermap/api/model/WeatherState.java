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

package com.github.prominence.openweathermap.api.model;

import com.github.prominence.openweathermap.api.enums.WeatherCondition;

import java.util.Objects;

/**
 * The type Weather state.
 */
public class WeatherState {
    private Integer id;
    private String name;
    private String description;
    private String iconId;
    private WeatherCondition weatherConditionEnum;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
        this.weatherConditionEnum = WeatherCondition.getById(id);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets icon id.
     *
     * @return the icon id
     */
    public String getIconId() {
        return iconId;
    }

    /**
     * Sets icon id.
     *
     * @param iconId the icon id
     */
    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    /**
     * Gets weather condition enum.
     *
     * @return the weather condition enum
     */
    public WeatherCondition getWeatherConditionEnum() {
        return weatherConditionEnum;
    }

    /**
     * Gets weather icon url.
     *
     * @return the weather icon url
     */
    public String getWeatherIconUrl() {
        if (iconId != null) {
            return WeatherCondition.getIconUrl(iconId);
        }
        if (weatherConditionEnum != null) {
            // return the default one for the current weather condition
            return weatherConditionEnum.getDayIconUrl();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherState that = (WeatherState) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(iconId, that.iconId) && weatherConditionEnum == that.weatherConditionEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, iconId, weatherConditionEnum);
    }

    @Override
    public String toString() { // TODO: change
        return "WeatherState{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", iconId='" + iconId + '\'' +
                ", weatherConditionEnum=" + weatherConditionEnum +
                '}';
    }
}

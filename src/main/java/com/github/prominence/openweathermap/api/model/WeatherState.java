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

package com.github.prominence.openweathermap.api.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.net.MalformedURLException;
import java.net.URL;

@EqualsAndHashCode
public class WeatherState {

    @JSONField(name = "id")
    // Weather condition id
    @Getter
    @Setter
    long conditionId;

    @JSONField(name = "main")
    // Group of weather parameters (Rain, Snow, Extreme etc.)
    @Getter
    @Setter
    String weatherGroup;

    @JSONField(name = "description")
    // Weather condition within the group
    @Getter
    @Setter
    String description;

    @JSONField(name = "icon")
    // Weather icon id
    @Getter
    @Setter
    String iconId;

    public URL getWeatherIconUrl() {
        URL iconUrl = null;
        try {
            iconUrl = new URL("http://openweathermap.org/img/w/" + iconId + ".png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return iconUrl;
    }

    @Override
    public String toString() {
        return "Weather: " + description;
    }
}

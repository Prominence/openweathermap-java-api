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

package com.github.prominence.openweathermap.api.model.roadrisk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.PrecipitationIntensityDeserializer;
import com.github.prominence.openweathermap.api.deserializer.TemperatureValueDeserializer;
import com.github.prominence.openweathermap.api.deserializer.VisibilityDeserializer;
import com.github.prominence.openweathermap.api.deserializer.WindSpeedDeserializer;
import com.github.prominence.openweathermap.api.model.PrecipitationIntensity;
import com.github.prominence.openweathermap.api.model.TemperatureValue;
import com.github.prominence.openweathermap.api.model.Visibility;
import com.github.prominence.openweathermap.api.model.generic.wind.WindSpeed;
import lombok.Data;

@Data
public class WeatherModel {

    @JsonDeserialize(using = TemperatureValueDeserializer.class)
    @JsonProperty("temp")
    private TemperatureValue temperature;
    @JsonDeserialize(using = WindSpeedDeserializer.class)
    @JsonProperty("wind_speed")
    private WindSpeed windSpeed;
    @JsonProperty("wind_deg")
    private Integer windDegrees;
    @JsonDeserialize(using = PrecipitationIntensityDeserializer.class)
    @JsonProperty("precipitation_intensity")
    private PrecipitationIntensity precipitationIntensity;
    @JsonDeserialize(using = VisibilityDeserializer.class)
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonDeserialize(using = TemperatureValueDeserializer.class)
    @JsonProperty("dew_point")
    private TemperatureValue dewPoint;

}

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

package com.github.prominence.openweathermap.api.model.air.pollution;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.prominence.openweathermap.api.deserializer.ConcentrationDeserializer;
import lombok.Data;

/**
 * The type Air pollution record.
 */
@Data
public class AirPollutionMeasurements {

    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("co")
    private Concentration carbonMonoxide;
    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("no")
    private Concentration nitrogenMonoxide;
    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("no2")
    private Concentration nitrogenDioxide;
    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("o3")
    private Concentration ozone;
    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("so2")
    private Concentration sulphurDioxide;
    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("pm2_5")
    private Concentration fineParticlesMatter;
    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("pm10")
    private Concentration coarseParticulateMatter;
    @JsonDeserialize(using = ConcentrationDeserializer.class)
    @JsonProperty("nh3")
    private Concentration ammonia;

}

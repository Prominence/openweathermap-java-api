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

import com.github.prominence.openweathermap.api.enums.AirQualityIndex;

import java.time.OffsetDateTime;

/**
 * Interface of air pollution concentration measurements.
 */
public interface AirPollutionConcentration {
    /**
     * The date time when the measurement happened.
     *
     * @return datetime
     */
    OffsetDateTime getMeasurementTime();

    /**
     * The air quality index as an (overview).
     *
     * @return index
     */
    AirQualityIndex getAirQualityIndex();

    /**
     * The concentration of CO in the air.
     *
     * @return CO
     */
    Concentration getCarbonMonoxide();

    /**
     * The concentration of NO in the air.
     *
     * @return NO
     */
    Concentration getNitrogenMonoxide();

    /**
     * The concentration of NO2 in the air.
     *
     * @return NO2
     */
    Concentration getNitrogenDioxide();

    /**
     * The concentration of O3 in the air.
     *
     * @return O3
     */
    Concentration getOzone();

    /**
     * The concentration of SO2 in the air.
     *
     * @return SO2
     */
    Concentration getSulphurDioxide();

    /**
     * The concentration of pine particles matter in the air.
     *
     * @return fine particles
     */
    Concentration getFineParticlesMatter();

    /**
     * The concentration of coarse particulate matter in the air.
     *
     * @return coarse particles
     */
    Concentration getCoarseParticulateMatter();

    /**
     * The concentration of NH3 in the air.
     *
     * @return NH3
     */
    Concentration getAmmonia();
}

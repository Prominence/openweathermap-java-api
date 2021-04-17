/*
 *
 *  * Copyright (c) 2021 Alexey Zinchenko
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package com.github.prominence.openweathermap.api.model.air.pollution;

import com.github.prominence.openweathermap.api.enums.AirQualityIndex;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The type Air pollution record.
 */
public class AirPollutionRecord {
    private LocalDateTime forecastTime;
    private AirQualityIndex airQualityIndex;

    private Double CO;
    private Double NO;
    private Double NO2;
    private Double O3;
    private Double SO2;
    private Double PM2_5;
    private Double PM10;
    private Double NH3;

    /**
     * Gets forecast time.
     *
     * @return the forecast time
     */
    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    /**
     * Sets forecast time.
     *
     * @param forecastTime the forecast time
     */
    public void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    /**
     * Gets air quality index.
     *
     * @return the air quality index
     */
    public AirQualityIndex getAirQualityIndex() {
        return airQualityIndex;
    }

    /**
     * Sets air quality index.
     *
     * @param airQualityIndex the air quality index
     */
    public void setAirQualityIndex(AirQualityIndex airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    /**
     * Gets carbon monoxide concentration value in μg/m^3.
     *
     * @return the carbon monoxide value
     */
    public Double getCO() {
        return CO;
    }

    /**
     * Gets carbon monoxide concentration value in μg/m^3.
     *
     * @return the carbon monoxide value
     */
    public Double getCarbonMonoxide() {
        return getCO();
    }

    /**
     * Sets carbon monoxide concentration value in μg/m^3.
     *
     * @param CO the carbon monoxide value
     */
    public void setCO(Double CO) {
        this.CO = CO;
    }

    /**
     * Gets nitrogen monoxide concentration value in μg/m^3.
     *
     * @return the nitrogen monoxide value
     */
    public Double getNO() {
        return NO;
    }

    /**
     * Gets nitrogen monoxide concentration value in μg/m^3.
     *
     * @return the nitrogen monoxide value
     */
    public Double getNitrogenMonoxide() {
        return getNO();
    }

    /**
     * Sets nitrogen monoxide concentration value in μg/m^3.
     *
     * @param NO the nitrogen monoxide value
     */
    public void setNO(Double NO) {
        this.NO = NO;
    }

    /**
     * Gets nitrogen dioxide concentration value in μg/m^3.
     *
     * @return the nitrogen dioxide value
     */
    public Double getNO2() {
        return NO2;
    }

    /**
     * Gets nitrogen dioxide concentration value in μg/m^3.
     *
     * @return the nitrogen dioxide value
     */
    public Double getNitrogenDioxide() {
        return getNO2();
    }

    /**
     * Sets nitrogen dioxide concentration value in μg/m^3.
     *
     * @param NO2 the nitrogen dioxide value
     */
    public void setNO2(Double NO2) {
        this.NO2 = NO2;
    }

    /**
     * Gets ozone concentration value in μg/m^3.
     *
     * @return the ozone value
     */
    public Double getO3() {
        return O3;
    }

    /**
     * Gets ozone concentration value in μg/m^3.
     *
     * @return the ozone value
     */
    public Double getOzone() {
        return getO3();
    }

    /**
     * Sets ozone concentration value in μg/m^3.
     *
     * @param o3 the ozone value
     */
    public void setO3(Double o3) {
        O3 = o3;
    }

    /**
     * Gets sulphur dioxide concentration value in μg/m^3.
     *
     * @return the sulphur dioxide value
     */
    public Double getSO2() {
        return SO2;
    }

    /**
     * Gets sulphur dioxide concentration value in μg/m^3.
     *
     * @return the sulphur dioxide value
     */
    public Double getSulphurDioxide() {
        return getSO2();
    }

    /**
     * Sets sulphur dioxide concentration value in μg/m^3.
     *
     * @param SO2 the sulphur dioxide value
     */
    public void setSO2(Double SO2) {
        this.SO2 = SO2;
    }

    /**
     * Gets fine particles matter concentration value in μg/m^3.
     *
     * @return the fine particles matter value
     */
    public Double getPM2_5() {
        return PM2_5;
    }

    /**
     * Gets fine particles matter concentration value in μg/m^3.
     *
     * @return the fine particles matter value
     */
    public Double getFineParticlesMatter() {
        return getPM2_5();
    }

    /**
     * Sets fine particles matter concentration value in μg/m^3.
     *
     * @param PM2_5 the fine particles matter value
     */
    public void setPM2_5(Double PM2_5) {
        this.PM2_5 = PM2_5;
    }

    /**
     * Gets coarse particulate matter concentration value in μg/m^3.
     *
     * @return the coarse particulate matter value
     */
    public Double getPM10() {
        return PM10;
    }

    /**
     * Gets coarse particulate matter concentration value in μg/m^3.
     *
     * @return the coarse particulate matter value
     */
    public Double getCoarseParticulateMatter() {
        return getPM10();
    }

    /**
     * Sets coarse particulate matter concentration value in μg/m^3.
     *
     * @param PM10 the coarse particulate matter value
     */
    public void setPM10(Double PM10) {
        this.PM10 = PM10;
    }

    /**
     * Gets ammonia concentration value in μg/m^3.
     *
     * @return the ammonia value
     */
    public Double getNH3() {
        return NH3;
    }

    /**
     * Gets ammonia concentration value in μg/m^3.
     *
     * @return the ammonia value
     */
    public Double getAmmonia() {
        return getNH3();
    }

    /**
     * Sets ammonia concentration value in μg/m^3.
     *
     * @param NH3 the ammonia value
     */
    public void setNH3(Double NH3) {
        this.NH3 = NH3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirPollutionRecord that = (AirPollutionRecord) o;
        return Objects.equals(forecastTime, that.forecastTime) && airQualityIndex == that.airQualityIndex && Objects.equals(CO, that.CO) && Objects.equals(NO, that.NO) && Objects.equals(NO2, that.NO2) && Objects.equals(O3, that.O3) && Objects.equals(SO2, that.SO2) && Objects.equals(PM2_5, that.PM2_5) && Objects.equals(PM10, that.PM10) && Objects.equals(NH3, that.NH3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, airQualityIndex, CO, NO, NO2, O3, SO2, PM2_5, PM10, NH3);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("Air Pollution Record for ")
                .append(forecastTime)
                .append(", AQI=")
                .append(airQualityIndex.name())
                .append(".");
        final boolean anyConcentrationAvailable = Stream.of(CO, NO, NO2, O3, SO2, PM2_5, PM10, NH3).anyMatch(Objects::nonNull);
        if (anyConcentrationAvailable) {
            stringBuilder.append(" Concentrations:");
            if (CO != null) {
                stringBuilder
                        .append(" CO(Carbon monoxide) = ")
                        .append(CO)
                        .append(" μg/m^3;");
            }
            if (NO != null) {
                stringBuilder
                        .append(" NO(Nitrogen monoxide) = ")
                        .append(NO)
                        .append(" μg/m^3;");
            }
            if (NO2 != null) {
                stringBuilder
                        .append(" NO2(Nitrogen dioxide) = ")
                        .append(NO2)
                        .append(" μg/m^3;");
            }
            if (O3 != null) {
                stringBuilder
                        .append(" O3(Ozone) = ")
                        .append(O3)
                        .append(" μg/m^3;");
            }
            if (SO2 != null) {
                stringBuilder
                        .append(" SO2(Sulphur dioxide) = ")
                        .append(SO2)
                        .append(" μg/m^3;");
            }
            if (PM2_5 != null) {
                stringBuilder
                        .append(" PM2.5(Fine particles matter) = ")
                        .append(PM2_5)
                        .append(" μg/m^3;");
            }
            if (PM10 != null) {
                stringBuilder
                        .append(" PM10(Coarse particulate matter) = ")
                        .append(PM10)
                        .append(" μg/m^3;");
            }
            if (NH3 != null) {
                stringBuilder
                        .append(" NH3(Ammonia) = ")
                        .append(NH3)
                        .append(" μg/m^3;");
            }
        }
        return stringBuilder.toString();
    }
}

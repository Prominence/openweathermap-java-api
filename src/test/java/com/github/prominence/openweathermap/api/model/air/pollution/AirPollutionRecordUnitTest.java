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
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirPollutionRecordUnitTest {
    @Test
    public void testGetForecastTime_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        underTest.setMeasurementTime(now);

        //when
        final OffsetDateTime actual = underTest.getMeasurementTime();

        //then
        assertEquals(now, actual);
    }

    @Test
    public void testGetAirQualityIndex_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        underTest.setAirQualityIndex(AirQualityIndex.FAIR);

        //when
        final AirQualityIndex actual = underTest.getAirQualityIndex();

        //then
        assertEquals(AirQualityIndex.FAIR, actual);
    }

    @Test
    public void testGetAmmonia_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("0.12369127571582794");
        components.setAmmonia(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getAmmonia();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    @Test
    public void testGetOzone_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("68.66455078125");
        components.setOzone(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getOzone();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    @Test
    public void testGetCarbonMonoxide_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("201.94053649902344");
        components.setCarbonMonoxide(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getCarbonMonoxide();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    @Test
    public void testGetNitrogenMonoxide_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("0.01877197064459324");
        components.setNitrogenMonoxide(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getNitrogenMonoxide();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    @Test
    public void testGetNitrogenDioxide_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("0.7711350917816162");
        components.setNitrogenDioxide(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getNitrogenDioxide();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    @Test
    public void testGetSulphurDioxide_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("0.6407499313354492");
        components.setSulphurDioxide(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getSulphurDioxide();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    @Test
    public void testGetFineParticlesMatter_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("0.5");
        components.setFineParticlesMatter(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getFineParticlesMatter();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    @Test
    public void testGetCoarseParticulateMatter_ShouldReturnPreviouslySetValue_WhenCalled() {
        //given
        final AirPollutionRecord underTest = new AirPollutionRecord();
        final AirPollutionMeasurements components = new AirPollutionMeasurements();
        final BigDecimal expected = new BigDecimal("0.540438711643219");
        components.setCoarseParticulateMatter(new Concentration(expected));
        underTest.setComponents(components);

        //when
        final Concentration actual = underTest.getCoarseParticulateMatter();

        //then
        assertEquals(round(expected), actual.asMicrogramsPerCubicMeters());
    }

    private static BigDecimal round(BigDecimal expected) {
        return expected.setScale(10, RoundingMode.HALF_EVEN);
    }
}

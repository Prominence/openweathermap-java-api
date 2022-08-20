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

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AirPollutionRecordUnitTest {
    @Test
    public void getForecastTime() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        airPollutionRecord.setForecastTime(now);

        assertEquals(now, airPollutionRecord.getForecastTime());
    }

    @Test
    public void getAirQualityIndex() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        airPollutionRecord.setAirQualityIndex(AirQualityIndex.FAIR);

        assertEquals(AirQualityIndex.FAIR, airPollutionRecord.getAirQualityIndex());
    }

    @Test
    public void getCarbonMonoxide() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setCO(value);

        assertEquals(value, airPollutionRecord.getCarbonMonoxide(), 0.00001);
    }

    @Test
    public void getNitrogenMonoxide() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setNO(value);

        assertEquals(value, airPollutionRecord.getNitrogenMonoxide(), 0.00001);
    }

    @Test
    public void getNitrogenDioxide() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setNO2(value);

        assertEquals(value, airPollutionRecord.getNitrogenDioxide(), 0.00001);
    }

    @Test
    public void getOzone() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setO3(value);

        assertEquals(value, airPollutionRecord.getOzone(), 0.00001);
    }

    @Test
    public void getSulphurDioxide() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setSO2(value);

        assertEquals(value, airPollutionRecord.getSulphurDioxide(), 0.00001);
    }

    @Test
    public void getFineParticlesMatter() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setPM2_5(value);

        assertEquals(value, airPollutionRecord.getFineParticlesMatter(), 0.00001);
    }

    @Test
    public void getCoarseParticulateMatter() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setPM10(value);

        assertEquals(value, airPollutionRecord.getCoarseParticulateMatter(), 0.00001);
    }

    @Test
    public void getAmmonia() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final double value = 0.55;
        airPollutionRecord.setNH3(value);

        assertEquals(value, airPollutionRecord.getAmmonia());
    }

    @Test
    public void testEquals() {
        final AirPollutionRecord first = new AirPollutionRecord();
        final AirPollutionRecord second = new AirPollutionRecord();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final AirQualityIndex aqi = AirQualityIndex.GOOD;
        final double value = 0.55;

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertEquals(first, second);
        assertNotEquals(first, new Object());

        assertEquals(first, second);

        first.setForecastTime(now);

        assertNotEquals(first, second);

        second.setForecastTime(now);

        assertEquals(first, second);

        first.setAirQualityIndex(aqi);

        assertNotEquals(first, second);

        second.setAirQualityIndex(aqi);

        assertEquals(first, second);

        first.setCO(value);

        assertNotEquals(first, second);

        second.setCO(value);

        assertEquals(first, second);

        first.setNO(value);

        assertNotEquals(first, second);

        second.setNO(value);

        assertEquals(first, second);

        first.setNO2(value);

        assertNotEquals(first, second);

        second.setNO2(value);

        assertEquals(first, second);

        first.setO3(value);

        assertNotEquals(first, second);

        second.setO3(value);

        assertEquals(first, second);

        first.setSO2(value);

        assertNotEquals(first, second);

        second.setSO2(value);

        assertEquals(first, second);

        first.setPM2_5(value);

        assertNotEquals(first, second);

        second.setPM2_5(value);

        assertEquals(first, second);

        first.setPM10(value);

        assertNotEquals(first, second);

        second.setPM10(value);

        assertEquals(first, second);

        first.setNH3(value);

        assertNotEquals(first, second);

        second.setNH3(value);

        assertEquals(first, second);
    }

    @Test
    public void testHashCode() {
        final AirPollutionRecord first = new AirPollutionRecord();
        final AirPollutionRecord second = new AirPollutionRecord();

        assertEquals(first.hashCode(), second.hashCode());

        first.setSO2(33.2);

        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testToString() {
        final AirPollutionRecord airPollutionRecord = new AirPollutionRecord();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final AirQualityIndex aqi = AirQualityIndex.GOOD;
        final double value = 0.55;

        airPollutionRecord.setForecastTime(now);
        airPollutionRecord.setAirQualityIndex(aqi);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setCO(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setNO(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setNO2(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setO3(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setSO2(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setPM2_5(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setPM10(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setNH3(value);

        assertNotNull(airPollutionRecord.toString());

        airPollutionRecord.setCO(null);

        assertNotNull(airPollutionRecord.toString());
    }
}

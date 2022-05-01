package com.github.prominence.openweathermap.api.model.radiation;

import java.time.LocalDateTime;
import java.util.Objects;

public class SolarRadiationRecord {
    private LocalDateTime measurementTime;

    private Double ghi;
    private Double dni;
    private Double dhi;

    private Double ghi_cs;
    private Double dni_cs;
    private Double dhi_cs;

    public LocalDateTime getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(LocalDateTime measurementTime) {
        this.measurementTime = measurementTime;
    }

    public Double getCloudSkyGlobalHorizontalIrradiance() {
        return ghi;
    }

    public void setCloudSkyGlobalHorizontalIrradiance(Double ghi) {
        this.ghi = ghi;
    }

    public Double getCloudSkyDirectNormalIrradiance() {
        return dni;
    }

    public void setCloudSkyDirectNormalIrradiance(Double dni) {
        this.dni = dni;
    }

    public Double getCloudSkyDiffuseHorizontalIrradiance() {
        return dhi;
    }

    public void setCloudSkyDiffuseHorizontalIrradiance(Double dhi) {
        this.dhi = dhi;
    }

    public Double getClearSkyGlobalHorizontalIrradiance() {
        return ghi_cs;
    }

    public void setClearSkyGlobalHorizontalIrradiance(Double ghi_cs) {
        this.ghi_cs = ghi_cs;
    }

    public Double getClearSkyDirectNormalIrradiance() {
        return dni_cs;
    }

    public void setClearSkyDirectNormalIrradiance(Double dni_cs) {
        this.dni_cs = dni_cs;
    }

    public Double getClearSkyDiffuseHorizontalIrradiance() {
        return dhi_cs;
    }

    public void setClearSkyDiffuseHorizontalIrradiance(Double dhi_cs) {
        this.dhi_cs = dhi_cs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolarRadiationRecord that = (SolarRadiationRecord) o;
        return Objects.equals(measurementTime, that.measurementTime) && Objects.equals(ghi, that.ghi) && Objects.equals(dni, that.dni) && Objects.equals(dhi, that.dhi) && Objects.equals(ghi_cs, that.ghi_cs) && Objects.equals(dni_cs, that.dni_cs) && Objects.equals(dhi_cs, that.dhi_cs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(measurementTime, ghi, dni, dhi, ghi_cs, dni_cs, dhi_cs);
    }

    @Override
    public String toString() {
        return "SolarRadiationRecord{" +
                "measurementTime=" + measurementTime +
                ", ghi=" + ghi +
                ", dni=" + dni +
                ", dhi=" + dhi +
                ", ghi_cs=" + ghi_cs +
                ", dni_cs=" + dni_cs +
                ", dhi_cs=" + dhi_cs +
                '}';
    }
}

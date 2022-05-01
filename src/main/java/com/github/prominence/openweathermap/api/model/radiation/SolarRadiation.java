package com.github.prominence.openweathermap.api.model.radiation;

import com.github.prominence.openweathermap.api.model.Coordinates;

import java.util.List;
import java.util.Objects;

public class SolarRadiation {
    private Coordinates coordinates;
    List<SolarRadiationRecord> solarRadiationRecords;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<SolarRadiationRecord> getSolarRadiationRecords() {
        return solarRadiationRecords;
    }

    public void setSolarRadiationRecords(List<SolarRadiationRecord> solarRadiationRecords) {
        this.solarRadiationRecords = solarRadiationRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolarRadiation that = (SolarRadiation) o;
        return Objects.equals(coordinates, that.coordinates) && Objects.equals(solarRadiationRecords, that.solarRadiationRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, solarRadiationRecords);
    }

    @Override
    public String toString() {
        return "SolarRadiation{" +
                "coordinates=" + coordinates +
                ", solarRadiationRecords=" + solarRadiationRecords +
                '}';
    }
}

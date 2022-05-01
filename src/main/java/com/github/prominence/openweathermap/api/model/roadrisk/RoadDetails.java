package com.github.prominence.openweathermap.api.model.roadrisk;

import com.github.prominence.openweathermap.api.enums.RoadState;

import java.util.Objects;

public class RoadDetails {
    private RoadState roadState;
    private Double surfaceTemperature;

    public RoadState getRoadState() {
        return roadState;
    }

    public void setRoadState(RoadState roadState) {
        this.roadState = roadState;
    }

    public Double getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public void setSurfaceTemperature(Double surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoadDetails that = (RoadDetails) o;
        return roadState == that.roadState && Objects.equals(surfaceTemperature, that.surfaceTemperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roadState, surfaceTemperature);
    }

    @Override
    public String toString() {
        return "RoadDetails{" +
                "roadState=" + roadState +
                ", surfaceTemperature=" + surfaceTemperature +
                '}';
    }
}

package com.github.prominence.openweathermap.api.model.roadrisk;

import com.github.prominence.openweathermap.api.model.Coordinates;

import java.time.LocalDateTime;
import java.util.Objects;

public class TrackPoint {
    private Coordinates coordinates;
    private LocalDateTime requestedTime;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(LocalDateTime requestedTime) {
        this.requestedTime = requestedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackPoint that = (TrackPoint) o;
        return Objects.equals(coordinates, that.coordinates) && Objects.equals(requestedTime, that.requestedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, requestedTime);
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "coordinates=" + coordinates +
                ", requestedTime=" + requestedTime +
                '}';
    }
}

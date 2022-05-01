package com.github.prominence.openweathermap.api.model.roadrisk;

import com.github.prominence.openweathermap.api.model.Coordinates;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class RoadRiskRecord {
    private LocalDateTime forecastTime;
    private Coordinates coordinates;

    private Weather weather;
    private RoadDetails roadDetails;

    private List<Alert> alerts;

    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public RoadDetails getRoadDetails() {
        return roadDetails;
    }

    public void setRoadDetails(RoadDetails roadDetails) {
        this.roadDetails = roadDetails;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoadRiskRecord record = (RoadRiskRecord) o;
        return Objects.equals(forecastTime, record.forecastTime) && Objects.equals(coordinates, record.coordinates) && Objects.equals(weather, record.weather) && Objects.equals(roadDetails, record.roadDetails) && Objects.equals(alerts, record.alerts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, coordinates, weather, roadDetails, alerts);
    }

    @Override
    public String toString() {
        return "RoadRiskRecord{" +
                "forecastTime=" + forecastTime +
                ", coordinates=" + coordinates +
                ", weather=" + weather +
                ", roadDetails=" + roadDetails +
                ", alerts=" + alerts +
                '}';
    }
}

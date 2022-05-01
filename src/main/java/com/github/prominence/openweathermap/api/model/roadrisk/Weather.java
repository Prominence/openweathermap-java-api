package com.github.prominence.openweathermap.api.model.roadrisk;

import java.util.Objects;

public class Weather {

    private Double temperature;
    private Double windSpeed;
    private Double windDegrees;
    private Double precipitationIntensity;
    private Double visibilityInMetres;
    private Double dewPoint;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(Double windDegrees) {
        this.windDegrees = windDegrees;
    }

    public Double getPrecipitationIntensity() {
        return precipitationIntensity;
    }

    public void setPrecipitationIntensity(Double precipitationIntensity) {
        this.precipitationIntensity = precipitationIntensity;
    }

    public Double getVisibilityInMetres() {
        return visibilityInMetres;
    }

    public void setVisibilityInMetres(Double visibilityInMetres) {
        this.visibilityInMetres = visibilityInMetres;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(temperature, weather.temperature) && Objects.equals(windSpeed, weather.windSpeed) && Objects.equals(windDegrees, weather.windDegrees) && Objects.equals(precipitationIntensity, weather.precipitationIntensity) && Objects.equals(visibilityInMetres, weather.visibilityInMetres) && Objects.equals(dewPoint, weather.dewPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, windSpeed, windDegrees, precipitationIntensity, visibilityInMetres, dewPoint);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", windDegrees=" + windDegrees +
                ", precipitationIntensity=" + precipitationIntensity +
                ", visibilityInMetres=" + visibilityInMetres +
                ", dewPoint=" + dewPoint +
                '}';
    }
}

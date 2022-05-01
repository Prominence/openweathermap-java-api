package com.github.prominence.openweathermap.api.model.roadrisk;

import com.github.prominence.openweathermap.api.enums.EventLevel;

import java.util.Objects;

public class Alert {
    private String senderName;
    private String event;
    private EventLevel eventLevel;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public EventLevel getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return Objects.equals(senderName, alert.senderName) && Objects.equals(event, alert.event) && eventLevel == alert.eventLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderName, event, eventLevel);
    }

    @Override
    public String toString() {
        return "Alert{" +
                "senderName='" + senderName + '\'' +
                ", event='" + event + '\'' +
                ", eventLevel=" + eventLevel +
                '}';
    }
}

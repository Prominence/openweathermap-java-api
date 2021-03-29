/*
 * Copyright (c) 2021 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model.onecall.current;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Alert.
 */
public class Alert {
    private String senderName;
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;

    /**
     * Gets sender name.
     *
     * @return the sender name
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Sets sender name.
     *
     * @param senderName the sender name
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * Gets event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets event name.
     *
     * @param eventName the event name
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return Objects.equals(senderName, alert.senderName) &&
                Objects.equals(eventName, alert.eventName) &&
                Objects.equals(startTime, alert.startTime) &&
                Objects.equals(endTime, alert.endTime) &&
                Objects.equals(description, alert.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderName, eventName, startTime, endTime, description);
    }

    @Override
    public String toString() {
        return senderName + " - " + eventName + "(" + startTime + " - " + endTime + "): " + description;
    }
}

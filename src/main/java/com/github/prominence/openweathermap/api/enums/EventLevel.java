package com.github.prominence.openweathermap.api.enums;

import java.util.Arrays;

public enum EventLevel {
    UNKNOWN(0),
    GREEN(1),
    YELLOW(2),
    ORANGE(3),
    RED(4);

    private final int value;

    EventLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EventLevel findByValue(int value) {
        return Arrays.stream(values()).filter(eventLevel -> eventLevel.getValue() == value).findFirst().orElse(null);
    }
}

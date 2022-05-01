package com.github.prominence.openweathermap.api.enums;

import java.util.Arrays;

public enum RoadState {
    NO_REPORT(0),
    DRY(1),
    MOIST(2),
    MOIST_AND_CHEMICALLY_TREATED(3),
    WET(4),
    WET_AND_CHEMICALLY_TREATED(5),
    ICE(6),
    FROST(7),
    SNOW(8),
    SNOW_OR_ICE_WATCH(9),
    SNOW_OR_ICE_WARNING(10),
    WET_ABOVE_FREEZING(11),
    WET_BELOW_FREEZING(12),
    ABSORPTION(13),
    ABSORPTION_AT_DEWPOINT(14),
    DEW(15),
    BLACK_ICE_WARNING(16),
    OTHER(17),
    SLUSH(18);

    private final int value;

    RoadState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoadState findByValue(int value) {
        return Arrays.stream(values()).filter(roadState -> roadState.getValue() == value).findFirst().orElse(null);
    }
}

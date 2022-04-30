package com.github.prominence.openweathermap.api.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class TestMappingUtils {

    public static LocalDateTime parseDateTime(int seconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), TimeZone.getDefault().toZoneId());
    }
}

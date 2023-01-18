package com.example.csvprocessing.converter;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@UtilityClass
public class TimestampConverter {

    public LocalDateTime toLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
                TimeZone.getDefault().toZoneId());
    }

    public Long toTimestamp(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}

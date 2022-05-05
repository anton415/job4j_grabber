package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class HarbCareerDateTimeParser implements DateTimeParser {

    /**
     * String to LocalDateTime.
     * @param dateAsString - string like 2022-05-04T10:27:23+03:00.
     * @return - local date time.
     */
    @Override
    public LocalDateTime parse(String dateAsString) {
        return ZonedDateTime.parse(dateAsString).toLocalDateTime();
    }
}
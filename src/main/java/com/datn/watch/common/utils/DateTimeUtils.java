package com.datn.watch.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author tobi
 * @desc : ...
 */
public class DateTimeUtils {
    private static final DateTimeFormatter dateTime24hFormatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
            .withLocale(Locale.US).withZone(ZoneOffset.UTC);
    public static String formatDateTime(LocalDateTime time) {
        if (time == null)
            return "";
        return dateTime24hFormatter.format(time);
    }
}

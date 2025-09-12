package uz.brb.spring_security_with_aop.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Util {
    public static String dateTimeFormatter(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");

        return date.toInstant()
                .atZone(ZoneId.of("UTC+5"))
                .toLocalDateTime()
                .format(formatter);
    }

    public static String localDateTimeFormatter(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");

        return localDateTime
                .atZone(ZoneId.of("UTC+5"))
                .toLocalDateTime()
                .format(formatter);
    }
}

package com.yellow.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd").withLocale(Locale.US);

    public static LocalDate parse(String str) {
        return LocalDate.parse(str, formatter);
    }

    public static String format(LocalDate date) {
        return formatter.format(date);
    }
}

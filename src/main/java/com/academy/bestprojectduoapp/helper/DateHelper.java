package com.academy.bestprojectduoapp.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateHelper {

    public static LocalDate parseDate(String dateString) {
        // If dateTo is null add LocalDate.now()
        if (dateString == null || dateString.trim().equalsIgnoreCase("NULL")) {
            return LocalDate.now();
        }

        String[] dateFormats = {
                "yyyy-MM-dd",
                "MM/dd/yyyy",
                "yyyyMMdd",
                "dd/MM/yyyy",
                "yyyy/MM/dd",
                "dd-MMM-yyyy"
        };

        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
                return LocalDate.parse(dateString, formatter);
            } catch (Exception ignored) {
                // Try next format
            }
        }

        throw new IllegalArgumentException("Unable to parse date: " + dateString);
    }
}

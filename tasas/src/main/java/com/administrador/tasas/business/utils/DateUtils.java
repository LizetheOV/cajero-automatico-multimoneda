

package com.administrador.tasas.business.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.business.utils
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 08:24
 * <p>
 */

public class DateUtils {

  public static Long startOfDay() {

    return (LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC));

  }

  public static Long endOfDay() {

    return (LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC));

  }

  public static String getFormattedDate(Long timestamp) {

    Instant instant = Instant.ofEpochMilli(timestamp * 1000);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("America/La_Paz"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    return localDateTime.format(formatter);

  }

}

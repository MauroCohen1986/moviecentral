package ar.com.moviecentral.utils;

import com.google.protobuf.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateConverter {

  public static LocalDate toLocalDate(Timestamp googleTime){
    return Instant.ofEpochSecond(googleTime.getSeconds(),googleTime.getNanos())
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public static Timestamp toGoogleTimestamp(LocalDate localDate){
    Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    return Timestamp.newBuilder()
        .setSeconds(instant.getEpochSecond())
        .setNanos(instant.getNano())
        .build();
  }


}

package src.algo;

import java.time.*;

public class testAlways {
    public static void main(String[] args) throws Exception {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(ZonedDateTime.of(now.with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay(), ZoneId.of("UTC")));
    }
}

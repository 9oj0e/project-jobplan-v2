package shop.mtcoding.projectjobplan._core.utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class FormatUtil {
    public static String timeFormatter(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String stringFormatter(String input) {
        int maxLength = 8; // 최대 길이

        String output;
        if (input.length() > maxLength) {
            output = input.substring(0, maxLength) + "...";
        } else {
            output = input;
        }
        return output;
    }

    public static Double 소수점한자리(Double num){
        return (double) Math.round(num * 10) / 10;
    }
}

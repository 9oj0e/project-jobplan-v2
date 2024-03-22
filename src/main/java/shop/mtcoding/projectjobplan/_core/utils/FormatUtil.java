package shop.mtcoding.projectjobplan._core.utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class FormatUtil {
    public String timeFormatter(Timestamp timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return timestamp.toLocalDateTime().format(formatter);
    }
}

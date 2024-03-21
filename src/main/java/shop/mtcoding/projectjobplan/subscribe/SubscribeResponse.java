package shop.mtcoding.projectjobplan.subscribe;

import lombok.Data;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class SubscribeResponse {

    @Data
    public static class ToUserDTO {
        private String address;
        private String businessName;
        private Integer boardId;
        private String field;
        private String title;
        private String salary;
        private Timestamp closingDate;

        public String getClosingDate(){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return closingDate.toLocalDateTime().format(formatter);
        }
    }

    @Data
    public static class ToEmployerDTO {
        private Integer resumeId;
        private String resumeUsername;
        private String title;
    }
}

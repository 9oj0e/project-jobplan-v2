package shop.mtcoding.projectjobplan.board;

import lombok.Data;

import java.sql.Timestamp;

public class BoardRequest {
    public static class SaveDTO {
        // toEntity
    }
    @Data
    public static class UpdateDTO {
        private String title;
        private String field;
        private String position;
        private String salary;
        private String content;
        private String openingDate;
        private String closingDate;
        // toEntity
    }
}

package shop.mtcoding.projectjobplan.board;

import lombok.Data;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String field;
        private String position;
        private String content;
        private String salary;
        private String openingDate;
        private String closingDate;

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .field(field)
                    .position(position)
                    .content(content)
                    .salary(salary)
                    .openingDate(openingDate)
                    .closingDate(closingDate)
                    .build();
        }

        // toEntity
    }

    public static class UpdateDTO {
        // toEntity
    }
}

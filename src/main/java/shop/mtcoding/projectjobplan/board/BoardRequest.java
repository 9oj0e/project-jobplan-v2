package shop.mtcoding.projectjobplan.board;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class BoardRequest {
    @Data
    public class UploadDTO {
        // (user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
        private String title;
        private String content;
        private String field;
        private String position;
        private String salary;
        private String openingDate;
        private String closingDate;

        private List<String> skill; // checkbox 5개 -> name 을 통일 skills
    }

    @Data
    public class UpdateDTO {
        // 수정시 userId(작성자)는 변할 수 없다.
        private String title;
        private String content;
        private String field;
        private String position;
        private String salary;
        private String openingDate;
        private String closingDate;

        private List<String> skill;

    }
}

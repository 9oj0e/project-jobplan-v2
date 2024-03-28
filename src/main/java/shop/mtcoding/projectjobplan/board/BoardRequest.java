package shop.mtcoding.projectjobplan.board;

import lombok.Data;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

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

        private List<String> skill;

        public Board toEntity(User user) {
            return Board.builder()
                    .user(user)
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

    @Data
    public static class UpdateDTO {
        private String title;
        private String field;
        private String position;
        private String salary;
        private String content;
        private String openingDate;
        private String closingDate;

        private List<String> skill;
        // toEntity
    }
}

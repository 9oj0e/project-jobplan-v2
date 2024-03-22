package shop.mtcoding.projectjobplan.board;

import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @Data
    public static class UpdateDTO{
        private Integer id; // boardId
        private String title;
        private String field;
        private String position;
        private String salary;
        private String content;
        private Timestamp openingDate;
        private Timestamp closingDate;


        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
            this.content = board.getContent();
            this.openingDate = board.getOpeningDate();
            this.closingDate = board.getClosingDate();
        }
    }
    /*
    *
    public String getOpeningDate(){ // todo : 메서드 화
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return openingDate.toLocalDateTime().format(formatter);
    }

    public String getClosingDate(){ // todo :
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return closingDate.toLocalDateTime().format(formatter);
    }
    * */
}

package shop.mtcoding.projectjobplan.subscribe;

import lombok.Data;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class SubscribeResponse {

    @Data
    public static class ToUserDTO { // 공고 구독 (일반회원이)
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

        public ToUserDTO(Board board) {
            this.address = board.getUser().getAddress();
            this.businessName = board.getUser().getBusinessName();
            this.boardId = board.getId();
            this.field = board.getField();
            this.title = board.getTitle();
            this.salary = board.getSalary();
            this.closingDate = board.getClosingDate();
        }
    }

    @Data
    public static class ToEmployerDTO { // 이력서 구독 (회사가)
        private Integer resumeId;
        private String resumeUsername;
        private String title;

        public ToEmployerDTO(Resume resume) {
            this.resumeId = resume.getId();
            this.resumeUsername = resume.getUser().getUsername();
            this.title = resume.getTitle();
        }
    }
}

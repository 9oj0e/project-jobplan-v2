package shop.mtcoding.projectjobplan.board;

import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.util.List;

public class BoardResponse {
    @Data
    public static class UpdateDTO {
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

    public static class DetailDTO {
        private Integer id;
        private String address;
        private String phoneNumber;
        private String email;
        private String businessName;

        private String title; // 제목
        private String content; // 내용
        private String field; // 채용 분야
        private String position; // 포지션
        private String salary; // 연봉

        private Timestamp openingDate; // 게시일
        private Timestamp closingDate; // 마감일 == null -> "상시채용"

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.address = board.getUser().getAddress();
            this.phoneNumber = board.getUser().getPhoneNumber();
            this.email = board.getUser().getEmail();
            this.businessName = board.getUser().getBusinessName();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
            this.openingDate = board.getOpeningDate();
            this.closingDate = board.getClosingDate();
        }

        public String getOpeningDate() {
            return FormatUtil.timeFormatter(this.openingDate);
        }

        public String getClosingDate() {
            return FormatUtil.timeFormatter(this.closingDate);
        }
    }

    @Data
    public static class IndexDTO {
        // 공고 정보
        private Integer id;
        private String title;
        private String field;
        private String position;

        // 게시자 정보 (기업)
        private Integer userId;
        private String businessName;

        public IndexDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.field = board.getField();
            this.position = board.getPosition();
            this.userId = board.getUser().getId();
            this.businessName = board.getUser().getBusinessName();
        }
    }
}

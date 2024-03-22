package shop.mtcoding.projectjobplan.board;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

public class BoardResponse {
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

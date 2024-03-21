package shop.mtcoding.projectjobplan.board;


import lombok.Data;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardResponse {

    @Data
    public static class BoardAndUserDTO{
        private Integer id;
        private Integer employerId; // 기업ID
        private String title; // 제목
        private String content; // 내용
        private String field; // 채용 분야
        private String position; // 포지션
        private String salary; // 연봉
        private String openingDate; // 게시일
        private String closingDate; // 마감일

        private String createdAt; // 생성일

        private String username;
        private String address;
        private boolean isEmployer; // 사업자인지
        private String businessName;

        private String keyword ;
        private String skillName ;

        public void parseOpeningDate(Timestamp timestamp){
            // Timestamp -> String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestampString = dateFormat.format(timestamp);
            // String "yyyy-MM-dd HH:mm:ss" -> "yyyy-MM-dd"
            String dateTime = LocalDateTime
                    .parse(timestampString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.openingDate = dateTime;
        }
        public void parseClosingDate(Timestamp timestamp){
            // Timestamp -> String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestampString = dateFormat.format(timestamp);
            // String "yyyy-MM-dd HH:mm:ss" -> "yyyy-MM-dd"
            String dateTime = LocalDateTime
                    .parse(timestampString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.closingDate = dateTime;
        }
        public void parseCreatedAt(Timestamp timestamp){
            // Timestamp -> String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS");
            String timestampString = dateFormat.format(timestamp);
            // String "yyyy-MM-dd HH:mm:ss.SSSSS" -> "yyyy-MM-dd"
            String dateTime = LocalDateTime
                    .parse(timestampString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.createdAt = dateTime;
        }
    }

    @Data
    public static class BoardDetailDTO{
        private Integer id;
        private String title;
        private String content;
        private String field;
        private String position;
        private String salary;
        private Timestamp openingDate;
        private Timestamp closingDate;

        private String address;
        private String businessName;
        private String email;
        private String name;
        private String phoneNumber;
        private Integer employerId;
        private Boolean boardOwner;

        public void isBoardOwner(User sessionUser){
            if(sessionUser == null) boardOwner = false;
            else boardOwner = sessionUser.getId() == employerId;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public String getOpeningDate(){
            return openingDate.toLocalDateTime().format(formatter);
        }
        public String getClosingDate(){
            return closingDate.toLocalDateTime().format(formatter);
        }
        // ADDRESS BUSINESS_NAME EMAIL NAME PHONE_NUMBER
        // ID TITLE CONTENT FIELD POSITION SALARY OPENING_DATE CLOSING_DATE
    }

    @Data
    public static class ApplyFormDTO {
        private Integer employerId;
        private String businessName;
        private Integer id;
        private String title;
        private Timestamp closingDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public String getClosingDate(){
            return closingDate.toLocalDateTime().format(formatter);
        }
    }

}

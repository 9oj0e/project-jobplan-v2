package shop.mtcoding.projectjobplan.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        // 회원 정보
        private String username;
        private String password;
        // 개인 정보
        private String name;
        private String birthdate;
        private Character gender; // 'M' or 'F'
        private String phoneNumber;
        private String address;
        private String email;
        // 학력 정보
        private String educationLevel; // 고졸/초대졸/대졸
        private String schoolName;
        private String major;
        // 회사 정보
        private Boolean isEmployer; // 사업자인지 userId, employerId
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름
    }

    @Data
    public static class UpdateDTO {
        // 회원 정보
        private String password;
        // 개인 정보
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        // 이력서정보
        private String schoolName;
        private String major;
        private String educationLevel; // 고졸/초대졸/대졸
        private String career; // 회사명+경력
        // 회사 정보
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}

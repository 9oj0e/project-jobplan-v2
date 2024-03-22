package shop.mtcoding.projectjobplan.user;

import lombok.Data;

import java.sql.Timestamp;

public class UserResponse {

    @Data
    public static class UpdateFormDTO {
        // 회원 정보
        private Integer id;
        private String username;
        private String password;

        // 개인 정보
        private String name;
        private String birthdate;
//        private Character gender; // view에서 분기 처리 하려면.. JS필요
        private String phoneNumber;
        private String address;
        private String email;

        // 회사 정보
        private Boolean isEmployer;
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름

        public UpdateFormDTO(User user, User sessionUser) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();

            if (sessionUser.getIsEmployer()) {
                this.isEmployer = user.getIsEmployer();
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
            }
        }
    }
    @Data
    public static class ProfileDTO{
        // 회원 정보
        private Integer id;
        private String username;
        private String password;

        // 개인 정보
        private String name;
        private String birthdate;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;

        // 기업 정보
        private Boolean isEmployer;
        private String employerIdNumber;
        private String businessName;

        public ProfileDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            if (user.getIsEmployer()) {
                this.isEmployer = user.getIsEmployer();
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
            }
        }
    }
}


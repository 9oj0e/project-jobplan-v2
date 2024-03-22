package shop.mtcoding.projectjobplan.user;

import lombok.Data;

import java.sql.Timestamp;

public class UserResponse {

    @Data
    public static class DTO {
        // 회원 정보
        private Integer id;
        private String username;
        private String password;

        // 개인 정보
        private String name;
        private String birthdate;
//        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;

        // 회사 정보
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름

        public DTO(User user, User sessionUser) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            if (sessionUser.getIsEmployer()) {
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
            }
        }
    }

    @Data
    public static class UserDTO {
        private Integer id;
        private String username;
        private String birthdate;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        private Boolean isEmployer;
        private String name;
        public UserDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.birthdate = user.getBirthdate();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            this.isEmployer = user.getIsEmployer();
            this.name = user.getName();
        }
    }
    @Data
    public static class EmployerDTO{
        private Integer id;
        private String username;
        private String birthdate;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        private Boolean isEmployer;
        private String employerIdNumber;
        private String businessName;
        private String name;
        public EmployerDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.birthdate = user.getBirthdate();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            this.isEmployer = user.getIsEmployer();
            this.employerIdNumber = user.getEmployerIdNumber();
            this.businessName = user.getBusinessName();
            this.name = user.getName();
        }
    }
}


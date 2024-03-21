package shop.mtcoding.projectjobplan.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class SaveDTO {

        private String username;
        private String password;
        private String name;

        private String birthdate;
        private Character gender; // 'M' or 'F'
        private String phoneNumber;
        private String address;
        private String email;

        // employer, 사업자 항목 nullable
        private Boolean isEmployer; // 사업자인지 userId, employerId
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름
        private Timestamp createdAt;



        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .birthdate(birthdate)
                    .gender(gender)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .email(email)
                    .isEmployer(isEmployer)
                    .employerIdNumber(employerIdNumber)
                    .businessName(businessName)
                    .createdAt(createdAt)
                    .build();
        }




    }

    @Data
    public static class UpdateDTO {
        // toEntity
        private String password;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;

        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름
    }



    public class LoginDTO {
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}

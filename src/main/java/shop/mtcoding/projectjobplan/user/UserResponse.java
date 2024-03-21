package shop.mtcoding.projectjobplan.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class DTO {
        private String password;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;

        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름

        public DTO(User user, User sessionUser) {
            this.password = user.getPassword();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();

            // 세션 유저의 isEmployer 값이 true면 회사
            if (sessionUser.getIsEmployer()) {
                this.address = getAddress();
                this.email = getEmail();
            }
        }
    }

}

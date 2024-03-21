package shop.mtcoding.projectjobplan.user;

import lombok.Data;

public class UserRequest {
    public static class SaveDTO {
        // toEntity
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
}

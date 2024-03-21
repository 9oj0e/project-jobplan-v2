package shop.mtcoding.projectjobplan.user;

import lombok.Data;

public class UserRequest {
    public static class SaveDTO {
        // toEntity
    }
    public static class UpdateDTO {
        // toEntity
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}

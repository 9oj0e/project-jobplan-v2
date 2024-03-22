package shop.mtcoding.projectjobplan.resume;

import lombok.Data;

public class ResumeRequest {
    public static class SaveDTO {
        // toEntity
    }
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String schoolName;
        private String major;
        private String educationLevel;
        private String career;

        // toEntity
    }
}

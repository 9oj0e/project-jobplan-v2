package shop.mtcoding.projectjobplan.resume;

import lombok.Data;

import java.util.List;

public class ResumeRequest {
    @Data
    public class UploadDTO {
        // title, cv, schoolName, major, educationLevel, career
        private String title;
        private String content;
        private String schoolName;
        private String major;
        private String educationLevel;
        private String career;

        private List<String> skill;
    }

    @Data
    public class UpdateDTO {

        private String title;
        private String content;
        private String schoolName;
        private String major;
        private String educationLevel;
        private String career;

        private List<String> skill;

    }
}

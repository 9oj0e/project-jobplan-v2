package shop.mtcoding.projectjobplan.resume;

import lombok.Data;
import shop.mtcoding.projectjobplan.user.User;

public class ResumeRequest {

    @Data // 이력서 작성하기
    public static class SaveDTO {
        private String educationLevel; // 졸업 형태
        private String schoolName;
        private String major; // 전공
        private String career; // 경력 정보
        private String title;
        private String content; // 자기소개서

        // toEntity
        public Resume toEntity(User user) {
            return Resume.builder()
                    .user(user)
                    .educationLevel(educationLevel)
                    .schoolName(schoolName)
                    .major(major)
                    .career(career)
                    .title(title)
                    .content(content)
                    .build();
        }
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

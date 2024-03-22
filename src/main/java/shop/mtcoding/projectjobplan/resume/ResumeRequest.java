package shop.mtcoding.projectjobplan.resume;

import lombok.Data;

public class ResumeRequest {

    @Data // 이력서 작성하기
    public static class SaveDTO {
        private String title;
        private String schoolName;
        private String educationLevel; // 졸업 형태
        private String major; // 전공

        // 스킬 제외

        private String career; // 경력 정보
        private String content; // 자기소개서

        // toEntity
        public Resume toEntity() {
            return Resume.builder()
                    .title(title)
                    .schoolName(schoolName)
                    .educationLevel(educationLevel)
                    .major(major)
                    .career(career)
                    .content(content)
                    .build();
        }
    }
    public static class UpdateDTO {
        // toEntity
    }
}

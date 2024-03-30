package shop.mtcoding.projectjobplan.resume;

import lombok.Data;

public class ResumeRequest {

    @Data // 이력서 작성하기
    public static class PostDTO {
        private String title;
        private String content; // 자기소개서
        private String career; // 경력 정보
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String career;
    }
}

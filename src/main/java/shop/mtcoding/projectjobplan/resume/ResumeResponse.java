package shop.mtcoding.projectjobplan.resume;

import lombok.Data;
import shop.mtcoding.projectjobplan.board.Board;

public class ResumeResponse {

    @Data
    public static class UpdateDTO{
        // 유저 정보
        private String name;
        private String birthdate;
        private String phoneNumber;
        private String email;
        private String address;

        // 이력서 정보
        private Integer id; // resumeId
        private String title;
        private String schoolName;
        private String educationLevel;
        private String major;
        private String career;
        private String content;

        public UpdateDTO(Resume resume) {
            this.id = resume.getId();
            this.name = resume.getUser().getName();
            this.birthdate = resume.getUser().getBirthdate();
            this.phoneNumber = resume.getUser().getPhoneNumber();
            this.email = resume.getUser().getEmail();
            this.address = resume.getUser().getAddress();
            this.title = resume.getTitle();
            this.schoolName = resume.getSchoolName();
            this.educationLevel = resume.getEducationLevel();
            this.major = resume.getMajor();
            this.career = resume.getCareer();
            this.content = resume.getContent();
        }
    }
}

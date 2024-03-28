package shop.mtcoding.projectjobplan.resume;

import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;

public class ResumeResponse {

    @Data
    public static class UpdateDTO {
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

    @Data
    public static class DetailDTO {
        // 회원 정보
        private Integer userId;
        private String username;
        private String name;
        private String birthdate;
        private String address;
        private String phoneNumber;
        private String email;

        // 이력 정보
        private Integer id;
        private String schoolName;
        private String major;
        private String educationLevel; // 고졸/초대졸/대졸
        private String career; // 회사명+경력
        private String title;
        private String content; // cv, cover letter 자기소개서

        // 평점
        private Double rating;

        public DetailDTO(Resume resume, Double rating) {
            this.id = resume.getId();
            this.userId = resume.getUser().getId();
            this.username = resume.getUser().getUsername();
            this.name = resume.getUser().getName();
            this.birthdate = resume.getUser().getBirthdate();
            this.address = resume.getUser().getBirthdate();
            this.email = resume.getUser().getEmail();
            this.phoneNumber = resume.getUser().getPhoneNumber();

            this.schoolName = resume.getSchoolName();
            this.major = resume.getMajor();
            this.educationLevel = resume.getEducationLevel();
            this.career = resume.getCareer();
            this.title = resume.getTitle();
            this.content = resume.getContent();

            this.rating = rating;
        }
        public Double getRating(){
            return FormatUtil.소수점한자리(this.rating);
        }
    }

    @Data
    public static class MainDTO {
        // resume_tb
        private Integer id;
        private String career;
        private String title;

        // user_tb
        private String name;

        public MainDTO(Resume resume) {
            this.id = resume.getId();
            this.career = resume.getCareer();
            this.title = resume.getTitle();
            this.name = resume.getUser().getName();
        }
    }
}
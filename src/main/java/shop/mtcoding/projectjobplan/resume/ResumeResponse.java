package shop.mtcoding.projectjobplan.resume;

public class ResumeResponse {
    public static class DetailDTO {

        private Integer id;
        private Integer userId;
        private String username;
        private String name;
        private String birthdate;
        private String address;
        private String phoneNumber;
        private String email;

        private String schoolName;
        private String major;
        private String educationLevel; // 고졸/초대졸/대졸
        private String career; // 회사명+경력

        private String title;
        private String content; // cv, cover letter 자기소개서

        public DetailDTO(Resume resume) {
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
        }
    }
}

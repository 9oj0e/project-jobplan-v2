package shop.mtcoding.projectjobplan.resume;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;

import java.util.List;

public class ResumeResponse {

    @Data
    public static class UpdateFormDTO {
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

        // skill
        private List<SkillDTO> skillList;

        public UpdateFormDTO(Resume resume) {
            this.id = resume.getId();
            this.name = resume.getUser().getName();
            this.birthdate = resume.getUser().getBirthdate();
            this.phoneNumber = resume.getUser().getPhoneNumber();
            this.email = resume.getUser().getEmail();
            this.address = resume.getUser().getAddress();

            this.schoolName = resume.getSchoolName();
            this.educationLevel = resume.getEducationLevel();
            this.major = resume.getMajor();
            this.career = resume.getCareer();

            this.title = resume.getTitle();
            this.content = resume.getContent();

            this.skillList = resume.getUser().getSkills().stream().map(skill -> new SkillDTO(skill.getName())).toList();
        }

        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }
    }

    @Data
    public static class DetailDTO {
        // user 정보
        private Integer userId;
        private String username;
        private String name;
        private String birthdate;
        private String address;
        private String phoneNumber;
        private String email;
        private boolean isResumeOwner; // 이력서 주인 여부
        private boolean hasSubscribed; // 구독 여부
        // skill 정보 (보유 스킬)
        private List<SkillDTO> skillList;
        // 평점
        private Double rating;
        // resume 정보
        private Integer id;
        private String schoolName;
        private String major;
        private String educationLevel; // 고졸/초대졸/대졸
        private String career; // 회사명+경력
        private String title;
        private String content; // cv, cover letter 자기소개서

        public DetailDTO(Resume resume, Double rating, boolean isResumeOwner, boolean hasSubscribed) {
            this.userId = resume.getUser().getId();
            this.username = resume.getUser().getUsername();
            this.name = resume.getUser().getName();
            this.birthdate = resume.getUser().getBirthdate();
            this.address = resume.getUser().getAddress();
            this.phoneNumber = resume.getUser().getPhoneNumber();
            this.email = resume.getUser().getEmail();
            this.isResumeOwner = isResumeOwner;
            this.hasSubscribed = hasSubscribed;
            this.skillList = resume.getUser().getSkills().stream().map(skill -> new SkillDTO(skill.getName())).toList();
            this.rating = rating;
            this.id = resume.getId();
            this.schoolName = resume.getSchoolName();
            this.major = resume.getMajor();
            this.educationLevel = resume.getEducationLevel();
            this.career = resume.getCareer();
            this.title = resume.getTitle();
            this.content = resume.getContent();
        }

        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }

        public Double getRating() {
            return FormatUtil.numberFormatter(this.rating);
        }
    }

    @Data
    public static class ListingsDTO {
        Page<ResumeDTO> resumeList;
        List<Integer> pageList;

        public ListingsDTO(List<Resume> resumes, Pageable pageable) {
            List<ResumeDTO> resumeList = resumes.stream().map(resume -> new ResumeDTO(resume)).toList();
            this.resumeList = PagingUtil.pageConverter(resumeList, pageable);
            this.pageList = PagingUtil.getPageList(this.resumeList);
        }

        public class ResumeDTO {
            // resume_tb
            private Integer id;
            private String career;
            private String title;

            // user_tb
            private String name;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.career = resume.getCareer();
                this.title = resume.getTitle();
                this.name = resume.getUser().getName();
            }
        }
    }
}
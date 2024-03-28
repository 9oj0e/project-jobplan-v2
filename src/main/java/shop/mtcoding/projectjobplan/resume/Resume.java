package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.subscribe.Subscribe;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String schoolName;
    private String major;
    private String educationLevel; // 고졸/초대졸/대졸
    private String career; // 회사명+경력

    private String title;
    private String content; // cv, cover letter 자기소개서

    @CreationTimestamp
    private Timestamp createdAt;

    @OrderBy("id desc")
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Entity 객체의 변수명 == FK의 주인
    private List<Subscribe> subscribes = new ArrayList<>();

    @OrderBy("id desc")
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Entity 객체의 변수명 == FK의 주인
    private List<Apply> applies = new ArrayList<>();

    public void update(ResumeRequest.UpdateDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.schoolName = requestDTO.getSchoolName();
        this.major = requestDTO.getMajor();
        this.educationLevel = requestDTO.getEducationLevel();
        this.career = requestDTO.getCareer();
    }

    @Builder
    public Resume(Integer id, User user, String schoolName, String major, String educationLevel, String career, String title, String content, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.schoolName = schoolName;
        this.major = major;
        this.educationLevel = educationLevel;
        this.career = career;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}

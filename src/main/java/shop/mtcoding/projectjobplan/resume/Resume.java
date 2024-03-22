package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.time.LocalDate;

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

    public void update(ResumeRequest.UpdateDTO reqDTO){
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.schoolName = reqDTO.getSchoolName();
        this.major = reqDTO.getMajor();
        this.educationLevel = reqDTO.getEducationLevel();
        this.career = reqDTO.getCareer();
    }
}

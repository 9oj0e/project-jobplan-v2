package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.*;
import lombok.Builder;
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

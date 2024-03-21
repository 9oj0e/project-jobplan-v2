package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId; // writtenBy 이력서 작성자 아이디 (구직자)
    private String title;
    private String content; // cv, cover letter 자기소개서
    private String schoolName;
    private String major;
    private String educationLevel; // 고졸/초대졸/대졸
    private String career; // 회사명+경력
    private LocalDate createdAt;
}

package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.*;
import lombok.Data;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;
    private String content; // cv, cover letter 자기소개서
    private LocalDate createdAt;
}

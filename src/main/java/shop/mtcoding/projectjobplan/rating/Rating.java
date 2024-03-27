package shop.mtcoding.projectjobplan.rating;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "rating_tb")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rater_id")
    private User rater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private User subject;

    @Column(nullable = false)
    private Double rate;

    // 이력서 주인이 공고를 보고 공고 주인을 평가
    public Rating(User sessionUser, Board board, Double rate) {
        this.rater = sessionUser;
        this.subject = board.getUser();
        this.rate = rate;
    }


    // 공고 주인이 이력서를 보고 이력서 주인을 평가
    public Rating(User sessionUser, Resume resume, Double rate) {
        this.rater = sessionUser;
        this.subject = resume.getUser();
        this.rate = rate;
    }

    @CreationTimestamp
    private Timestamp createdAt;

    /*
    private Boolean isRater;
    public Boolean getIsRater(Integer sessionUserId) { // 평가 기록이 있는지.
        return sessionUserId == this.raterId ? true : false;
    }
    */
}

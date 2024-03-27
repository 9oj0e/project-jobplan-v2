package shop.mtcoding.projectjobplan.rating;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "rating_tb")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // raterId 평가자 id
    // subjectId 피평가자 id
    private Integer rating;
    @CreationTimestamp
    private Timestamp createdAt;

    /*
    private Boolean isRater;
    public Boolean getIsRater(Integer sessionUserId) { // 평가 기록이 있는지.
        return sessionUserId == this.raterId ? true : false;
    }
    */
}

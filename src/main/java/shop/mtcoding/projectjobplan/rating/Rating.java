package shop.mtcoding.projectjobplan.rating;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rating_tb")
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer raterId; // 평가자 ID
    private Integer subjectId; // 피평가자 ID
    private Integer rating; // 점수

    /*
    private Boolean isRater;
    public Boolean getIsRater(Integer sessionUserId) { // 평가 기록이 있는지.
        return sessionUserId == this.raterId ? true : false;
    }
    */
}

package shop.mtcoding.projectjobplan.rating;

import lombok.Data;

public class RatingRequest {

    @Data
    public class RatingDTO {
        private Integer raterId; // 평가자 ID
        private Integer subjectId; // 피평가자 ID
        private Integer rate; // 점수
    }
}

package shop.mtcoding.projectjobplan.rating;

import lombok.Data;

public class RatingRequest {

    @Data
    public static class rateBoardUser {
        private Integer raterId;
        private Integer boardId;
        private Integer rating;
    }

    @Data
    public static class rateResumeUser {
        private Integer raterId;
        private Integer resumeId;
        private Integer rating;
    }
}

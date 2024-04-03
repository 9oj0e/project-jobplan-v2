package shop.mtcoding.projectjobplan.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

public class RatingRequest {

    @Data
    public static class RateBoardUser {
        private Integer raterId;
        private Integer boardId;
        @Min(1)
        @Max(5)
        private int rating;
    }

    @Data
    public static class RateResumeUser {
        private Integer raterId;
        private Integer resumeId;
        @Min(1)
        @Max(5)
        private int rating;
    }
}

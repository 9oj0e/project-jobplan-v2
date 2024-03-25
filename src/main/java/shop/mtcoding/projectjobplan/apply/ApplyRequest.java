package shop.mtcoding.projectjobplan.apply;

import lombok.Data;

public class ApplyRequest {
    @Data
    public static class ApplyDTO {
        private Integer resumeId;
        private Integer boardId;
    }
    
    @Data
    public static class UpdateDTO {
        private Integer resumeId;
        private Integer boardId;
        private Boolean status;
    }
}
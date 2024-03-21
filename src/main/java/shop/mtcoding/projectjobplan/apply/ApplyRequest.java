package shop.mtcoding.projectjobplan.apply;

import lombok.Data;

import java.sql.Timestamp;

public class ApplyRequest {
    @Data
    public class UploadDTO{ // 지원하기
        private Integer resumeId;
        private Integer resumeUserId;
        private Integer boardId;
        private Integer boardUserId;
    }
    @Data
    public class UpdateDTO{ // 합격 불합격 업데이트
        private Integer resumeId;
        private Integer resumeUserId;
        private Integer boardId;
        private Integer boardUserId;
        private Boolean status;
    }

}

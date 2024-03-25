package shop.mtcoding.projectjobplan.apply;

import lombok.Data;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

public class ApplyResponse {

    @Data
    public static class ApplyDTO {
        private Integer resumeId; // 이력서 id
        private Integer boardId; // 공고 id
        private String resumeTitle; // 이력서 제목
        private String businessName; // 지원한 회사 이름
        private String boardTitle; // 지원한 공고 제목
        private String appliedAt; // 공고 지원 날짜


        public ApplyDTO(Apply apply, User sessionUser) {
            if (!sessionUser.getIsEmployer()) {
                this.boardId = apply.getBoard().getId();
                this.resumeId = apply.getResume().getId();
                this.resumeTitle = apply.getResume().getTitle();
                this.businessName = apply.getBoard().getUser().getBusinessName();
                this.boardTitle = apply.getBoard().getTitle();
                this.appliedAt = apply.getCreatedAt();
            }
        }
    }




}

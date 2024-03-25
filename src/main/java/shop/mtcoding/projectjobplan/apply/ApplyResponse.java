package shop.mtcoding.projectjobplan.apply;

import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;
import java.sql.Timestamp;

@Data
public class ApplyResponse {
    public static class ApplyFormDTO {
        private Integer resumeId;
        private String title;
        private Timestamp createdAt;

        public ApplyFormDTO(Resume resume) {
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.createdAt = resume.getCreatedAt();
        }

        public String getCreatedAt() {
            return FormatUtil.timeFormatter(this.createdAt);
        }
    }

    @Data
    public static class ApplyDTO {
        private Integer resumeId; // 이력서 id
        private Integer boardId; // 공고 id
        private String resumeTitle; // 이력서 제목
        private String businessName; // 지원한 회사 이름
        private String boardTitle; // 지원한 공고 제목
        private Timestamp appliedAt; // 공고 지원 날짜


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

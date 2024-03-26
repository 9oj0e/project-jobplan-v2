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
        private String appliedAt; // 공고 지원 날짜
        private Boolean status;
        private String applicantName ;

        public String getStatusOutput(){
            try {
                if (this.status) return "합격";
                else if (!this.status) return "불합격";
                else return null;
            } catch (Exception e) {
                return null;
            }
        }


        public ApplyDTO(Apply apply, User sessionUser) {
            if (!sessionUser.getIsEmployer()) {
                this.boardId = apply.getBoard().getId();
                this.resumeId = apply.getResume().getId();
                this.resumeTitle = apply.getResume().getTitle();
                this.businessName = apply.getBoard().getUser().getBusinessName();
                this.boardTitle = apply.getBoard().getTitle();
                this.appliedAt = apply.getCreatedAt();
            } else{
                this.boardId = apply.getBoard().getId();
                this.resumeId = apply.getResume().getId();
                this.resumeTitle = apply.getResume().getTitle();
                this.businessName = apply.getBoard().getUser().getBusinessName();
                this.boardTitle = apply.getBoard().getTitle();
                this.appliedAt = apply.getCreatedAt();
                this.applicantName = apply.getResume().getUser().getName();
            }

        }

        public String getBoardTitle(){
            String input = this.boardTitle; // 입력받은 문자열
            int maxLength = 8; // 최대 길이

            String output;
            if (input.length() > maxLength) {
                output = input.substring(0, maxLength) + "...";
            } else {
                output = input;
            }

            return output;
        }
        public String getResumeTitle(){
            String input = this.resumeTitle; // 입력받은 문자열
            int maxLength = 8; // 최대 길이

            String output;
            if (input.length() > maxLength) {
                output = input.substring(0, maxLength) + "...";
            } else {
                output = input;
            }

            return output;
        }
    }
}



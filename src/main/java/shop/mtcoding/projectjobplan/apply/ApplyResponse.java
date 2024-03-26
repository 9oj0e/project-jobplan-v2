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
}



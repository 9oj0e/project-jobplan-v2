package shop.mtcoding.projectjobplan.subscribe;

import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.util.List;

public class SubscribeResponse {
    @Data
    public static class DTO {
        private List<BoardDTO> boardList;
        private List<ResumeDTO> resumeList;

        public DTO(User user, List<Subscribe> subscription) {
            if (user.getIsEmployer()) {
                this.resumeList = subscription.stream().map(subscribe -> new ResumeDTO(subscribe.getResume())).toList();
            } else {
                this.boardList = subscription.stream().map(subscribe -> new BoardDTO(subscribe.getBoard())).toList();
            }
        }

        // (개인) 공고 구독 목록
        public class BoardDTO {
            private String address;
            private String businessName;
            private Integer boardId;
            private String field;
            private String title;
            private String salary;
            private Timestamp closingDate;

            public String getClosingDate(){
                return FormatUtil.timeFormatter(this.closingDate);
            }

            public BoardDTO(Board board) {
                this.address = board.getUser().getAddress();
                this.businessName = board.getUser().getBusinessName();
                this.boardId = board.getId();
                this.field = board.getField();
                this.title = board.getTitle();
                this.salary = board.getSalary();
                this.closingDate = board.getClosingDate();
            }
        }

        // (기업) 이력서 구독 목록
        public class ResumeDTO {
            private Integer resumeId;
            private String resumeUserName;
            private String title;
            private String birthdate ;
            private String email;

            public ResumeDTO(Resume resume) {
                this.resumeId = resume.getId();
                this.resumeUserName = resume.getUser().getName();
                this.title = resume.getTitle();
                this.birthdate = resume.getUser().getBirthdate();
                this.email = resume.getUser().getEmail();
            }
        }
    }
}

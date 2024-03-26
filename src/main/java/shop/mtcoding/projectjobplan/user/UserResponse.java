package shop.mtcoding.projectjobplan.user;

import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    @Data
    public static class UpdateFormDTO {
        // 회원 정보
        private Integer id;
        private String username;
        private String password;

        // 개인 정보
        private String name;
        private String birthdate;
        // private Character gender; // view에서 분기 처리 하려면.. JS필요
        private String phoneNumber;
        private String address;
        private String email;

        // 회사 정보
        private Boolean isEmployer;
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름

        public UpdateFormDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();

            if (user.getIsEmployer()) {
                this.isEmployer = user.getIsEmployer();
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
            }
        }
    }

    @Data
    public static class ProfileDTO {
        // 회원 정보
        private Integer id;
        private String username;
        private String password;

        // 개인 정보
        private String name;
        private String birthdate;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        private List<ResumeDTO> resumeList = new ArrayList<>();

        // 기업 정보
        private Boolean isEmployer;
        private String employerIdNumber;
        private String businessName;
        private List<BoardDTO> boardList = new ArrayList<>();

        // 지원자 현황 및 지원 현황
        private List<ApplyDTO> applyList;

        public ProfileDTO(User user, List<Apply> applyList) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            if (user.getIsEmployer()) {
                this.isEmployer = user.getIsEmployer();
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
                this.boardList = user.getBoards().stream().map(board -> new BoardDTO(board)).toList();
            } else {
                this.resumeList = user.getResumes().stream().map(resume -> new ResumeDTO(resume)).toList();
            }
            this.applyList = applyList.stream().map(apply -> new ApplyDTO(apply)).toList();
        }

        public class BoardDTO {
            private Integer id;
            private String title;
            private String field;
            private String position;
            private Timestamp openingDate;

            public BoardDTO(Board board) {
                this.id = board.getId();
                this.title = board.getTitle();
                this.field = board.getField();
                this.position = board.getPosition();
                this.openingDate = board.getOpeningDate();
            }

            public String getOpeningDate() {
                return FormatUtil.timeFormatter(this.openingDate);
            }

            public String getTitle() {
                return FormatUtil.stringFormatter(this.title);
            }
        }

        public class ResumeDTO {
            private Integer id;
            private String title;
            private Timestamp createdAt;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
                this.createdAt = resume.getCreatedAt();
            }

            public String getTitle() {
                return FormatUtil.stringFormatter(this.title);
            }

            public String getCreatedAt() {
                return FormatUtil.timeFormatter(this.createdAt);
            }
        }

        public class ApplyDTO {
            // 이력서 정보
            private Integer resumeId;
            private String resumeTitle;
            private String applicantName;

            // 공고 정보
            private Integer boardId;
            private String boardTitle;
            private String businessName;

            // 지원 정보
            private Integer id;
            private Boolean status;
            private String createdAt;

            public ApplyDTO(Apply apply) {
                this.resumeId = apply.getResume().getId();
                this.resumeTitle = apply.getResume().getTitle();
                this.applicantName = apply.getResume().getUser().getName();
                this.boardId = apply.getBoard().getId();
                this.boardTitle = apply.getBoard().getTitle();
                this.businessName = apply.getBoard().getUser().getBusinessName();
                this.id = apply.getId();
                this.createdAt = apply.getCreatedAt();
                this.status = apply.getStatus();
            }

            public String getResumeTitle() {
                return FormatUtil.stringFormatter(this.resumeTitle);
            }

            public String getBoardTitle() {
                return FormatUtil.stringFormatter(this.boardTitle);
            }

            public String getStatus() {
                try {
                    if (this.status) return "합격";
                    else if (!this.status) return "불합격";
                    else return null;
                } catch (Exception e) {
                    return null;
                }
            }
        }
    }
}


package shop.mtcoding.projectjobplan.board;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;

import java.sql.Timestamp;
import java.util.List;

public class BoardResponse {
    @Data
    public static class UpdateFormDTO {
        private Integer id; // boardId
        private String title;
        private String field;
        private String position;
        private String salary;
        private String content;
        private String openingDate;
        private String closingDate;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
            this.content = board.getContent();
            this.openingDate = FormatUtil.timeFormatter(board.getOpeningDate());
            this.closingDate = FormatUtil.timeFormatter(board.getClosingDate());
        }
    }

    @Data
    public static class DetailDTO {
        // user 정보
        private String address;
        private String phoneNumber;
        private String email;
        private String businessName;
        private boolean isBoardOwner; // 공고 주인 여부
        private boolean hasSubscribed; // 구독 여부
        // 평점
        private Double rating;
        // board 정보
        private Integer id;
        private String title; // 제목
        private String content; // 내용
        private String field; // 채용 분야
        private String position; // 포지션
        private String salary; // 연봉
        // skill 정보 (우대 스킬)
        private List<SkillDTO> skillList;
        // 시간 정보
        private Timestamp openingDate; // 게시일
        private Timestamp closingDate; // 마감일 == null -> "상시채용"

        public DetailDTO(Board board, Double rating, boolean isBoardOwner, boolean hasSubscribed) {
            this.address = board.getUser().getAddress();
            this.phoneNumber = board.getUser().getPhoneNumber();
            this.email = board.getUser().getEmail();
            this.businessName = board.getUser().getBusinessName();
            this.isBoardOwner = isBoardOwner;
            this.hasSubscribed = hasSubscribed;
            this.rating = rating;
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
            this.skillList = board.getSkillList().stream().map(skill -> new SkillDTO(skill.getName())).toList();
            this.openingDate = board.getOpeningDate();
            this.closingDate = board.getClosingDate();
        }

        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }

        public String getOpeningDate() {
            return FormatUtil.timeFormatter(this.openingDate);
        }

        public String getClosingDate() {
            return FormatUtil.timeFormatter(this.closingDate);
        }

        public Double getRating() {
            return FormatUtil.numberFormatter(this.rating);
        }

    }

    @Data
    public static class ListingsDTO {
        Page<BoardDTO> page;
        List<Integer> pageList;

        public ListingsDTO(List<Board> boards, Pageable pageable) {
            List<BoardDTO> boardList = boards.stream().map(board -> new BoardDTO(board)).toList();
            this.page = PagingUtil.pageConverter(boardList, pageable);
            this.pageList = PagingUtil.getPageList(this.page);
        }

        public class BoardDTO {
            // board_tb
            private Integer id;
            private String title;
            private String salary;
            private Timestamp closingDate;

            // user_tb
            private String address;
            private String businessName;

            public BoardDTO(Board board) {
                this.id = board.getId();
                this.title = board.getTitle();
                this.salary = board.getSalary();
                this.closingDate = board.getClosingDate();
                this.address = board.getUser().getAddress();
                this.businessName = board.getUser().getBusinessName();
            }

            public String getClosingDate() {
                return FormatUtil.timeFormatter(closingDate);
            }
        }
    }

    @Data
    public static class IndexDTO {
        // 공고 정보
        private Integer id;
        private String title;
        private String field;
        private String position;

        // 게시자 정보 (기업)
        private Integer userId;
        private String businessName;

        public IndexDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.field = board.getField();
            this.position = board.getPosition();
            this.userId = board.getUser().getId();
            this.businessName = board.getUser().getBusinessName();
        }
    }
}

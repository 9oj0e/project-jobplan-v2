package shop.mtcoding.projectjobplan.user;

import lombok.Data;
import shop.mtcoding.projectjobplan.board.Board;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    @Data
    public static class DTO {
        // 회원 정보
        private Integer id;
        private String username;
        private String password;

        // 개인 정보
        private String name;
        private String birthdate;
//        private Character gender; // view에서 분기 처리 하려면.. JS필요
        private String phoneNumber;
        private String address;
        private String email;

        // 회사 정보
        private Boolean isEmployer;
        private String employerIdNumber; // 사업자번호
        private String businessName; // 기업이름

        public DTO(User user, User sessionUser) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.name = user.getName();
            this.birthdate = user.getBirthdate();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            if (sessionUser.getIsEmployer()) {
                this.isEmployer = user.getIsEmployer();
                this.employerIdNumber = user.getEmployerIdNumber();
                this.businessName = user.getBusinessName();
            }
        }
    }

    @Data
    public static class UserDTO {
        private Integer id;
        private String username;
        private String birthdate;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        private Boolean isEmployer;
        private String name;
        public UserDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.birthdate = user.getBirthdate();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.email = user.getEmail();
            this.isEmployer = user.getIsEmployer();
            this.name = user.getName();
        }
    }
    @Data
    public static class EmployerDTO{
        private Integer id;
        private String username;
        private String birthdate;
        private Character gender;
        private String phoneNumber;
        private String address;
        private String email;
        private Boolean isEmployer;
        private String employerIdNumber;
        private String businessName;
        private String name;
        private List<BoardDTO> boardList =new ArrayList<>();

        public EmployerDTO(User sessionUser) {
            this.id = sessionUser.getId();
            this.username = sessionUser.getUsername();
            this.birthdate = sessionUser.getBirthdate();
            this.gender = sessionUser.getGender();
            this.phoneNumber = sessionUser.getPhoneNumber();
            this.address = sessionUser.getAddress();
            this.email = sessionUser.getEmail();
            this.isEmployer = sessionUser.getIsEmployer();
            this.employerIdNumber = sessionUser.getEmployerIdNumber();
            this.businessName = sessionUser.getBusinessName();
            this.name = sessionUser.getName();

            this.boardList = sessionUser.getBoards().stream().map(board -> new BoardDTO(board,sessionUser)).toList();
        }
        public class BoardDTO{
            private Integer id;
            private String title;
            private String field;
            private String position;
            private Timestamp openingDate;

            public BoardDTO(Board board ,User sessionUser) {
                this.id = board.getId();
                this.title = board.getTitle();
                this.field = board.getField();
                this.position = board.getPosition();
                this.openingDate = board.getOpeningDate();
            }
        }
    }
}


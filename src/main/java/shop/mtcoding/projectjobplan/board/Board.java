package shop.mtcoding.projectjobplan.board;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer employerId; // 게시자 id
    // @Column(nullable = false)
    private String title; // 제목
    private String content; // 내용
    private String field; // 채용 분야
    private String position; // 포지션
    private String salary; // 연봉
    // 날짜
    private Timestamp openingDate; // 게시일
    private Timestamp closingDate; // 마감일
    // closingDate == null -> "상시채용"
    private Timestamp createdAt; // 생성일


    public String getOpeningDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return openingDate.toLocalDateTime().format(formatter);
    }

    public String getClosingDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return closingDate.toLocalDateTime().format(formatter);
    }
}

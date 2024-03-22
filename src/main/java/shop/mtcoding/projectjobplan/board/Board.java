package shop.mtcoding.projectjobplan.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.user.User;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // @Column(nullable = false)
    private String title; // 제목
    private String content; // 내용
    private String field; // 채용 분야
    private String position; // 포지션
    private String salary; // 연봉

    // 날짜
    private String openingDate; // 게시일
    private String closingDate; // 마감일 == null -> "상시채용"

    @CreationTimestamp
    private Timestamp createdAt; // 생성일

    @Builder
    public Board(Integer id, User user, String title, String content, String field, String position, String salary, String openingDate, String closingDate, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.field = field;
        this.position = position;
        this.salary = salary;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.createdAt = createdAt;
    }
}

package shop.mtcoding.projectjobplan.board;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Timestamp openingDate; // 게시일
    private Timestamp closingDate; // 마감일 == null -> "상시채용"

    @CreationTimestamp
    private Timestamp createdAt; // 생성일
}

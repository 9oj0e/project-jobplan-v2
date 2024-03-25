package shop.mtcoding.projectjobplan.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan._core.utils.ConvertUtil;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
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

    public void update(BoardRequest.UpdateDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.field = requestDTO.getField();
        this.position = requestDTO.getPosition();
        this.salary = requestDTO.getSalary();
        this.openingDate = ConvertUtil.timestampConverter(requestDTO.getOpeningDate());
        this.closingDate = ConvertUtil.timestampConverter(requestDTO.getClosingDate());
    }

    @Builder
    public Board(Integer id, User user, String title, String content, String field, String position, String salary, String openingDate, String closingDate, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.field = field;
        this.position = position;
        this.salary = salary;
        this.openingDate = ConvertUtil.timestampConverter(openingDate);
        this.closingDate = ConvertUtil.timestampConverter(closingDate);
        this.createdAt = createdAt;
    }
}

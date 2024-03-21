package shop.mtcoding.projectjobplan.subscribe;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "subscribe_tb")
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer boardId;
    private Integer boardUserId; // 기업 회원
    private Integer resumeId;
    private Integer resumeUserId; // 일반 회원
    private Timestamp createdAt;

    public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return createdAt.toLocalDateTime().format(formatter);
    }
}

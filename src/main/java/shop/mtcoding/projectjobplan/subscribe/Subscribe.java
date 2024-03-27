package shop.mtcoding.projectjobplan.subscribe;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "subscribe_tb")
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // boardId, boardUserId
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    // resumeId, resumeUserId
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @CreationTimestamp
    private Timestamp createdAt;
}

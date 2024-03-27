package shop.mtcoding.projectjobplan.subscribe;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    public Subscribe(User user, Board board) {
        this.board = board;
        this.user = user;
    }

    public Subscribe(User user, Resume resume) {
        this.resume = resume;
        this.user = user;
    }
}

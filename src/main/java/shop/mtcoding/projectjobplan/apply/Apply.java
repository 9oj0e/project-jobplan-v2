package shop.mtcoding.projectjobplan.apply;

import jakarta.persistence.*;
import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "apply_tb")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;
    // resume_id, resume_user_id
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    // board_id, board_user_id
    private Boolean status;
    private Timestamp createdAt;

    public String getCreatedAt() {
        return FormatUtil.timeFormatter(createdAt);
    }
}

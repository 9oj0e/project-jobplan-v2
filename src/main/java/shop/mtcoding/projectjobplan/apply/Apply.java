package shop.mtcoding.projectjobplan.apply;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "apply_tb")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer resumeId;
    private Integer resumeUserId;
    private Integer boardId;
    private Integer boardUserId;
    private Boolean status;
    private Timestamp createdAt;
}

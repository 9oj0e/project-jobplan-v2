package shop.mtcoding.projectjobplan.subscribe;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "subscribe_tb")
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // boardId, boardUserId
    // resumeId, resumeUserId

    @CreationTimestamp
    private Timestamp createdAt;
}

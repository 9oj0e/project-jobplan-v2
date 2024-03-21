package shop.mtcoding.projectjobplan.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final EntityManager em;
}

package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ResumeQueryRepository {
    private final EntityManager em;
}

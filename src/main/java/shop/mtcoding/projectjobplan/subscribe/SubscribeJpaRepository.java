package shop.mtcoding.projectjobplan.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscribeJpaRepository extends JpaRepository<Subscribe, Integer> {
    Optional<List<Subscribe>> findByUserId(int userId);
}

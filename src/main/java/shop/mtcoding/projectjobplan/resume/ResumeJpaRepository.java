package shop.mtcoding.projectjobplan.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResumeJpaRepository extends JpaRepository <Resume, Integer> {
    @Query("SELECT r FROM Resume r WHERE r.user.id = :id ORDER BY r.id DESC")
    Optional<List<Resume>> findByUserId(@Param("id") int id);

    @Query("SELECT r FROM Resume r JOIN FETCH r.user ORDER BY r.id DESC")
    Optional<List<Resume>> findAllJoinUser();
}
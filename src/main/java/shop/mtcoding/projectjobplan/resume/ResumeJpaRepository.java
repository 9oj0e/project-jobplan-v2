package shop.mtcoding.projectjobplan.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.projectjobplan.user.User;

import java.util.Optional;

public interface ResumeJpaRepository extends JpaRepository <Resume, Integer> {
    @Query("select r from Resume r where r.id = :id")
    Optional<Resume> findResumeById(@Param("id") Integer id);
}

package shop.mtcoding.projectjobplan.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeJpaRepository extends JpaRepository <Resume, Integer> {
    @Query("select r from Resume r where r.user.id = :id ")
    List<Resume> findByUserId(@Param("id") int id);

    @Query("SELECT r FROM Resume r JOIN fetch r.user order by r.createdAt desc")
    List<Resume> findAllResume();
}
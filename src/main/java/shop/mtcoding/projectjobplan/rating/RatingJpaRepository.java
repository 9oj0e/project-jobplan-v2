package shop.mtcoding.projectjobplan.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingJpaRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.subject = :boardUserId")
    Optional<Double> findRatingAvgByBoardUserId(@Param("boardUserId") Integer boardUserId);
}

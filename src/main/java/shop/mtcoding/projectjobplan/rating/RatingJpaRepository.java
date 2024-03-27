package shop.mtcoding.projectjobplan.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.user.User;

public interface RatingJpaRepository extends JpaRepository<Rating, Integer> {


}

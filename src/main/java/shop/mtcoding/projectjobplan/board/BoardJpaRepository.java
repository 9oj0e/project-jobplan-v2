package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.projectjobplan.user.User;

public interface BoardJpaRepository extends JpaRepository <User, Integer> {

}

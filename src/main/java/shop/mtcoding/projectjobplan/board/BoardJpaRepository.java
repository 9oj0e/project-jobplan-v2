package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository <Board, Integer> {
    List<Board> findAllByUserId(@Param("userId")Integer userId);
}

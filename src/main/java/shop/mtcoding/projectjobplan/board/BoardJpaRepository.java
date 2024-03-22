package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
  
}

package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
  
}

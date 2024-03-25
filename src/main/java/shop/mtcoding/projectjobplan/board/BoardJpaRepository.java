package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT b FROM Board b JOIN fetch b.user order by b.createdAt desc")
    List<Board> findAllBoard();
}

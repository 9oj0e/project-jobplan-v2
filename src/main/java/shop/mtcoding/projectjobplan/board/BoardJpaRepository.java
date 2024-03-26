package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT b FROM Board b JOIN FETCH b.user ORDER BY b.createdAt DESC ")
    Optional<List<Board>> findAllBoardJoinUser();

    @Query("SELECT b FROM Board b JOIN FETCH User u WHERE b.user.id = u.id ORDER BY b.id DESC")
    Optional<List<Board>> findAllByUserId(int userId);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u ORDER BY b.id DESC")
    Optional<List<Board>> findAllJoinUser();
}

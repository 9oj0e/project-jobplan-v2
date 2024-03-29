package shop.mtcoding.projectjobplan.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT b FROM Board b JOIN FETCH b.user ORDER BY b.createdAt DESC")
    Optional<List<Board>> findAllBoardJoinUser();

    @Query("SELECT b FROM Board b JOIN FETCH User u WHERE b.user.id = u.id ORDER BY b.id DESC")
    Optional<List<Board>> findAllByUserId(int userId);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u ORDER BY b.id DESC")
    Optional<List<Board>> findAllJoinUser();

    @Query("select s.board from Skill s join fetch s.board.user where s.name =:skill ORDER BY s.board.createdAt DESC")
    Optional<List<Board>> findAllBoardJoinUserSkill(String skill);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u where u.address like %:address% ORDER BY b.createdAt DESC")
    Optional<List<Board>> findAllBoardJoinUserAddress(String address);
}

package shop.mtcoding.projectjobplan.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyJpaRepository extends JpaRepository<Apply, Integer> {
//    @Query
//        // todo : (기업) 모든 지원자 현황
//    Optional<List<?>> findByBoardUserId(int boardUserId);

    @Query
        // todo : (기업) 공고별 지원자 현황
    Optional<List<?>> findByBoardId(int boardId);

//    @Query
//        // todo : (개인) 지원 현황
//    Optional<List<?>> findByResumeUserId(int resumeUserId);

    @Query("SELECT a FROM Apply a WHERE a.board.user.id = :userId")
    List<Apply> findByBoardUserId(@Param("userId")int userId);


    @Query("SELECT a FROM Apply a WHERE a.resume.user.id = :userId")
    List<Apply> findByResumeUserId(@Param("userId")int userId);

    @Query("SELECT a FROM Apply a WHERE a.board.id = :boardId AND a.resume.id = :resumeId")
    Apply findByIdByBoardIdAndResumeId(@Param("boardId") Integer boardId, @Param("resumeId") Integer resumeId);
}

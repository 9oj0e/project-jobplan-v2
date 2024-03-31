package shop.mtcoding.projectjobplan.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.projectjobplan.apply.Apply;

import java.util.List;

public interface OfferJpaRepository extends JpaRepository<Offer, Integer> {

    // (기업) 제안 현황
    @Query("SELECT a FROM Apply a WHERE a.board.id = :boardId")
    List<Offer> findByBoardId(@Param("boardId") int boardId);

    // (개인) 받은 제안 현황
    @Query("SELECT a FROM Offer a WHERE a.resume.id = :resumeId")
    List<Offer> findByResumeId(@Param("resumeId") int resumeId);

}

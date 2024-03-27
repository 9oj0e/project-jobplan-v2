package shop.mtcoding.projectjobplan.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingJpaRepository ratingJpaRepository;
    private final BoardJpaRepository boardJpaRepository ;
    private final ResumeJpaRepository resumeJpaRepository ;

    @Transactional
    public void 별점주기공고(User sessionUser, int boardId, Double rate) {
        Board board = boardJpaRepository.findById(boardId).get();
        Rating rating = new Rating(sessionUser, board, rate);
        ratingJpaRepository.save(rating);
    }

    @Transactional
    public void 별점주기이력서(User sessionUser, int resumeId, Double rate) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        Rating rating = new Rating(sessionUser,resume,rate);
        ratingJpaRepository.save(rating);
    }
}

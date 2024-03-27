package shop.mtcoding.projectjobplan.subscribe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final SubscribeJpaRepository subscribeJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;

    @Transactional // 공고 구독
    public void createBoardSubscription(User sessionUser, int boardId) {
        Board board = boardJpaRepository.findById(boardId).get();
        Subscribe subscribe = new Subscribe(sessionUser, board);

        subscribeJpaRepository.save(subscribe);
    }

    @Transactional // 이력서 구독
    public void createResumeSubscription(User sessionUser, int resumeId) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        Subscribe subscribe = new Subscribe(sessionUser, resume);

        subscribeJpaRepository.save(subscribe);
    }
}

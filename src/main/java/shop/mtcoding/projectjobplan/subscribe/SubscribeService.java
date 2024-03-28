package shop.mtcoding.projectjobplan.subscribe;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final SubscribeJpaRepository subscribeJpaRepository;
    private final UserJpaRepository userJpaRepository;
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

    // 구독 리스트 불러오기
    public SubscribeResponse.DTO getSubscription(int userId) {
        User user = userJpaRepository.findById(userId).get();
        List<Subscribe> subscription = subscribeJpaRepository.findByUserId(userId).get();

        return new SubscribeResponse.DTO(user, subscription);
    }

    // 공고 구독 여부 확인 todo : BoardService 로 이동, BoardResponse.DetailDTO 에 값 전달
    public boolean checkBoardSubscription(int boardId, int userId) {
        Optional<Subscribe> optionalSubscribe = subscribeJpaRepository.findByBoardIdAndUserId(boardId, userId);
        if (optionalSubscribe.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    // 이력서 구독 여부 확인 todo : ResumeService 로 이동, ResumeResponse.DetailDTO 에 값 전달
    public boolean checkResumeSubscription(int resumeId, int userId) {
        Optional<Subscribe> optionalSubscribe = subscribeJpaRepository.findByResumeIdAndUserId(resumeId, userId);
        if (optionalSubscribe.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional // 공고 구독 중지
    public void removeBoardSubscription(int boardId, int userId) {
        Subscribe subscribe = subscribeJpaRepository.findByBoardIdAndUserId(boardId, userId).get();

        subscribeJpaRepository.delete(subscribe);
    }

    @Transactional // 이력서 구독 중지
    public void removeResumeSubscription(int resumeId, int userId) {
        Subscribe subscribe = subscribeJpaRepository.findByResumeIdAndUserId(resumeId, userId).get();

        subscribeJpaRepository.delete(subscribe);
    }
}

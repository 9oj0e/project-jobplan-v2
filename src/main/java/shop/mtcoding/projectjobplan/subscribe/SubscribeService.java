package shop.mtcoding.projectjobplan.subscribe;

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final SubscribeJpaRepository subscribeJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional // 공고 구독
    public void uploadByBoardId(SubscribeResponse.ToUserDTO respDTO, Board board, User sessionUser) {
        User user = userJpaRepository.findById(sessionUser.getId()).get();
    }

    @Transactional // 이력서 구독
    public void uploadByResumeId(SubscribeResponse.ToEmployerDTO respDTO, Resume resume) {

    }

    @Transactional // 공고 구독
    public void uploadByBoardId(Board board, User user) {
        Subscribe subscribe = Subscribe.builder()
                .board(board)
                .user(user)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        subscribeJpaRepository.save(subscribe);
    }

    @Transactional // 이력서 구독
    public void uploadByResumeId(Resume resume, User user) {
        Subscribe subscribe = Subscribe.builder()
                .resume(resume)
                .user(user)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        subscribeJpaRepository.save(subscribe);
    }


}

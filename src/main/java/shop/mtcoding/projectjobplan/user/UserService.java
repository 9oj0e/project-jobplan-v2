package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.apply.ApplyJpaRepository;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.apply.ApplyService;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;

    @Transactional
    public User createUser(UserRequest.JoinDTO requestDTO) { // join
        User user = requestDTO.toEntity();

        return userJpaRepository.save(user);
    }

    public User getUser(UserRequest.LoginDTO requestDTO) { // login
        User sessionUser = userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword()).get();

        return sessionUser;
    }

    public UserResponse.ProfileDTO getUser(User sessionUser, Integer boardId) {
        User user = userJpaRepository.findById(sessionUser.getId()).get();
        List<Apply> applyList;
        if (sessionUser.getIsEmployer()) {
            if (boardId == null) {
                // (기업) 모든 지원자 현황 보기
                applyList = applyJpaRepository.findByBoardUserId(user.getId());
            } else {
                // (기업) 공고별 지원자 보기
                applyList = applyJpaRepository.findByBoardId(boardId);
            }
        } else {
            // (개인) 지원 현황 보기
            applyList = applyJpaRepository.findByResumeUserId(user.getId());
        }
        Double rating = ratingJpaRepository.findRatingAvgByUserId(sessionUser.getId()).orElse(0.0);

        return new UserResponse.ProfileDTO(user, applyList, rating);
    }

    public UserResponse.UpdateFormDTO getUser(int id) {
        User user = userJpaRepository.findById(id).get();

        return new UserResponse.UpdateFormDTO(user);
    }

    @Transactional // 회원수정
    public User setUser(int id, UserRequest.UpdateDTO requestDTO) {
        // todo : 구직자, 구인자가 필요한 정보를 여기서 받도록?
        User user = userJpaRepository.findById(id).get();
        user.update(requestDTO);

        return user;
    }

    @Transactional
    public void removeUser(int id) {
        // todo : delete
    }
}

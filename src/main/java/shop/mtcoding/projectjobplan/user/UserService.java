package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception400;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception401;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.apply.ApplyJpaRepository;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.apply.ApplyService;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.skill.SkillJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;
    private final SkillJpaRepository skillJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;

    @Transactional
    public User createUser(UserRequest.JoinDTO requestDTO) { // join
        User user = requestDTO.toEntity();
        Optional<User> userOP = userJpaRepository.findByUsername(requestDTO.getUsername());

        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저입니다.");
        }

        return userJpaRepository.save(user);
    }

    public User getUser(UserRequest.LoginDTO requestDTO) { // login
        User sessionUser = userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));

        return sessionUser;
    }

    public UserResponse.ProfileDTO getUser(User sessionUser, Integer boardId) {
        User user = userJpaRepository.findById(sessionUser.getId()).get();
        List<Apply> applyList;
        List<Skill> skillList = new ArrayList<>();
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

    // 회원수정폼
    public UserResponse.UpdateFormDTO getUser(int id) {
        User user = userJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        return new UserResponse.UpdateFormDTO(user);
    }

    @Transactional // 회원수정
    public User setUser(int id, UserRequest.UpdateDTO requestDTO) {
        // todo : 구직자, 구인자가 필요한 정보를 여기서 받도록?
        User user = userJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        user.update(requestDTO);

        return user;
    }

    @Transactional
    public void removeUser(int id) {
        // todo : delete
    }
}

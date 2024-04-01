package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception400;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception401;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.apply.ApplyJpaRepository;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.offer.Offer;
import shop.mtcoding.projectjobplan.offer.OfferJpaRepository;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.skill.SkillJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;
    private final OfferJpaRepository offerJpaRepository;
    private final SkillJpaRepository skillJpaRepository;

    @Transactional
    public User createUser(UserRequest.JoinDTO requestDTO) { // join
        Optional<User> userOP = userJpaRepository.findByUsername(requestDTO.getUsername());
        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저입니다.");
        }
        User user = new User(requestDTO);

        return userJpaRepository.save(user);
    }

    public User getUser(UserRequest.LoginDTO requestDTO) { // login

        return userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));
    }

    public UserResponse.ProfileDTO getUser(Integer sessionUserId, Integer boardId, Integer resumeId, Pageable pageable) {
        User user = userJpaRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        List<Board> boardList = new ArrayList<>();
        List<Resume> resumeList = new ArrayList<>();
        List<Apply> applyList;
        List<Offer> offerList;
        List<Skill> skillList = new ArrayList<>();
        if (user.getIsEmployer()) { // 기업 마이페이지
            boardList = boardJpaRepository.findByUserId(sessionUserId)
                    .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
            List<UserResponse.ProfileDTO.BoardDTO> boards = boardList.stream().map(board -> new UserResponse.ProfileDTO.BoardDTO(board)).toList();
            if (boardId == null) { // 모든 지원자 현황 보기 & 모든 제안 현황 보기
                applyList = applyJpaRepository.findByBoardUserId(user.getId());
                offerList = offerJpaRepository.findByBoardUserId(user.getId());
                // todo : DTO에 미리 담아서 보내기..
//            this.applyList = applies.stream().map(apply -> new ApplyDTO(apply)).toList();
//            this.offerList = offers.stream().map(offer -> new OfferDTO(offer)).toList();
            } else { // 공고별 지원자 보기 & 공고별 제안 현황 보기
                applyList = applyJpaRepository.findByBoardId(boardId);
                offerList = offerJpaRepository.findByBoardId(boardId);

//            this.applyList = applies.stream().map(apply -> new ApplyDTO(apply)).toList();
//            this.offerList = offers.stream().map(offer -> new OfferDTO(offer)).toList();
            }
        } else { // 개인 마이페이지
            resumeList = resumeJpaRepository.findByUserId(sessionUserId)
                    .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
            skillList = skillJpaRepository.findByUserId(sessionUserId)
                    .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
            if (resumeId == null) { // 모든 지원 현황 보기 & 모든 제안 현황 보기
                applyList = applyJpaRepository.findByResumeUserId(user.getId());
                offerList = offerJpaRepository.findByResumeUserId(user.getId());
            } else { // 이력서별 지원 현황 보기 & 이력서별 제안 현황 보기
                applyList = applyJpaRepository.findByResumeId(resumeId);
                offerList = offerJpaRepository.findByResumeId(resumeId);
            }
        }
        Double rating = ratingJpaRepository.findRatingAvgByUserId(user.getId()).orElse(0.0);

        return new UserResponse.ProfileDTO(user, boardList, resumeList, applyList, offerList, skillList, rating);
    }

    public UserResponse.UpdateFormDTO getUser(int userId) { // 회원수정폼
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        return new UserResponse.UpdateFormDTO(user);
    }

    @Transactional // 회원수정
    public User setUser(int userId, UserRequest.UpdateDTO requestDTO) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));
        user.update(requestDTO);

        return user;
    }

    @Transactional
    public void removeUser(int userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        userJpaRepository.delete(user);
    }
}

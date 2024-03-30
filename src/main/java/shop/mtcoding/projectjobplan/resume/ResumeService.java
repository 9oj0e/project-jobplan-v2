package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception403;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.rating.Rating;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.subscribe.Subscribe;
import shop.mtcoding.projectjobplan.subscribe.SubscribeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJpaRepository resumeJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;
    private final SubscribeJpaRepository subscribeJpaRepository;

    @Transactional
    public Resume createResume(ResumeRequest.PostDTO requestDTO, User sessionUser) {
        Resume resume = new Resume(requestDTO, sessionUser);

        return resumeJpaRepository.save(resume);
    }

    public ResumeResponse.DetailDTO getResumeInDetail(int resumeId, Integer sessionUserId) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        Double rating = ratingJpaRepository.findRatingAvgByUserId(resume.getUser().getId()).orElse(0.0);

        boolean isResumeOwner = false;
        boolean hasSubscribed = false;
        boolean hasRated = false;
        if (sessionUserId != null) {
            isResumeOwner = resume.getUser().getId() == sessionUserId ? true : false;
            Optional<Subscribe> optionalSubscribe = subscribeJpaRepository.findByResumeIdAndUserId(resumeId, sessionUserId);
            hasSubscribed = optionalSubscribe.isPresent() ? true : false;
            Optional<Rating> optionalRating = ratingJpaRepository.findByRaterIdAndSubjectId(sessionUserId, resume.getUser().getId());
            hasRated = optionalRating.isPresent() ? true : false;
        }
        return new ResumeResponse.DetailDTO(resume, rating, isResumeOwner, hasSubscribed, hasRated);
    }

    public ResumeResponse.ListingsDTO getAllResume(Pageable pageable, String skill, String address, String keyword) {
        List<Resume> resumeList;
        if (skill != null) {
            resumeList = resumeJpaRepository.findAllJoinUserWithSkill(skill).orElseThrow(() -> new Exception404("조회된 이력서가 없습니다."));
        } else if (address != null) {
            resumeList = resumeJpaRepository.findAllJoinUserWithAddress(address).orElseThrow(() -> new Exception404("조회된 이력서가 없습니다."));
        } else if (keyword != null) {
            resumeList = resumeJpaRepository.findAllJoinUserWithKeyword(keyword).orElseThrow(() -> new Exception404("조회된 이력서가 없습니다."));
        } else {
            resumeList = resumeJpaRepository.findAllJoinUser().orElseThrow(() -> new Exception404("조회된 이력서가 없습니다."));
        }
        return new ResumeResponse.ListingsDTO(resumeList, pageable);
    }

    // 이력서수정폼
    public ResumeResponse.UpdateFormDTO getResume(int id, User sessionUser) {
        // 조회 및 예외처리
        Resume resume = resumeJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUser.getId() != resume.getUser().getId()) {
            throw new Exception403("해당 이력서의 수정페이지로 이동할 권한이 없습니다.");
        }

        return new ResumeResponse.UpdateFormDTO(resumeJpaRepository.findById(id).get());
    }

    @Transactional // 이력서수정
    public void setResume(int id, ResumeRequest.UpdateDTO requestDTO, User sessionUser) {
        // 조회 및 예외처리
        Resume resume = resumeJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUser.getId() != resume.getUser().getId()) {
            throw new Exception403("해당 이력서를 수정할 권한이 없습니다.");
        }

        resume.update(requestDTO);
    }

    @Transactional // 이력서삭제
    public void removeResume(int id, User sessionUser) {
        // 조회 및 예외처리
        Resume resume = resumeJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUser.getId() != resume.getUser().getId()) {
            throw new Exception403("해당 이력서를 삭제할 권한이 없습니다.");
        }

        resumeJpaRepository.delete(resume);
    }
}

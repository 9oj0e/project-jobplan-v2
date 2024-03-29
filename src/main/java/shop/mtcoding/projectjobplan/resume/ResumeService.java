package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception403;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.subscribe.Subscribe;
import shop.mtcoding.projectjobplan.subscribe.SubscribeJpaRepository;
import shop.mtcoding.projectjobplan.subscribe.SubscribeService;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJpaRepository resumeJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;
    private final SubscribeJpaRepository subscribeJpaRepository;

    @Transactional
    public Resume createResume(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        return resumeJpaRepository.save(requestDTO.toEntity(sessionUser));
    }

    public ResumeResponse.DetailDTO getResumeInDetail(int resumeId, Integer sessionUserId) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        Double rating = ratingJpaRepository.findRatingAvgByUserId(resume.getUser().getId()).orElse(0.0);

        boolean isResumeOwner = false;
        boolean hasSubscribed = false;
        if (sessionUserId != null) {
            isResumeOwner = resume.getUser().getId() == sessionUserId ? true : false;
            Optional<Subscribe> optional = subscribeJpaRepository.findByResumeIdAndUserId(resumeId, sessionUserId);
            hasSubscribed = optional.isPresent() ? true : false;
        }
        return new ResumeResponse.DetailDTO(resume, rating, isResumeOwner, hasSubscribed);
    }

    public Page<ResumeResponse.ListingsDTO> getAllResume(Pageable pageable) {
        List<ResumeResponse.ListingsDTO> responseDTO = new ArrayList<>();
        List<Resume> resumeList = resumeJpaRepository.findAllJoinUser().get();
        resumeList.stream().forEach(resume -> responseDTO.add(new ResumeResponse.ListingsDTO(resume)));

        return PagingUtil.pageConverter(responseDTO, pageable);
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

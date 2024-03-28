package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception403;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
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
    private final SubscribeService subscribeService;

    @Transactional
    public Resume createResume(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        return resumeJpaRepository.save(requestDTO.toEntity(sessionUser));
    }

    public ResumeResponse.DetailDTO findResumeById(int resumeId, int sessionUserId) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        Boolean resumeOwner = false;
        if (resume.getUser().getId() == sessionUserId) resumeOwner = true;
        Double rate = ratingJpaRepository.findRatingAvgByUserId(resume.getUser().getId()).orElse(0.0);
        Boolean hasSubscribed = subscribeService.checkResumeSubscription(resumeId, sessionUserId);
        ResumeResponse.DetailDTO responseDTO = new ResumeResponse.DetailDTO(resume, rate, resumeOwner, hasSubscribed);

        return responseDTO;
    }

    public List<ResumeResponse.MainDTO> getAllResume() {
        // todo : pagination
        List<ResumeResponse.MainDTO> responseDTO = new ArrayList<>();
        List<Resume> resumeList = resumeJpaRepository.findAllResume().get();
        resumeList.stream().forEach(resume -> responseDTO.add(new ResumeResponse.MainDTO(resume)));

        return responseDTO;
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

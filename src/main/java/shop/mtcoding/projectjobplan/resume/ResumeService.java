package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJpaRepository resumeJpaRepository;
    private final ResumeQueryRepository resumeQueryRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public Resume createResume(ResumeRequest.SaveDTO requestDTO) {

        return resumeJpaRepository.save(requestDTO.toEntity());
    }

    public ResumeResponse.DetailDTO findResumeById(int resumeId) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        ResumeResponse.DetailDTO responseDTO = new ResumeResponse.DetailDTO(resume);

        return responseDTO;
    }

    public ResumeResponse.UpdateDTO getResume(int id) {
        // todo : resume/update-form

        return new ResumeResponse.UpdateDTO(resumeJpaRepository.findById(id).get());
    }

    public List<Resume> getAllResume() {
        // todo : resume/listings

        return null;
    }

    public List<ApplyResponse.ApplyFormDTO> getAllResumeByUserId(int id) {
        List<Resume> resumeList = resumeJpaRepository.findByUserId(id);
        List<ApplyResponse.ApplyFormDTO> responseDTO = new ArrayList<>();
        resumeList.stream().forEach(resume -> {responseDTO.add(new ApplyResponse.ApplyFormDTO(resume));});

        return responseDTO;
    }

    @Transactional
    public void setResume(int id, ResumeRequest.UpdateDTO requestDTO) {
        // todo : resume/id/update
        Resume resume = resumeJpaRepository.findById(id).get();

        resume.update(requestDTO);
    }

    @Transactional
    public void removeResume(int id) {
        Resume resume = resumeJpaRepository.findById(id).get();

        resumeJpaRepository.delete(resume);
    }
}

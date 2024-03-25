package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<ResumeResponse.MainDTO> getAllResume() {
        // todo : pagination
        List<ResumeResponse.MainDTO> responseDTO = new ArrayList<>();
        List<Resume> resumeList = resumeJpaRepository.findAllResume();
        resumeList.stream().forEach(resume -> responseDTO.add(new ResumeResponse.MainDTO(resume)));

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

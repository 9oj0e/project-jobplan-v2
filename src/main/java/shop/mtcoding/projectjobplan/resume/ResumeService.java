package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJpaRepository resumeJpaRepository;
    private final ResumeQueryRepository resumeQueryRepository;

    public void createResume(ResumeRequest.SaveDTO requestDTO) {
        // todo : resume/save

    }

    public ResumeResponse.UpdateDTO getResume(int id) {
        // todo : resume/update-form

        return new ResumeResponse.UpdateDTO(resumeJpaRepository.findById(id).get());
    }

    public List<Resume> getAllResume() {
        // todo : resume/listings

        return null;
    }

    @Transactional
    public void setResume(int id, ResumeRequest.UpdateDTO reqDTO) {
        // todo : resume/id/update
        Resume resume = resumeJpaRepository.findById(id).get();

        resume.update(reqDTO);
    }

    public void removeResume(int id) {
        // todo : resume/id/delete

    }
}

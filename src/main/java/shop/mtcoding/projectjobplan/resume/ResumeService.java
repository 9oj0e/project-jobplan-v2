package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;
import shop.mtcoding.projectjobplan.user.UserResponse;

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

    public Resume getResume(int id) {
        // todo : resume/update-form

        return null;
    }

    public List<Resume> getAllResume() {
        // todo : resume/listings

        return null;
    }
  
    public Resume setResume(int id) {
        // todo : resume/id/update

        return null;
    }
    
    @Transactional
    public void removeResume(int id) {
        Resume resume = resumeJpaRepository.findById(id).get();

        resumeJpaRepository.delete(resume);
    }
}

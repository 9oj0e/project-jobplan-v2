package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJpaRepository resumeJpaRepository;

    @Transactional
    public Resume createResume(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        return resumeJpaRepository.save(requestDTO.toEntity(sessionUser));
    }

    public ResumeResponse.DetailDTO findResumeById(int resumeId) {
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        ResumeResponse.DetailDTO responseDTO = new ResumeResponse.DetailDTO(resume);

        return responseDTO;
    }

    public ResumeResponse.UpdateDTO getResume(int id) {

        return new ResumeResponse.UpdateDTO(resumeJpaRepository.findById(id).get());
    }

    public List<ResumeResponse.MainDTO> getAllResume() {
        // todo : pagination
        List<ResumeResponse.MainDTO> responseDTO = new ArrayList<>();
        List<Resume> resumeList = resumeJpaRepository.findAllResume().get();
        resumeList.stream().forEach(resume -> responseDTO.add(new ResumeResponse.MainDTO(resume)));

        return responseDTO;
    }

    @Transactional
    public void setResume(int id, ResumeRequest.UpdateDTO requestDTO) {
        Resume resume = resumeJpaRepository.findById(id).get();

        resume.update(requestDTO);
    }

    @Transactional
    public void removeResume(int id) {
        Resume resume = resumeJpaRepository.findById(id).get();

        resumeJpaRepository.delete(resume);
    }
}

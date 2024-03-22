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

    // 이력서 작성
    @Transactional
    public Resume createResume(ResumeRequest.SaveDTO requestDTO) {
        // todo : resumes/post
        return resumeJpaRepository.save(requestDTO.toEntity());

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

    public void removeResume(int id) {
        // todo : resume/id/delete

    }
}

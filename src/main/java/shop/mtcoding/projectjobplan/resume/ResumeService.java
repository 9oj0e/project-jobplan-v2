package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJpaRepository resumeJpaRepository;
    private final ResumeQueryRepository resumeQueryRepository;

    public void createResume(ResumeRequest.SaveDTO requestDTO) {
        // todo : resume/save

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

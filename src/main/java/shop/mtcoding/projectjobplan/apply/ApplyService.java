package shop.mtcoding.projectjobplan.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJpaRepository applyJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Transactional
    public void createApply(ApplyRequest.ApplyDTO requestDTO) {
        Resume resume = resumeJpaRepository.findById(requestDTO.getResumeId()).get();
        Board board = boardJpaRepository.findById(requestDTO.getBoardId()).get();
        Apply apply = new Apply(resume, board);

        applyJpaRepository.save(apply);
    }

    public ApplyResponse.ApplyFormDTO getBoardAndResume(int boardId, User sessionUser) {
        Board board = boardJpaRepository.findById(boardId).get();
        List<Resume> resumeList = resumeJpaRepository.findByUserId(sessionUser.getId()).get();

        return new ApplyResponse.ApplyFormDTO(board, resumeList);
    }

    @Transactional
    public void updateApply(ApplyRequest.UpdateDTO requestDTO) {
        Apply apply = applyJpaRepository.findById(requestDTO.getId()).get();

        apply.update(requestDTO);
    }
}

package shop.mtcoding.projectjobplan.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJpaRepository applyJpaRepository;
    private final ApplyQueryRepository applyQueryRepository;
    private final ResumeJpaRepository resumeJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Transactional
    public void createApply(ApplyRequest.ApplyDTO requestDTO) {
        Resume resume = resumeJpaRepository.findById(requestDTO.getResumeId()).get();
        Board board = boardJpaRepository.findById(requestDTO.getBoardId()).get();
        Apply apply = new Apply(resume, board);

        applyJpaRepository.save(apply);
    }

    public List<ApplyResponse.ApplyDTO> getAllResumeByBoardUserId(User sessionUser) { // (기업) 모든 지원자 현황 보기
        Sort sort = Sort.by(Sort.Direction.DESC, "boardId");
        List<Apply> applyList = applyJpaRepository.findByBoardUserId(sessionUser.getId());

        return applyList.stream().map(apply -> new ApplyResponse.ApplyDTO(apply, sessionUser)).toList();
    }

    public void getAllResumeByBoardId(int boardId) {
        // todo : (기업) 공고별 지원자 보기
    }

    public List<ApplyResponse.ApplyDTO> getAllResumeByUserId(User sessionUser) { // (개인) 지원 현황 보기
        Sort sort = Sort.by(Sort.Direction.DESC, "resumeId");
        List<Apply> applyList = applyJpaRepository.findByResumeUserId(sessionUser.getId());

        return applyList.stream().map(apply -> new ApplyResponse.ApplyDTO(apply, sessionUser)).toList();
    }
}

package shop.mtcoding.projectjobplan.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.projectjobplan.board.Board;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJpaRepository applyJpaRepository;
    private final ApplyQueryRepository applyQueryRepository;

    public void createApply(ApplyRequest.ApplyDTO requestDTO) {
        // todo : (개인) 지원하기
    }

    public void getAllResumeByBoardUserId(int boardUserId) {
        // todo : (기업) 모든 지원자 현황 보기
    }

    public void getAllResumeByBoardId(int boardId) {
        // todo : (기업) 공고별 지원자 보기
    }

    public void getAllBoardByResumeId(int resumeId) {
        // todo : (개인) 지원 현황 보기
    }

    public void updateApply(ApplyRequest.UpdateDTO requestDTO) {
        // todo : 합격/불합격 처리
    }


}

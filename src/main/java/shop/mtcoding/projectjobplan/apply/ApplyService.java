package shop.mtcoding.projectjobplan.apply;

import jakarta.persistence.Entity;
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
        // 엔티티 객체 생성
        Apply apply = new Apply();

        // resume, board 객체 가져오기
        Resume resume = resumeJpaRepository.findById(requestDTO.getResumeId()).get();
        Board board = boardJpaRepository.findById(requestDTO.getBoardId()).get();

        // 뤼튼의 도움,,,,,
        apply.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // 엔티티에 Resume, Board 설정
        apply.setResume(resume);
        apply.setBoard(board);

        applyJpaRepository.save(apply);
    }

    public void getAllResumeByBoardUserId(int boardUserId) {
        // todo : (기업) 모든 지원자 현황 보기
    }

    public void getAllResumeByBoardId(int boardId) {
        // todo : (기업) 공고별 지원자 보기
    }

    public List<ApplyResponse.ApplyDTO> getAllByBoardIdAndUserId(User sessionUser, Integer boardId){
        List<Apply> applyList = applyJpaRepository.findByBoardIdAndUserId(sessionUser.getId(), boardId);

        return applyList.stream().map(apply -> new ApplyResponse.ApplyDTO(apply, sessionUser)).toList();
    }

    public List<ApplyResponse.ApplyDTO> getAllBoardByUserId(User sessionUser) {
        // todo : (개인) 지원 현황 보기
        Sort sort = Sort.by(Sort.Direction.DESC, "resumeId");
        List<Apply> applyList = applyJpaRepository.findByBoardUserId(sessionUser.getId());

        return applyList.stream().map(apply -> new ApplyResponse.ApplyDTO(apply, sessionUser)).toList();
    }

    public List<ApplyResponse.ApplyDTO> getAllResumeByUserId(User sessionUser) {
        // todo : (개인) 지원 현황 보기
        Sort sort = Sort.by(Sort.Direction.DESC, "resumeId");
        List<Apply> applyList = applyJpaRepository.findByResumeUserId(sessionUser.getId());

        return applyList.stream().map(apply -> new ApplyResponse.ApplyDTO(apply, sessionUser)).toList();
    }


//    public List<BoardResponse.MainDTO> 글목록조회() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        List<Board> boardList = boardJPARepository.findAll(sort);
//
//        return boardList.stream().map(board -> new BoardResponse.MainDTO(board)).toList();
//    }



}

package shop.mtcoding.projectjobplan.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferJpaRepository offerJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    public OfferResponse.OfferFormDTO getBoardAndResume(int resumeId, User sessionUser) {
        Resume resume = resumeJpaRepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        List<Board> boardList = boardJpaRepository.findByUserId(sessionUser.getId())
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        return new OfferResponse.OfferFormDTO(resume, boardList);
    }
}

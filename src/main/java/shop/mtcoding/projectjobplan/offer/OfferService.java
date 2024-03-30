package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final HttpSession session;

    public OfferResponse.OfferFormDTO getBoardAndResume(int resumeId, User sessionUser) {
        System.out.println("1. Resume resume = resumeJpaRepository.findById(resumeId) : ");
        Resume resume = resumeJpaRepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        // todo: boardList를 못 찾음
        System.out.println("3. List<Board> boardList = boardJpaRepository.findByUserId(sessionUser.getId()) : ");
        List<Board> boardList = boardJpaRepository.findByUserId(sessionUser.getId())
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        return new OfferResponse.OfferFormDTO(resume, boardList);
    }

    @Transactional
    public void createOffer(OfferRequest.OfferDTO reqDTO) {
        Resume resume = resumeJpaRepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        Board board = boardJpaRepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        Offer offer = new Offer(resume, board);
        offerJpaRepository.save(offer);
    }
}

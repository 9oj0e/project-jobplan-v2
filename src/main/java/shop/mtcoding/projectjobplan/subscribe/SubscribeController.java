package shop.mtcoding.projectjobplan.subscribe;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.board.BoardService;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class SubscribeController {

    private final HttpSession session;
    private final SubscribeService subscribeService;
    private final BoardJpaRepository boardJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;

    // 공고 구독
    @PostMapping("/boards/{boardId}/subscribe")
    public String subscribeBoard(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardJpaRepository.findById(boardId).get();
        subscribeService.uploadByBoardId(board, sessionUser);

        return "redirect:/board/" + boardId;
    }
    // 이력서 구독
    @PostMapping("/resumes/{resumeId}/subscribe")
    public String subscribeResume(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Resume resume = resumeJpaRepository.findById(resumeId).get();
        subscribeService.uploadByResumeId(resume, sessionUser);

        return "redirect:/resume/" + resumeId;
    }
}

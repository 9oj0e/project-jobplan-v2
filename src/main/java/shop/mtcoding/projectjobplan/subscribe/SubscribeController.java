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

    @PostMapping("/boards/{boardId}/subscribe") // 공고 구독
    public String subscribeBoard(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeService.createBoardSubscription(sessionUser, boardId);

        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/resumes/{resumeId}/subscribe") // 이력서 구독
    public String subscribeResume(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeService.createResumeSubscription(sessionUser, resumeId);

        return "redirect:/resumes/" + resumeId;
    }
}

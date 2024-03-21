package shop.mtcoding.projectjobplan.subscribe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SubscribeController {
    private final HttpSession session;
    private final SubscribeRepository subscribeRepository;

    // 구독 목록
    @GetMapping("/user/{sessionUserId}/subscription")
    public String subscription(
                                @PathVariable int sessionUserId,
                                HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        if (user.getIsEmployer() != true) { // 기업 유저 (이력서 목록)
            List<SubscribeResponse.ToUserDTO> subscribeList = subscribeRepository.findByUserId(sessionUserId);
            request.setAttribute("subscribeList", subscribeList);

            return "/user/subscription";
        } else { // 개인 유저 (공고 목록)
            List<SubscribeResponse.ToEmployerDTO> subscribeList = subscribeRepository.findByEmployerId(sessionUserId);
            request.setAttribute("subscribeList", subscribeList);

            return "/employer/subscription";
        }
    }
    // 공고 구독
    @PostMapping("/board/{boardId}/subscribe")
    public String subscribeBoard(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeRepository.uploadByBoardId(boardId, sessionUser.getId());

        return "redirect:/board/" + boardId;
    }
    // 이력서 구독
    @PostMapping("/resume/{resumeId}/subscribe")
    public String subscribeResume(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeRepository.uploadByResumeId(resumeId, sessionUser.getId());

        return "redirect:/resume/" + resumeId;
    }
    // 공고 구독 취소
    @PostMapping("/board/{boardId}/unsubscribe")
    public String unsubscribeBoard(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeRepository.deleteByBoardId(boardId, sessionUser.getId());

        return "redirect:/board/" + boardId;
    }
    // 공고 구독리스트에서 공고 구독 취소
    @PostMapping("/user/{boardId}/unsubscribeBoard")
    public String unsubscribeBoardOnSubscription(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeRepository.deleteByBoardId(boardId, sessionUser.getId());

        return "redirect:/user/" + sessionUser.getId() + "/subscription";
    }
    // 이력서 구독 취소
    @PostMapping("/resume/{resumeId}/unsubscribe")
    public String unsubscribeResume(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeRepository.deleteByResumeId(resumeId, sessionUser.getId());

        return "redirect:/resume/" + resumeId;
    }
    // 이력서 구독리스트에서 이력서 구독 취소
    @PostMapping("/user/{resumeId}/unsubscribeResume")
    public String unsubscribeResumeOnSubscription(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        subscribeRepository.deleteByResumeId(resumeId, sessionUser.getId());

        return "redirect:/user/" + sessionUser.getId() + "/subscription";
    }
}

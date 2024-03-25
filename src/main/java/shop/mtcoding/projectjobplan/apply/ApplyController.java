package shop.mtcoding.projectjobplan.apply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final HttpSession session;

    @GetMapping("/boards/{boardId}/apply-form")
    public String applyForm(int boardId, HttpServletRequest request) {
        // todo : 지원하기 Form

        return "/apply/apply-form";
    }

    @PostMapping("/boards/{boardId}/apply")
    public String apply(int boardId, ApplyRequest.ApplyDTO requestDTO) {
        // todo : 지원하기

        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/apply/update")
    public String update(ApplyRequest.UpdateDTO requestDTO) {
        // todo : 지원자 합격 / 불합격 처리
        User user = (User) session.getAttribute("sessionUser");

        return "redirect:/user/" + user.getId();
    }
}

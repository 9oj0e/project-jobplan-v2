package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class OfferController {

    private final HttpSession session;

    @GetMapping("/resumes/{resumeId}/offer-form")
    public String applyForm(@PathVariable int boardId, HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
//        ApplyResponse.ApplyFormDTO responseDTO = applyService.getBoardAndResume(boardId, user);
//        request.setAttribute("applyForm", responseDTO);

        return "/offer/offer-form";
    }
}

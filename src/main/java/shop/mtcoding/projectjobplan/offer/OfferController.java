package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.mtcoding.projectjobplan.user.User;

@Controller
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    @GetMapping("/resumes/{resumeId}/offer-form")
    public String offerForm(@PathVariable int resumeId, HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        OfferResponse.OfferFormDTO respDTO = offerService.getBoardAndResume(resumeId, user);
        request.setAttribute("offerForm", respDTO);
        return "offer/offer-form";
    }
}

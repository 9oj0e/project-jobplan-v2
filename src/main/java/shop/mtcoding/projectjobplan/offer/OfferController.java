package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

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

    @PostMapping("/resumes/{resumeId}/offer")
    public String offer(@PathVariable Integer resumeId, OfferRequest.OfferDTO reqDTO) {
        offerService.createOffer(reqDTO);

        return "redirect:/resumes/" + resumeId;
    }
}

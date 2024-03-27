package shop.mtcoding.projectjobplan.rating;


import jakarta.persistence.Column;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class RatingController {

    private final HttpSession session;
    private final RatingService ratingService ;

    @PostMapping("/boards/{boardId}/rate")
    public String rateBoard(@RequestParam("rate") Double rate, @PathVariable int boardId) {
        //개인 회원이 채용공고에 평점
        User user = (User) session.getAttribute("sessionUser");
        ratingService.별점주기공고(user,boardId,rate);

        return "redirect:/board/" + boardId;
    }



    @PostMapping("/resumes/{resumeId}/rate")
    public String rateResume(@RequestParam("rate") Double rate, @PathVariable int resumeId) {

        // 기업이 이력서페이지에 평점
        User user = (User) session.getAttribute("sessionUser");
        ratingService.별점주기이력서(user,resumeId,rate);

        return "redirect:/board/"+ resumeId ;
    }

}

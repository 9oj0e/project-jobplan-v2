package shop.mtcoding.projectjobplan.rating;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardRepository;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeRepository;

@Controller
@RequiredArgsConstructor
public class RatingController {
    private final HttpSession session;
    private final RatingRepository ratingRepository;
    private final BoardRepository boardRepository;
    private final ResumeRepository resumeRepository;

    @PostMapping("/board/{boardId}/rate")
    public String rateBoard(@RequestParam("rate") int rate, @PathVariable int boardId, Model model) {
        User user = (User) session.getAttribute("sessionUser");
        int raterId = user.getId(); // 평가자 ID 불러오기
        //int rate = requestDTO.getRate(); // 점수 불러오기
        model.addAttribute("score", rate);

        // 보드 id로 기업 id 불러오기
        Board board = boardRepository.findById(boardId);
        int subjectId = board.getEmployerId();

        // 평가자, 피평가자, 점수 insert
        ratingRepository.upload(raterId, subjectId, rate);

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/resume/{resumeId}/rate") //
    public String rateResume(@RequestParam("rate") int rate, @PathVariable int resumeId, Model model) {
        User user = (User) session.getAttribute("sessionUser");
        if (user.getId() != null) {
            Integer raterId = user.getId();

            if (raterId != null) {
                int subjectId = resumeRepository.findById(resumeId).getUserId();
                ratingRepository.upload(raterId, subjectId, rate);
                Resume resume = resumeRepository.findById(resumeId);
                Double rating = ratingRepository.findBySubjectId(resume.getUserId());
                model.addAttribute("rating", rating);
                return "redirect:/resume/" + resumeId;
            } else {
                return "redirect:/login";
            }
        }

        return "redirect:/login";

    }
}

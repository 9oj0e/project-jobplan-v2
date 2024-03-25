package shop.mtcoding.projectjobplan.apply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.mtcoding.projectjobplan.board.BoardResponse;
import shop.mtcoding.projectjobplan.board.BoardService;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeService;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final HttpSession session;
    private final BoardService boardService;
    private final ResumeService resumeService;
    private final ApplyService applyService;

    @GetMapping("/boards/{boardId}/apply-form")
    public String applyForm(@PathVariable int boardId, HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");

        BoardResponse.DetailDTO respDTO = boardService.getBoardInDetail(boardId);
        request.setAttribute("board", respDTO);

        List<ApplyResponse.ApplyFormDTO> resumeList = resumeService.getAllResumeByUserId(user.getId());
        request.setAttribute("resumeList", resumeList);

        return "/apply/apply-form";
    }

    @PostMapping("/boards/{boardId}/apply")
    public String apply(@PathVariable int boardId, ApplyRequest.ApplyDTO reqDTO) {
        // todo : 지원하기
        applyService.createApply(reqDTO);

        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/apply/update")
    public String update(ApplyRequest.UpdateDTO requestDTO) {
        // todo : 지원자 합격 / 불합격 처리
        User user = (User) session.getAttribute("sessionUser");

        return "redirect:/user/" + user.getId();
    }
}

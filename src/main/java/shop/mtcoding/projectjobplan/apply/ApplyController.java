package shop.mtcoding.projectjobplan.apply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.board.BoardRepository;
import shop.mtcoding.projectjobplan.board.BoardResponse;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeRepository;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final HttpSession session;
    private final ApplyRepository applyRepository;
    private final BoardRepository boardRepository;
    private final ResumeRepository resumeRepository;

    // 지원자 합격/불합격 처리
    @PostMapping("/apply/update")
    public String update(Integer status, Integer resumeId, Integer boardId) {
        User user = (User) session.getAttribute("sessionUser");
        applyRepository.update(status, resumeId, boardId);

        return "redirect:/user/" + user.getId();
    }

    // 지원하기
    @GetMapping("/board/{boardId}/applyForm")
    public String applyForm(@PathVariable int boardId, HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        // 공고 제목, 회사명, 마감일 (card-header)
        BoardResponse.ApplyFormDTO board = boardRepository.findWithBusinessNameById(boardId);
        request.setAttribute("board", board);
        // 이력서 리스트 (작성일, 이력서 제목, 버튼 : (이력서id, 이력서id/update)) (table, 최근 3개)
        List<Resume> resumeList = resumeRepository.findByUserId(user.getId());
        request.setAttribute("resumeList", resumeList);

        return "/apply/applyForm";
    }

    @PostMapping("/board/{boardId}/apply")
    public String apply(@PathVariable int boardId, ApplyRequest.UploadDTO requestDTO){
        System.out.println("영향 받은 행 : " + applyRepository.upload(requestDTO));
        return "redirect:/board/" + boardId;
    }
}

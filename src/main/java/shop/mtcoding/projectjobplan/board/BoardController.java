package shop.mtcoding.projectjobplan.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    private final HttpSession session;

    @GetMapping("/boards/post-form")
    public String postForm() {

        return "/board/post-form";
    }

    @PostMapping("/boards/post")
    public String post(@PathVariable int boardId) {

        return "redirect:/board/" + boardId;
    }

    @GetMapping("/boards/{boardId}")
    public String detail(@PathVariable int boardId) {
        BoardResponse.DetailDTO boardDetail = boardService.findBoardById(boardId);
        session.setAttribute("boardDetail", boardDetail);

        return "/board/detail";
    }

    @GetMapping("/boards/listings")
    public String listings() {

        return "/board/listings";
    }

    @GetMapping("/boards/{boardId}/update-form")
    public String updateForm(@PathVariable int boardId) {

        return "/board/update-form";
    }

    @PostMapping("/boards/{boardId}/update")
    public String update(@PathVariable int boardId) {

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/boards/{boardId}/delete")
    public String delete(@PathVariable int boardId) {
        boardService.removeBoard(boardId);

        return "redirect:/board/listings";
    }
}

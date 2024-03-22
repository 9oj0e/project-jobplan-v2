package shop.mtcoding.projectjobplan.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards/main")
    public String main() {
        return "/board/main";
    }

    @GetMapping("/board/post-form")
    public String postForm() {

        return "/board/post-form";
    }

    @PostMapping("/board/post")
    public String post(@PathVariable int boardId) {

        return "redirect:/board/" + boardId;
    }

    @GetMapping("/board/{boardId}")
    public String detail(@PathVariable int boardId) {

        return "redirect:/board/" + boardId;
    }

    @GetMapping("/board/listings")
    public String listings() {

        return "/board/listings";
    }

    @GetMapping("/boards/{boardId}/update-form")
    public String updateForm(@PathVariable int boardId, HttpServletRequest request) {
        BoardResponse.UpdateDTO responseDTO = boardService.getBoard(boardId);
        request.setAttribute("board", responseDTO);

        return "/board/update-form";
    }

    @PostMapping("/boards/{boardId}/update")
    public String update(@PathVariable int boardId, BoardRequest.UpdateDTO requestDTO) {
        boardService.setBoard(boardId, requestDTO);

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/board/{boardId}/delete")
    public String delete(@PathVariable int boardId) {

        return "redirect:/board/listings";
    }
}

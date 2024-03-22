package shop.mtcoding.projectjobplan.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

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

    @GetMapping("/board/{boardId}/update-form")
    public String updateForm(@PathVariable int boardId) {

        return "/board/update-form";
    }

    @PostMapping("/board/{boardId}/update")
    public String update(@PathVariable int boardId) {

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/board/{boardId}/delete")
    public String delete(@PathVariable int boardId) {
        boardService.removeBoard(boardId);

        return "redirect:/board/listings";
    }
}

package shop.mtcoding.projectjobplan.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @GetMapping({"/", "/boards"})
    public String index(HttpServletRequest request) {
        List<BoardResponse.IndexDTO> responseDTO = boardService.getAllBoardOnIndex();
        request.setAttribute("boardList", responseDTO);

        return "/index";
    }

    @GetMapping("/boards/main")
    public String main() {
        return "/board/main";
    }

    @GetMapping("/boards/post-form")
    public String postForm() {

        return "/board/post-form";
    }

    @PostMapping("/boards/post")
    public String post(BoardRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.createBoard(requestDTO, sessionUser);

        return "redirect:/board/" + board.getId();
    }

    @GetMapping("/boards/{boardId}")
    public String detail(@PathVariable int boardId) {
        BoardResponse.DetailDTO boardDetail = boardService.getBoardInDetail(boardId);
        session.setAttribute("boardDetail", boardDetail);

        return "/board/detail";
    }

    @GetMapping("/boards/listings")
    public String listings(HttpServletRequest request) {
        List<BoardResponse.ListingsDTO> responseDTO = boardService.getAllBoard();
        request.setAttribute("boardList", responseDTO);

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

    @PostMapping("/boards/{boardId}/delete")
    public String delete(@PathVariable int boardId) {
        boardService.removeBoard(boardId);

        return "redirect:/board/listings";
    }
}

package shop.mtcoding.projectjobplan.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository boardQueryRepository;

    public Board findBoardById(int boardId) {
        Board board = boardJpaRepository.findBoardById(boardId).get();
        return board;
    }

    public void createBoard(BoardRequest.SaveDTO requestDTO) {
        // todo : board/save

    }

    public Board getBoard(int id) {
        // todo : board/update-form

        return null;
    }

    public List<Board> getAllBoard() {
        // todo : board/listings

        return null;
    }

    public Board setBoard(int id) {
        // todo : board/id/update

        return null;
    }

    public void removeBoard(int id) {
        // todo : board/id/delete

    }
}

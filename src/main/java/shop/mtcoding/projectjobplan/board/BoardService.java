package shop.mtcoding.projectjobplan.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository boardQueryRepository;

    public void createBoard(BoardRequest.SaveDTO requestDTO) {
        // todo : board/save

    }

    public BoardResponse.UpdateDTO getBoard(int id) {
        // todo : board/update-form

        return new BoardResponse.UpdateDTO(boardJpaRepository.findById(id).get());
    }

    public List<Board> getAllBoard() {
        // todo : board/listings

        return null;
    }

    @Transactional
    public void setBoard(int id, BoardRequest.UpdateDTO requestDTO) {
        // todo : board/id/update
        Board board = boardJpaRepository.findById(id).get();
        board.update(requestDTO);
    }

    public void removeBoard(int id) {
        // todo : board/id/delete

    }
}

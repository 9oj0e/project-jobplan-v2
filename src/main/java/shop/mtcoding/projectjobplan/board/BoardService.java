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

    public BoardResponse.DetailDTO findBoardById(int boardId) {
        Board board = boardJpaRepository.findById(boardId).get();
        BoardResponse.DetailDTO responseDTO = new BoardResponse.DetailDTO(board);
      
        return responseDTO;
    }

    public Board createBoard(BoardRequest.SaveDTO requestDTO) {

        return boardJpaRepository.save(requestDTO.toEntity());
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
        Board board = boardJpaRepository.findById(id).get();

        boardJpaRepository.delete(board);
    }
}

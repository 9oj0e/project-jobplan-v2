package shop.mtcoding.projectjobplan.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;

    public Board createBoard(BoardRequest.SaveDTO requestDTO, User sessionUser) {

        return boardJpaRepository.save(requestDTO.toEntity(sessionUser));
    }

    public BoardResponse.DetailDTO getBoardInDetail(int id) {
        Board board = boardJpaRepository.findById(id).get();
        Double rating = ratingJpaRepository.findRatingAvgByBoardUserId(5).orElse(0.0);

        return new BoardResponse.DetailDTO(board, rating);
    }

    public List<BoardResponse.ListingsDTO> getAllBoard() { // board/listings
        List<BoardResponse.ListingsDTO> responseDTO = new ArrayList<>();
        List<Board> boardList = boardJpaRepository.findAllBoardJoinUser().get();
        boardList.stream().forEach(board -> responseDTO.add(new BoardResponse.ListingsDTO(board)));

        return responseDTO;
    }

    public List<BoardResponse.IndexDTO> getAllBoardOnIndex() { // index
        List<Board> boardList = boardJpaRepository.findAllJoinUser().get();
        List<BoardResponse.IndexDTO> responseDTO = new ArrayList<>();
        boardList.stream().forEach(board -> {
            responseDTO.add(new BoardResponse.IndexDTO(board));
        });

        return responseDTO;
    }

    public BoardResponse.UpdateDTO getBoard(int id) {

        return new BoardResponse.UpdateDTO(boardJpaRepository.findById(id).get());
    }

    @Transactional
    public void setBoard(int id, BoardRequest.UpdateDTO requestDTO) {
        Board board = boardJpaRepository.findById(id).get();

        board.update(requestDTO);
    }

    public void removeBoard(int id) {
        Board board = boardJpaRepository.findById(id).get();

        boardJpaRepository.delete(board);
    }
}

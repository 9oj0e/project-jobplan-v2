package shop.mtcoding.projectjobplan.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception403;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.apply.ApplyJpaRepository;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.subscribe.SubscribeService;
import shop.mtcoding.projectjobplan.user.User;
import shop.mtcoding.projectjobplan.user.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;
    private final SubscribeService subscribeService;
    private final ApplyJpaRepository applyJpaRepository;

    public Board createBoard(BoardRequest.SaveDTO requestDTO, User sessionUser) {

        return boardJpaRepository.save(requestDTO.toEntity(sessionUser));
    }

    public BoardResponse.DetailDTO getBoardInDetail(int id, User sessionUser) {
        Board board = boardJpaRepository.findById(id).get();
        Boolean boardOwner = false;
        Double rating = ratingJpaRepository.findRatingAvgByUserId(board.getUser().getId()).orElse(0.0);
        if (sessionUser != null) {
            if (board.getUser().getId() == sessionUser.getId()) boardOwner = true;
            Boolean hasSubscribed = subscribeService.checkBoardSubscription(id, sessionUser.getId());
            return new BoardResponse.DetailDTO(board, rating, boardOwner, hasSubscribed);
        }
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

    // 공고수정폼
    public BoardResponse.UpdateDTO getBoard(int boardId, User sessionUser) {
        // 조회 및 예외 처리
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("해당 공고의 수정페이지로 이동할 권한이 없습니다.");
        }

        return new BoardResponse.UpdateDTO(boardJpaRepository.findById(boardId).get());
    }

    @Transactional // 공고수정
    public void setBoard(int boardId, BoardRequest.UpdateDTO requestDTO, User sessionUser) {
        // 조회 및 예외 처리
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));

        // 권한 처리
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("해당 공고를 수정할 권한이 없습니다.");
        }

        // 글 수정
        board.update(requestDTO);
    }

    // 공고삭제
    @Transactional
    public void removeBoard(int id, User sessionUser) {
        // 조회 및 예외 처리
        Board board = boardJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));
        // 권한 처리
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("해당 공고를 삭제할 권한이 없습니다.");
        }
        System.out.println("3");

        applyJpaRepository.findByBoardId(id);
        System.out.println("4");
        System.out.println("5");
        applyJpaRepository.deleteApplyByBoardId(id);
        System.out.println("6");
        boardJpaRepository.delete(board);
        System.out.println("7");
    }
}

package shop.mtcoding.projectjobplan.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository boardQueryRepository;
}

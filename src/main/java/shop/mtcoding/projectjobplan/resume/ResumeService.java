package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.board.BoardQueryRepository;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository boardQueryRepository;
}

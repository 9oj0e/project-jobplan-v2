package shop.mtcoding.projectjobplan.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJpaRepository boardJpaRepository;
    @Test
    public void findByUserId_test(){
        // given
        int userId = 1 ;
        // when
       List<Board> boardList = boardJpaRepository.findAllByUserId(userId);
        // then
        System.out.println(boardList);
    }
}

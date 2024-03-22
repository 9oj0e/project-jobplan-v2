package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Transactional
    public User createUser(UserRequest.JoinDTO requestDTO) { // join
        User user = requestDTO.toEntity();

        return userJpaRepository.save(user);
    }

    public User getUser(UserRequest.LoginDTO requestDTO) { // login
        User sessionUser = userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword()).get();

        return sessionUser;
    }

    public UserResponse.UpdateFormDTO getUser(int id, User sessionUser) {
        User user = userJpaRepository.findById(id).get();

        return new UserResponse.UpdateFormDTO(user, sessionUser);
    }

    public UserResponse.ProfileDTO getUser(int sessionUserId) {

        return new UserResponse.ProfileDTO(userJpaRepository.findById(sessionUserId).get());
    }

    @Transactional // 회원수정
    public User setUser(int id, UserRequest.UpdateDTO requestDTO) {
        // todo : 구직자, 구인자가 필요한 정보를 여기서 받도록.
        User user = userJpaRepository.findById(id).get();
        user.update(requestDTO);

        return user;
    }

    @Transactional
    public void removeUser(int id) {
        // todo : delete

    }
}

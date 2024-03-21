package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public User addUser() {
        // todo : join

        return null;
    }

    public User findUser(UserRequest.LoginDTO requestDTO) {
        // todo : login

        return null;
    }

    public UserResponse.DTO findUser(int id) {
        // todo : updateForm

        return null;
    }

    @Transactional
    public UserResponse.DTO modifyUser(int id) {
        // todo : update

        return null;
    }

    @Transactional
    public void removeUser(int id) {
        // todo : delete

    }
}

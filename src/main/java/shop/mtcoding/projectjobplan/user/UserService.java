package shop.mtcoding.projectjobplan.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final EntityManager em;

    @Transactional
    public User addUser() {
        // todo : join

        return null;
    }

    public User findUser() {
        // todo : login
        User sessionUser = userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword()).get();
        return sessionUser;
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
    @Transactional
    public User save(UserRequest.SaveDTO requestDTO) {
        User user = requestDTO.toEntity();
        return userJpaRepository.save(user);

    }
}

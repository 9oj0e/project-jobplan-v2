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

    // 회원수정폼
    public UserResponse.DTO findUser(int id, User sessionUser) {
        // todo : updateForm
        User user = userJpaRepository.findById(id).get();

        return new UserResponse.DTO(user, sessionUser);
    }

    @Transactional // 회원수정
    public User modifyUser(int id, UserRequest.UpdateDTO reqDTO, User sessionUser) {
        // todo : update
        User user = userJpaRepository.findById(id).get();

        user.setPassword(reqDTO.getPassword());
        user.setGender(reqDTO.getGender());
        user.setPhoneNumber(reqDTO.getPhoneNumber());
        user.setAddress(reqDTO.getAddress());
        user.setEmail(reqDTO.getEmail());

        if (sessionUser.getIsEmployer()) {
            user.setEmployerIdNumber(reqDTO.getEmployerIdNumber());
            user.setBusinessName(reqDTO.getBusinessName());
        }

        return user;
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

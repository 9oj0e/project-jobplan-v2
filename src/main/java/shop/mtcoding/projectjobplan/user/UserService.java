package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public User createUser(UserRequest.JoinDTO requestDTO) {
        User user = requestDTO.toEntity();

        return userJpaRepository.save(user);
    }

    public User getUser(UserRequest.LoginDTO requestDTO) {
        User sessionUser = userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword()).get();

        return sessionUser;
    }

    public UserResponse.DTO getUser(int id, User sessionUser) {
        User user = userJpaRepository.findById(id).get();

        return new UserResponse.DTO(user, sessionUser);
    }

    @Transactional // 회원수정
    public User setUser(int id, UserRequest.UpdateDTO reqDTO, User sessionUser) {
        // todo : 구직자, 구인자가 필요한 정보를 여기서 받도록.
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

    public UserResponse.EmployerDTO findEmployer(int sessionUserId){
        return new UserResponse.EmployerDTO(userJpaRepository.findById(sessionUserId).get());
    }
    public UserResponse.UserDTO findUser(int sessionUserId) {
        // todo : updateForm, profile
        return new UserResponse.UserDTO(userJpaRepository.findById(sessionUserId).get());
    }
}

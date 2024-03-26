package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.apply.ApplyService;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final ApplyService applyService ;

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

//    public UserResponse.ProfileDTO getUser(User sessionUser) {
//       User user = userJpaRepository.findById(sessionUser.getId()).get();
//        List<ApplyResponse.ApplyDTO> applyList = applyService.getAllBoardByResumeId(sessionUser);
//
//
//        return new UserResponse.ProfileDTO(user,applyList)  ;
//    }

    @Transactional // 회원수정
    public User setUser(int id, UserRequest.UpdateDTO requestDTO) {
        // todo : 구직자, 구인자가 필요한 정보를 여기서 받도록?
        User user = userJpaRepository.findById(id).get();
        user.update(requestDTO);

        return user;
    }

    @Transactional
    public void removeUser(int id) {
        // todo : delete

    }


    public UserResponse.ProfileDTO getUserProfileDTO(User sessionUser, Integer boardId) {
        User user = userJpaRepository.findById(sessionUser.getId()).get();
        if (boardId != null){
            if(sessionUser.getIsEmployer()){
                List<ApplyResponse.ApplyDTO> applyList = applyService.getAllByBoardIdAndUserId(sessionUser, boardId);
                return new UserResponse.ProfileDTO(user, applyList);
            }else{
                List<ApplyResponse.ApplyDTO> applyList = applyService.getAllByBoardIdAndUserId(sessionUser, boardId);
                return new UserResponse.ProfileDTO(user, applyList);
            }
        }else {
            if(sessionUser.getIsEmployer()){
                List<ApplyResponse.ApplyDTO> applyList = applyService.getAllBoardByUserId(sessionUser);
                return new UserResponse.ProfileDTO(user, applyList);
            }else{
                List<ApplyResponse.ApplyDTO> applyList = applyService.getAllResumeByUserId(sessionUser);
                return new UserResponse.ProfileDTO(user, applyList);
            }
        }
    }

}

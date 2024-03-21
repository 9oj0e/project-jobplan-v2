package shop.mtcoding.projectjobplan.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.projectjobplan._core.ApiUtil;
import shop.mtcoding.projectjobplan.apply.ApplyRepository;
import shop.mtcoding.projectjobplan.apply.ApplyResponse;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardRepository;
import shop.mtcoding.projectjobplan.pic.Pic;
import shop.mtcoding.projectjobplan.pic.PicRepository;
import shop.mtcoding.projectjobplan.pic.PicRequest;
import shop.mtcoding.projectjobplan.rating.RatingRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeRepository;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.skill.SkillRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final BoardRepository boardRepository;
    private final ApplyRepository applyRepository;
    private final RatingRepository ratingRepository;
    private final PicRepository picRepository;

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String username){

       User user = userRepository.findByUsername(username);
       if(user==null){
           return new ApiUtil<>(false);
       }else {
           return new ApiUtil<>(true);
       }

    }

    @GetMapping("/user/joinSelection")
    public String joinSelection() {
        return "/user/joinSelection";
    }

    @GetMapping("/user/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/employer/joinForm")
    public String employerJoinForm() {
        return "/employer/joinForm";
    }

    @PostMapping("/join")
    public String join(HttpServletRequest request, UserRequest.JoinDTO requestDTO) {
        // 동일 username 체크
        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null) {
            // 3. model에 위임하기
            userRepository.upload(requestDTO);
            return "redirect:/loginForm";
        } else {
            request.setAttribute("status", "400");
            request.setAttribute("msg", "중복된 아이디입니다.");
            return "error";
        }
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, UserRequest.LoginDTO requestDTO) {
        User user = userRepository.findByUsernameAndPassword(requestDTO); // 암호화 안됨
        if (user == null) {
            request.setAttribute("status", 401);
            request.setAttribute("msg", "아이디 혹은 비밀번호가 틀렸습니다.");
            return "error";
        } else {
            session.setAttribute("sessionUser", user);
        }
        try {
            if (picRepository.findImg() != null) {
                String imgFilename = picRepository.findImg();
                request.getSession().setAttribute("userPic2", imgFilename);
                return "redirect:/";
            }
        } catch (Exception e) {
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping({"/user/{userId}", "/user/{userId}/{boardId}"})
    public String profile(HttpServletRequest request,
                          @PathVariable int userId,
                          @PathVariable(required = false) Integer boardId) {
        User user = userRepository.findById(userId);
        request.setAttribute("user", user);

        Double rawRating = ratingRepository.findBySubjectId(user.getId()); // cyj-030809
        if (rawRating != null) { // 평점 출력
            // 소수점 한자리수 까지 출력
            String rating = String.format("%.1f", rawRating);
            request.setAttribute("rating", rating);
        }

        // 지원 삭제 (개인, 지원 취소)

        // 기업 회원 인지..
        if (user.getIsEmployer()) {
            // 내가 쓴 공고 조회
            List<Board> boardList = boardRepository.findByEmployerId(user.getId());
            request.setAttribute("boardList", boardList);
            if (boardId == null) {
                // 전체 지원자 현황 조회
                List<ApplyResponse.ToEmployerDTO> applicationList = applyRepository.findByEmployerId(userId);
                request.setAttribute("applicationList", applicationList);
            } else {
                // 공고별 지원자 현황 조회
                List<ApplyResponse.ToEmployerDTO> applicationList = applyRepository.findByBoardId(boardId);
                request.setAttribute("applicationList", applicationList);
            }

            return "/employer/profile";
        } else {
            // 지원 현황 조회
            List<Resume> resumeList = resumeRepository.findByUserId(user.getId());
            request.setAttribute("resumeList", resumeList);
            List<ApplyResponse.ToUserDTO> applyList = applyRepository.findByUserId(userId);
            request.setAttribute("applyList", applyList);

            return "/user/profile";
        }
    }

    @GetMapping("/user/{userId}/updateForm")
    public String updateForm(HttpServletRequest request, @PathVariable int userId) {
        User user = userRepository.findById(userId);
        request.setAttribute("user", user);

        // 기업 회원인지..
        if (user.getIsEmployer())
            return "/employer/updateForm";
        else
            return "/user/updateForm";
    }

    @PostMapping("/user/{userId}/update")
    public String update(@PathVariable int userId, UserRequest.UpdateDTO requestDTO, HttpServletRequest request) {

        request.setAttribute("user", userRepository.updateById(requestDTO, userId));

        return "redirect:/user/" + userId;
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}

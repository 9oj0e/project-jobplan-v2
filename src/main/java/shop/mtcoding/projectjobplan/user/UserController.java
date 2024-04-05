package shop.mtcoding.projectjobplan.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/users/join-form")
    public String joinForm(boolean isEmployer, HttpServletRequest request) {
        request.setAttribute("isEmployer", isEmployer);

        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO requestDTO, Errors errors) {
        User sessionUser = userService.createUser(requestDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        User sessionUser = userService.getUser(requestDTO);
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }

    @PostMapping("/users/{userId}/update")
    public String update(@PathVariable Integer userId, @Valid UserRequest.UpdateDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.setUser(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return "redirect:/users/" + userId;
    }

    @GetMapping({"/users/{userId}",
            "/users/{userId}/boards",
            "/users/{userId}/boards/{boardId}",
            "/users/{userId}/resumes",
            "/users/{userId}/resumes/{resumeId}"})
    public String profile(
            @PathVariable Integer userId,
            @PathVariable(required = false) Integer boardId,
            @PathVariable(required = false) Integer resumeId,
            @PageableDefault(size = 3) Pageable pageable,
            HttpServletRequest request) {
        // todo: NullPointException
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.ProfileDTO profileDTO = userService.getUser(sessionUser.getId(), boardId, resumeId, pageable);
        System.out.println(profileDTO.getImgFilename());
        request.setAttribute("profileDTO", profileDTO);

        return "user/profile";
    }

    @PostMapping("/users/{userId}/skill/add") // 스킬 추가/수정/삭제
    public String skillAdd(@PathVariable int userId, UserRequest.SkillDTO requestDTO) {
        userService.createSkillList(requestDTO, userId);

        return "redirect:/users/" + userId;
    }

    @PostMapping("/users/{userId}/pic") // 사진 업로드
    public String picUpload(@PathVariable int userId, UserRequest.PicDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        userService.picUpload(requestDTO, sessionUser.getId());

        return "redirect:/users/" + userId;
    }
}

package shop.mtcoding.projectjobplan.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;
    /* modal로 대체
    @GetMapping("/users/join-type")
    public String joinType() {

        return "/user/join-type";
    }
    */
    @PostMapping("/users/join-form")
    public String joinForm(boolean isEmployer, HttpServletRequest request) {
        request.setAttribute("isEmployer", isEmployer);

        return "/user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        userService.createUser(requestDTO);

        return "redirect:/";
    }
    /* modal로 대체
    @GetMapping("/login-form")
    public String loginForm() {

        return "/user/login-form";
    }
    */
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
    /* modal 로 대체
    @GetMapping("/users/{userId}/update-form")
    public String updateForm(@PathVariable Integer userId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.UpdateFormDTO user = userService.getUser(sessionUser.getId());
        request.setAttribute("user", user);

        return "/user/update-form";
    }
    */
    @PostMapping("/users/{userId}/update")
    public String update(@PathVariable Integer userId, UserRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.setUser(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return "redirect:/users/" + userId;
    }

    @GetMapping({"/users/{userId}", "/users/{userId}/boards", "/users/{userId}/boards/{boardId}"})
    public String profile(
            @PathVariable Integer userId,
            @PathVariable(required = false) Integer boardId,
            @PageableDefault(size = 3) Pageable pageable,
            HttpServletRequest request) {
        // todo: NullPointException
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.ProfileDTO profileDTO = userService.getUser(sessionUser, boardId, pageable);
        request.setAttribute("profileDTO", profileDTO);

        return "/user/profile";
    }
}

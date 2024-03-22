package shop.mtcoding.projectjobplan.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.board.Board;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/users/join-type")
    public String joinType() {

        return "/user/join-type";
    }

    @GetMapping("/users/join-form")
    public String joinForm(boolean isEmployer, HttpServletRequest request) {
        request.setAttribute("isEmployer", isEmployer);

        return "/user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        userService.createUser(requestDTO);

        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {

        return "/user/login-form";
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

    @GetMapping("/users/{userId}/update-form")
    public String updateForm(@PathVariable Integer userId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.DTO user = userService.getUser(userId, sessionUser);
        request.setAttribute("user", user);

        return "/user/update-form";
    }

    @PostMapping("/users/{userId}/update")
    public String update(@PathVariable Integer userId, UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.setUser(userId, reqDTO, sessionUser);
        session.setAttribute("sessionUser", newSessionUser);

        return "redirect:/";
    }

    @GetMapping("/users/{userId}")
    public String profile(@PathVariable Integer userId, HttpServletRequest request){
        // todo: NullPointException
        User sessionUser = (User)session.getAttribute("sessionUser");
        if (sessionUser.getIsEmployer()){
            request.setAttribute("user", userService.findEmployer(sessionUser.getId()));
        }else {
            request.setAttribute("user", userService.findUser(sessionUser.getId()));
        }
        return "/user/profile";
    }
}

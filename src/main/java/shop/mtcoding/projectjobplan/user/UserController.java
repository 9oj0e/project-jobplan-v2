package shop.mtcoding.projectjobplan.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/user/join-type")
    public String joinType() {
        return "/user/join-type";
    }

    @GetMapping("/user/join-form")
    public String joinForm(boolean isEmployer) {

        if (isEmployer) {
            return "/employer/join-form";
        } else {
            return "/user/join-form";
        }
    }

    @PostMapping("/join")
    public String join() {

        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @GetMapping("/login")
    public String login() {

        return "redirect:/";
    }


    @GetMapping("/users/{userId}/update-form")
    public String updateForm(@PathVariable Integer userId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.DTO user = userService.findUser(userId, sessionUser);

        request.setAttribute("user", user);

        // 기업 회원인지..
        if (sessionUser.getIsEmployer())
            return "/employers/updateForm";
        else
            return "/users/updateForm";

    }

    @PostMapping("/users/{userId}/update")
    public String update(@PathVariable Integer userId, UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.modifyUser(userId, reqDTO, sessionUser);

        session.setAttribute("sessionUser", newSessionUser);

        return "redirect:/";
    }
}

package shop.mtcoding.projectjobplan.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;
  
    @GetMapping("/join-type")
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
    public String join(UserRequest.SaveDTO requestDTO) {
        userService.save(requestDTO);

        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
      
        return "/user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.findUser(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
      
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
      
        return "redirect:/";
    }

    @GetMapping("/user/{userId}/update-form")
    public String updateForm() {

        /*
        // 기업 회원인지..
        if (user.getIsEmployer())
            return "/employer/updateForm";
        else
            return "/user/updateForm";
        */
        return "";
    }

    @PostMapping("/user/{userId}/update")
    public String update() {

        return "redirect:/";
    }
}

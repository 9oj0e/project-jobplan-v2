package shop.mtcoding.projectjobplan.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class SkillController {
    @PostMapping("/users/{userId}/skill/add")
    public String add(@PathVariable int userId, SkillRequest.DTO requestDTO) {

        return "redirect:/users/" + userId;
    }

    @PostMapping("/users/{userId}/skill/update")
    public String update(@PathVariable int userId, SkillRequest.DTO requestDTO) {

        return "redirect:/users/" + userId;
    }
}

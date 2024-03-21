package shop.mtcoding.projectjobplan.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/resume/post-form")
    public String postForm() {

        return "/resume/post-form";
    }

    @PostMapping("/resume/post")
    public String post(@PathVariable int resumeId) {

        return "redirect:/resume/" + resumeId;
    }

    @GetMapping("/resume/{resumeId}")
    public String detail(@PathVariable int resumeId) {

        return "redirect:/resume/" + resumeId;
    }

    @GetMapping("/resume/listings")
    public String listings() {

        return "/resume/listings";
    }

    @GetMapping("/resume/{resumeId}/update-form")
    public String updateForm(@PathVariable int resumeId) {

        return "/resume/update-form";
    }

    @PostMapping("/resume/{resumeId}/update")
    public String update(@PathVariable int resumeId) {

        return "redirect:/resume/" + resumeId;
    }

    @PostMapping("/resume/{resumeId}/delete")
    public String delete(@PathVariable int resumeId) {

        return "redirect:/resume/listings";
    }
}

package shop.mtcoding.projectjobplan.resume;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

    @GetMapping("/resumes/post-form")
    public String postForm() {

        return "/resume/post-form";
    }

    @PostMapping("/resumes/post")
    public String post(@PathVariable int resumeId) {

        return "redirect:/resume/" + resumeId;
    }

    @GetMapping("/resumes/{resumeId}")
    public String detail(@PathVariable int resumeId) {
        ResumeResponse.DetailDTO resumeDetail = resumeService.findResumeById(resumeId);
        session.setAttribute("resumeDetail", resumeDetail);

        return "/resume/detail";
    }

    @GetMapping("/resumes/listings")
    public String listings() {

        return "/resume/listings";
    }

    @GetMapping("/resumes/{resumeId}/update-form")
    public String updateForm(@PathVariable int resumeId) {

        return "/resume/update-form";
    }

    @PostMapping("/resumes/{resumeId}/update")
    public String update(@PathVariable int resumeId) {

        return "redirect:/resume/" + resumeId;
    }

    @PostMapping("/resumes/{resumeId}/delete")
    public String delete(@PathVariable int resumeId) {
        resumeService.removeResume(resumeId);

        return "redirect:/resume/listings";
    }
}

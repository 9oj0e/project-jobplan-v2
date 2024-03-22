package shop.mtcoding.projectjobplan.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final HttpSession session;
    private final ResumeService resumeService;

    @GetMapping("/resumes/post-form") // 이력서 작성 폼
    public String postForm(HttpServletRequest request) {

        return "/resume/post-form";
    }

    @PostMapping("/resumes/post") // 이력서 작성 action
    public String post(ResumeRequest.SaveDTO requestDTO) {
    Resume resume = resumeService.createResume(requestDTO);

        return "redirect:/resume/" + resume.getId();
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

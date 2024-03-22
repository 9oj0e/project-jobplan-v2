package shop.mtcoding.projectjobplan.resume;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/resumes")
    public String main(){
        return "/resume/main";
    }

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
        return "/resume/detail";
    }

    @GetMapping("/resume/listings")
    public String listings() {

        return "/resume/listings";
    }

    @GetMapping("/resumes/{resumeId}/update-form")
    public String updateForm(@PathVariable int resumeId, HttpServletRequest request) {
        // todo: 권한체크
        ResumeResponse.UpdateDTO responseDTO = resumeService.getResume(resumeId);
        request.setAttribute("resume", responseDTO);

        return "/resume/update-form";
    }

    @PostMapping("/resumes/{resumeId}/update")
    public String update(@PathVariable int resumeId, ResumeRequest.UpdateDTO requestDTO) {
        resumeService.setResume(resumeId, requestDTO);
        return "redirect:/resumes/" + resumeId;
    }

    @PostMapping("/resume/{resumeId}/delete")
    public String delete(@PathVariable int resumeId) {

        return "redirect:/resume/listings";
    }
}

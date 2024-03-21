package shop.mtcoding.projectjobplan.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.PagingUtil;
import shop.mtcoding.projectjobplan.pic.PicRepository;
import shop.mtcoding.projectjobplan.rating.RatingRepository;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.skill.SkillRepository;
import shop.mtcoding.projectjobplan.subscribe.Subscribe;
import shop.mtcoding.projectjobplan.subscribe.SubscribeRepository;
import shop.mtcoding.projectjobplan.user.User;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;
    private final SubscribeRepository subscribeRepository;
    private final HttpSession session;
    private final PicRepository picRepository;
    private final RatingRepository ratingRepository;

    @PostMapping("resume/{resumeId}/update")
    public String update(@PathVariable int resumeId, ResumeRequest.UpdateDTO requestDTO) {
        resumeRepository.updateById(requestDTO, resumeId);
        User user = (User) session.getAttribute("sessionUser");
        skillRepository.updateByResumeId(requestDTO.getSkill(), resumeId, user.getId());

        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/resume/upload")
    public String upload(ResumeRequest.UploadDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Integer resumeId = resumeRepository.upload(requestDTO, sessionUser.getId());

        List<String> skills = requestDTO.getSkill();
        for (String skill : skills) {
            skillRepository.uploadByUserId(skill, sessionUser.getId(), resumeId);
        }
        return "redirect:/user/" + sessionUser.getId();
    }

    @GetMapping("/resume/main")
    public String main() {
        return "/resume/main";
    }

    @GetMapping("/resume/listings")
    public String listings(HttpServletRequest request, @RequestParam(defaultValue = "1") int page, @RequestParam(value = "keyword", required = false) String keyword) {
        // 기업 메인 페이지

        if (keyword != null) {
            List<ResumeResponse.ResumeAndUserDTO> responseDTO = resumeRepository.findByResumeAndUser(page, keyword);
            List<ResumeResponse.ResumeAndUserDTO> resumeList = new ArrayList<>();
            for (ResumeResponse.ResumeAndUserDTO dto : responseDTO) {
                if (dto.isEmployer() == false) {
                    resumeList.add(dto);
                }
            }
            request.setAttribute("resumeList", resumeList);

            // 페이지네이션 모듈
            int totalPage = resumeRepository.countIsEmployerFalse();
            ;
            PagingUtil paginationHelper = new PagingUtil(totalPage, page);

            request.setAttribute("nextPage", paginationHelper.getNextPage());
            request.setAttribute("prevPage", paginationHelper.getPrevPage());
            request.setAttribute("first", paginationHelper.isFirst());
            request.setAttribute("last", paginationHelper.isLast());
            request.setAttribute("numberList", paginationHelper.getNumberList());

            return "/resume/listings";

        } else {
            List<ResumeResponse.ResumeAndUserDTO> responseDTO = resumeRepository.findByResumeAndUser(page);
            List<ResumeResponse.ResumeAndUserDTO> resumeList = new ArrayList<>();
            for (ResumeResponse.ResumeAndUserDTO dto : responseDTO) {
                if (dto.isEmployer() == false) {
                    resumeList.add(dto);
                }
            }
            request.setAttribute("resumeList", resumeList);

            // 페이지네이션 모듈
            int totalPage = resumeRepository.countIsEmployerFalse();
            ;
            PagingUtil paginationHelper = new PagingUtil(totalPage, page);

            request.setAttribute("nextPage", paginationHelper.getNextPage());
            request.setAttribute("prevPage", paginationHelper.getPrevPage());
            request.setAttribute("first", paginationHelper.isFirst());
            request.setAttribute("last", paginationHelper.isLast());
            request.setAttribute("numberList", paginationHelper.getNumberList());

            return "/resume/listings";
        }
    }

    @GetMapping("/resume/{resumeId}")
    @PostMapping("/resume/{resumeId}") // ksj-030810 : Mapping 두번
    public String detail(@PathVariable int resumeId, HttpServletRequest request, Model model) { // ksj-030810 - model
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.ResumeDetailDTO resumeDetailDTO = resumeRepository.detail(resumeId); // todo : resumeId가 없는 경우 처리
        resumeDetailDTO.isResumeOwner(sessionUser);
        request.setAttribute("resumeDetail", resumeDetailDTO);

        List<Skill> resumeSkillList = skillRepository.findByResumeId(resumeId);
        request.setAttribute("skillList", resumeSkillList);
  
        Double rawRating = ratingRepository.findBySubjectId(resumeDetailDTO.getUserId()); // ksj-030810
        if (rawRating != null) {
            // 소수점 한자리수까지 출력
            String rating = String.format("%.1f", rawRating);
            // model.addAttribute("resumeList", resumeDetailDTO.getId()); // ksj-03081
            model.addAttribute("rating", rating); // ksj-030810
        }
        if (sessionUser != null){
            Boolean hasRated = ratingRepository.hasRated(sessionUser.getId(), resumeDetailDTO.getUserId());
            request.setAttribute("hasRated", hasRated);
            Subscribe subscribe = subscribeRepository.findAllByUserIdResumeId(sessionUser.getId(), resumeId);
            request.setAttribute("subscribe", subscribe);
        }

        return "/resume/detail";
    }
//    @PostMapping("/resume/{id}")
//    public String detail(Model model, @PathVariable int id) {
//        Resume resume = resumeRepository.findById(id);
//        Double avgRate = ratingRepository.findAvgRateBySubjectId(resume.getUserId());
//        model.addAttribute("avgRate", avgRate);
//
//        return "/resume/detail";
//    }


    @GetMapping("/resume/uploadForm")
    public String uploadForm() {
        return "/resume/uploadForm";
    }

    @GetMapping("/resume/{resumeId}/updateForm")
    public String updateForm(@PathVariable int resumeId, HttpServletRequest request) {
        Resume resume = resumeRepository.findById(resumeId);
        request.setAttribute("resume", resume);

        return "/resume/updateForm";
    }

    @PostMapping("/resume/{resumeId}/delete")
    public String delete(@PathVariable int resumeId, HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        Resume resume = resumeRepository.findById(resumeId);

        if (resume == null) {
            request.setAttribute("msg", "해당 아이디를 찾을 수 없습니다.");
            request.setAttribute("status", "404");
            return "/error";
        } else {
            resumeRepository.deleteById(resumeId);
            return "redirect:/user/" + user.getId();
        }
    }
}

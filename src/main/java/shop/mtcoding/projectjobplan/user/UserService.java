package shop.mtcoding.projectjobplan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception400;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception401;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.apply.Apply;
import shop.mtcoding.projectjobplan.apply.ApplyJpaRepository;
import shop.mtcoding.projectjobplan.offer.Offer;
import shop.mtcoding.projectjobplan.offer.OfferJpaRepository;
import shop.mtcoding.projectjobplan.rating.RatingJpaRepository;
import shop.mtcoding.projectjobplan.skill.Skill;
import shop.mtcoding.projectjobplan.skill.SkillJpaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;
    private final OfferJpaRepository offerJpaRepository;
    private final SkillJpaRepository skillJpaRepository;
    private final RatingJpaRepository ratingJpaRepository;

    @Transactional
    public User createUser(UserRequest.JoinDTO requestDTO) { // join
        Optional<User> userOP = userJpaRepository.findByUsername(requestDTO.getUsername());
        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저입니다.");
        }
        User user = new User(requestDTO);

        return userJpaRepository.save(user);
    }

    public User getUser(UserRequest.LoginDTO requestDTO) { // login

        return userJpaRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));
    }

    @Transactional(readOnly = true)
    public UserResponse.ProfileDTO getUser(Integer sessionUserId, Integer boardId, Integer resumeId, Pageable pageable) {
        User user = userJpaRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        List<Apply> applyList;
        List<Offer> offerList;
        if (user.getIsEmployer()) { // 기업 마이페이지
            if (boardId == null) { // 모든 지원자 현황 보기 & 모든 제안 현황 보기
                applyList = applyJpaRepository.findByBoardUserId(user.getId());
                offerList = offerJpaRepository.findByBoardUserId(user.getId());
            } else { // 공고별 지원자 보기 & 공고별 제안 현황 보기
                applyList = applyJpaRepository.findByBoardId(boardId);
                offerList = offerJpaRepository.findByBoardId(boardId);
            }
        } else { // 개인 마이페이지
            if (resumeId == null) { // 모든 지원 현황 보기 & 모든 제안 현황 보기
                applyList = applyJpaRepository.findByResumeUserId(user.getId());
                offerList = offerJpaRepository.findByResumeUserId(user.getId());
            } else { // 이력서별 지원 현황 보기 & 이력서별 제안 현황 보기
                applyList = applyJpaRepository.findByResumeId(resumeId);
                offerList = offerJpaRepository.findByResumeId(resumeId);
            }
        }
        Double rating = ratingJpaRepository.findRatingAvgByUserId(user.getId()).orElse(0.0);

        return new UserResponse.ProfileDTO(user, applyList, offerList, rating, pageable);
    }

    public UserResponse.UpdateFormDTO getUser(int userId) { // 회원수정폼
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        return new UserResponse.UpdateFormDTO(user);
    }

    @Transactional // 회원수정
    public User setUser(int userId, UserRequest.UpdateDTO requestDTO) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));
        user.update(requestDTO);

        return user;
    }

    @Transactional
    public void removeUser(int userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));

        userJpaRepository.delete(user);
    }

    @Transactional
    public void createSkillList(UserRequest.SkillDTO requestDTO, int userId) {
        User user = userJpaRepository.findById(userId).get();

        List<Skill> skillList = new ArrayList<>();
        for (String skillName : requestDTO.getSkill()) {
            Skill skill = Skill.builder()
                    .user(user)
                    .name(skillName)
                    .build();
            skillList.add(skill);
        }
        // dto.getSkill().stream().forEach(s -> new Skill(user, s));
        List<Skill> skillFound = skillJpaRepository.findByUserId(userId).orElse(null);
        if (skillFound != null) {
            skillJpaRepository.deleteAll(skillFound);
        }
        skillJpaRepository.saveAll(skillList);
    }

    @Transactional
    public void picUpload(UserRequest.PicDTO requestDTO, Integer sessionUserId) {
        User user = userJpaRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        // byte[] img = Base64.getDecoder().decode();
        // base64 확장자, 파싱하는 법 gpt...
        MultipartFile imgFilename = requestDTO.getImgFilename();
        // 사진이 변경되었는지 여부 확인
        boolean hasImgChanged = imgFilename != null && !imgFilename.isEmpty();
        // 이미지 파일의 저장 경로 설정
        String webImgPath = null;
        if (hasImgChanged) { // 변경된 경로가 넘어올 경우
            String userImgFilename = UUID.randomUUID() + "_" + imgFilename.getOriginalFilename();
            Path imgPath = Paths.get("./upload/" + userImgFilename);
            try {
                Files.write(imgPath, imgFilename.getBytes());
                webImgPath = imgPath.toString().replace("\\", "/");
                webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 새로운 이미지 파일 경로와 함께 업데이트
            user.picPost(webImgPath);
        } else { // 빈 경로가 넘어올 경우
            // 현재 경로를 가져와서 삭제하고 기본 프로필 사진 경로로 초기화
            Path currentPath = Paths.get("./upload/" + user.getImgFilename());
            webImgPath = user.getIsEmployer() ? "default/business.png" : "default/avatar.png";
            if (!currentPath.equals(webImgPath)) {
                try {
                    Files.delete(currentPath); // todo : 기존 파일 삭제하는 위험이 있음. (user1, user2)
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            user.picPost(webImgPath);
        }
    }
}

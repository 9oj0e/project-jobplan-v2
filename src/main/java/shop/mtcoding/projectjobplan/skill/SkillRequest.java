package shop.mtcoding.projectjobplan.skill;

import lombok.Data;

import java.util.List;

public class SkillRequest {

    @Data
    public static class SaveDTO {
        private List<String> skill;
    }

    @Data
    public static class UpdateDTO {
        private List<String> skill;
    }
}

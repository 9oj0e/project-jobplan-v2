package shop.mtcoding.projectjobplan.skill;

import lombok.Data;

import java.util.List;

public class SkillRequest {

    @Data
    public static class DTO {
        private List<String> skill;
    }
}

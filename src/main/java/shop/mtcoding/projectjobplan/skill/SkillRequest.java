package shop.mtcoding.projectjobplan.skill;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class SkillRequest {

    @Data
    public static class DTO {
        private List<@Pattern(
                regexp = "^(C|Java|Python|Linux|MySQL|Spring" +
                        "|HTML|javaScript|jQuery|AWS|JSP" +
                        "|Flutter|React|Node.js|Vue.js" +
                        "|Swift|Kotlin)$"
        ) String> skill = new ArrayList<>();
    }
}

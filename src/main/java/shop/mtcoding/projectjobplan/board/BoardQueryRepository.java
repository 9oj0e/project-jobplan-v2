package shop.mtcoding.projectjobplan.board;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.projectjobplan.skill.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final EntityManager entityManager;

    private static String buildWhereClause(List<String> keywords) {
        StringJoiner whereClause = new StringJoiner(" OR ");
        for (String keyword : keywords) {
            whereClause.add("s.name = '" + keyword + "'");
        }
        return whereClause.toString();
    }

    public List<Object[]> findWithSkill(List<Skill> skills) {
        List<String> skillNameList = new ArrayList<>();
        skills.forEach(skill -> skillNameList.add(skill.getName()));
        String whereClause = buildWhereClause(skillNameList);
        String queryStart = """
                SELECT b.id, b.title, b.field, u.business_name FROM
                (SELECT s.board_id, COUNT(s.name) AS name_count
                FROM skill_tb AS s
                WHERE s.board_id IS NOT NULL
                AND (""";
        String queryEnd = """
                )
                GROUP BY s.board_id
                ORDER BY name_count DESC) s,
                board_tb b, user_tb u
                WHERE b.id = s.board_id
                AND
                b.user_id = u.id
                """;
        String query = queryStart + whereClause + queryEnd;
        /*
        List<Object[]> results = entityManager.createQuery(query).getResultList();
        System.out.println(results);
        List<BoardResponse.ListingsDTO.RecommendationDTO> responseDTO = new ArrayList<>();
        for (Object[] result : results) {
            BoardResponse.ListingsDTO.RecommendationDTO dto = new BoardResponse.ListingsDTO.RecommendationDTO();
            dto.setBoardId((Integer) result[0]);
            dto.setTitle((String) result[1]);
            dto.setField((String) result[2]);
            dto.setBusinessName((String) result[3]);

            responseDTO.add(dto);
        }
        */
        return entityManager.createQuery(query).getResultList();
    }
}

package shop.mtcoding.projectjobplan.skill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.user.UserRequest;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SkillRepository {

    private final EntityManager entityManager;

    @Transactional
    public void uploadByResumeId(String skill, int resumeId) {
        String q = """
                INSERT INTO skill_tb(resume_id, skill_name) VALUES (?, ?)
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, skill);

        query.executeUpdate();
    }
    @Transactional
    public void uploadByEmployerId(String skill, int employerId, int boardId) {
        String q = """
                INSERT INTO skill_tb(employer_id, skill_name,board_id) VALUES (?, ?,?)
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, employerId);
        query.setParameter(2, skill);
        query.setParameter(3,boardId);

        query.executeUpdate();
    }

    @Transactional
    public void uploadByUserId(String skill, int userId,int resumeId) {
        String q = """
                INSERT INTO skill_tb(user_id, skill_name,resume_id) VALUES (?, ?,?)
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1,userId);
        query.setParameter(2,skill);
        query.setParameter(3,resumeId);

        query.executeUpdate();
    }

    @Transactional
//    public void uploadByUserId(List<String> skills, Integer userId) {
//        String q1 = """
//            delete from skill_tb where user_id = ?
//        """;
//        Query query1 = entityManager.createNativeQuery(q1);
//        query1.setParameter(1, userId);
//        query1.executeUpdate();
//
//        for(String skill : skills) {
//            String q2 =
//                    """
//                INSERT INTO skill_tb(user_id, skill_name)
//                    VALUES (?, ?)
//                """;
//            Query query2 = entityManager.createNativeQuery(q2);
//            query2.setParameter(1, userId);
//            query2.setParameter(2, skill);
//            query2.executeUpdate();
//        }
//    }
//
//    @Transactional
//    public void uploadByEmployerId(List<String> skills,Integer employerId) {
//        String q1 = """
//            delete from skill_tb where employer_id = ?
//        """;
//        Query query1 = entityManager.createNativeQuery(q1);
//        query1.setParameter(1, employerId);
//        query1.executeUpdate();
//
//        for(String skill : skills) {
//            String q2 =
//                    """
//                INSERT INTO skill_tb(employer_id, skill_name)
//                    VALUES (?, ?)
//                """;
//            Query query2 = entityManager.createNativeQuery(q2);
//            query2.setParameter(1, employerId);
//            query2.setParameter(2, skill);
//            query2.executeUpdate();
//        }
//    }

    public List<Skill> findByBoardId(int boardId) {
        String q = """
                select * from skill_tb where board_id = ?
                """;
        Query query = entityManager.createNativeQuery(q,Skill.class);
        query.setParameter(1,boardId);

        try {
            List<Skill> skillList = query.getResultList();
            return skillList;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Skill> findByResumeId(int resumeId) {
        String q = """
                select * from skill_tb where resume_id = ?
                """;
        Query query = entityManager.createNativeQuery(q,Skill.class);
        query.setParameter(1,resumeId);

        try {
            List<Skill> skillList = query.getResultList();
            return skillList;
        } catch (Exception e) {
            return null;
        }
    }
    @Transactional
    public void updateByResumeId(List<String> skills,int resumeId, int userId) {
        String q1 = """
                delete from skill_tb where resume_id = ?
                """;
        Query query1 = entityManager.createNativeQuery(q1);
        query1.setParameter(1,resumeId);

        query1.executeUpdate();
        for(String skill : skills){
            String q2 = """
                insert into skill_tb(skill_name,resume_id,user_id) values(?,?,?)
                """;
            Query query2 = entityManager.createNativeQuery(q2);
            query2.setParameter(1,skill);
            query2.setParameter(2,resumeId);
            query2.setParameter(3,userId);

            query2.executeUpdate();
        }

    }
    @Transactional
    public void updateByBoardId(List<String> skills, int boardId, int employerId) {
        String q1 = """
                delete from skill_tb where board_id = ?
                """;
        Query query1 = entityManager.createNativeQuery(q1);
        query1.setParameter(1,boardId);

        query1.executeUpdate();
        for(String skill : skills){
            String q2 = """
                    insert into skill_tb(skill_name,board_id,employer_id) values(?,?,?)
                    """;
            Query query2 = entityManager.createNativeQuery(q2);
            query2.setParameter(1,skill);
            query2.setParameter(2,boardId);
            query2.setParameter(3,employerId);

            query2.executeUpdate();
        }
    }
}

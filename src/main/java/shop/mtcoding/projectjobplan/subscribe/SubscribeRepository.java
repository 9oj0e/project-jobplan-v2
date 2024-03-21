package shop.mtcoding.projectjobplan.subscribe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SubscribeRepository {
    private final EntityManager entityManager;

    public Subscribe findAllByUserIdResumeId(Integer boardUserId, Integer resumeId){
        String q = "select * from subscribe_tb where board_user_id = ? and resume_id = ?";
        Query query = entityManager.createNativeQuery(q, Subscribe.class);
        query.setParameter(1, boardUserId);
        query.setParameter(2, resumeId);

        try {
            return (Subscribe)query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Subscribe findAllByUserIdBoardId(Integer resumeUserId, Integer boardId){
        String q = "select * from subscribe_tb where resume_user_id = ? and board_id = ?";
        Query query = entityManager.createNativeQuery(q, Subscribe.class);
        query.setParameter(1, resumeUserId);
        query.setParameter(2, boardId);

        try {
            return (Subscribe)query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Integer findResumeUserIdByResumeId(int resumeId){
        String q = "select user_id from resume_tb where id = ?";
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, resumeId);

        return (Integer) query.getSingleResult();
    }

    public Integer findBoardUserIdByBoardId(int boardId){
        String q = "select employer_id from board_tb where id = ?";
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, boardId);

        return (Integer) query.getSingleResult();
    }

    @Transactional
    public int uploadByBoardId(int boardId,Integer sessionUserId) {
        String q = """
                insert into subscribe_tb (resume_user_id, board_id, created_at) values(?,?, now())
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, sessionUserId);
        query.setParameter(2, boardId);

        return query.executeUpdate();
    }

    @Transactional
    public int uploadByResumeId(int resumeId, Integer sessionUserId) {
        String q = """
                insert into subscribe_tb (board_user_id, resume_id, created_at) values(?,?, now())
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, sessionUserId);
        query.setParameter(2, resumeId);

        return query.executeUpdate();
    }

    public List<SubscribeResponse.ToUserDTO> findByUserId(int sessionUserId) {
        String q = """
                SELECT u.address, u.business_name, b.id, b.field, b.title, b.salary, b.closing_date
                FROM subscribe_tb s
                JOIN board_tb b ON b.id = s.board_id
                JOIN user_tb u ON b.employer_id = u.id
                WHERE s.resume_user_id = ?;
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, sessionUserId);
        List<Object[]> results = query.getResultList();
        List<SubscribeResponse.ToUserDTO> responseDTO = new ArrayList<>();
        for (Object[] result : results){
            SubscribeResponse.ToUserDTO dto = new SubscribeResponse.ToUserDTO();
            dto.setAddress((String) result[0]);
            dto.setBusinessName((String) result[1]);
            dto.setBoardId((Integer) result[2]);
            dto.setField((String) result[3]);
            dto.setTitle((String) result[4]);
            dto.setSalary((String) result[5]);
            dto.setClosingDate((Timestamp) result[6]);
            responseDTO.add(dto);
        }

        return responseDTO;
    }

    public List<SubscribeResponse.ToEmployerDTO> findByEmployerId(int sessionUserId) {
        // todo u.birthdate(year로 치환), u.gender, u.address 도 조회
        String q = """
                SELECT s.resume_id, r.title, u.name 
                FROM subscribe_tb s
                JOIN resume_tb r ON s.resume_id = r.id
                JOIN user_tb u ON r.user_id = u.id
                WHERE s.board_user_id = ?;
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, sessionUserId);
        List<Object[]> results = query.getResultList();
        List<SubscribeResponse.ToEmployerDTO> responseDTO = new ArrayList<>();
        for (Object[] result : results){
            SubscribeResponse.ToEmployerDTO dto = new SubscribeResponse.ToEmployerDTO();
            dto.setResumeId((Integer) result[0]);
            dto.setTitle((String) result[1]);
            dto.setResumeUsername((String) result[2]);
            responseDTO.add(dto);
        }

        return responseDTO;
    }


    @Transactional
    public int deleteByBoardId(int boardId, Integer sessionUserId) {
        String q = """
                delete from subscribe_tb where board_id = ? and resume_user_id = ?
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, boardId);
        query.setParameter(2, sessionUserId);

        return query.executeUpdate();
    }

    @Transactional
    public int deleteByResumeId(int resumeId, Integer sessionUserId) {
        String q = """
                delete from subscribe_tb where resume_id = ? and board_user_id = ?
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, sessionUserId);

        return query.executeUpdate();
    }
}

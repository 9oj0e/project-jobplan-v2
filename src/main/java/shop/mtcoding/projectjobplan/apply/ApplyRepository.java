package shop.mtcoding.projectjobplan.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager entityManager;

    @Transactional
    public int upload(ApplyRequest.UploadDTO requestDTO){
        String q = """
                INSERT INTO apply_tb
                (resume_id, resume_user_id, board_id, board_user_id, created_at)
                VALUES
                (?, ?, ?, ?, now())
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getResumeUserId());
        query.setParameter(3, requestDTO.getBoardId());
        query.setParameter(4, requestDTO.getBoardUserId());

        return query.executeUpdate();
    }
    public List<ApplyResponse.ToEmployerDTO> findByEmployerId(Integer sessionUserId){
        String q = """
                SELECT
                    b.id AS board_id,
                    b.title AS board_title,
                    u.name AS applicant_name,
                    r.id AS resume_id,
                    r.title AS resume_title,
                    a.created_at,
                    a.status
                FROM board_tb b
                JOIN apply_tb a ON b.id = a.board_id
                JOIN user_tb u ON a.resume_user_id = u.id
                JOIN resume_tb r ON a.resume_id = r.id
                WHERE a.board_user_id = ?
                ORDER BY a.id DESC
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, sessionUserId);
        List<Object[]> rawResultList = query.getResultList();
        List<ApplyResponse.ToEmployerDTO> responseDTO = new ArrayList<>();
        for (Object[] r : rawResultList ) {
            ApplyResponse.ToEmployerDTO dto = new ApplyResponse.ToEmployerDTO();
            dto.setBoardId((Integer) r[0]);
            dto.setBoardTitle((String) r[1]);
            dto.setApplicantName((String) r[2]);
            dto.setResumeId((Integer) r[3]);
            dto.setResumeTitle((String) r[4]);
            dto.setAppliedAt((Timestamp) r[5]);
            dto.setStatus((Boolean) r[6]);

            responseDTO.add(dto);
        }

        return responseDTO;
    }
    public List<ApplyResponse.ToEmployerDTO> findByBoardId(Integer boardId){
        String q = """
                SELECT
                    b.id AS board_id,
                    b.title AS board_title,
                    u.name AS applicant_name,
                    r.id AS resume_id,
                    r.title AS resume_title,
                    a.created_at,
                    a.status
                FROM board_tb b
                JOIN apply_tb a ON b.id = a.board_id
                JOIN user_tb u ON a.resume_user_id = u.id
                JOIN resume_tb r ON a.resume_id = r.id
                WHERE a.board_id = ?
                ORDER BY a.id DESC
                ;
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, boardId);

        List<Object[]> rawResultList = query.getResultList();
        List<ApplyResponse.ToEmployerDTO> responseDTO = new ArrayList<>();
        for (Object[] r : rawResultList ) {
            ApplyResponse.ToEmployerDTO dto = new ApplyResponse.ToEmployerDTO();
            dto.setBoardId((Integer) r[0]);
            dto.setBoardTitle((String) r[1]);
            dto.setApplicantName((String) r[2]);
            dto.setResumeId((Integer) r[3]);
            dto.setResumeTitle((String) r[4]);
            dto.setAppliedAt((Timestamp) r[5]);
            dto.setStatus((Boolean) r[6]);

            responseDTO.add(dto);
        }
        return responseDTO;

    }
    public List<ApplyResponse.ToUserDTO> findByUserId(Integer sessionUserId){
        String q = """
                SELECT 
                  r.title AS resume_title,
                  a.resume_id,
                  u.business_name,
                  b.title AS board_title,
                  b.id,
                  a.created_at,
                  a.status
               FROM apply_tb a
               JOIN resume_tb r ON a.resume_id = r.id
               JOIN board_tb b ON a.board_id = b.id
               JOIN user_tb u ON a.board_user_id = u.id
               WHERE a.resume_user_id = ?;
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, sessionUserId);
        List<Object[]> rawResultList = query.getResultList();
        List<ApplyResponse.ToUserDTO> responseDTO = new ArrayList<>();
        for (Object[] r : rawResultList){
            ApplyResponse.ToUserDTO dto = new ApplyResponse.ToUserDTO();
            dto.setResumeTitle((String) r[0]);
            dto.setResumeId((Integer) r[1]);
            dto.setBusinessName((String) r[2]);
            dto.setBoardTitle((String) r[3]);
            dto.setBoardId((Integer) r[4]);
            dto.setAppliedAt((Timestamp) r[5]);
            dto.setStatus((Boolean) r[6]);
            responseDTO.add(dto);
        }

        return responseDTO;
    }
    @Transactional
    public int update(Integer status, Integer resumeId, Integer boardId){
        String q = """
                update apply_tb set status = ? where resume_id = ? and board_id = ?
                """;

        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, status);
        query.setParameter(2, resumeId);
        query.setParameter(3, boardId);

        return query.executeUpdate();
    }
    @Transactional
    public int deleteByEmployerId(){
        return 0;
    }
    @Transactional
    public int deleteByUserId(){
        return 0;
    }
}

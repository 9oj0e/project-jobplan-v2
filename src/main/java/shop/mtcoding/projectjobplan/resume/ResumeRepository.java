package shop.mtcoding.projectjobplan.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.board.BoardResponse;
import shop.mtcoding.projectjobplan.skill.Skill;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {
    private final EntityManager entityManager;

    public ResumeResponse.ResumeDetailDTO detail(int resumeId) {
        String q = """
                SELECT
                u.name, u.birthdate, u.address, u.phone_number, u.email,
                r.user_id, r.title, r.content, r.school_name, r.major, r.education_level, r.career
                FROM user_tb AS u, resume_tb AS r
                WHERE r.id = ? AND r.user_id = u.id
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, resumeId);

        Object[] row = (Object[]) query.getSingleResult();
        String name = (String) row[0];
        String birthdate = (String) row[1];
        String address = (String) row[2];
        String phoneNumber = (String) row[3];
        String email = (String) row[4];

        Integer resumeUserId = (Integer) row[5];
        String title = (String) row[6];
        String content = (String) row[7];
        String schoolName = (String) row[8];
        String major = (String) row[9];
        String educationLevel = (String) row[10];
        String career = (String) row[11];

        ResumeResponse.ResumeDetailDTO resumeDetailDTO = new ResumeResponse.ResumeDetailDTO();
        resumeDetailDTO.setName(name);
        resumeDetailDTO.setBirthdate(birthdate);
        resumeDetailDTO.setAddress(address);
        resumeDetailDTO.setPhoneNumber(phoneNumber);
        resumeDetailDTO.setEmail(email);

        resumeDetailDTO.setId(resumeId);
        resumeDetailDTO.setUserId(resumeUserId);
        resumeDetailDTO.setTitle(title);
        resumeDetailDTO.setContent(content);
        resumeDetailDTO.setSchoolName(schoolName);
        resumeDetailDTO.setMajor(major);
        resumeDetailDTO.setEducationLevel(educationLevel);
        resumeDetailDTO.setCareer(career);

        return resumeDetailDTO;
    }

    public List<ResumeResponse.ResumeAndUserDTO> findByResumeAndUser(int page,String keyword) {
        final int COUNT = 10;
        int value = (page - 1) * COUNT;

        String q = """

            SELECT\s
                r.id, r.user_id, r.title, r.content, r.career,\s
                u.address, u.is_employer, u.name\s
                FROM\s
               resume_tb r\s
               INNER JOIN\s
              user_tb u\s
              ON\s
             r.user_id = u.id\s
            INNER JOIN\s
            skill_tb s\s
            ON\s
            r.id = s.resume_id\s
            WHERE\s
            u.is_employer = false\s
            AND s.skill_name = ?  ORDER BY r.id DESC LIMIT ?,?
            """ ;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1,keyword);
        query.setParameter(2, value);
        query.setParameter(3, COUNT);

        List<Object[]> results = query.getResultList();
        List<ResumeResponse.ResumeAndUserDTO> responseDTO = new ArrayList<>();

        for (Object[] result : results) {

            ResumeResponse.ResumeAndUserDTO dto = new ResumeResponse.ResumeAndUserDTO();
            dto.setId((Integer) result[0]);
            dto.setUserId((Integer) result[1]);
            dto.setTitle((String) result[2]);
            dto.setContent((String) result[3]);
            dto.setCareer((String) result[4]);
            dto.setAddress((String) result[5]);
            dto.setEmployer((boolean) result[6]);
            dto.setName((String) result[7]);

            responseDTO.add(dto);
        }
        return responseDTO;
    }

    public List<ResumeResponse.ResumeAndUserDTO> findByResumeAndUser(int page) {
        final int COUNT = 10;
        int value = (page - 1) * COUNT;

        String q = """
                select r.id,r.user_id,r.title,r.content,r.career,u.address,u.is_employer,u.name  from resume_tb r inner join user_tb u on r.user_id = u.id where u.is_employer =false order by id desc limit ?,?;
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, value);
        query.setParameter(2, COUNT);

        List<Object[]> results = query.getResultList();
        List<ResumeResponse.ResumeAndUserDTO> responseDTO = new ArrayList<>();

        for (Object[] result : results) {

            ResumeResponse.ResumeAndUserDTO dto = new ResumeResponse.ResumeAndUserDTO();
            dto.setId((Integer) result[0]);
            dto.setUserId((Integer) result[1]);
            dto.setTitle((String) result[2]);
            dto.setContent((String) result[3]);
            dto.setCareer((String) result[4]);
            dto.setAddress((String) result[5]);
            dto.setEmployer((boolean) result[6]);
            dto.setName((String) result[7]);

            responseDTO.add(dto);
        }
        return responseDTO;
    }


    public List<ResumeResponse.ResumeAndUserDTO> findByResumeAndUser() {
        String q = """
                select r.id,r.user_id,r.title,r.content,r.career,u.address,u.is_employer,u.name  from resume_tb r inner join user_tb u on r.user_id = u.id order by id desc;
                """;
        Query query = entityManager.createNativeQuery(q);
        List<Object[]> results = query.getResultList();
        List<ResumeResponse.ResumeAndUserDTO> responseDTO = new ArrayList<>();

        for (Object[] result : results) {

            ResumeResponse.ResumeAndUserDTO dto = new ResumeResponse.ResumeAndUserDTO();
            dto.setId((Integer) result[0]);
            dto.setUserId((Integer) result[1]);
            dto.setTitle((String) result[2]);
            dto.setContent((String) result[3]);
            dto.setCareer((String) result[4]);
            dto.setAddress((String) result[5]);
            dto.setEmployer((boolean) result[6]);
            dto.setName((String) result[7]);

            responseDTO.add(dto);
        }
        return responseDTO;
    }

    @Transactional
    public Integer upload(ResumeRequest.UploadDTO requestDTO, Integer userId) {
        String q = """
                INSERT INTO resume_tb
                (user_id, title, content, school_name, major, education_level, career, created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, now())
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getContent());
        query.setParameter(4, requestDTO.getSchoolName());
        query.setParameter(5, requestDTO.getMajor());
        query.setParameter(6, requestDTO.getEducationLevel());
        query.setParameter(7, requestDTO.getCareer());

        query.executeUpdate();

        String q1 = """
                select max(id) from resume_tb;
                """;
        Query query1 = entityManager.createNativeQuery(q1);
        Integer resumeId = (Integer) query1.getSingleResult();
      
        return resumeId ;
    }

    public List<Resume> findAll() {
        String q = "select * from resume_tb order by id desc";
        Query query = entityManager.createNativeQuery(q, Resume.class);

        return (List<Resume>) query.getResultList();

    }

    public Resume findById(Integer id) {
        String q = "select * from resume_tb where id = ?";

        Query query = entityManager.createNativeQuery(q, Resume.class);
        query.setParameter(1, id);

        return (Resume) query.getSingleResult();
    }

//    public Resume findUserIdById(Integer id) {
//        Query query = entityManager.createNativeQuery("select id, user_id from resume_tb where id = ?");
//        query.setParameter(1, id);
//        return (Resume) query.getSingleResult();
//    }

    public List<Resume> findByUserId(Integer userId) {
        String q = "select * from resume_tb where user_id = ? order by id desc";

        Query query = entityManager.createNativeQuery(q, Resume.class);
        query.setParameter(1, userId);

        return (List<Resume>) query.getResultList();
    }
    public List<Resume> findThreeByUserId(Integer userId) {
        String q = "select * from resume_tb where user_id = ? order by id desc limit 3";

        Query query = entityManager.createNativeQuery(q, Resume.class);
        query.setParameter(1, userId);

        return (List<Resume>) query.getResultList();
    }

    @Transactional
    public Integer updateById(ResumeRequest.UpdateDTO requestDTO, Integer id) {
        String q = """
                UPDATE resume_tb
                SET
                title = ?,
                content = ?,
                school_name = ?,
                major = ?,
                education_level = ?,
                career = ?
                WHERE id = ?
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, requestDTO.getSchoolName());
        query.setParameter(4, requestDTO.getMajor());
        query.setParameter(5, requestDTO.getEducationLevel());
        query.setParameter(6, requestDTO.getCareer());
        query.setParameter(7, id);

        return query.executeUpdate(); // 영향 받은 행
    }

    @Transactional
    public Integer deleteById(Integer id) {
        String q = "DELETE FROM resume_tb WHERE id = ?";
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, id);

        try {
            return query.executeUpdate(); // 영향 받은 행
        } catch (Exception e) {
            return null;
        }
    }

    public int countIsEmployerFalse() {
        String q = """
                SELECT COUNT(*) FROM resume_tb r INNER JOIN user_tb u ON r.user_id = u.id WHERE u.is_employer = false;
                """;
        Query query = entityManager.createNativeQuery(q);
        Long count = (Long) query.getSingleResult();

        return count.intValue();
    }
}


package shop.mtcoding.projectjobplan.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager entityManager;

    @Transactional
    public void upload(UserRequest.JoinDTO requestDTO) {
        String q = """
                INSERT INTO user_tb
                (username, password, name, birthdate, gender, phone_number, address, email,
                 is_employer, employer_id_number, business_name, created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?,
                 ?, ?, ?, now())
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getName());
        query.setParameter(4, requestDTO.getBirthdate());
        query.setParameter(5, requestDTO.getGender());
        query.setParameter(6, requestDTO.getPhoneNumber());
        query.setParameter(7, requestDTO.getAddress());
        query.setParameter(8, requestDTO.getEmail());

        query.setParameter(9, requestDTO.getIsEmployer());
        query.setParameter(10, requestDTO.getEmployerIdNumber());
        query.setParameter(11, requestDTO.getBusinessName());

        query.executeUpdate(); // 영향 받은 행
    }

    public User findById(int id) {
        String q = ("""
                SELECT *
                FROM user_tb
                WHERE id = ?
                """);
        Query query = entityManager.createNativeQuery(q, User.class);
        query.setParameter(1, id);
        return (User) query.getSingleResult();
    }

    public List<User> findAll() {
        String q = "select * from user_tb order by id desc";
        Query query = entityManager.createNativeQuery(q, User.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        String q = "select * from user_tb where username = ? and password = ?";
        Query query = entityManager.createNativeQuery(q, User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());

        try { // 아이디나 비밀번호 틀렸을 때
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findByUsername(String username) {
        String q = "select * from user_tb where username = ?";
        Query query = entityManager.createNativeQuery(q, User.class);
        query.setParameter(1, username);
        // query.setParameter(2, requestDTO.getPassword()); 암호화 필요

        try { // 아이디 중복체크
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Integer updateById(UserRequest.UpdateDTO requestDTO, Integer id) {
        if (findById(id).getIsEmployer() == false) {
            Query query = entityManager.createNativeQuery("""
                    update user_tb
                    set
                    username = ?,
                    password = ?,
                    email = ?,
                    birthdate = ?,
                    gender = ?,
                    phone_number = ?,
                    address = ?
                    where id = ?
                """);
            query.setParameter(1, requestDTO.getUsername());
            query.setParameter(2, requestDTO.getPassword());
            query.setParameter(3, requestDTO.getEmail());
            query.setParameter(4, requestDTO.getBirthdate());
            query.setParameter(5, requestDTO.getGender());
            query.setParameter(6, requestDTO.getPhoneNumber());
            query.setParameter(7, requestDTO.getAddress());
            query.setParameter(8, id);

            return query.executeUpdate();
        } else {
            Query query = entityManager.createNativeQuery("""
                update user_tb
                    set
                    username = ?,
                    password = ?,
                    email = ?,
                    phone_number = ?,
                    address = ?
                    where id = ?
                """);
            query.setParameter(1, requestDTO.getUsername());
            query.setParameter(2, requestDTO.getPassword());
            query.setParameter(3, requestDTO.getEmail());
            query.setParameter(4, requestDTO.getPhoneNumber());
            query.setParameter(5, requestDTO.getAddress());
            query.setParameter(6, id);

            return query.executeUpdate();
        }
    }
    /* 계정 탈퇴
    @Transactional
    public Integer deleteById(Integer id) {
        String q = "DELETE FROM user_tb WHERE id = ?";
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, id);

        return query.executeUpdate(); // 영향 받은 행
    }
    */
}

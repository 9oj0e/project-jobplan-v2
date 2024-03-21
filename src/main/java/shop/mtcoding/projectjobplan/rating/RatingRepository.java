package shop.mtcoding.projectjobplan.rating;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.user.User;

@Repository
@RequiredArgsConstructor
public class RatingRepository {
    private final EntityManager entityManager;

    @Transactional
    public int upload(int raterId, int subjectId, int rating) {
        String q = "insert into rating_tb (rater_id, subject_id, rating) values (?, ?, ?)";
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, raterId);
        query.setParameter(2, subjectId);
        query.setParameter(3, rating);

        return query.executeUpdate();
    }

    public Double findBySubjectId(int subjectId) {
        Query query = entityManager.createNativeQuery("select avg(rating) from rating_tb where subject_id = ?");
        query.setParameter(1, subjectId);

        return (Double) query.getSingleResult();
    }

    public boolean hasRated(int raterId, int subjectId) {
        String q = """
                SELECT *
                FROM
                rating_tb
                WHERE
                rater_id = ?
                AND
                subject_id = ?
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, raterId);
        query.setParameter(2, subjectId);
        try {
            System.out.println(query.getSingleResult());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Rating findAllBySubjectId(Integer subjectId) {
        Query query = entityManager.createNativeQuery("select * from rating_tb where subjectId = ?", Rating.class);
        query.setParameter(1, subjectId);
      
        return (Rating) query.getSingleResult();
    }
}

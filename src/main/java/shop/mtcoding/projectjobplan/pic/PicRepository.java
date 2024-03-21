package shop.mtcoding.projectjobplan.pic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class PicRepository {
    private final EntityManager em;

    @Transactional
    public void upload(int userId, String imgFilename){
        Query query = em.createNativeQuery("insert into pic_tb (user_id, img_filename) values (?,?)");
        query.setParameter(1, userId);
        query.setParameter(2, imgFilename);

        query.executeUpdate();
    }

    @Transactional
    public Pic findById(int id){
        Query query = em.createNativeQuery("select * from pic_tb", Pic.class);
        return (Pic) query.getSingleResult();
    }

    @Transactional
    public Integer deleteByUserId(int id) {
        Query query = em.createNativeQuery("delete from pic_tb where user_id = ?");
        query.setParameter(1, id);
        return query.executeUpdate();
    }

    @Transactional
    public String findImg() {
        Query query = em.createNativeQuery("select img_filename from pic_tb");
        return (String) query.getSingleResult();
    }
}
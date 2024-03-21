package shop.mtcoding.projectjobplan.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.projectjobplan.user.User;

public interface ResumeJpaRepository extends JpaRepository <User, Integer> {
}

package shop.mtcoding.projectjobplan.skill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillJpaRepository extends JpaRepository<Skill, Integer> {
    Optional<List<Skill>> findAllByUserId(int userId);
}
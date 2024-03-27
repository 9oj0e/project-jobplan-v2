package shop.mtcoding.projectjobplan.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingJpaRepository ratingJpaRepository;
}

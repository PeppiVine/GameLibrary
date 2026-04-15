package haagahelia.fi.demo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserReviewRepository extends CrudRepository<UserReview, Long> {

    List<UserReview> findByUserUsername(String userName);

    List<UserReview> findByGameId(Long gameId);
}
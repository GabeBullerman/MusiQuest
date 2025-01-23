package musiquest.Leaderboard;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * LeaderboardEntry Repository
 */
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntry, Integer> {

	LeaderboardEntry findById(int id);

    void deleteById(int id);




}

package musiquest.Leaderboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import musiquest.Users.User;
import musiquest.Users.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeaderboardServie used with LeaderboardController
 */

@Service
public class LeaderboardService {
	
	@Autowired
	private LeaderboardRepository leaderboardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * HashMap used to find leaderboardEntry quickly
	 */
    private static Map<Integer, LeaderboardEntry> leaderboard = new HashMap<>();

    /**
     * Adds a score to the leaderboard
     * @param int accountId
     * @param int score
     */
    public void addScore(int accountId, int score) {
    	if(leaderboard == null) {
    		for(LeaderboardEntry entry: leaderboardRepository.findAll()) {
    			leaderboard.put(entry.getAccountId(), entry);
    		}
    	}
        leaderboard.put(accountId, new LeaderboardEntry(accountId, score));
    }
    
    /**
     * Updates the score for a user
     * @param int accountId
     * @param int score
     */
    public void updateScore(int accountId, int score) {
    	LeaderboardEntry entry = null;
    	this.fill();
    	try {
    		entry = leaderboard.get(accountId);
    	} catch (NullPointerException e) {
    		this.addScore(accountId, score);
    		entry = leaderboard.get(accountId);
    	} finally {
    		if(entry.getScore() < score) {
    			entry.setScore(score);
    			leaderboard.replace(accountId, entry);
    		}
    	}
    }
    
    public int getSize() {
    	return leaderboard.size();
    }
    
    public void fill() {
    	if(leaderboard == null) {
    		leaderboard = new HashMap<>();
    	}
    	for(LeaderboardEntry entry: leaderboardRepository.findAll()) {
			leaderboard.put(entry.getAccountId(), entry);
		}
    }
    
    /**
     * Gets the top scores to a limit 
     * @param int limit
     * @return List<LeaderboardEntry> gotScores
     */
    public List<LeaderboardEntry> getTopScores(int limit) {
        return leaderboard.values()
                .stream()
                .sorted(Comparator.comparingInt(LeaderboardEntry::getScore).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    public List<String> getTopScoresWithNames(int limit) {
        return leaderboard.values()
                .stream()
                .sorted(Comparator.comparingInt(LeaderboardEntry::getScore).reversed())
                .limit(limit)
                .map(entry -> {
                    User user = userRepository.findById(entry.getAccountId());
                    String userName = user != null ? user.getName() : "Unknown"; // Use the user's name or "Unknown" if not found
                    return userName + " score " + entry.getScore();
                })
                .collect(Collectors.toList());
    }

    /**
     * Gets the score for a specific user
     * @param accountId
     * @return
     */
    public int getScoreForAccount(int accountId) {
        LeaderboardEntry entry = leaderboard.get(accountId);
        return (entry != null) ? entry.getScore() : -1;
    }
    
    /**
     * Gets the leaderboardEntry for a specific user
     * @param int accountId
     * @return LeaderboardEntry
     */
    public LeaderboardEntry getLeaderboardEntry(int accountId) {
    	return leaderboard.get(accountId);
    }

}


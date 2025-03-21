package musiquest.Leaderboard;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import musiquest.SoloUser.SoloUser;
import musiquest.SoloUser.SoloUserRepository;
import musiquest.Users.UserRepository;

import java.util.Optional;

/**
 * Leaderboard Controller, "/leaderboard"
 */
@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {


    private String success = "{\"message\":\"success\"}";
    public String failure = "{\"message\":\"failure\"}";

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private LeaderboardService leaderboardService;
    
    @Autowired 
    private UserRepository userRepository;

    /**
     * Adds a score to the leaderboard, POST request, "/addScore"
     * @RequestParam int accountId
     * @RequestParam int score
     * @return String success or failure
     */
    @PostMapping("/addScore")
    public String addScore(@RequestParam int accountId, @RequestParam int score) {
        leaderboardService.addScore(accountId, score);
        leaderboardRepository.save(new LeaderboardEntry(accountId, score));
        return success;
    }
    
    /**
     * Updates a score of the leaderboard, PUT request, "/updateScore"
     * @RequestParam int accountId
     * @RequestParam int score
     */
    @PutMapping("/updateScore")
    public void updateScore(@RequestParam int accountId, @RequestParam int score) {
    	leaderboardService.updateScore(accountId, score);
    	leaderboardRepository.save(leaderboardService.getLeaderboardEntry(accountId));
//        for(LeaderboardEntry x: leaderboardRepository.findAll()) {
//            try {
//                leaderboardRepository.save(x);
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * Gets the top scores in terms of a limit, ex: limit=10- top 10 scores, GET request, "/topScores"
     * @RequestParam int limit
     * @return String success or failure
     */
    @GetMapping("/topScores")
    public String getTopScores(@RequestParam int limit) {
    	List<LeaderboardEntry> list= leaderboardService.getTopScores(limit);
        String temp = "";
        for(LeaderboardEntry entry: list) {
        	temp += userRepository.findById(entry.getAccountId()).getName() + " " + entry.getScore() + ",";
        }
        return temp;
    }
    
    
    /**
     * gets the score for a specific user, GET request, "/scoreForAccount"
     * @RequestParam int accountId
     * @return int score
     */
    @GetMapping("/scoreForAccount")
    public int getScoreForAccount(@RequestParam int accountId) {
        return leaderboardService.getScoreForAccount(accountId);
    }
	
	
}

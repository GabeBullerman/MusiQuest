package musiquest.Leaderboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import musiquest.Users.User;

/**
 * LeaderboardEntry that holds the AccountId and a score
 */
@Entity
@Table
public class LeaderboardEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column
	private int accountId;
	@Column
    private int score;
	
	/**
	 * Create a leaderboardEntry
	 * @param int accountId
	 * @param int score
	 */
    public LeaderboardEntry(int accountId, int score) {
        this.accountId = accountId;
        this.score = score;
    }
	
	public LeaderboardEntry() {
	}
	
	/**
	 * Set the score of the user
	 * @param int score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Get score of user
	 * @return int score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set the accountId, should not be used
	 * @param int accountId
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	/**
	 * Gets the accountId
	 * @return int accountId
	 */
	public int getAccountId() {
		return accountId;
	}
	
	/**
	 * Gets the ID of the leaderboardEntry
	 * @return int ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Sets the ID of the LeaderboardEntry, should not be used
	 * @param int ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
  	
	
	

}

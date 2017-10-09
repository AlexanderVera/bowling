package org.jobsity.run.model;

import java.util.ArrayList;
import java.util.List;

/**
* GameLine class. A player with all its score in one game.
* 
*
* @author alexander.vera
* @since 30/09/2017
*/
public class GameLine{
	
	/**
	* List of score from a player frame
	*/
	private List<Score> scores;
	/**
	* Total score from a player
	*/
	private int totalScore;
	/**
	* Player name
	*/
	private String playerName;
	
	/**
	* Empty constructor
	*/
	public GameLine(){
		scores = new ArrayList<Score>();		
		totalScore = 0;
	}

	/**
	* Constructor
	* @param id: Long represents the id of player
	* 		 name: String that represents the name of player	
	* 		 scores: All player shoots
	*/		  
	public GameLine(String name, List<Score> scores){
		this.playerName=name;
		this.scores = scores;
		totalScore = 0;
	}	
	
	/**
	 * Getter from Score
	 * @return Score
	 */
	public List<Score> getScore() {
		return scores;
	}
	/**
	 * Setter from Score
	 */
	public void setScore(List<Score> scores) {
		this.scores = scores;
	}

	/**
	 * Getter from totalScore
	 * @return totalScore
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * Setter from Score
	 * 
	 */
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * Getter from PlayerName
	 * @return PlayerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Setter from playerName
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
/*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
*/
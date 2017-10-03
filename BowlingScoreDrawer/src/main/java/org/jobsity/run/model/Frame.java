package org.jobsity.run.model;

import java.util.ArrayList;
import java.util.List;

/**
* Frame
* Object to represent a line of all pinfall for a player
*
* @author alexander.vera
* @since 30/09/2017
*/
public class Frame{
	
	/**
	* List of score from a player frame
	*/
	private List<Score> scores;
	/**
	* Last score from a player
	*/
	private Score currentScore;
	/**
	* Total score from a player
	*/
	private int totalScore;
	/**
	* Player name
	*/
	private String playerName;
	/**
	* Player id
	*/
	private Long playerId;
	
	/**
	* Empty constructor
	*/
	public Frame(){
		scores = new ArrayList<Score>();		
		totalScore = 0;
	}

	/**
	* Constructor
	* @param id: Long represents the id of player
	* 		 name: String that represents the name of player	
	* 		 scores: All player shoots
	*/		  
	public Frame(Long playerId, String name, List<Score> scores){
		this.playerId=playerId;
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
	 * Getter from currentScore
	 * @return Score
	 */
	public Score getCurrentScore() {
		return currentScore;
	}
	
	/**
	 * Setter from currentScore
	 * 
	 */
	public void setCurrentScore(Score currentScore) {
		scores.add(currentScore);
		this.currentScore = currentScore;
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
	 * Getter from PlayerId
	 * @return PlayerId
	 */
	public Long getPlayerId() {
		return playerId;
	}

	/**
	 * Setter from PlayerId
	 * 
	 */
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
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
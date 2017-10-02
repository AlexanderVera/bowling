package org.jobsity.run.model;

import java.util.ArrayList;
import java.util.List;

/**
* Frame
* Object to represent a line of all pinfall for a player
*
* @author alexander.vera
* @since 30/10/2017
*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* 
*/
public class Frame{
	private List<Score> scores;
	private Score currentScore;
	private int totalScore;
	private String playerName;
	private Long id;
	
	public Frame(){
		scores = new ArrayList<Score>();		
		totalScore = 0;
	}

	public Frame(Long id, String name, List<Score> scores){
		setId(id);
		setPlayerName(name);
		this.scores = scores;
		scores = new ArrayList<Score>();
		totalScore = 0;
	}	
	
	public List<Score> getScore() {
		return scores;
	}

	public void setScore(List<Score> scores) {
		this.scores = scores;
	}

	public Score getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(Score currentScore) {
		scores.add(currentScore);
		this.currentScore = currentScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	
}

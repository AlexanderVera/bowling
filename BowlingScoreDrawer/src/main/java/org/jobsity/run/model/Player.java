package org.jobsity.run.model;

import java.util.ArrayList;
import java.util.List;

public class Player extends Person{
	private List<Score> scores;
	private Score currentScore;
	private int totalScore;
	
	
	public Player(){
		scores = new ArrayList<Score>();		
		totalScore = 0;
	}

	public Player(Long id, String name, List<Score> scores){
		setId(id);
		setName(name);
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


	
}

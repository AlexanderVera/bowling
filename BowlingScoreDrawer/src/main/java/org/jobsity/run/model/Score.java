package org.jobsity.run.model;

/**
* Score class. Object that represents a turn single score for a player, the shoots and the total score.
*
* @author alexander.vera
* @since 30/10/2017
*
**/
public class Score {
	private int[] shoots;
	private int total;
	private boolean flagFinal;
	private boolean strike;
	private boolean spare;
	
	public Score(){
		shoots = new int[3];
	}
	
	public Score(boolean flagFinal){
		this.flagFinal=flagFinal;
		this.shoots = new int[3];
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isFlagFinal() {
		return flagFinal;
	}

	public boolean getFlagFinal() {
		return flagFinal;
	}

	public void setFlagFinal(boolean flagFinal) {
		this.flagFinal = flagFinal;
	}

	public boolean isSpare() {
		return spare;
	}

	public void setSpare(boolean spare) {
		this.spare = spare;
	}

	public boolean isStrike() {
		return strike;
	}

	public void setStrike(boolean strike) {
		this.strike = strike;
	}

	public int[] getShoots() {
		return shoots;
	}

	public void setShoots(int[] shoots) {
		this.shoots = shoots;
	}
}
/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* 
*/
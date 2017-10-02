package org.jobsity.run.model;

/**
 * 
 * @author alexander.vera
 * @since 2017-09-30
 * 
 * 
 */

public class Score {
	private int[] shoots;
	private int total;
	private String symbol;
	private boolean flagFinal;
	private boolean strike;
	private boolean spare;
	
	public Score(){
		shoots = new int[3];
	}
	
	public Score(boolean flagFinal){
		setFlagFinal(flagFinal);
		shoots = new int[3];
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

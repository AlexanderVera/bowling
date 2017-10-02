package org.jobsity.run.model;

/**
 * 
 * @author alexander.vera
 * @since 2017-09-30
 * 
 * 
 */

public class Score {
	private int firstTry;
	private int secondTry;
	private int tirdTry;
	private int total;
	private String symbol;
	private boolean flagFinal;
	private boolean strike;
	private boolean spare;
	
	public Score(){
		
	}
	
	public Score(int _firstTry, int _secondTry, int _tirdTry, boolean _flagFinal){
		setFirstTry(_firstTry);
		setSecondTry(_secondTry);
		setTirdTry(_tirdTry);
		setFlagFinal(_flagFinal);	
	}
	
	public int getFirstTry() {
		return firstTry;
	}

	public void setFirstTry(int firstTry) {
		this.firstTry = firstTry;
	}

	public int getSecondTry() {
		return secondTry;
	}

	public void setSecondTry(int secondTry) {
		this.secondTry = secondTry;
	}

	public int getTirdTry() {
		return tirdTry;
	}

	public void setTirdTry(int tirdTry) {
		this.tirdTry = tirdTry;
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
}

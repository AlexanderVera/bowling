package org.jobsity.run.model;

/**
* PlayerScore
* Object that represents a player 
*
* @author alexander.vera
* @since 30/10/2017
* 
*
*/
public class PlayerScore extends Person implements Comparable<PlayerScore>{
	private int pinfalls;
	
	public PlayerScore(){
		
	}
	
	public PlayerScore(String name, int pinfalls){
		setName(name);
		this.pinfalls = pinfalls;
	}

	public int getPinfalls() {
		return pinfalls;
	}

	public void setPinfalls(int pinfalls) {
		this.pinfalls = pinfalls;
	}

	public int compareTo(PlayerScore newPlayer) {
		return this.getName().compareTo(newPlayer.getName());
		
	}
}
/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* 
*/
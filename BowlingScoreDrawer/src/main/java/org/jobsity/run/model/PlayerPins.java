package org.jobsity.run.model;

/**
* PlayerPins class. Object that represents a player pin pair (e.g Jhon 3) 
*
* @author alexander.vera
* @since 30/10/2017
*
*/
public class PlayerPins extends Person implements Comparable<PlayerPins>{
	private int pinfalls;
	
	public PlayerPins(){
		
	}
	
	public PlayerPins(String name, int pinfalls){
		setName(name);
		this.pinfalls = pinfalls;
	}

	public int getPinfalls() {
		return pinfalls;
	}

	public void setPinfalls(int pinfalls) {
		this.pinfalls = pinfalls;
	}

	public int compareTo(PlayerPins newPlayer) {
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
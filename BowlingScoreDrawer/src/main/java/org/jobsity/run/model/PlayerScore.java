package org.jobsity.run.model;

public class PlayerScore implements Comparable<PlayerScore>{
	private String name;
	private int pinfalls;
	
	public PlayerScore(){
		
	}
	
	public PlayerScore(String name, int pinfalls){
		this.name = name;
		this.pinfalls = pinfalls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

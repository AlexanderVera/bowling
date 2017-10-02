package org.jobsity.run.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.util.Utilities;


public class PlayerController implements IPlayerController{
	private List<String> plainPlayerScores;

	public PlayerController(List<String> plainPlayerScores) {
		this.plainPlayerScores = plainPlayerScores;
	}
	
	
	public List<List<PlayerScore>> buildFrameOfPlayer(List<String> plainPlayerScores){
		return null;
	}
	
	
	public List<PlayerScore> buildPlayerScore(List<String> plainPlayerScores) {
		List<PlayerScore> newPlayerScores = null;
		if(plainPlayerScores != null){
			newPlayerScores = new ArrayList<PlayerScore>();
			PlayerScore newPlayerScore = null; 
			for(String plainScore : plainPlayerScores){
				newPlayerScore = new PlayerScore(); 
				String[] plainScoreSplited = plainScore.split(" ");
				newPlayerScore.setName(plainScoreSplited[0]);
				newPlayerScore.setPinfalls(Utilities.parseValidateInteger(plainScoreSplited[1]));
				newPlayerScores.add(newPlayerScore);
			}
			Collections.sort(newPlayerScores);
			return newPlayerScores;
		}
		else{
			return null;
		}
	}

	

	public List<String> getPlainPlayerScores() {
		return plainPlayerScores;
	}

	public void setPlainPlayerScores(List<String> plainPlayerScores) {
		this.plainPlayerScores = plainPlayerScores;
	}

}

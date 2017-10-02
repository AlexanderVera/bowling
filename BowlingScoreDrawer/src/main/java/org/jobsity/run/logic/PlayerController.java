package org.jobsity.run.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.util.Utilities;

public class PlayerController implements IPlayerController {
	private List<String> plainPlayerScores;
	private static final int TURNS_BY_GAME = 10;

	public PlayerController(List<String> plainPlayerScores) {
		this.plainPlayerScores = plainPlayerScores;
	}

	public List<List<PlayerScore>> buildFrameOfPlayer(List<String> plainPlayerScores) {
		return null;
	}

	public List<PlayerScore> buildPlayerScore(List<String> plainPlayerScores) {
		List<PlayerScore> newPlayerScores = null;
		if (plainPlayerScores != null) {
			newPlayerScores = new ArrayList<PlayerScore>();
			PlayerScore newPlayerScore = null;
			for (String plainScore : plainPlayerScores) {
				newPlayerScore = new PlayerScore();
				String[] plainScoreSplited = plainScore.split(" ");
				newPlayerScore.setName(plainScoreSplited[0]);
				newPlayerScore.setPinfalls(Utilities.parseValidateInteger(plainScoreSplited[1]));
				newPlayerScores.add(newPlayerScore);
			}
			List<PlayerScore> playerScoreToValidate = new ArrayList<PlayerScore>();
			playerScoreToValidate.addAll(newPlayerScores);
			//validateScore(playerScoreToValidate);
			return newPlayerScores;
		} else {
			return null;
		}
	}

	public void validateScore(List<PlayerScore> playerScoreToValidate) {
		Collections.sort(playerScoreToValidate);
		int playerPosition = 0;
		int turn = 0;
		Set<String> namesOfPlayers = new TreeSet<String>();
		for (int i = 0; i < playerScoreToValidate.size(); i++) {
			namesOfPlayers.add(playerScoreToValidate.get(i).getName());
		}

		int numOfPlayers = namesOfPlayers.size();

		while (turn < TURNS_BY_GAME && playerPosition < numOfPlayers) {
			int[] postitionFrame = null;
			for (String pivotePlayerName : namesOfPlayers) {
				postitionFrame = new int[21];
				/*
				 * if(pivotePlayerName.equals(anObject)){
				 * 
				 * }
				 */

			}

		}
		System.out.println("namesOfPlayers.size(" + numOfPlayers + ")");

	}

	public List<String> getPlainPlayerScores() {
		return plainPlayerScores;
	}

	public void setPlainPlayerScores(List<String> plainPlayerScores) {
		this.plainPlayerScores = plainPlayerScores;
	}

}

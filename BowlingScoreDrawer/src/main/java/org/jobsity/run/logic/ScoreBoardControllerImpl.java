package org.jobsity.run.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jobsity.run.interfaces.IScoreBoardController;
import org.jobsity.run.model.Player;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;

public class ScoreBoardControllerImpl implements IScoreBoardController {

	private static final int NUMBER_OF_ROUNDS = 10;
	private static final int NUMBER_OF_PINS = 10;
	private List<String> listPlainScorePlayers;


	public ScoreBoardControllerImpl(List<String> listPlainScorePlayers){
		this.listPlainScorePlayers=listPlainScorePlayers;
	}
	
	public List<Player> addPlayer(Player player, List<Player> listOfPLayers) {
		List<Player> outPlayers = new ArrayList<Player>();
		boolean match = false;
		if(player!=null){
		if (listOfPLayers == null || listOfPLayers.size() == 0) {
			outPlayers.add(player);
		} else {
			for (Player iterPlayer : listOfPLayers) {
				if (iterPlayer.getName().equals(player.getName())) {
					iterPlayer.getScore().add(player.getCurrentScore());
					match = true;
				}
				outPlayers.add(iterPlayer);
			}
			if (!match) {
				outPlayers.add(player);
			}
		}}
		return outPlayers;
	}

	public int numberOfPlayers(List<String> plainScorePlayers) {
		Set<String> playerNames = new TreeSet<String>();
		for (String strScore : plainScorePlayers) {
			String[] points = strScore.split(" ");
			String playerName = points[0];
			playerNames.add(playerName);
		}
		return playerNames.size();
	}

	public List<Player> calculateScore(List<Player> listOfPLayers) {
		List<Player> outPlayers = new ArrayList<Player>();

		for (Player player : listOfPLayers) {
			List<Score> listScores = new ArrayList<Score>();
			for (int posScore = 0; posScore < player.getScore().size(); posScore++) {
				Score currentScore = player.getScore().get(posScore);
				//
				int bonus = evaluateBonus(currentScore, posScore, player.getScore());
				int tempTotal = currentScore.getFirstTry() + currentScore.getSecondTry() + bonus;
				player.setTotalScore(player.getTotalScore() + tempTotal);
				currentScore.setTotal(player.getTotalScore());
				listScores.add(currentScore);
			}
			player.setScore(listScores);
			outPlayers.add(player);
		}

		return outPlayers;
	}

	public int evaluateBonus(Score score, int posScore, List<Score> listScores) {
		if (score.isFlagFinal()) {
			return score.getTirdTry();
		} else {
			Score nextScore = listScores.get(posScore + 1);
			if (score.isSpare()) {
				return nextScore.getFirstTry();
			} else if (score.isStrike()) {

				if (nextScore.isFlagFinal()) {
					return nextScore.getFirstTry() + nextScore.getSecondTry();
				} else if (nextScore.isStrike()) {
					Score postNextScore = listScores.get(posScore + 2);
					return nextScore.getFirstTry() + postNextScore.getFirstTry();
				} else {
					return nextScore.getFirstTry() + nextScore.getSecondTry();
				}
			} else {
				return 0;
			}
		}

	}
	
	public List<Player> buildFrame() {
		List<Player> listOfPLayers = null;
		if(getListPlainScorePlayers() != null){
		int numberOfPlayers = numberOfPlayers(getListPlainScorePlayers());
		int round=1, countTurns=0;

		int playersThatPlayed = 0;
		
		Score score = new Score();
		for (String strScore : getListPlainScorePlayers()) {
			Player player = new Player();
			String[] points = strScore.split(" ");
			String playerName = points[0];
			int playerPoints = (Utilities.resultAsNumber(points[1]));

			player.setName(playerName);

			if (countTurns == 0) {
				score.setFirstTry(playerPoints);
				if (playerPoints < NUMBER_OF_PINS) {
					countTurns++;
				} else if (round < NUMBER_OF_ROUNDS) {
					score.setStrike(true);
					countTurns = 3;
				} else {
					score.setStrike(true);
					countTurns++;
				}
			} else if (countTurns == 1) {
				score.setSecondTry(playerPoints);
				if (score.getFirstTry() >= NUMBER_OF_PINS || score.getSecondTry() >= NUMBER_OF_PINS) {
					score.setStrike(true);
				} else if ((score.getFirstTry() + score.getSecondTry()) >= 10) {
					score.setSpare(true);
				}
				if (round >= NUMBER_OF_ROUNDS) {
					countTurns++;
				} else {
					countTurns = 3;
				}
			} else if (countTurns == 2) {
				if (round >= NUMBER_OF_ROUNDS && ((score.getFirstTry() + score.getSecondTry()) >= NUMBER_OF_PINS)) {
					score.setTirdTry(playerPoints);
				}
				countTurns = 3;
			}

			if (countTurns > 2) {
				if (round >= NUMBER_OF_ROUNDS) {
					score.setFlagFinal(true);
				}
				player.setCurrentScore(score);
				listOfPLayers = addPlayer(player,listOfPLayers);
				score = new Score();
				playersThatPlayed++;

				if (playersThatPlayed == numberOfPlayers) {
					round++;
					playersThatPlayed = 0;
				}
				countTurns = 0;
			}

		}
		listOfPLayers = calculateScore(listOfPLayers);
		}
		return listOfPLayers;
	}

	public List<String> getListPlainScorePlayers() {
		return listPlainScorePlayers;
	}

	public void setListPlainScorePlayers(List<String> listPlainScorePlayers) {
		this.listPlainScorePlayers = listPlainScorePlayers;
	}


	

}

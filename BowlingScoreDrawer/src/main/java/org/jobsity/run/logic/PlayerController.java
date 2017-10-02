package org.jobsity.run.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.model.Player;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;

public class PlayerController implements IPlayerController {
	
	private List<String> plainPlayerScores;
	private static final int TURNS_BY_GAME = 9;
	private static final int STRIKE_POINTS = 10;
	private static final int MAX_SHOOTS_BY_GAME = 21;
	private static final int SHOOTS_BY_TURN = 2;
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	private Messages messages;

	public PlayerController(List<String> plainPlayerScores) {
		messages = new Messages();
		this.plainPlayerScores = plainPlayerScores;
	}

	public List<Player> buildPlayerScore(List<String> plainPlayerScores) {
		List<PlayerScore> newPlayerScores = null;
		
		if (plainPlayerScores != null) {
			newPlayerScores = new ArrayList<PlayerScore>();
			PlayerScore newPlayerScore = null;
			for (String plainScore : plainPlayerScores) {
				
				newPlayerScore = new PlayerScore();
				String[] plainScoreSplited = plainScore.split(" ");
				newPlayerScore.setName(plainScoreSplited[0]);
				if(plainScoreSplited.length>1){
					newPlayerScore.setPinfalls(Utilities.parseValidateInteger(plainScoreSplited[1]));
				}
				else{
					newPlayerScore.setPinfalls(0);
				}
				newPlayerScores.add(newPlayerScore);
			
			}
			List<PlayerScore> playerScoreToValidate = new ArrayList<PlayerScore>();
			playerScoreToValidate.addAll(newPlayerScores);

			// Ordeno la lista de participantes y obengo el número total de los
			// mismo
			Collections.sort(playerScoreToValidate);
			Set<String> namesOfPlayers = new TreeSet<String>();

			for (int i = 0; i < playerScoreToValidate.size(); i++) {
				namesOfPlayers.add(playerScoreToValidate.get(i).getName());
			}

			List<String> playerNameList = new ArrayList<String>();
			playerNameList.addAll(namesOfPlayers);

			int numOfPlayers = namesOfPlayers.size();

			return validateScore(playerScoreToValidate, playerNameList, numOfPlayers);

		} else {
			return null;
		}
	}

	public List<Player> validateScore(List<PlayerScore> scoresToValidate, List<String> playerNameList, int countPlayers) {
		List<Player> listOfPLayers = null;

		int playerPosition = 0, turn = 0, shootByPlayer = 0;

		if (scoresToValidate != null) {

			int[] shootCharByPlayer = null;

			while (playerPosition < countPlayers) {
				shootCharByPlayer = new int[MAX_SHOOTS_BY_GAME];
				shootByPlayer = 0;
				int bonusTurn = 0;
				Score score = new Score();
				Player player = new Player();
				String playerName = "";

				for (int contPlayer = 0; contPlayer < scoresToValidate.size(); contPlayer++) {
					PlayerScore pivotePlayerScore = scoresToValidate.get(contPlayer);
					
					int shootPosition = shootByPlayer % SHOOTS_BY_TURN;
					playerName = playerNameList.get(playerPosition);

					if (playerPosition < countPlayers && pivotePlayerScore.getName().equals(playerName)) {
						
						player.setName(pivotePlayerScore.getName());
						if (shootByPlayer >= MAX_SHOOTS_BY_GAME) {
							StringBuilder incompleteMessage = new StringBuilder(playerName);
							incompleteMessage.append("! ").append(messages.getMessage("src.main.labels.much.shoots"));
							LOG.info(incompleteMessage.toString());
							return null;
						}
						
						if (pivotePlayerScore.getPinfalls() >= STRIKE_POINTS && shootPosition == 0 && turn < TURNS_BY_GAME) {
							shootCharByPlayer[shootByPlayer] = -1;
							shootCharByPlayer[shootByPlayer + 1] = STRIKE_POINTS;
							shootByPlayer += 2;
							score.getShoots()[0] = STRIKE_POINTS;
							score.getShoots()[1] = 0;
							score.setStrike(true);
						} else {
							shootCharByPlayer[shootByPlayer] = (pivotePlayerScore.getPinfalls()==0?-1:pivotePlayerScore.getPinfalls());
							if (turn >= TURNS_BY_GAME) {
								score.setFlagFinal(true);
								score.getShoots()[bonusTurn] = pivotePlayerScore.getPinfalls();
								bonusTurn++;
							} else {
								score.getShoots()[shootPosition] = pivotePlayerScore.getPinfalls();
							}
							shootByPlayer++;
						}
						shootPosition = shootByPlayer % SHOOTS_BY_TURN;
						if ((shootPosition) == 0 && turn < TURNS_BY_GAME) {
							turn++;
							if ((score.getShoots()[0] + score.getShoots()[1]) >= STRIKE_POINTS && !score.isStrike()) {
								score.setSpare(true);
							}
							player.setCurrentScore(score);
							score = new Score();
						}
						else if (shootByPlayer >= MAX_SHOOTS_BY_GAME) {
							player.setCurrentScore(score);
							score = new Score();
						}
					} else {
						try {
							validatePlayerChar(shootCharByPlayer, playerName);
						} catch (Exception e) {
							LOG.info(e.getMessage());
							return null;
						}
						listOfPLayers = addPlayer(player, listOfPLayers);
						player = new Player();
						shootCharByPlayer = new int[MAX_SHOOTS_BY_GAME];
						
						shootByPlayer = 0; turn = 0; playerPosition++; contPlayer--; bonusTurn = 0;
					}
				}
				try {
					validatePlayerChar(shootCharByPlayer, playerName);
					listOfPLayers = addPlayer(player, listOfPLayers);
				} catch (Exception e) {
					LOG.info(e.getMessage());
					return null;
				}
				playerPosition++;
			}
		}
		
		System.out.println("FIN");
		return calculateScore(listOfPLayers);

	}

	public void validatePlayerChar(int[] shootCharByPlayer, String playerName)
			throws Exception {

		for (int i = 0; i < shootCharByPlayer.length; i++) {
			if (shootCharByPlayer[i] == 0) {
				throwIncorrectBoard(playerName, "src.main.labels.incomplete");
			}
		}

	}

	public void throwIncorrectBoard(String playerName, String message) throws Exception{
		StringBuilder incompleteMessage = new StringBuilder(playerName);
		incompleteMessage.append("! ").append(messages.getMessage(message));
		throw new Exception(incompleteMessage.toString());
	}
	
	public List<Player> addPlayer(Player player, List<Player> listOfPLayers) {
		List<Player> outPlayers = new ArrayList<Player>();
		boolean match = false;
		if (player != null) {
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
			}
		}
		return outPlayers;
	}
	
	public List<Player> calculateScore(List<Player> listOfPLayers) {
		List<Player> outPlayers = new ArrayList<Player>();

		for (Player player : listOfPLayers) {
			List<Score> listScores = new ArrayList<Score>();
			for (int posScore = 0; posScore < player.getScore().size(); posScore++) {
				Score currentScore = player.getScore().get(posScore);
				//
				int bonus = evaluateBonus(currentScore, posScore, player.getScore());
				int tempTotal = currentScore.getShoots()[0] + currentScore.getShoots()[1] + bonus;
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
			return score.getShoots()[2];
		} else {
			Score nextScore = listScores.get(posScore + 1);
			if (score.isSpare()) {
				return nextScore.getShoots()[0];
			} else if (score.isStrike()) {

				if (nextScore.isFlagFinal()) {
					return nextScore.getShoots()[0] + nextScore.getShoots()[1];
				} else if (nextScore.isStrike()) {
					Score postNextScore = listScores.get(posScore + 2);
					return nextScore.getShoots()[0] + postNextScore.getShoots()[0];
				} else {
					return nextScore.getShoots()[0] + nextScore.getShoots()[1];
				}
			} else {
				return 0;
			}
		}

	}

	public List<String> getPlainPlayerScores() {
		return plainPlayerScores;
	}

	public void setPlainPlayerScores(List<String> plainPlayerScores) {
		this.plainPlayerScores = plainPlayerScores;
	}

}

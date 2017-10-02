package org.jobsity.run.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.model.Frame;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;

/**
* PlayerController
*
*
* @author alexander.vera
* @since 01/10/2017
*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		improve the buildPlayerScore method
* alexander.vera	02/10/2017 		create validation for a incorrect scores
*/
public class PlayerController implements IPlayerController {
	
	private List<String> plainPlayerScores;
	private static final int TURNS_BY_GAME = 9;
	private static final int STRIKE_POINTS = 10;
	private static final int MAX_SHOOTS_BY_GAME = 21;
	private static final int SHOOTS_BY_TURN = 2;
	private static final Logger LOG = Logger.getLogger(PlayerController.class.getName());
	private Messages messages;

	public PlayerController(List<String> plainPlayerScores) {
		messages = new Messages();
		this.plainPlayerScores = plainPlayerScores;
	}

	/**
     * Method that take a list of Shoot lines with a name and pins for each player
     *
     * @return A String value with the message
     * @param List of string player score
     */
	public List<Frame> buildPlayerScore(List<String> plainPlayerScores) {
		List<PlayerScore> newPlayerScores = null;
		
		//Verify that list of players has a least one player
		if (plainPlayerScores != null) {
			
			//Init the list to store the score
			newPlayerScores = new ArrayList<PlayerScore>();
			PlayerScore newPlayerScore = null;
			
			//Walk each player line to take the score
			for (String plainScore : plainPlayerScores) {
				//Init a new player
				newPlayerScore = new PlayerScore();
				//Split the player line in a name and pins
				String[] plainScoreSplited = plainScore.split(" ");
				
				//Set the name in the Player
				newPlayerScore.setName(plainScoreSplited[0].trim());
				
				//Validate if the line come with a valid number of pins. (Maria 10 => Ok, Juan => Bad)
				if(plainScoreSplited.length>1){
					newPlayerScore.setPinfalls(Utilities.parseValidateInteger(plainScoreSplited[1].trim()));
				}
				else{
					//If the number of pins is not valid, put 0
					newPlayerScore.setPinfalls(0);
				}
				//Add the new player
				newPlayerScores.add(newPlayerScore);
			
			}
			
			//Order the player list by name
			Collections.sort(newPlayerScores);
			Set<String> namesOfPlayers = new TreeSet<String>();

			//Delete the repeated names in order to get the number of player
			for (int i = 0; i < newPlayerScores.size(); i++) {
				namesOfPlayers.add(newPlayerScores.get(i).getName());
			}
			
			//Move the distinct names to a List in order to manipulate
			List<String> playerNameList = new ArrayList<String>();
			playerNameList.addAll(namesOfPlayers);

			//Get the numbers of players
			int numOfPlayers = namesOfPlayers.size();

			//Call a method to validate the shoots for each player and build a frame
			return validateScore(newPlayerScores, playerNameList, numOfPlayers);

		} else {
			return null;
		}
	}

	/**
     * Method that walks each player in order to verify that they have finish the game
     *
     * @return A list of PlayerScore, a List of String player name and the number of player
     * @param List of a valid Frame to Draw
     */
	public List<Frame> validateScore(List<PlayerScore> scoresToValidate, List<String> playerNameList, int countPlayers) {
		
		List<Frame> listOfFrames = null;
		int playerPosition = 0, turn = 0, shootByPlayer = 0;

		//Validate if a list of player scores is full
		if (scoresToValidate != null) {
			int[] shootCharByPlayer = null;

			//Init a loop for each player
			while (playerPosition < countPlayers) {
				//Create a array to simulate the frame in a bowling game 
				// Ej. [0][/] [][X] [7][2] [6][3] [2][/] [1][6] [][X] [][X] [3][5] [X][9][0]
				shootCharByPlayer = new int[MAX_SHOOTS_BY_GAME];
				// Init the counter to manage the shoots by player
				shootByPlayer = 0;
				// Init the counter to manage the bonus shoot by player
				int bonusTurn = 0;
				//Init a blank score to manage each turn of player
				Score score = new Score();
				//Init a blank frame to manage all score for turn of a player
				Frame playerFrame = new Frame();
				String playerName = "";

				//Loop all player scores in order to get each score and add to the frame 
				for (int contPlayer = 0; contPlayer < scoresToValidate.size(); contPlayer++) {
					
					//Get a Player from the list
					PlayerScore pivotePlayerScore = scoresToValidate.get(contPlayer);
					
					//Calculate the module between the shoot module of player and the maximum shoots by turn (No bonus) 
					//in order to know if is a first or second shoot in one turn
					int shootPosition = shootByPlayer % SHOOTS_BY_TURN;
					
					//Get the name of a player
					playerName = playerNameList.get(playerPosition);
					
					//Loop condition to validate if the score that i get is the score from the current player
					//and evaluate if there are more player score to evaluate
					if (playerPosition < countPlayers && pivotePlayerScore.getName().equals(playerName)) {
						
						//Set name of a current player in the frame
						playerFrame.setPlayerName(pivotePlayerScore.getName());
						
						//Validate if the shoot of this player doesn't beat the maximum allowed shots in a bowling frame
						//In that case the app stop and sohw a message
						if (shootByPlayer >= MAX_SHOOTS_BY_GAME) {
							StringBuilder incompleteMessage = new StringBuilder(playerName);
							incompleteMessage.append("! ").append(messages.getMessage("src.main.labels.much.shoots"));
							LOG.info(incompleteMessage.toString());
							return null;
						}
						
						//Validate if the player make a strike and this is his first shoot in this turn 
						if (pivotePlayerScore.getPinfalls() >= STRIKE_POINTS && shootPosition == 0 && turn < TURNS_BY_GAME) {
							//Mark the first simulated frame position with a negative number to evaluate if the frame was correctly filled
							shootCharByPlayer[shootByPlayer] = -1;
							
							//Set the second position with a strike value
							shootCharByPlayer[shootByPlayer + 1] = STRIKE_POINTS;
							
							//When a player make a Strike, he doesn't have a second shoot in this turn
							shootByPlayer += 2;
							
							//Set the real score object with the pins value
							score.getShoots()[0] = STRIKE_POINTS;
							score.getShoots()[1] = 0;
							
							//set the label strike in true
							score.setStrike(true);
						} else {
							//Set pins for the player in the simulated frame
							shootCharByPlayer[shootByPlayer] = (pivotePlayerScore.getPinfalls()==0?-1:pivotePlayerScore.getPinfalls());
							
							//Evaluate if this turn is the final turn to set the bonus shoots.
							//Because the final turn can have a extra shoot
							if (turn >= TURNS_BY_GAME) {
								score.setFlagFinal(true);
								score.getShoots()[bonusTurn] = pivotePlayerScore.getPinfalls();
								bonusTurn++;
							} else {
								score.getShoots()[shootPosition] = pivotePlayerScore.getPinfalls();
							}
							
							//Evaluate if the current player make a spare
							if ((score.getShoots()[0] + score.getShoots()[1]) >= STRIKE_POINTS && !score.isStrike()) {
								score.setSpare(true);
							}
							
							//Increase the shoot counter to indicate the player make this shoot form his turn
							shootByPlayer++;
						}
						
						//Calculate the new module to know if the if the player ends his turn
						shootPosition = shootByPlayer % SHOOTS_BY_TURN;
						if ((shootPosition) == 0 && turn < TURNS_BY_GAME) {
							//Increase the turn
							turn++;
							//Add the turn score in the player frame
							playerFrame.setCurrentScore(score);
							//Clean the socre
							score = new Score();
						}
						else if (shootByPlayer >= MAX_SHOOTS_BY_GAME) {
							playerFrame.setCurrentScore(score);
							score = new Score();
						}
					} else {
						//When the player change, i must evaluate if the current simulated frame is full
						//That indicate that player play his all shoots and the score is valid
						try {
							validatePlayerChar(shootCharByPlayer, playerName);
						} catch (Exception e) {
							//If the simulated frame is not full the app stops and shows a message 
							LOG.info(e.getMessage());
							return null;
						}
						//Add the current frame to a list of players frames
						listOfFrames = addPlayer(playerFrame, listOfFrames);
						playerFrame = new Frame();
						shootCharByPlayer = new int[MAX_SHOOTS_BY_GAME];
						//Clean the counters
						shootByPlayer = 0; turn = 0; playerPosition++; contPlayer--; bonusTurn = 0;
					}
				}
				//Validate the simulated frame with the last player
				try {
					validatePlayerChar(shootCharByPlayer, playerName);
					listOfFrames = addPlayer(playerFrame, listOfFrames);
				} catch (Exception e) {
					LOG.info(e.getMessage());
					return null;
				}
				playerPosition++;
			}
		}
		//Calculate the extra score with bonus for strike or spare and return frames to print
		return calculateScore(listOfFrames);

	}

	/**
     * Method that walks each score by player in order to verify that they have finish the game
     *
     * 
     * @param shootCharByPlayer: Array with score for a player
     * 		  playerName: Name of player	 
     */
	public void validatePlayerChar(int[] shootCharByPlayer, String playerName)
			throws Exception {
		
		//Loop the simulated frame in order to evaluate if all position are full
		for (int i = 0; i < shootCharByPlayer.length; i++) {
			//Not points -1 is considered full and 0 is a empty space
			if (shootCharByPlayer[i] == 0) {
				//if one box on the simulated frame is empty, stop the app and shows a message
				StringBuilder incompleteMessage = new StringBuilder(playerName);
				incompleteMessage.append("! ").append(messages.getMessage("src.main.labels.incomplete"));
				throw new Exception(incompleteMessage.toString());
			}
		}
	}

	/**
     * Method that take each player shoot (PlayerScore) and add it to its correspond frame
     *
     * 
     * @param listOfPLayers: List of player frame
     * 		  player: Frame to add	 
     */	
	public List<Frame> addPlayer(Frame player, List<Frame> listOfPLayers) {
		List<Frame> outPlayers = new ArrayList<Frame>();
		boolean match = false;
		if (player != null) {
			if (listOfPLayers == null || listOfPLayers.size() == 0) {
				outPlayers.add(player);
			} else {
				for (Frame iterPlayer : listOfPLayers) {
					if (iterPlayer.getPlayerName().equals(player.getPlayerName())) {
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

	/**
     * Method that take each frame (PlayerScore) and calculate its score based on bonus
     *
     * 
     * @param listOfPLayers: List with frames
     * 	 
     */	
	public List<Frame> calculateScore(List<Frame> listOfPLayers) {
		List<Frame> outPlayers = new ArrayList<Frame>();

		for (Frame player : listOfPLayers) {
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

	/**
     * Method that calculate bonus by strike or spare
     *
     * 
     * @param score: Score with a point by current turn
     *		  posScore: position or turn for this score
     *        listScores: List of all scores for a player	 	 
     */	
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

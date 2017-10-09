package org.jobsity.run.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jobsity.run.interfaces.BowlingScore;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.PlayerPins;
import org.jobsity.run.model.ScoreFrame;

/**
* BowlingScoreController class. Calculate the total score with bonus.
*
* @author alexander.vera
* @since 01/10/2017
*
*/
public class BowlingScoreController implements BowlingScore {

	/**
     * List of couple Name and score as a string (Joe 9)
     */
	private List<PlayerPins> plainPlayerPins;


	public BowlingScoreController() throws IOException {
	}


	/**
     * Method that calculate the total of player point and calculate the bonus 
     * by Strike o spare
     * @param listOfPLayers: List with frames 	 
     */	
	public List<GameLine> calculateScore(List<GameLine> listOfPLayers) {
		List<GameLine> outPlayers = new ArrayList<GameLine>();
		listOfPLayers.forEach(player -> {
			List<ScoreFrame> listScores = new ArrayList<ScoreFrame>();	
			for (int posScore = 0; posScore < player.getScore().size(); posScore++) {
				ScoreFrame currentScore = player.getScore().get(posScore);
				int bonus = evaluateBonus(currentScore, posScore, player.getScore());
				int tempTotal = currentScore.getShoots()[0] + currentScore.getShoots()[1] + bonus;
				player.setTotalScore(player.getTotalScore() + tempTotal);
				currentScore.setTotal(player.getTotalScore());
				listScores.add(currentScore);
			}
			player.setScore(listScores);
			outPlayers.add(player);			
		});
		return outPlayers;
	}
	/**
     * Method that calculate bonus by strike or spare
     * Strike take 10 points plus the next two balls
     * Spare take 10 points plus the next ball
     * @param score: Score with a point by current turn
     *		  posScore: position or turn for this score
     *        listScores: List of all scores for a player	 	 
     */	
	public int evaluateBonus(ScoreFrame score, int posScore, List<ScoreFrame> listScores) {
		//If the score is the last score, there is no bonus and just sum all points from this round
		if (score.isFlagFinal()) {
			return score.getShoots()[2];
		} else {
			//Get the score for the next round
			ScoreFrame nextScore = listScores.get(posScore + 1);
			if (score.isSpare()) {
				//If current score is a spare, the bonus is the next shoot
				return nextScore.getShoots()[0];
			} else if (score.isStrike()) {
				if (nextScore.isFlagFinal()) {
					//If the current score is a strike but the next score is the final score
					//The bonus will be the the next first and second shoot
					return nextScore.getShoots()[0] + nextScore.getShoots()[1];
				} else if (nextScore.isStrike()) {
					//If the score is a strike and the next score is a strike too.
					//The bonus will be the next first next first shoot and
					//The next next first shoot
					ScoreFrame postNextScore = listScores.get(posScore + 2);
					return nextScore.getShoots()[0] + postNextScore.getShoots()[0];
				} else {
					//If the score is a strike but the next is a regular shoot.
					//The bonus will be the next first and second shoot
					return nextScore.getShoots()[0] + nextScore.getShoots()[1];
				}
			} else {
				//If the current shoot is a regular shoot. The bonus is 0
				return 0;
			}
		}
	}
	/**
     * Getter from PlainPlayerSCore
     */
	public List<PlayerPins> getPlainPlayerPins() {
		return plainPlayerPins;
	}
	/**
     * Setter from PlainPlayerSCore
     */
	public void setPlainPlayerPins(List<PlayerPins> plainPlayerPins) {
		this.plainPlayerPins = plainPlayerPins;
	}
}
/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		improve the buildPlayerPin method
* alexander.vera	02/10/2017 		create validation for a incorrect scores
*/
 
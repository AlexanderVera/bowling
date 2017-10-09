package org.jobsity.run.logic;

import java.io.IOException;
import java.util.List;

import org.jobsity.run.interfaces.IFrameDrawer;
import org.jobsity.run.interfaces.IMessages;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;

/**
* FrameDrawer
*
* @author alexander.vera
* @since 30/09/2017
**/
public class FrameDrawer implements IFrameDrawer {

	/**
     * Maximum round in a a bowling game
     */
	private static final int NUMBER_OF_ROUNDS = 10;
	
	
	/**
     * List of Frame of a bowling game
     */
	private List<GameLine> gameLines;
	
	/**
     * Parameter to get de messages
     */
	private IMessages messages;

	/**
     * Constructor
     * @param players List<Frame>
	 * @throws IOException 
     */
	public FrameDrawer(final List<GameLine> gameLines) throws IOException {
		this.gameLines  = gameLines;
		setMessages(new Messages());
	}
	/**
 	* 
 	* @param players
 	* @throws IOException
 	*/
	public FrameDrawer(final GameBoard frame) throws IOException {
		this.gameLines  = frame.getListOfGameLines();
		setMessages(new Messages());
	}
	/**
     * Print a full frame based in a list of frames
     *
     */
	public StringBuilder printFrame() {
		StringBuilder frameToPrint = new StringBuilder();
		if (this.gameLines != null) {
			//Print the numbers 1-10 on top of the frame
			frameToPrint.append(printFrameTitle());
			
			//Walk all the player
			for (GameLine tmpPlayerFrame : this.gameLines) {
				frameToPrint.append(tmpPlayerFrame.getPlayerName());
				frameToPrint.append('\n');
				frameToPrint.append(messages.getMessage("src.main.labels.pinfall"));
				//Print the scores
				frameToPrint.append(printPlayerScores(tmpPlayerFrame.getScore()));
				frameToPrint.append("\n");
				//Print the total score	
				frameToPrint.append(printTotalScore(tmpPlayerFrame));

			}
		}
		return frameToPrint;
	}
	
	/**
	 * @param scoreByPlayer: StringBuilder to print the scores
	 * 		  scores: List of scores for a player
	 */
	public StringBuilder printPlayerScores(List<Score> scores){
		StringBuilder scoreByPlayer = new StringBuilder();
		for (Score score : scores) {
			scoreByPlayer.append("\t");
			//Print the strike and spare score
			if (!score.isFlagFinal()) {
				if (score.isStrike()) {
					scoreByPlayer.append("\t");
					scoreByPlayer.append('X');
				} else {
					scoreByPlayer.append(score.getShoots()[0]);
					scoreByPlayer.append("\t");
					if (score.isSpare()) {
						scoreByPlayer.append('/');
					} else {
						scoreByPlayer.append(score.getShoots()[1]);
					}
				}
			}
			//Print the final turn strike and spare
			if (score.getFlagFinal()) {
				if (score.getShoots()[0] >= Utilities.STRIKE_POINTS) {
					scoreByPlayer.append('X');
				} else {
					scoreByPlayer.append(score.getShoots()[0]);
				}
				scoreByPlayer.append("\t");
				if (score.getShoots()[1] >= Utilities.STRIKE_POINTS) {
					scoreByPlayer.append('X');
				} else {
					scoreByPlayer.append(score.getShoots()[1]);
				}
				scoreByPlayer.append("\t");

				if (score.getShoots()[2] >= Utilities.STRIKE_POINTS) {
					scoreByPlayer.append('X');
				} else {
					scoreByPlayer.append(score.getShoots()[2]);
				}

			}
		}
		return scoreByPlayer;
	}
	/**Print the number on the frame header
	 * 
	 * @param scoreByPlayer: StringBuilder to build a labels from the top
	 */
	public StringBuilder printFrameTitle(){
		StringBuilder scoresByPlayer = new StringBuilder(messages.getMessage("src.main.labels.frame"));
		scoresByPlayer.append("\t\t");
		//Load the number labels of frame
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
			scoresByPlayer.append((i + 1) + "\t\t");
		}
		scoresByPlayer.append('\n');
		return scoresByPlayer;
	} 
	
	/**
	 * @param scoreByPlayer: StringBuilder to print the score to print 
	 * 		  frame: Object with the scores of current player 	
	 */
	public StringBuilder printTotalScore(GameLine frame){
		StringBuilder scoresByPlayer = new StringBuilder(messages.getMessage("src.main.labels.score"));
		scoresByPlayer.append("\t");
		for (Score score : frame.getScore()) {
			scoresByPlayer.append("\t");
			scoresByPlayer.append(score.getTotal());
			scoresByPlayer.append("\t");
		}
		scoresByPlayer.append("\n");
		return scoresByPlayer;
	}
	
	/**
     * Getter from players
     */
	public List<GameLine> getGameLines() {
		return gameLines;
	}

	/**
     * Setter from players
     */
	public void setGameLines(List<GameLine> players) {
		this.gameLines = players;
	}
	
	/**
     * Getter from IMessages
     */
	public IMessages getMessages() {
		return messages;
	}

	/**
     * Setter from IMessages
     */
	public void setMessages(IMessages messages) {
		this.messages = messages;
	}
}

/*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	2017/10/01		Update call of score objects 
*/
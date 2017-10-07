package org.jobsity.run.logic;

import java.io.IOException;
import java.util.List;

import org.jobsity.run.interfaces.IFrameDrawer;
import org.jobsity.run.interfaces.IMessages;
import org.jobsity.run.model.Frame;
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
	private List<Frame> players;
	
	/**
     * Parameter to get de messages
     */
	private IMessages messages;

	/**
     * Constructor
     * @param players List<Frame>
	 * @throws IOException 
     */
	public FrameDrawer(final List<Frame> players) throws IOException {
		this.players  = players;
		setMessages(new Messages());
	}

	/**
     * Print a full frame based in a list of frames
     *
     */
	public void printFrame() {

		if (getPlayers() != null) {
			
			StringBuilder scoreByPlayer = null;
			//Print the numbers 1-10 on top of the frame
			printFrameTitle(scoreByPlayer);
			
			//Walk all the player
			for (Frame tmpPlayerFrame : getPlayers()) {
				scoreByPlayer = new StringBuilder(tmpPlayerFrame.getPlayerName());
				scoreByPlayer.append('\n');
				System.out.print(scoreByPlayer);
				scoreByPlayer = new StringBuilder(messages.getMessage("src.main.labels.pinfall"));
				//Print the scores
				printPlayerScores(scoreByPlayer, tmpPlayerFrame.getScore());
				scoreByPlayer.append('\n');
				System.out.print(scoreByPlayer.toString());
				//Print the total score	
				printTotalScore(scoreByPlayer, tmpPlayerFrame);

			}
		}
	}
	
	/**
	 * @param scoreByPlayer: StringBuilder to print the scores
	 * 		  scores: List of scores for a player
	 */
	public void printPlayerScores(StringBuilder scoreByPlayer, List<Score> scores){
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
	}
	/**Print the number on the frame header
	 * 
	 * @param scoreByPlayer: StringBuilder to build a labels from the top
	 */
	public void printFrameTitle(StringBuilder scoreByPlayer){
		scoreByPlayer = new StringBuilder(messages.getMessage("src.main.labels.frame"));
		scoreByPlayer.append("\t\t");
		//Load the number labels of frame
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
			scoreByPlayer.append((i + 1) + "\t\t");
		}
		scoreByPlayer.append('\n');
		System.out.print(scoreByPlayer.toString());
	} 
	
	/**
	 * @param scoreByPlayer: StringBuilder to print the score to print 
	 * 		  frame: Object with the scores of current player 	
	 */
	public void printTotalScore(StringBuilder scoreByPlayer, Frame frame){
		scoreByPlayer = new StringBuilder(messages.getMessage("src.main.labels.score"));
		scoreByPlayer.append("\t");
		for (Score score : frame.getScore()) {
			scoreByPlayer.append("\t");
			scoreByPlayer.append(score.getTotal());
			scoreByPlayer.append("\t");
		}
		scoreByPlayer.append("\n");
		System.out.print(scoreByPlayer.toString());
	}
	
	/**
     * Getter from players
     */
	public List<Frame> getPlayers() {
		return players;
	}

	/**
     * Setter from players
     */
	public void setPlayers(List<Frame> players) {
		this.players = players;
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
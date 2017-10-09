package org.jobsity.run.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.GameParser;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.PlayerPins;
import org.jobsity.run.model.ScoreFrame;
import org.jobsity.util.Utilities;

/**
 * BowlingGameParser Class. Covert a List of Player score into a valid Game lines
 * 
 * @author alexander.vera
 * @since 06/10/2017
 *
 */
public class BowlingGameParser implements GameParser{
	private List<PlayerPins> plainPlayers;
	private Dictionary messages;
	
	public BowlingGameParser() throws IOException {
		messages = new Dictionary();
	}
	public BowlingGameParser(List<PlayerPins> plainPlayers) throws IOException{
		this.plainPlayers = plainPlayers;
		messages = new Dictionary();
	}
	
	
	/**
	 * @return GameBoard
	 */
	public GameBoard buildBoardFromPlayers() throws BuildException, IOException {
		return buildGame(this.plainPlayers);
	}
	
	/**
	 * Walk through each the player and build each game line to make a Game board
	 * @param listPlayers
	 * @return GameBoard 
	 * @throws BuildException
	 * @throws IOException
	 */
	public GameBoard buildGame(List<PlayerPins> listPlayers) throws BuildException, IOException{
		 List<GameLine> gameLines; 
		 List<PlayerPins> lstSinglePlayer = new ArrayList<PlayerPins>();
		 if(listPlayers!=null && listPlayers.size() > 0){
			 gameLines = new ArrayList<GameLine>();
			 PlayerPins playerPivote = listPlayers.get(0);
				for(int i =0; i<listPlayers.size(); i++ ){
					PlayerPins currentPlayer = listPlayers.get(i);
					if(currentPlayer.getName().equals(playerPivote.getName())){
						lstSinglePlayer.add(currentPlayer);
					}	
					else{
						//Build a game line for the current player.
						gameLines.add(buildValidGameLine(lstSinglePlayer, playerPivote.getName()));
						i--;
						//Change the player
						playerPivote = currentPlayer;
						lstSinglePlayer = new ArrayList<PlayerPins>();
					}
				}
				gameLines.add(buildValidGameLine(lstSinglePlayer, playerPivote.getName()));
		 }
		 else {
			 throw new BuildException(messages.getMessage("src.main.messages.no.players"));
		 }
		 return new GameBoard(gameLines);
	 }

	/**
	 * Build a Game Line for a single player.
	 * @param lstPlayer
	 * @param playerName
	 * @return
	 * @throws BuildException
	 * @throws IOException
	 */
	public GameLine buildValidGameLine(List<PlayerPins> lstPlayer, String playerName) throws BuildException, IOException {
		// GameLine for each player.
		GameLine gameLine = new GameLine();
		//All scores for a single player to make a game line.
		List<ScoreFrame> scoreByPlayer = new ArrayList<ScoreFrame>();
		//Each score for a player.
		ScoreFrame score;
		// Init turn counter.
		int turn = 0;
		// Pinfall position
		int shootPosition = 0;
		// Shoots by player
		int shoots = 0;
		// Walk through each turn.
		while (turn < Utilities.TURNS_BY_GAME) {
			score = new ScoreFrame();
			try {
				gameLine.setPlayerName(playerName);
				// If is a regular turn
				int firstShoot = lstPlayer.get(shootPosition).getPinfalls();
				if (turn < Utilities.LAST_TURN) {
					// If this scores is a Strike set just the first frame and flag a Strike.
					if (firstShoot == Utilities.STRIKE_POINTS) {
						shootPosition += 1;
						score.setStrike(true);
						score.getShoots()[0] = Utilities.STRIKE_POINTS;
					} else if (firstShoot > -1 && lstPlayer.get(shootPosition + 1).getPinfalls() > -1) {
						int secondShoot = lstPlayer.get(shootPosition + 1).getPinfalls();
						shootPosition += Utilities.SHOOTS_BY_TURN;
						score.getShoots()[0] = firstShoot;
						score.getShoots()[1] = secondShoot;
						//If the both shoots sum in one turn is 10 flag a spare
						if (firstShoot + secondShoot >= Utilities.STRIKE_POINTS) {
							score.setSpare(true);
						}
					}
					//Sum 1 shoot
					shoots += Utilities.SHOOTS_BY_TURN;
				} else {
					// If if the final turn set extra shoot
					int secndShoot = lstPlayer.get(shootPosition + 1).getPinfalls();
					int thirdShoot = lstPlayer.get(shootPosition + 2).getPinfalls();
					if (firstShoot > -1 && secndShoot > -1 && thirdShoot > -1) {
						score.setFlagFinal(true);
						score.getShoots()[0] = firstShoot;
						score.getShoots()[1] = secndShoot;
						score.getShoots()[2] = thirdShoot;
						//Sum 3 shoots
						shoots += 3;
						shootPosition += 3;
						//If the last shoot position is lower than number of players it means the player has more shots than the maximum
						if (shootPosition < lstPlayer.size()) {
							throw new BuildException(Utilities.infoPlayerMessage(playerName,"src.main.labels.much.shoots"));
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				throw new BuildException(Utilities.infoPlayerMessage(playerName,"src.main.labels.incomplete"));
			}
			//Add score in this player
			scoreByPlayer.add(score);
			//Change turn
			turn++;
		}
		//If the shoots number is lower than shoots by game it means the player has more shots than the maximum
		if (shoots < Utilities.SHOOTS_BY_GAME) {
			throw new BuildException(Utilities.infoPlayerMessage(playerName, "src.main.labels.incomplete"));
		}
		gameLine.setScore(scoreByPlayer);
		return gameLine;
	}
	
	public List<PlayerPins> getPlainPlayers() {
		return plainPlayers;
	}
	public void setPlainPlayers(List<PlayerPins> plainPlayers) {
		this.plainPlayers = plainPlayers;
	}
}

package org.jobsity.run.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.IGameParser;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;

/**
 * GameParser Turn a PlayerScores into Frame, with validations
 * 
 * @author alexander.vera
 * @since 06/10/2017
 *
 */
public class GameParser implements IGameParser{
	private List<PlayerScore> plainPlayers;
	private Messages messages;
	
	public GameParser() throws IOException {
		messages = new Messages();
	}
	public GameParser(List<PlayerScore> plainPlayers) throws IOException{
		this.plainPlayers = plainPlayers;
		messages = new Messages();
	}
	
	
	/**
	 * 
	 */
	public GameBoard buildFrameFromPlayers() throws BuildException, IOException {
		return buildGame(this.plainPlayers);
	}
	
	 public GameBoard buildGame(List<PlayerScore> listPlayers) throws BuildException, IOException{
		 List<GameLine> gameLines; 
		 List<PlayerScore> lstSinglePlayer = new ArrayList<PlayerScore>();
		 if(listPlayers!=null && listPlayers.size() > 0){
			 gameLines = new ArrayList<GameLine>();
			 PlayerScore playerPivote = listPlayers.get(0);
				for(int i =0; i<listPlayers.size(); i++ ){
					PlayerScore currentPlayer = listPlayers.get(i);
					if(currentPlayer.getName().equals(playerPivote.getName())){
						lstSinglePlayer.add(currentPlayer);
					}	
					else{
						gameLines.add(buildValidGameLine(lstSinglePlayer, playerPivote.getName()));
						i--;
						playerPivote = currentPlayer;
						lstSinglePlayer = new ArrayList<PlayerScore>();
					}
				}
				gameLines.add(buildValidGameLine(lstSinglePlayer, playerPivote.getName()));
		 }
		 else {
			 throw new BuildException(messages.getMessage("src.main.messages.no.players"));
		 }
		 return new GameBoard(gameLines);
	 }

	public GameLine buildValidGameLine(List<PlayerScore> lstPlayer, String playerName) throws BuildException, IOException {
		// GameLine
		GameLine gameLine = new GameLine();
		//
		List<Score> scoreByPlayer = new ArrayList<Score>();
		//
		Score score;
		// Current turn
		int turn = 0;
		// Pinfall position
		int shootPosition = 0;
		// Shoots by player
		int shoots = 0;
		while (turn < Utilities.TURNS_BY_GAME) {
			score = new Score();
			try {
				gameLine.setPlayerName(playerName);
				// If is a regular turn
				if (turn < Utilities.LAST_TURN) {
					int firstShoot = lstPlayer.get(shootPosition).getPinfalls();
					if (firstShoot == Utilities.STRIKE_POINTS) {
						shootPosition += 1;
						score.setStrike(true);
						score.getShoots()[0] = Utilities.STRIKE_POINTS;
					} else if (firstShoot > -1 && lstPlayer.get(shootPosition + 1).getPinfalls() > -1) {
						int secondShoot = lstPlayer.get(shootPosition + 1).getPinfalls();
						shootPosition += Utilities.SHOOTS_BY_TURN;
						score.getShoots()[0] = firstShoot;
						score.getShoots()[1] = secondShoot;
						//If both shoots in one turn is 10. The turn is a spare
						if (firstShoot + secondShoot >= Utilities.STRIKE_POINTS) {
							score.setSpare(true);
						}
					}
					shoots += Utilities.SHOOTS_BY_TURN;
				} else {
					// If if the final turn
					int firstShoot = lstPlayer.get(shootPosition).getPinfalls();
					int secndShoot = lstPlayer.get(shootPosition + 1).getPinfalls();
					int thirdShoot = lstPlayer.get(shootPosition + 2).getPinfalls();
					if (firstShoot > -1 && secndShoot > -1 && thirdShoot > -1) {
						score.setFlagFinal(true);
						score.getShoots()[0] = firstShoot;
						score.getShoots()[1] = secndShoot;
						score.getShoots()[2] = thirdShoot;
						shoots += 3;
						shootPosition += 3;
						if (shootPosition < lstPlayer.size()) {
							throw new BuildException(Utilities.infoPlayerMessage(playerName,"src.main.labels.much.shoots"));
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				throw new BuildException(Utilities.infoPlayerMessage(playerName,"src.main.labels.incomplete"));
			}
			scoreByPlayer.add(score);
			turn++;
		}
		if (shoots < Utilities.SHOOTS_BY_GAME) {
			throw new BuildException(Utilities.infoPlayerMessage(playerName, "src.main.labels.incomplete"));
		}
		gameLine.setScore(scoreByPlayer);
		return gameLine;
	}
	
	public List<PlayerScore> getPlainPlayers() {
		return plainPlayers;
	}
	public void setPlainPlayers(List<PlayerScore> plainPlayers) {
		this.plainPlayers = plainPlayers;
	}
}

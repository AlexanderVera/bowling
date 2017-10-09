package org.jobsity.test.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.IGameParser;
import org.jobsity.run.logic.GameParser;
import org.jobsity.run.logic.PlayerController;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.util.Utilities;
import org.junit.Test;

public class PlayerControllerTest {
	private final static Logger LOG = Logger.getLogger(PlayerController.class.getName());
	@Test
	public void buildPlayerScoreTest(){
		boolean thrown = false;
		try{
			List<PlayerScore> listPlainPlayers = Utilities.generateMockPlayers();
			IGameParser gameParser = new GameParser(listPlainPlayers);
			GameBoard gameBoard = gameParser.buildBoardFromPlayers();
			PlayerController player = new PlayerController();
			List<GameLine> gameLines = player.calculateScore(gameBoard.getListOfGameLines());
			Integer scorePlayerOne = gameLines.get(0).getScore().get(0).getTotal();
			LOG.debug(scorePlayerOne);
			assertNotNull(scorePlayerOne);
		}
		catch(IOException | BuildException exc){
			thrown = true;
			LOG.error(exc.getMessage());
		}
		assertFalse(thrown);
	}

	
}

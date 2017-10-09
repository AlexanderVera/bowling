package org.jobsity.test.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.GameParser;
import org.jobsity.run.logic.BowlingGameParser;
import org.jobsity.run.logic.BowlingScoreController;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.PlayerPins;
import org.jobsity.util.Utilities;
import org.junit.Test;

public class BowlingScoreTest {
	private final static Logger LOG = Logger.getLogger(BowlingScoreController.class.getName());
	@Test
	public void buildPlayerPinTest(){
		boolean thrown = false;
		try{
			List<PlayerPins> listPlainPlayers = Utilities.generateMockPlayers();
			GameParser gameParser = new BowlingGameParser(listPlainPlayers);
			GameBoard gameBoard = gameParser.buildBoardFromPlayers();
			BowlingScoreController player = new BowlingScoreController();
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

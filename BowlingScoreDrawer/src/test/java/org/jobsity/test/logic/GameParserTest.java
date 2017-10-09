package org.jobsity.test.logic;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.logic.GameParser;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class GameParserTest extends TestCase{
	
	private static final Logger LOG = Logger.getLogger(GameParserTest.class.getName());
	
	@Test
	public void buildGameTest() {
		List<PlayerScore> listPlainPlayers = Utilities.generateMockPlayers();
		
		boolean thrown = false;
		
		try {
			GameParser gameParser = new GameParser();
			GameBoard game = gameParser.buildGame(listPlainPlayers);
			assertNotNull(game);
			assertEquals("Player0", game.getListOfGameLines().get(0).getPlayerName());
		}
		catch(IOException | BuildException exc) {
			LOG.error(exc.getMessage());
			thrown = true;
		}
		assertFalse(thrown);
	}
}

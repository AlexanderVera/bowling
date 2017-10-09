package org.jobsity.test.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.logic.GameParser;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.PlayerScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class GameParserTest extends TestCase{
	
	private static final Logger LOG = Logger.getLogger(GameParserTest.class.getName());
	
	@Test
	public void buildGameTest() {
		List<PlayerScore> listPlainPlayers = new ArrayList<PlayerScore>();

		listPlainPlayers.add(new PlayerScore("Jeff", 10));
		listPlainPlayers.add(new PlayerScore("Jeff", 7));
		listPlainPlayers.add(new PlayerScore("Jeff", 3));
		listPlainPlayers.add(new PlayerScore("Jeff", 9));
		listPlainPlayers.add(new PlayerScore("Jeff", 0));
		listPlainPlayers.add(new PlayerScore("Jeff", 10));
		listPlainPlayers.add(new PlayerScore("Jeff", 0));
		listPlainPlayers.add(new PlayerScore("Jeff", 8));
		listPlainPlayers.add(new PlayerScore("Jeff", 8));
		listPlainPlayers.add(new PlayerScore("Jeff", 2));
		listPlainPlayers.add(new PlayerScore("Jeff", 0));
		listPlainPlayers.add(new PlayerScore("Jeff", 6));		
		listPlainPlayers.add(new PlayerScore("Jeff",10));
		listPlainPlayers.add(new PlayerScore("Jeff",10));
		listPlainPlayers.add(new PlayerScore("Jeff",10));
		listPlainPlayers.add(new PlayerScore("Jeff",8));
		listPlainPlayers.add(new PlayerScore("Jeff",1));
		
		listPlainPlayers.add(new PlayerScore("John", 3));
		listPlainPlayers.add(new PlayerScore("John", 7));
		listPlainPlayers.add(new PlayerScore("John", 6));
		listPlainPlayers.add(new PlayerScore("John", 3));
		listPlainPlayers.add(new PlayerScore("John", 10));
		listPlainPlayers.add(new PlayerScore("John", 8));
		listPlainPlayers.add(new PlayerScore("John", 1));
		listPlainPlayers.add(new PlayerScore("John", 10));
		listPlainPlayers.add(new PlayerScore("John", 10));
		listPlainPlayers.add(new PlayerScore("John",9));
		listPlainPlayers.add(new PlayerScore("John",0));
		listPlainPlayers.add(new PlayerScore("John",7));
		listPlainPlayers.add(new PlayerScore("John",3));
		listPlainPlayers.add(new PlayerScore("John",4));
		listPlainPlayers.add(new PlayerScore("John",4));
		listPlainPlayers.add(new PlayerScore("John",10));
		listPlainPlayers.add(new PlayerScore("John",9));
		listPlainPlayers.add(new PlayerScore("John",0));
		
		boolean thrown = false;
		
		try {
			GameParser gameParser = new GameParser();
			GameBoard game = gameParser.buildGame(listPlainPlayers);
			assertNotNull(game);
			assertEquals("Jeff", game.getListOfGameLines().get(0).getPlayerName());
		}
		catch(IOException | BuildException exc) {
			LOG.error(exc.getMessage());
			thrown = true;
		}
		assertFalse(thrown);
	}
}

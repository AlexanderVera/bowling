package org.jobsity.test.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.logic.BoardDrawer;
import org.jobsity.run.model.Player;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 
 * @author alexnder.vera
 * @since 2017/10/01
 * 
 * HISTORY CHANGES
 * 
 */
@RunWith(JUnit4.class)
public class BoardDrawerTest {
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	@Test
	public void constructorTest(){
		
		List<Player> nullListPlayers = null;
		List<Player> emptyListPlayers = new ArrayList<Player>();
		List<Player> fullListPlayers = new ArrayList<Player>();
		Player player = null;
		for(int i = 0; i< 10; i++){
			player = new Player();
			player.setId(System.currentTimeMillis());
			StringBuilder name = new StringBuilder("Test");
			name.append(i);
			player.setName(name.toString());
			List<Score> scores = new ArrayList<Score>(); 
			player.setScore(scores);
		}
		
		BoardDrawer boardTestEmpty = new BoardDrawer(nullListPlayers);
		boardTestEmpty = new BoardDrawer(emptyListPlayers);
		boardTestEmpty = new BoardDrawer(fullListPlayers);
		
		LOG.debug(boardTestEmpty);
	
	}
	
	@Test
	public void printFrameTest() {
		StringBuilder messageTest = new StringBuilder();
		
		List<Player> nullListPlayers = null;
		List<Player> emptyListPlayers = new ArrayList<Player>();
		List<Player> fullListPlayers = new ArrayList<Player>();
		Player player = null;
		for(int i = 0; i< 10; i++){
			player = new Player();
			player.setId(System.currentTimeMillis());
			StringBuilder name = new StringBuilder("Test");
			name.append(i);
			player.setName(name.toString());
			List<Score> scores = new ArrayList<Score>(); 
			player.setScore(scores);
		}
		messageTest = new StringBuilder("Print frame with a null list");
		BoardDrawer boardTestEmpty = new BoardDrawer(nullListPlayers);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		
		messageTest = new StringBuilder("Print frame with a empty list");		
		boardTestEmpty = new BoardDrawer(emptyListPlayers);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		
		messageTest = new StringBuilder("Print frame with a full List");		
		boardTestEmpty = new BoardDrawer(fullListPlayers);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		

	}
	
}

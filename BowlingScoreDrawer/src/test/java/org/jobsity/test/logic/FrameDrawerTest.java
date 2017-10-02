package org.jobsity.test.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.logic.FrameDrawer;
import org.jobsity.run.model.Frame;
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
public class FrameDrawerTest {
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	@Test
	public void constructorTest(){
		
		List<Frame> nullListPlayers = null;
		List<Frame> emptyListPlayers = new ArrayList<Frame>();
		List<Frame> fullListPlayers = new ArrayList<Frame>();
		Frame player = null;
		for(int i = 0; i< 10; i++){
			player = new Frame();
			player.setId(System.currentTimeMillis());
			StringBuilder name = new StringBuilder("Test");
			name.append(i);
			player.setPlayerName(name.toString());
			List<Score> scores = new ArrayList<Score>(); 
			player.setScore(scores);
		}
		
		FrameDrawer boardTestEmpty = new FrameDrawer(nullListPlayers);
		boardTestEmpty = new FrameDrawer(emptyListPlayers);
		boardTestEmpty = new FrameDrawer(fullListPlayers);
		
		LOG.debug(boardTestEmpty);
	
	}
	
	@Test
	public void printFrameTest() {
		StringBuilder messageTest = new StringBuilder();
		
		List<Frame> nullListPlayers = null;
		List<Frame> emptyListPlayers = new ArrayList<Frame>();
		List<Frame> fullListPlayers = new ArrayList<Frame>();
		Frame player = null;
		for(int i = 0; i< 10; i++){
			player = new Frame();
			player.setId(System.currentTimeMillis());
			StringBuilder name = new StringBuilder("Test");
			name.append(i);
			player.setPlayerName(name.toString());
			List<Score> scores = new ArrayList<Score>(); 
			player.setScore(scores);
		}
		messageTest = new StringBuilder("Print frame with a null list");
		FrameDrawer boardTestEmpty = new FrameDrawer(nullListPlayers);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		
		messageTest = new StringBuilder("Print frame with a empty list");		
		boardTestEmpty = new FrameDrawer(emptyListPlayers);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		
		messageTest = new StringBuilder("Print frame with a full List");		
		boardTestEmpty = new FrameDrawer(fullListPlayers);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		

	}
	
}

package org.jobsity.test.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.logic.FrameDrawer;
import org.jobsity.run.model.Frame;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;
import org.junit.Test;

/**
 * 
 * @author alexander.vera
 * @since 2017/10/01
 * 
 * HISTORY CHANGES
 * 
 */
public class FrameDrawerTest {
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
		
	/*Test the printFrame in 3 scenarios: null, empty and full list. 
	*/
	@Test
	public void printFrameTest() {
		StringBuilder messageTest = new StringBuilder();
		
		List<Frame> nullListFrames = null;
		List<Frame> emptyListFrames = new ArrayList<Frame>();

		messageTest = new StringBuilder("Print frame with a null list");
		FrameDrawer boardTestEmpty = new FrameDrawer(nullListFrames);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		
		messageTest = new StringBuilder("Print frame with a empty list");		
		boardTestEmpty = new FrameDrawer(emptyListFrames);
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
		
		messageTest = new StringBuilder("Print frame with a full List");		
		boardTestEmpty = new FrameDrawer(makeFullListOfFrames());
		LOG.debug(messageTest.toString());
		boardTestEmpty.printFrame();
	}
	
	/**
	 * @return List<Frame> List of frame with dummie players
	 */
	public List<Frame> makeFullListOfFrames(){
		List<Frame> fullListFrames = new ArrayList<Frame>();
		Frame player = null;
		for(int i = 0; i< 10; i++){
			player = new Frame();
			player.setPlayerId(System.currentTimeMillis());
			StringBuilder name = new StringBuilder("Test");
			name.append(i);
			player.setPlayerName(name.toString());
			List<Score> scores = new ArrayList<Score>(); 
			player.setScore(scores);
		}
		return fullListFrames;
	}
	
}

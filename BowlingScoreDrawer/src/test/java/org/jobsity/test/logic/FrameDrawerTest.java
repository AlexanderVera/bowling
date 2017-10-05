package org.jobsity.test.logic;

import java.io.IOException;
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
		List<Frame> nullListFrames = null;
		List<Frame> emptyListFrames = new ArrayList<Frame>();
		try{
			printFrameCases("Print frame with a null list", nullListFrames);
			printFrameCases("Print frame with a empty list", emptyListFrames);		
			printFrameCases("Print frame with a dummie List", makeFullListOfFrames());
		}
		catch(IOException exc){
			LOG.debug(exc.getMessage());
		}
	}
	
	/***
	 * PrintFrame
	 * @param frames
	 * @throws IOException 
	 */
	public void printFrameCases(String message, List<Frame> frames) throws IOException{
		StringBuilder messageTest = new StringBuilder(message);		
		FrameDrawer boardTestEmpty = new FrameDrawer(frames);
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

package org.jobsity.test.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.ScoreFrame;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class GameLineTest extends TestCase{
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	@Test
	public void validBuildObject() {
	
		StringBuilder messageTestPerson = null;
	    // Test empty construct
		messageTestPerson = new StringBuilder();
		GameLine emptyGameLine = new GameLine();
		messageTestPerson.append("GameLine ").append(emptyGameLine.getPlayerName()).append(" ");
		assertNull(emptyGameLine.getPlayerName());
		LOG.debug(messageTestPerson.toString());
		
	    // Test construct
		messageTestPerson = new StringBuilder();
		GameLine completeGameLine = new GameLine("test", new ArrayList<ScoreFrame>());
		assertEquals("test", completeGameLine.getPlayerName());
		messageTestPerson.append("PersonComplete ").append(completeGameLine.getPlayerName()).append(" ");
		LOG.debug(messageTestPerson.toString());
		
		
	}
	
}
